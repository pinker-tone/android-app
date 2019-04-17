package com.pinkertone.pinkercardsmvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogToken {

    @SerializedName("auth_token")
    @Expose
    public String authToken;

}