package com.pinkertone.pinkercardsmvp;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BattleActivity extends AppCompatActivity {

    private byte questionNumber = 0;
    private boolean[] rightAnswers;
    private List<Boolean> userAnswers;
    private TextView questionsTV;
    public String[] questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        Bundle arguments = getIntent().getExtras();
        assert arguments != null;

        userAnswers = new ArrayList(5);
        questionsTV = findViewById(R.id.questionTV);

        questions = arguments.getStringArray("questions");

        questions = arguments.getStringArray("questions");
        rightAnswers = arguments.getBooleanArray("rightAnswers");
        assert questions != null;
        questionsTV.setText(questions[questionNumber]);
        questionNumber++;
    }

    public void splashRight(View view){
        Snackbar.make(view, "А ты шаришь", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
    }

    public void splashWrong(View view){
        Snackbar.make(view, "Вот тут ты не прав, дружок!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public void answerYes(View view){
        if (questionNumber < 5) {
            splashRight(view);
            userAnswers.add(true);
            nextQuestion();
        } else {
            gotoBattleResultsActivity();
        }
    }

    public void answerNo(View view){
        if (questionNumber < 5) {
            splashWrong(view);
            userAnswers.add(false);
            nextQuestion();
        } else {
            gotoBattleResultsActivity();
        }
    }

    private void nextQuestion(){
        questionsTV.setText(questions[questionNumber]);
        questionNumber++;
    }

    private byte calculateRightAnswers(){
        byte rightAnswersNum = 0;
        for (byte i = 0; i < 4; i++){
            if (userAnswers.get(i) == rightAnswers[i]){
                rightAnswersNum++;
            }
        }
        return rightAnswersNum;
    }

    public void gotoBattleResultsActivity(){
        Intent intent = new Intent(this, BattleResultsActivity.class);
        intent.putExtra("rightAnswersNum", calculateRightAnswers());
        startActivity(intent);
        finish();
    }
}
