package com.pinkertone.pinkercardsmvp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sPref;
    final String TOKEN = "Token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sPref = getSharedPreferences("AuthData", MODE_PRIVATE);
        String token = sPref.getString(TOKEN, "");
        if (token.equals("")){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            Intent intent = new Intent(this, BattlesActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void jumpToLogin(View view) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void jumpToBattles(View view) {
        Intent intent = new Intent(MainActivity.this, BattlesActivity.class);
        startActivity(intent);
        finish();
    }

    public void jumpToEnemies(View view) {
        Intent intent = new Intent(MainActivity.this, EnemiesActivity.class);
        startActivity(intent);
    }
}
