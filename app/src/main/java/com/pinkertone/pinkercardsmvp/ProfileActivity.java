package com.pinkertone.pinkercardsmvp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pinkertone.pinkercardsmvp.model.AccountInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    private SharedPreferences sPref;
    private SharedPreferences.Editor ed;
    private TextView name;
    private TextView designation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        System.out.println("Hello bitches!");

        name = findViewById(R.id.name);
        designation = findViewById(R.id.designation);

        sPref = getSharedPreferences("AuthData", MODE_PRIVATE);
        ed = sPref.edit();

        name.setText(sPref.getString("username", ""));
        designation.setText(sPref.getString("email", "You have no email"));
    }

    public void exit(View view){

        ed.remove("Token");
        ed.remove("username");
        ed.remove("email");
        ed.commit();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
     }
}
