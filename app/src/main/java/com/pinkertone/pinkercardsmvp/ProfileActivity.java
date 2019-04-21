package com.pinkertone.pinkercardsmvp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void exit(View view){
        SharedPreferences sPref = getSharedPreferences("AuthData", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.remove("Token");
        ed.remove("username");
        ed.commit();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
     }
}
