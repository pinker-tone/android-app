package com.pinkertone.pinkercardsmvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StandardAnswer {
    @SerializedName("status")
    @Expose
    public int status;
    @SerializedName("data")
    @Expose
    public String data;
}
