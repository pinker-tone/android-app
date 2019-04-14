package com.pinkertone.pinkercardsmvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BattlesActivity extends AppCompatActivity {

    private String[] name = {
            "Иван", "Марья", "Петр", "Антон", "Даша",
            "Борис", "Костя", "Игорь", "Иван", "Марья",
            "Петр", "Антон", "Даша", "Борис", "Костя"
    };
    private String[] date = {
            "22/1/56", "34/23/542", "03/53/345", "22/1/56", "34/23/542",
            "03/53/345", "22/1/56", "34/23/542", "22/1/56", "34/23/542",
            "03/53/345", "22/1/56", "34/23/542", "03/53/345", "22/1/56"
    };
    private String[] subject = {
            "Math", "История", "Укрмова", "География", "Физика",
            "Информатика", "Английский", "Биология", "Math", "История",
            "Укрмова", "География", "Физика", "Информатика", "Английский"
    };
    private byte[] state = {
            0, 3, 1, 2, 3,
            1, 1, 2, 3, 0,
            3, 1, 2, 3, 1
    };
    private String[] score = {
            "4:2", "3:2", "2:0", "5:1", "4:4",
            "3:1", "1:2", "3:2", "4:2", "3:2",
            "2:0", "5:1", "4:4", "3:1", "1:2"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battles);

        LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);

        LayoutInflater ltInflater = getLayoutInflater();

        int colorWaiting = getResources().getColor(R.color.colorWaiting);
        int colorWin = getResources().getColor(R.color.colorWin);
        int colorDefeat = getResources().getColor(R.color.colorDefeat);
        int colorDraw = getResources().getColor(R.color.colorDraw);

        for (int i = 0; i < name.length; i++) {
            View battle_item = ltInflater.inflate(R.layout.battle_item, linLayout, false);

            TextView enemyName = (TextView) battle_item.findViewById(R.id.enemyName);
            enemyName.setText(name[i]);
            TextView battleDate = (TextView) battle_item.findViewById(R.id.battleDate);
            battleDate.setText(date[i]);
            TextView subjectName = (TextView) battle_item.findViewById(R.id.subjectName);
            subjectName.setText(subject[i]);
            TextView battleState = (TextView) battle_item.findViewById(R.id.battleState);
            TextView battleScore = (TextView) battle_item.findViewById(R.id.battleScore);

            switch (state[i]) {
                case 0:
                    battleState.setText("Ожидание");
                    battleState.setBackgroundColor(colorWaiting);
                    break;
                case 1:
                    battleState.setText("Победа");
                    battleState.setBackgroundColor(colorWin);
                    break;
                case 2:
                    battleState.setText("Поражение");
                    battleState.setBackgroundColor(colorDefeat);
                    break;
                case 3:
                    battleState.setText("Ничья");
                    battleState.setBackgroundColor(colorDraw);
                    break;
            }

            battleScore.setText(score[i]);

            linLayout.addView(battle_item);
        }

    }
}
