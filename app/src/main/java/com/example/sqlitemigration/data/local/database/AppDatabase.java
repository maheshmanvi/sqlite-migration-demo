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
        // Create table here
         db.execSQL("CREATE TABLE \"users\" (\n" +
                 "  \"id\" SERIAL PRIMARY KEY,\n" +
                 "  \"name\" VARCHAR(255),\n" +
                 "  \"username\" VARCHAR(255),\n" +
                 "  \"email\" VARCHAR(255),\n" +
                 "  \"address\" JSONB,\n" +
                 "  \"phone\" VARCHAR(255),\n" +
                 "  \"website\" VARCHAR(255),\n" +
                 "  \"company\" JSONB\n" +
                 ");");

        db.execSQL("CREATE TABLE \"posts\" (\n" +
                "  \"userId\" INTEGER,\n" +
                "  \"id\" INTEGER PRIMARY KEY,\n" +
                "  \"title\" TEXT,\n" +
                "  \"body\" TEXT\n" +
                ");");

        // Trigger onUpgrade for V1 -> V2 baseline if starting fresh at V2
        onUpgrade(db, 1, Constants.DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(@NonNull SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle migrations here.

    }
}



