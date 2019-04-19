
package com.pinkertone.pinkercardsmvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateUser {

    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("id")
    @Expose
    public int id;

}