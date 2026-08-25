package com.example.sqlitemigration.utils;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class Resource<T> {

    // Instead of passing three different 'LiveData' objects(like loading, data, error), we expose one 'LiveDate<Resource<T>>'.
    // This keeps the ViewModel and UI simpler.
    // This is common in MVVM pattern.

    // This Resource<T> is used as a wrapper for the state of an operation, usually from a ViewModel to the UI

    @NonNull
    private final Status status;

    @Nullable
    private final T data;

    @Nullable
    private final String message;


    public Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    @NonNull
    public static <T> Resource<T> loading(){
        return new Resource<>(Status.LOADING, null, null);
    }

    @NonNull
    public static <T> Resource<T> success(@NonNull T data){
        return new Resource<>(Status.SUCCESS, data, null);
    }

    @NonNull
    public static <T> Resource<T> error(@NonNull String message){
        return new Resource<>(Status.ERROR, null, message);
    }


    @NonNull
    public Status getStatus(){
        return status;
    }

    @Nullable
    public T getData(){
        return data;
    }

    @Nullable
    public String getMessage(){
        return message;
    }
}
