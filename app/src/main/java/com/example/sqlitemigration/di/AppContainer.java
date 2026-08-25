package com.example.sqlitemigration.di;

import android.content.Context;

import com.example.sqlitemigration.data.local.database.AppDatabase;
import com.example.sqlitemigration.data.local.preference.PreferenceManager;
import com.example.sqlitemigration.data.remote.ApiClient;
import com.example.sqlitemigration.data.repository.UserRepository;

public final class AppContainer {


    // USAGE:
    // AppContainer container = ((App) getApplication()).getAppContainer();
    // UserRepository repository = container.userRepository();

    private final PreferenceManager preferences;
    private final ApiClient apiClient;
    private final UserRepository userRepository;

    private final AppDatabase database;


    public AppContainer(Context context) {
        Context appContext = context.getApplicationContext();

        // Local
        preferences = new PreferenceManager(appContext);
        database = new AppDatabase(appContext);

        // Remote
         apiClient = new ApiClient(); // Internal dependency

        // Repositories
        userRepository = new UserRepository(apiClient.apiService());

    }

    public PreferenceManager preferences() {
        return preferences;
    }

    public AppDatabase database() {
        return database;
    }

    public UserRepository userRepository() {
        return userRepository;
    }

}