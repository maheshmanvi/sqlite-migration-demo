package com.example.sqlitemigration.data.remote;


import com.example.sqlitemigration.data.model.Post;
import com.example.sqlitemigration.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    // This interface contains ONLY API definitions.
    // NO Logic.
    @GET("users")
    Call<List<User>> getUsers();

    @GET("posts")
    Call<List<Post>> getPost();
}
