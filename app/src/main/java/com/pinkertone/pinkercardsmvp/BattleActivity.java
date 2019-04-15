package com.pinkertone.pinkercardsmvp;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BattleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
    }

    public void splashRight(View view){
        Snackbar.make(view, "А ты шаришь", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
    }

    public void splashWrong(View view){
        Snackbar.make(view, "Вот тут ты не прав, дружок!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
