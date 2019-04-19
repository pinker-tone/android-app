package com.pinkertone.pinkercardsmvp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.pinkertone.pinkercardsmvp.model.Game;
import com.pinkertone.pinkercardsmvp.model.LogToken;

import java.util.ArrayList;
import java.util.List;

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

        sPref = getSharedPreferences("AuthData", MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battles);

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
                if (response.isSuccessful()){
                    //ListView battlesLV = findViewById(R.id.battlesLV);

                    //LayoutInflater ltInflater = getLayoutInflater();

//                    for (Game game: response.body()) {
//
//                        View battle_item = ltInflater.inflate(R.layout.battle_item, battlesLV, false);
//
//                        TextView enemyName = battle_item.findViewById(R.id.enemyName);
//                        if (game.user1.username.equals(sPref.getString("username", ""))){
//                            enemyName.setText(game.user2.username);
//                        } else {
//                            enemyName.setText(game.user1.username);
//                        }
//
//                        String date = game.date.substring(11, 19) + " " + game.date.substring(8, 10) + "/" + game.date.substring(5, 7);
//                        TextView battleDate = battle_item.findViewById(R.id.battleDate);
//                        battleDate.setText(date);
//
//                        TextView subjectName = battle_item.findViewById(R.id.subjectName);
//                        subjectName.setText("История");
//
//                        TextView battleState = battle_item.findViewById(R.id.battleState);
//                        TextView battleScore = battle_item.findViewById(R.id.battleScore);
//
//                        switch (game.status) {
//                            case "WAITING":
//                                battleState.setText("Ожидание");
//                                battleState.setBackgroundColor(colorWaiting);
//                                battleScore.setVisibility(View.GONE);
//                                break;
//                            case "ENDED":
//                                if (!game.draw) {
//                                    if (game.winner.username.equals(sPref.getString("username", ""))) {
//                                        battleState.setText("Победа");
//                                        battleState.setBackgroundColor(colorWin);
//                                    }
//                                    else {
//                                        battleState.setText("Поражение");
//                                        battleState.setBackgroundColor(colorDefeat);
//                                    }
//                                }
//                                else {
//                                    battleState.setText("Ничья");
//                                    battleState.setBackgroundColor(colorDraw);
//                                }
//                                if (game.user1.username.equals(sPref.getString("username", ""))) {
//                                    battleScore.setText(game.answersCorrectUser1 + ":" + game.answersCorrectUser2);
//                                }
//                                else {
//                                    battleScore.setText(game.answersCorrectUser2 + ":" + game.answersCorrectUser1);
//                                }
//                        }
//
//                        battlesLV.addView(battle_item);
//                    }


                    ArrayList<BattleItem> battleItems = new ArrayList<BattleItem>();
                    BattlesAdapter battlesAdapter;
                    fillData();
                    battlesAdapter = new BattlesAdapter(BattlesActivity.this, battleItems);

                    ListView battlesLV = findViewById(R.id.battlesLV);
                    battlesLV.setAdapter(battlesAdapter);

                    void fillData(){
                        BattleItem i = new BattleItem();
                        i.setBattleDate("Date");
                        i.setBattleScore("4:3");
                        i.setBattleState("Win");
                        i.setEnemyName("Vitalia");
                        i.setSubjectName("History");

                        BattleItem j = new BattleItem();
                        j.setBattleDate("2019/102/3");
                        j.setBattleScore("1:3");
                        j.setBattleState("Loose");
                        j.setEnemyName("Misha");
                        j.setSubjectName("History");

                        battleItems.add(i);
                        battleItems.add(j);
                    }
                }
                else {
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
        finish();
    }

}

