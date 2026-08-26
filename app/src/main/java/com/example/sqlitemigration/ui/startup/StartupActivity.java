package com.example.sqlitemigration.ui.startup;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.sqlitemigration.databinding.ActivityStartupBinding;
import com.example.sqlitemigration.ui.common.BaseActivity;
import com.example.sqlitemigration.ui.home.HomeActivity;

@SuppressLint("CustomSplashScreen") // Suppress warning as we explicitly want a custom 3s delay
public class StartupActivity extends BaseActivity {
    private ActivityStartupBinding binding;
    private StartupViewModel viewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. Inflate and set content view
        binding = ActivityStartupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 2. Apply window insets to prevent UI from overlapping with system bars
        applyWindowInsets(binding.getRoot());

        // 3. Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(StartupViewModel.class);

        // Observe the navigation event
        viewModel.getNavigateEvent().observe(this, shouldNavigate -> {
            if (shouldNavigate != null && shouldNavigate) {
                // 1. Consume the event so it doesn't fire again
                viewModel.doneNavigating();
                navigateToHome();
            }
        });
   }

    private void navigateToHome() {
        // 2. Safely check if the activity is already in the process of closing
        if (!isFinishing()) {
            Intent intent = new Intent(StartupActivity.this, HomeActivity.class);
            // 3. Best practice for Splash Screens: Clear the task to prevent back-stack ghosting
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }


}

