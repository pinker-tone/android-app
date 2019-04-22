package com.pinkertone.pinkercardsmvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountInfo {
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("username")
    @Expose
    public String username;
}
