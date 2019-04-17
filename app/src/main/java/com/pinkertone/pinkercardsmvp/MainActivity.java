package com.pinkertone.pinkercardsmvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jumpToLogin(View view) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void jumpToSignUp(View view) {
        Intent intent = new Intent(MainActivity.this, signUpActivity.class);
        startActivity(intent);
    }

    public void jumpToBattleResults(View view) {
        Intent intent = new Intent(MainActivity.this, BattleResultsActivity.class);
        startActivity(intent);
    }

    public void jumpToBattles(View view) {
        Intent intent = new Intent(MainActivity.this, BattlesActivity.class);
        startActivity(intent);
    }

    public void jumpToBattle(View view) {
        Intent intent = new Intent(this, BattleActivity.class);
        //String jsonString = "{ \"name\" : \"Ronaldo\", \"sport\" : \"soccer\", \"age\" : 25, \"id\" : 121, \"lastScores\" : [ 2, 1, 3, 5, 0, 0, 1, 1 ]  }";
        //Gson g = new Gson();
        //BattleData b = g.fromJson(jsonString, BattleData.class);
        //Log.d("mylog", b.name);

        String[] questionsArray = {
                "Был ли кто-то когда я умер?",
                "Да или нет?",
                "В питере можно пить?",
                "Стиви Джобс умер в 2007",
                "У матросов есть вопросы?"
        };
        boolean[] answersArray = {false, true, true, false, false};
        intent.putExtra("questions", questionsArray);
        intent.putExtra("rightAnswers", answersArray);
        //Intent intent = new Intent(MainActivity.this, BattleActivity.class);
        startActivity(intent);
    }

    public void jumpToEnemies(View view) {
        Intent intent = new Intent(MainActivity.this, EnemiesActivity.class);
        startActivity(intent);
    }
}
