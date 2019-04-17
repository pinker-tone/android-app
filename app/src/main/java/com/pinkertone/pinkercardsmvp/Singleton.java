package com.pinkertone.pinkercardsmvp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singleton {
    private static Singleton instance;
    private static final String BASE_URL = "http://35.156.240.204/";


    public ApiEndpointsInterface apiService;

    public Singleton(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiEndpointsInterface.class);

    }

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
