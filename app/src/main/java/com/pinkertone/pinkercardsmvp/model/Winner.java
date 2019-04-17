
package com.pinkertone.pinkercardsmvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Winner {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("username")
    @Expose
    public String username;

}
