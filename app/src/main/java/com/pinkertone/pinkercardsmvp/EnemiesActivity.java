package com.pinkertone.pinkercardsmvp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.pinkertone.pinkercardsmvp.model.LogToken;
import com.pinkertone.pinkercardsmvp.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnemiesActivity extends AppCompatActivity {

//    final String[] catNames = new String[] {
//            "Рыжик", "Барсик", "Мурзик", "Мурка", "Васька",
//            "Томасина", "Кристина", "Пушок", "Дымка", "Кузя",
//            "Китти", "Масяня", "Симба"
//    };

    SharedPreferences sPref;
    final String TOKEN = "Token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enemies);
        sPref = getSharedPreferences("AuthData", MODE_PRIVATE);
        String token = sPref.getString(TOKEN, "");
        if (token == ""){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        final ListView listView = findViewById(R.id.enemiesLV);


        Call<ArrayList<User>> call = Singleton.getInstance().apiService.getUsers(TOKEN + " " + token);

        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                if (response.isSuccessful()){
                    String[] enemyNames = new String[response.body().size()-1];
                    for (int i = 0; i < response.body().size(); i++){
                        User user = response.body().get(i);
                        if (!user.username.equals(sPref.getString("username", ""))) {
                            enemyNames[i] = user.username;
                        }
                    }


                    //TODO: кастомный лайаут
                    ArrayAdapter<String> adapter = new ArrayAdapter(EnemiesActivity.this,
                            android.R.layout.simple_list_item_1, enemyNames);

                    listView.setAdapter(adapter);

                }
                else {

                }
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                // nothing
            }
        });
    }
}
