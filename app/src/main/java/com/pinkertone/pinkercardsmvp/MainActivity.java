package com.pinkertone.pinkercardsmvp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sPref = getSharedPreferences("AuthData", MODE_PRIVATE);
        if (!sPref.contains("Token") || !sPref.contains("username")){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
//        else {
//            Intent intent = new Intent(this, BattlesActivity.class);
//            startActivity(intent);
//            finish();
//        }
    }

    public void jumpToProfile(View view) {
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    public void jumpToBattles(View view) {
        Intent intent = new Intent(MainActivity.this, BattlesActivity.class);
        startActivity(intent);
    }

    public void jumpToFlashcards(View view) {
        Intent intent = new Intent(MainActivity.this, FlashcardsActivity.class);
        startActivity(intent);
    }

    public void jumpToInDeveloping(View view) {
        Intent intent = new Intent(MainActivity.this, InDevelopingActivity.class);
        startActivity(intent);
    }
}
