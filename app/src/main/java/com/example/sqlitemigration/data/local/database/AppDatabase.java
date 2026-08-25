package com.example.sqlitemigration.data.local.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sqlitemigration.utils.Constants;

public final class AppDatabase extends SQLiteOpenHelper {
    public AppDatabase(@NonNull Context context) {
        // Use application context to prevent Activity leak
        super(context.getApplicationContext(), Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE users (" +
                        "id INTEGER PRIMARY KEY, " +
                        "name TEXT, " +
                        "username TEXT, " +
                        "email TEXT, " +
                        "phone TEXT, " +
                        "website TEXT" +
                        ")"
        );

        db.execSQL(
                "CREATE TABLE user_addresses (" +
                        "user_id INTEGER PRIMARY KEY, " +
                        "street TEXT, " +
                        "suite TEXT, " +
                        "city TEXT, " +
                        "zipcode TEXT, " +
                        "latitude TEXT, " +
                        "longitude TEXT, " +
                        "FOREIGN KEY(user_id) REFERENCES users(id)" +
                        ")"
        );

        db.execSQL(
                "CREATE TABLE user_companies (" +
                        "user_id INTEGER PRIMARY KEY, " +
                        "name TEXT, " +
                        "catch_phrase TEXT, " +
                        "bs TEXT, " +
                        "FOREIGN KEY(user_id) REFERENCES users(id)" +
                        ")"
        );

        db.execSQL(
                "CREATE TABLE posts (" +
                        "id INTEGER PRIMARY KEY, " +
                        "user_id INTEGER NOT NULL, " +
                        "title TEXT, " +
                        "body TEXT, " +
                        "FOREIGN KEY(user_id) REFERENCES users(id)" +
                        ")"
        );
    }

    @Override
    public void onUpgrade(@NonNull SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle migrations here.

    }
}



