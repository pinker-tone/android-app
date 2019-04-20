package com.pinkertone.pinkercardsmvp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.pinkertone.pinkercardsmvp.model.CreateUser;
import com.pinkertone.pinkercardsmvp.model.LogToken;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class signUpActivity extends AppCompatActivity {

    private EditText username;
    private EditText email;
    private EditText password;
    private EditText repeat_password;
    private TextView errors;
    private SharedPreferences sPref;
    SharedPreferences.Editor ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sPref = getSharedPreferences("AuthData", MODE_PRIVATE);
        ed  = sPref.edit();

        username = findViewById(R.id.registrationUsernameET);
        email = findViewById(R.id.registrationEmailET);
        password = findViewById(R.id.registrationPasswordET);
        repeat_password = findViewById(R.id.registrationConfirmPasswordET);
        errors = findViewById(R.id.errorsSignup);

    }

    public void signUp(View view){
        if ((!username.getText().toString().isEmpty() && !email.getText().toString().isEmpty()
                && !password.getText().toString().isEmpty()
                && !repeat_password.getText().toString().isEmpty())
                && (password.getText().toString().equals(repeat_password.getText().toString()))){
            Call<CreateUser> call = Singleton.getInstance().apiService.createUser(username.getText().toString(),
                    password.getText().toString(), email.getText().toString());
            errors.setVisibility(View.GONE);
            call.enqueue(new Callback<CreateUser>() {
                @Override
                public void onResponse(Call<CreateUser> call, Response<CreateUser> response) {
                    if (response.isSuccessful()){
                        ed.putString("username", username.getText().toString().toLowerCase());
                        ed.commit();
                        Call<LogToken> call2 = Singleton.getInstance().apiService.logToken(username.getText().toString(),
                                password.getText().toString());

                        call2.enqueue(new Callback<LogToken>() {
                            @Override
                            public void onResponse(Call<LogToken> call, Response<LogToken> response) {
                                if (response.isSuccessful()){
                                    ed.putString("Token", response.body().authToken);
                                    ed.commit();
                                    Intent intent = new Intent(signUpActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }

                            }

                            @Override
                            public void onFailure(Call<LogToken> call, Throwable t) {
                                errors.setVisibility(View.VISIBLE);
                                errors.setText(R.string.something_went_wrong);
                                System.out.println(t.getMessage());
                            }
                        });

                    }
                    else{
                        errors.setVisibility(View.VISIBLE);
                        errors.setText(R.string.something_went_wrong);
                    }
                }

                @Override
                public void onFailure(Call<CreateUser> call, Throwable t) {
                    errors.setVisibility(View.VISIBLE);
                    errors.setText(R.string.something_went_wrong);
                    System.out.println(t.getMessage());
                }
            });
        } else{
            errors.setVisibility(View.VISIBLE);
            errors.setText(R.string.password_dont_match_error);

        }
    }

    public void jumpToLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
