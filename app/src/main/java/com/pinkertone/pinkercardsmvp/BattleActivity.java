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
    private TextView questionsTV;
    public String[] questions;
    private int correctAnswers = 0;
    private int id;
    private String status;
    private TextView numberQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        Bundle arguments = getIntent().getExtras();
        assert arguments != null;
        id = arguments.getInt("game_id");
        questionsTV = findViewById(R.id.questionTV);
        numberQuestion = findViewById(R.id.questionNumber);

        questions = arguments.getStringArray("questions");
        rightAnswers = arguments.getBooleanArray("rightAnswers");
        status = arguments.getString("status");

        assert questions != null;
        questionsTV.setText(questions[questionNumber]);
        numberQuestion.setText(questionNumber+1 + "/5");
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
        if (rightAnswers[questionNumber-1] == true){
            splashRight(view);
            this.correctAnswers++;
        } else {
            splashWrong(view);
        }
        if (questionNumber < 5) {
            nextQuestion();
        } else {
            gotoBattleResultsActivity();
        }
    }

    public void answerNo(View view){
        if (rightAnswers[questionNumber-1] == false){
            splashRight(view);
            this.correctAnswers++;
        } else {
            splashWrong(view);
        }
        if (questionNumber < 5) {
            nextQuestion();
        } else {
            gotoBattleResultsActivity();
        }
    }

    private void nextQuestion(){
        questionsTV.setText(questions[questionNumber]);
        numberQuestion.setText(questionNumber+1 + "/5");
        questionNumber++;
    }

    public void gotoBattleResultsActivity(){
        System.out.println(correctAnswers);
        Intent intent = new Intent(this, BattleResultsActivity.class);
        intent.putExtra("rightAnswersNum", correctAnswers);
        intent.putExtra("whatToDo", "SEND");
        intent.putExtra("game_id", id);
        intent.putExtra("status", status);
        startActivity(intent);
        finish();
    }
}
