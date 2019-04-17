
package com.pinkertone.pinkercardsmvp.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pinkertone.pinkercardsmvp.model.User;
import com.pinkertone.pinkercardsmvp.model.Question;
import com.pinkertone.pinkercardsmvp.model.Winner;

public class Game {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("user_1")
    @Expose
    public User user1;
    @SerializedName("user_2")
    @Expose
    public User user2;
    @SerializedName("questions")
    @Expose
    public List<Question> questions = null;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("winner")
    @Expose
    public Winner winner;
    @SerializedName("answers_correct_user_1")
    @Expose
    public Integer answersCorrectUser1;
    @SerializedName("answers_correct_user_2")
    @Expose
    public Integer answersCorrectUser2;
    @SerializedName("draw")
    @Expose
    public Boolean draw;
    @SerializedName("date")
    @Expose
    public String date;

}
