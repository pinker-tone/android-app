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
        if (!sPref.contains("Token") || !sPref.contains("username")){
            Intent intent = new Intent(BattlesActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        final String myusername = sPref.getString("username", "").toLowerCase();
        final String token = sPref.getString(TOKEN, "");
        Call<ArrayList<Game>> call = Singleton.getInstance().apiService.getGames(TOKEN + " " +token);

        call.enqueue(new Callback<ArrayList<Game>>() {
            @Override
            public void onResponse(Call<ArrayList<Game>> call, final Response<ArrayList<Game>> response) {
                if (response.isSuccessful()) {

                    ArrayList<BattleItem> battleItems = new ArrayList<BattleItem>();
                    BattlesAdapter battlesAdapter;

                    for (Game game: response.body()) {
                        String enemyName;
                        boolean isAccepted;

                        if (game.user1.username.toLowerCase().equals(myusername)){
                            enemyName = game.user2.username;
                            if (game.answersCorrectUser1 == null){
                                isAccepted = false;
                            }
                            else { isAccepted = true; }
                        } else {
                            enemyName = game.user1.username;
                            if (game.answersCorrectUser2 == null){
                                isAccepted = false;
                            }
                            else { isAccepted = true; }
                        }

                        String date = game.date.substring(11, 19) + " " + game.date.substring(8, 10) + "/" + game.date.substring(5, 7);
                        String battleState = null;
                        String battleScore = "";

                        switch (game.status) {
                            case "WAITING":
                                battleState = "Ожидание";
                                break;
                            case "ENDED":
                                if (!game.draw) {
                                    if (game.winner.username.toLowerCase().equals(myusername)) {
                                        battleState = "Победа";
                                    } else {
                                        battleState = "Поражение";
                                    }
                                } else {
                                    battleState = "Ничья";
                                }
                                if (game.user1.username.toLowerCase().equals(myusername)) {
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
                        item.setAccepted(isAccepted);
                        item.setSubjectName("История");

                        battleItems.add(item);

                    }

                    battlesAdapter = new BattlesAdapter(BattlesActivity.this, battleItems);

                    ListView battlesLV = findViewById(R.id.battlesLV);
                    battlesLV.setAdapter(battlesAdapter);
                    battlesLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Game game = response.body().get(position);
                            switch (game.status) {
                                case "ENDED":
                                    jumpToResultsActivity(game.id, game.status, "SHOW");
                                    break;
                                case "WAITING":
                                    if (game.user1.username.toLowerCase().equals(myusername)){
                                        if (game.answersCorrectUser1 == null){
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
                                            jumpToBattleActivity(questionsArray, answersArray, game.id, game.status);
                                        }
                                        else {
                                            jumpToResultsActivity(game.id, game.status, "SHOW", game.answersCorrectUser1, game.user2.username);
                                            }
                                    } else {
                                        if (game.answersCorrectUser2 == null){
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
                                            jumpToBattleActivity(questionsArray, answersArray, game.id, game.status);
                                        }
                                        else {
                                            jumpToResultsActivity(game.id, game.status, "SHOW", game.answersCorrectUser2, game.user1.username);
                                        }
                                    }
                                    break;
                            }
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

    public void jumpToProfile(View view) {
        Intent intent = new Intent(BattlesActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    public void jumpToBattleActivity(String [] questions, boolean[] rightAnswers, int game_id, String status){
        Intent intent = new Intent(BattlesActivity.this, BattleActivity.class);
        intent.putExtra("questions", questions);
        intent.putExtra("rightAnswers", rightAnswers);
        intent.putExtra("status", status);
        intent.putExtra("game_id", game_id);
        startActivity(intent);
    }

    public void jumpToResultsActivity(int id, String status, String whatToDo){
        Intent intent = new Intent(BattlesActivity.this, BattleResultsActivity.class);
        intent.putExtra("game_id", id);
        intent.putExtra("status", status);
        intent.putExtra("whatToDo", whatToDo);
        startActivity(intent);
    }

    public void jumpToResultsActivity(int id, String status, String whatToDo, int correctAnswers, String opponent){
        Intent intent = new Intent(BattlesActivity.this, BattleResultsActivity.class);
        intent.putExtra("game_id", id);
        intent.putExtra("status", status);
        intent.putExtra("whatToDo", whatToDo);
        intent.putExtra("rightAnswersNum", correctAnswers);
        intent.putExtra("opponent", opponent);
        startActivity(intent);
    }

}

