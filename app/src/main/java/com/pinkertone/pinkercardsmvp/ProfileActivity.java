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

        name = findViewById(R.id.name);
        designation = findViewById(R.id.designation);

        sPref = getSharedPreferences("AuthData", MODE_PRIVATE);
        ed = sPref.edit();

        Call<AccountInfo> call = Singleton.getInstance().apiService.getAccountInfo("Token " + sPref.getString("Token", ""));

        call.enqueue(new Callback<AccountInfo>() {
            @Override
            public void onResponse(Call<AccountInfo> call, Response<AccountInfo> response) {
                if (response.isSuccessful()) {
                    AccountInfo acc_info = response.body();

                    name.setText(acc_info.username);
                    designation.setText(acc_info.email);
                }
            }

            @Override
            public void onFailure(Call<AccountInfo> call, Throwable t) {

            }
        });
    }

    public void exit(View view){

        ed.remove("Token");
        ed.remove("username");
        ed.commit();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
     }
}
