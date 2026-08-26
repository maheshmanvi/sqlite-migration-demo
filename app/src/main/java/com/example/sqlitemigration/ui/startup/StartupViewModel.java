package com.example.sqlitemigration.ui.startup;

import androidx.lifecycle.ViewModel;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class StartupViewModel extends ViewModel {

    private static final int SPLASH_DELAY_MS = 3000;

    // Use LiveData to notify the Activity when it's time to navigate
    private final MutableLiveData<Boolean> navigateEvent = new MutableLiveData<>(false);

    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable navigationRunnable = () -> navigateEvent.setValue(true);

    public StartupViewModel() {
        // Start the timer as soon as the ViewModel is created
        handler.postDelayed(navigationRunnable, SPLASH_DELAY_MS);
    }

    public LiveData<Boolean> getNavigateEvent() {
        return navigateEvent;
    }

    // NEW: Method to reset the event so it doesn't trigger multiple times on lifecycle changes
    public void doneNavigating() {
        navigateEvent.setValue(false);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        // CRITICAL: Clean up the handler if the ViewModel is destroyed early
        // (e.g., user presses the back button or closes the app before 3 seconds)
        handler.removeCallbacks(navigationRunnable);
    }
}