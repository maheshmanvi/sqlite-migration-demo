package com.example.sqlitemigration.data.local.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sqlitemigration.utils.Constants;

public final class PreferenceManager {

    // USAGE:
    // When using manual DI(AppContainer):
    // AppContainer container = ((App) getApplication()).getAppContainer();
    // PreferenceManager preferences = container.preferences();
    // boolean loggedIn = preferences.getBoolean("is_logged_in", false);

    private final SharedPreferences preferences;

    public PreferenceManager(Context context) {
        preferences = context.getApplicationContext()
                .getSharedPreferences(
                        Constants.PREF_NAME,
                        Context.MODE_PRIVATE
                );
    }

    public void putBoolean(String key, boolean value) {
        preferences.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return preferences.getBoolean(key, defaultValue);
    }

    public void putString(String key, String value){
        preferences.edit().putString(key, value).apply();
    }

    public String getString(String key){
        return preferences.getString(key, "");
    }

    public void clear(){
        preferences.edit().clear().apply();
    }
}
