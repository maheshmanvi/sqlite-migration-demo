package com.example.sqlitemigration.data.remote;

import androidx.annotation.NonNull;


import com.example.sqlitemigration.BuildConfig;
import com.example.sqlitemigration.utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ApiClient {

    // Why both:
    // Retrofit → "What API endpoint am I calling and how do I convert the data?"
    // OkHttp   → "How do I actually communicate with the server?"

    // OkHttp used when: Authentication interceptor, Logging interceptor, Timeouts, Headers, Retry/network handling, Network configuration

    private static final long CONNECT_TIMEOUT_SECONDS = 30L;
    private static final long READ_TIMEOUT_SECONDS = 30L;
    private static final long WRITE_TIMEOUT_SECONDS = 30L;
    private final ApiService apiService;

    public ApiClient() {

        HttpLoggingInterceptor loggingInterceptor =
                new HttpLoggingInterceptor();

        loggingInterceptor.setLevel(
                BuildConfig.DEBUG
                        ? HttpLoggingInterceptor.Level.BODY
                        : HttpLoggingInterceptor.Level.NONE
        );

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public ApiService apiService() {
        return apiService;
    }
}