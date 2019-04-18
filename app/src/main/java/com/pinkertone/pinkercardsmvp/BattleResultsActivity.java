package com.pinkertone.pinkercardsmvp;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pinkertone.pinkercardsmvp.model.Game;
import com.pinkertone.pinkercardsmvp.model.StandartAnswer;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BattleResultsActivity extends AppCompatActivity {

    private final String TOKEN = "Token";
    private byte rightAnswersNum;
    private TextView resultNumTV;
    private TextView motivationTV;
    private String[] motivationArr;
    private int id;
    SharedPreferences sPref;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_results);

        sPref = getSharedPreferences("AuthData", MODE_PRIVATE);
        String token = sPref.getString(TOKEN, "");
        Bundle arguments = getIntent().getExtras();
        assert arguments != null;

        rightAnswersNum = arguments.getByte("rightAnswersNum");
        id = arguments.getInt("game_id");

        Call<StandartAnswer> answerCall = Singleton.getInstance().apiService.sendAnswer(TOKEN + " " + token, id, rightAnswersNum);
        answerCall.enqueue(new Callback<StandartAnswer>() {
            @Override
            public void onResponse(Call<StandartAnswer> call, Response<StandartAnswer> response) {
                if (response.isSuccessful()) {
                    resultNumTV = findViewById(R.id.resultNumTV);
                    resultNumTV.setText(rightAnswersNum + "/5");
                }
            }

            @Override
            public void onFailure(Call<StandartAnswer> call, Throwable t) {
            }
        });
    }




//        resultNumTV = findViewById(R.id.resultNumTV);
//        resultNumTV.setText(rightAnswersNum+"/5");
//        motivationArr = new String[]{
//                "В следующий раз повезет",
//                "Старайся лучше",
//                "Почти...",
//                "Ещё немного и победа наша!"
//        };
//        motivationTV = findViewById(R.id.motivationTV);
//        final Random random = new Random();
//        motivationTV.setText(motivationArr[random.nextInt(motivationArr.length - 1)]);
}
