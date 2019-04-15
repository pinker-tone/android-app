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
        Intent intent = new Intent(MainActivity.this, BattleActivity.class);
        startActivity(intent);
    }
}
