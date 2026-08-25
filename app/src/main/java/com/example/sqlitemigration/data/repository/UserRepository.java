package com.example.sqlitemigration.data.repository;


import com.example.sqlitemigration.data.model.User;
import com.example.sqlitemigration.data.remote.ApiService;

import java.util.List;

import retrofit2.Call;

public class UserRepository {


    private final ApiService apiService;

    // This repository doesn't know about LiveDate, ViewModel, Activity, UI.
    // It only knows about data.
    public UserRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Call<List<User>> getUsers(){
        return apiService.getUsers();
    }
}

