package com.pinkertone.pinkercardsmvp;

import com.pinkertone.pinkercardsmvp.model.AccountInfo;
import com.pinkertone.pinkercardsmvp.model.CreateUser;
import com.pinkertone.pinkercardsmvp.model.LogToken;
import com.pinkertone.pinkercardsmvp.model.Game;
import com.pinkertone.pinkercardsmvp.model.StandartAnswer;
import com.pinkertone.pinkercardsmvp.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ApiEndpointsInterface {

    @GET("api/v1/games/")
    Call<ArrayList<Game>> getGames(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("auth/token/login/")
    Call<LogToken> logToken(@Field("username") String username, @Field("password") String password);

    @GET("api/v1/users/")
    Call<ArrayList<User>> getUsers(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("api/v1/games/create/")
    Call<ArrayList<Game>> createGame(@Header("Authorization") String token, @Field("opponent") String opponent);

    @GET("api/v1/games/{id}/")
    Call<ArrayList<Game>> getCertainGame(@Header("Authorization") String token, @Path("id") int id);

    @FormUrlEncoded
    @POST("api/v1/games/answer/")
    Call<StandartAnswer> sendAnswer(@Header("Authorization") String token, @Field("game_id") int id, @Field("correct_answers") int correct_answers);

    @FormUrlEncoded
    @POST("auth/users/create/")
    Call<CreateUser> createUser(@Field("username") String username, @Field("password") String password,
                                @Field("email") String email);

    @GET("auth/users/me/")
    Call<AccountInfo> getAccountInfo(@Header("Authorization") String token);
}
