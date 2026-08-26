package com.example.sqlitemigration.di;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

// A custom functional interface that Java CAN convert to a lambda
public interface InlineFactory<V extends ViewModel> {
    V create();

    // Helper method to bridge our lambda to Android's Factory
    static <V extends ViewModel> ViewModelProvider.Factory of(InlineFactory<V> factory) {
        return new ViewModelProvider.Factory() {

            @NonNull
            @Override
            @SuppressWarnings("unchecked")
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) factory.create();
            }

        };
    }
}