package com.pinkertone.pinkercardsmvp;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class BattleResultsActivity extends AppCompatActivity {

    private byte rightAnswersNum;
    private TextView resultNumTV;
    private TextView motivationTV;
    private String[] motivationArr;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_results);

        Bundle arguments = getIntent().getExtras();
        assert arguments != null;

        rightAnswersNum = arguments.getByte("rightAnswersNum");
        resultNumTV = findViewById(R.id.resultNumTV);
        resultNumTV.setText(rightAnswersNum+"/5");
        motivationArr = new String[]{
                "В следующий раз повезет",
                "Старайся лучше",
                "Почти...",
                "Ещё немного и победа наша!"
        };
        motivationTV = findViewById(R.id.motivationTV);
        final Random random = new Random();
        motivationTV.setText(motivationArr[random.nextInt(motivationArr.length - 1)]);
    }
}
