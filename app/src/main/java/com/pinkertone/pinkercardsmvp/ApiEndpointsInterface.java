package com.pinkertone.pinkercardsmvp;

import com.pinkertone.pinkercardsmvp.model.LogToken;
import com.pinkertone.pinkercardsmvp.model.Game;
import com.pinkertone.pinkercardsmvp.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Header;

public interface ApiEndpointsInterface {

    @GET("api/v1/games/")
    Call<ArrayList<Game>> getGames(@Header("Authorization") String token);


    @FormUrlEncoded
    @POST("auth/token/login/")
    Call<LogToken> logToken(@Field("username") String username, @Field("password") String password);

    @GET("api/v1/users/")
    Call<ArrayList<User>> getUsers(@Header("Authorization") String token);
}
