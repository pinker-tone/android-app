package com.pinkertone.pinkercardsmvp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.pinkertone.pinkercardsmvp.model.AccountInfo;
import com.pinkertone.pinkercardsmvp.model.LogToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    SharedPreferences sPref;
    final String TOKEN = "Token";
    Editor ed;
    private TextView login_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sPref = getSharedPreferences("AuthData", MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ed = sPref.edit();
        login_error = findViewById(R.id.login_error);
    }

    public void jumpToSignUp(View view) {
        Intent intent = new Intent(LoginActivity.this, signUpActivity.class);
        startActivity(intent);
        finish();
    }

    public void jumpToBattles() {
        Intent intent = new Intent(LoginActivity.this, BattlesActivity.class);
        startActivity(intent);
        finish();
    }

    public void enter(View view){
        EditText login_edit = findViewById(R.id.enterLoginEditText);
        EditText password_edit = findViewById(R.id.enterPasswordEditText);
        String login = login_edit.getText().toString();
        String password = password_edit.getText().toString();
        if (!login.equals("") && !password.equals("")){
            getToken(login, password);
        }
    }


    public void getToken(final String username, String password){
        login_error.setVisibility(View.GONE);
        Call<LogToken> call = Singleton.getInstance().apiService.logToken(username, password);
        call.enqueue(new Callback<LogToken>() {
            @Override
            public void onResponse(Call<LogToken> call, Response<LogToken> response) {
                if (response.isSuccessful()){
                    LogToken token = response.body();
                    ed.putString(TOKEN, token.authToken);
                    ed.putString("username", username);
                    ed.commit();

                    Call<AccountInfo> call2 = Singleton.getInstance().apiService.getAccountInfo("Token " + sPref.getString("Token", ""));

                    call2.enqueue(new Callback<AccountInfo>() {
                        @Override
                        public void onResponse(Call<AccountInfo> call, Response<AccountInfo> response) {
                            if (response.isSuccessful()) {
                                AccountInfo acc_info = response.body();
                                if (!acc_info.email.isEmpty()){
                                    ed.putString("email", acc_info.email);
                                    ed.commit();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<AccountInfo> call, Throwable t) {

                        }
                    });
                    jumpToBattles();
                }
                else {
                    login_error.setVisibility(View.VISIBLE);
                    login_error.setText("Неверный логин или пароль.");
                }
            }

            @Override
            public void onFailure(Call<LogToken> call, Throwable t) {
                // nothing
            }
        });
    }
}