package com.pinkertone.pinkercardsmvp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pinkertone.pinkercardsmvp.model.Game;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BattlesActivity extends AppCompatActivity {

    SharedPreferences sPref;
    final String TOKEN = "Token";
    int colorWaiting;
    int colorWin;
    int colorDefeat;
    int colorDraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        colorWaiting = getResources().getColor(R.color.colorWaiting);
        colorWin = getResources().getColor(R.color.colorWin);
        colorDefeat = getResources().getColor(R.color.colorDefeat);
        colorDraw = getResources().getColor(R.color.colorDraw);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battles);

    }

    @Override
    protected void onStart() {
        super.onStart();

        sPref = getSharedPreferences("AuthData", MODE_PRIVATE);

        String token = sPref.getString(TOKEN, "");
        if (token == ""){
            Intent intent = new Intent(BattlesActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        Call<ArrayList<Game>> call = Singleton.getInstance().apiService.getGames(TOKEN + " " +token);

        call.enqueue(new Callback<ArrayList<Game>>() {
            @Override
            public void onResponse(Call<ArrayList<Game>> call, Response<ArrayList<Game>> response) {
                if (response.isSuccessful()) {

                    ArrayList<BattleItem> battleItems = new ArrayList<BattleItem>();
                    BattlesAdapter battlesAdapter;

                    for (Game game: response.body()) {
                        String enemyName;

                        if (game.user1.username.equals(sPref.getString("username", ""))){
                            enemyName = game.user2.username;
                        } else {
                            enemyName = game.user1.username;
                        }

                        String date = game.date.substring(11, 19) + " " + game.date.substring(8, 10) + "/" + game.date.substring(5, 7);

                        String battleState = null;
                        String battleScore = "";
                        boolean scoreIsVisible;

                        switch (game.status) {
                            case "WAITING":
                                battleState = "Ожидание";
//                                battleState.setBackgroundColor(colorWaiting);
                                scoreIsVisible = false;
                                break;
                            case "ENDED":
                                if (!game.draw) {
                                    if (game.winner.username.equals(sPref.getString("username", ""))) {
                                        battleState = "Победа";
//                                        battleState.setBackgroundColor(colorWin);
                                    } else {
                                        battleState = "Поражение";
//                                        battleState.setBackgroundColor(colorDefeat);
                                    }
                                } else {
                                    battleState = "Ничья";
//                                    battleState.setBackgroundColor(colorDraw);
                                }
                                if (game.user1.username.equals(sPref.getString("username", ""))) {
                                    battleScore = game.answersCorrectUser1 + ":" + game.answersCorrectUser2;
                                }
                                else {
                                    battleScore = game.answersCorrectUser2 + ":" + game.answersCorrectUser1;
                                }
                        }

                        BattleItem item = new BattleItem();
                        item.setBattleDate(date);
                        item.setBattleScore(battleScore);
                        item.setBattleState(battleState);
                        item.setEnemyName(enemyName);
                        item.setSubjectName("История");

                        battleItems.add(item);

                    }

                    battlesAdapter = new BattlesAdapter(BattlesActivity.this, battleItems);

                    ListView battlesLV = findViewById(R.id.battlesLV);
                    battlesLV.setAdapter(battlesAdapter);
                    battlesLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            System.out.println(position);
                        }
                    });
                } else {
                    System.out.println(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Game>> call, Throwable t) {
                // nothing
            }

        });

    }


    public void jumpToEnemies(View view) {
        Intent intent = new Intent(BattlesActivity.this, EnemiesActivity.class);
        startActivity(intent);
    }

}

