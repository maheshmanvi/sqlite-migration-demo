package com.example.sqlitemigration.utils;

import android.util.Log;

import com.example.sqlitemigration.BuildConfig;

public final class Logger {

    // USAGE:
    // Logger.d("Loading users from database");

    private static final String TAG = "SQLiteMigration";

    private Logger(){
        throw new IllegalStateException("Utility class");  // Cannot instantiate
    }

    // Debug:
    // d → "What is the app doing?"
    // Example:
    // Logger.d("Loading users from database");
    // Logger.d("User count: " + users.size());
    public static void d(String message){
        if(BuildConfig.DEBUG){
            Log.d(TAG, message);
        }
    }

    // Info:
    // i → "Important normal event"
    // Example:
    // Logger.i("Database migration completed");
    // Logger.i("User logged in");
    // Logger.i("Sync completed");
    public static void i(String message) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, message);
        }
    }

    // Warning:
    // w → "Something unusual happened, but we recovered"
    // Example:
    // if (user == null) {
    //     Logger.w("User record not found");
    // }
    // OR
    // if (cachedData == null) {
    //     Logger.w("No cached data available, fetching from server");
    // }
    public static void w(String message) {
        if (BuildConfig.DEBUG) {
            Log.w(TAG, message);
        }
    }


    // Error:
    // e → "Something failed"
    // Example:
    // try {
    //     saveUser(user);
    // } catch (Exception e) {
    //     Logger.e("Failed to save user");
    // }
    // Prefer: Logger.e(e);
    public static void e(String message) {
        Log.e(TAG, message);

        if (!BuildConfig.DEBUG) {
            reportError(new RuntimeException(message));
        }
    }

    // Throwable Error:
    // e(Throwable) → "An exception occurred; investigate it"
    // Example:
    // try {
    //     database.insertUser(user);
    // } catch (Exception e) {
    //     Logger.e(e);
    // }
    public static void e(Throwable throwable) {
        Log.e(TAG, "Exception", throwable);

        if (!BuildConfig.DEBUG) {
            reportError(throwable);
        }
    }

    private static void reportError(Throwable throwable) {
        // Crash reporting service
        // Example: Firebase Crashlytics
    }


}
