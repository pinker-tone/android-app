package com.pinkertone.pinkercardsmvp;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BattleResultsActivity extends AppCompatActivity {

    private byte rightAnswersNum;
    private TextView resultNumTV;

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

    }
}
