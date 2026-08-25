package com.example.sqlitemigration.utils;

public final class Constants {

    // Prevents accidental instantiation.
    // Single source for constants.
    // Easy to expand later.


    // USAGE
    // Constants.BASE_URL
    // Constants.DATABASE_NAME
    private Constants() {
        throw new IllegalStateException("Utility class");
    }


    // Network configurations
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";


    // Storage
    public static final String DATABASE_NAME = "sqlite_migration_demo.db";

    // DATABASE_VERSION should be pure integer like 1, 2, 3. Cannot be semantic version like 0.0.1.
    // App version can be semantic version like 1.0.0
    public static final int DATABASE_VERSION = 1;
    public static final String PREF_NAME = "app_pref";

    // Notifications
    public static final String NOTIFICATION_CHANNEL = "default_channel";
}
