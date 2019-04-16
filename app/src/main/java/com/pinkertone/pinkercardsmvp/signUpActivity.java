package com.pinkertone.pinkercardsmvp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class signUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void jumpToLogin(View view) {
        Intent intent = new Intent(signUpActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
