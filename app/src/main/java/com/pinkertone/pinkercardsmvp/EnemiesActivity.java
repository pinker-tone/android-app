package com.pinkertone.pinkercardsmvp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.pinkertone.pinkercardsmvp.model.Game;
import com.pinkertone.pinkercardsmvp.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnemiesActivity extends AppCompatActivity {

    SharedPreferences sPref;
    final String TOKEN = "Token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enemies);
        sPref = getSharedPreferences("AuthData", MODE_PRIVATE);
        final String token = sPref.getString(TOKEN, "");
        if (token == ""){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        final ListView listView = findViewById(R.id.enemiesLV);


        Call<ArrayList<User>> call = Singleton.getInstance().apiService.getUsers(TOKEN + " " + token);

        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(final Call<ArrayList<User>> call, final Response<ArrayList<User>> response) {
                if (response.isSuccessful()){
                    String[] enemyNames = new String[response.body().size()];
                    for (int i = 0; i < response.body().size(); i++){
                        enemyNames[i] = response.body().get(i).username;
                    }


                    //TODO: кастомный лайаут
                    ArrayAdapter<String> adapter = new ArrayAdapter(EnemiesActivity.this,
                            android.R.layout.simple_list_item_1, enemyNames);

                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            TextView list_element = (TextView) view;

                            Call<ArrayList<Game>>call = Singleton.getInstance().apiService.createGame(TOKEN + " " + token,
                                    list_element.getText().toString());
                            call.enqueue(new Callback<ArrayList<Game>>() {
                                @Override
                                public void onResponse(final Call<ArrayList<Game>> call, final Response<ArrayList<Game>> response) {
                                    if (response.isSuccessful()){
                                        Game game = response.body().get(0);
                                        String[] questionsArray = new String[5];
                                        boolean[] answersArray = new boolean[5];
                                        for (int i = 0; i < 5; i++){
                                            questionsArray[i] = game.questions.get(i).questionText;
                                            switch (game.questions.get(i).answer) {
                                                case "Yes":
                                                    answersArray[i] = true;
                                                    break;
                                                case "No":
                                                    answersArray[i] = false;
                                                    break;
                                            }
                                        }
                                        Intent intent = new Intent(EnemiesActivity.this, BattleActivity.class);
                                        intent.putExtra("questions", questionsArray);
                                        intent.putExtra("rightAnswers", answersArray);
                                        intent.putExtra("game_id", game.id);
                                        startActivity(intent);
                                        finish();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ArrayList<Game>> call, Throwable t) {
                                    // nothing
                                }
                            });
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                // nothing
            }
        });
    }
}
