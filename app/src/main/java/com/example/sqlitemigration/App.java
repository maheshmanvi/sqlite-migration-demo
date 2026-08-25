package com.example.sqlitemigration;

import android.app.Application;

import com.example.sqlitemigration.di.AppContainer;

public class App extends Application {

    private static AppContainer appContainer;

    @Override
    public void onCreate() {
        super.onCreate();
        appContainer = new AppContainer(this);
    }

    public AppContainer getAppContainer(){
        return appContainer;
    }
}
