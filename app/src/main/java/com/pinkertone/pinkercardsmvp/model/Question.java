
package com.pinkertone.pinkercardsmvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Question {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("question_text")
    @Expose
    public String questionText;
    @SerializedName("question_type")
    @Expose
    public String questionType;
    @SerializedName("answer")
    @Expose
    public String answer;

}
