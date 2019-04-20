package com.pinkertone.pinkercardsmvp;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pinkertone.pinkercardsmvp.model.Game;
import com.pinkertone.pinkercardsmvp.model.StandartAnswer;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BattleResultsActivity extends AppCompatActivity {

    private final String TOKEN = "Token";
    private int rightAnswersNum;
    private TextView resultNumTV;
    private TextView resultTV;
    private TextView motivationTV;
    private TextView someTV;
    private int id;
    SharedPreferences sPref;
    private String status;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_results);

        resultNumTV = findViewById(R.id.resultNumTV);
        motivationTV = findViewById(R.id.motivationTV);
        resultTV = findViewById(R.id.resultTV);
        someTV = findViewById(R.id.someTV);

        sPref = getSharedPreferences("AuthData", MODE_PRIVATE);
        final String token = sPref.getString(TOKEN, "");
        final Bundle arguments = getIntent().getExtras();
        assert arguments != null;

        final String whatToDo = arguments.getString("whatToDo");
        status = arguments.getString("status");
        id = arguments.getInt("game_id");

        final String myusername = sPref.getString("username", "").toLowerCase();



        if (!status.equals("ENDED")){
            rightAnswersNum = arguments.getInt("rightAnswersNum");
            if (whatToDo.equals("SHOW")) {
                resultTV.setText("Ожидание");
                resultNumTV.setText(rightAnswersNum + "/5");
                String opponent = arguments.getString("opponent");
                motivationTV.setText("Вы играете против " + opponent);
            } else if (whatToDo.equals("SEND")) {
                Call<StandartAnswer> answerCall = Singleton.getInstance().apiService.sendAnswer(TOKEN + " " + token, id, rightAnswersNum);
                answerCall.enqueue(new Callback<StandartAnswer>() {
                    @Override
                    public void onResponse(Call<StandartAnswer> call, Response<StandartAnswer> response) {
                        if (response.isSuccessful()) {
                            Call<ArrayList<Game>> gameCall = Singleton.getInstance().apiService.getCertainGame(TOKEN + " " + token, id);
                            gameCall.enqueue(new Callback<ArrayList<Game>>() {
                                @Override
                                public void onResponse(Call<ArrayList<Game>> call, Response<ArrayList<Game>> response) {
                                    if (response.isSuccessful()){
                                        Game game = response.body().get(0);
                                        if (game.status.equals("ENDED")) {
                                            if (game.user1.username.toLowerCase().equals(myusername)){
                                                motivationTV.setText("Вы играли против "+game.user2.username);
                                            } else {
                                                motivationTV.setText("Вы играли против "+game.user1.username);
                                            }

                                            if (!game.draw) {
                                                if (game.winner.username.toLowerCase().equals(myusername)){
                                                    resultTV.setText("Победа");
                                                } else {
                                                    resultTV.setText("Поражение");
                                                }
                                            } else {
                                                resultTV.setText("Ничья");
                                            }
                                            if (game.user1.username.toLowerCase().equals(myusername)){
                                                resultNumTV.setText(game.answersCorrectUser1+":"+game.answersCorrectUser2);
                                            } else {
                                                resultNumTV.setText(game.answersCorrectUser2+":"+game.answersCorrectUser1);
                                            }
                                            someTV.setText("");
                                        } else {
                                            resultTV.setText("Ожидание");
                                            resultNumTV.setText(rightAnswersNum + "/5");
                                            if (game.user1.username.toLowerCase().equals(myusername)) {
                                                String opponent = game.user2.username;
                                                motivationTV.setText("Вы играете против " + opponent);
                                            } else{
                                                String opponent = game.user1.username;
                                                motivationTV.setText("Вы играете против " + opponent);
                                            }

                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<ArrayList<Game>> call, Throwable t) {

                                }
                            });

                        }
                    }
                    @Override
                    public void onFailure(Call<StandartAnswer> call, Throwable t){

                    }
                });
            }
        } else {
            Call<ArrayList<Game>> gameCall = Singleton.getInstance().apiService.getCertainGame(TOKEN + " " + token, id);

            gameCall.enqueue(new Callback<ArrayList<Game>>() {
                @Override
                public void onResponse(Call<ArrayList<Game>> call, Response<ArrayList<Game>> response) {
                    Game game = response.body().get(0);
                    if (game.user1.username.toLowerCase().equals(myusername)){
                        motivationTV.setText("Вы играли против "+game.user2.username);
                    } else {
                        motivationTV.setText("Вы играли против "+game.user1.username);
                    }

                    if (!game.draw) {
                        if (game.winner.username.toLowerCase().equals(myusername)){
                            resultTV.setText("Победа");
                        } else {
                            resultTV.setText("Поражение");
                        }
                    } else {
                        resultTV.setText("Ничья");
                    }
                    if (game.user1.username.toLowerCase().equals(myusername)){
                        resultNumTV.setText(game.answersCorrectUser1+":"+game.answersCorrectUser2);
                    } else {
                        resultNumTV.setText(game.answersCorrectUser2+":"+game.answersCorrectUser1);
                    }
                    someTV.setText("");

                }

                @Override
                public void onFailure(Call<ArrayList<Game>> call, Throwable t) {

                }
            });
        }

    }




//        resultNumTV.setText(rightAnswersNum+"/5");
//        motivationArr = new String[]{
//                "В следующий раз повезет",
//                "Старайся лучше",
//                "Почти...",
//                "Ещё немного и победа наша!"
//        };
//        final Random random = new Random();
//        motivationTV.setText(motivationArr[random.nextInt(motivationArr.length - 1)]);
}
