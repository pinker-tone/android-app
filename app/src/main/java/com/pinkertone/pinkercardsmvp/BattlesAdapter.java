package com.pinkertone.pinkercardsmvp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BattlesAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<BattleItem> objects;

    BattlesAdapter(Context context, ArrayList<BattleItem> products) {
        ctx = context;
        objects = products;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.battle_item, parent, false);
        }

        BattleItem p = getBattleItem(position);

        // заполняем View в пункте списка данными
        ((TextView) view.findViewById(R.id.enemyName)).setText(p.getEnemyName());
        ((TextView) view.findViewById(R.id.battleDate)).setText(p.getBattleDate());
        ((TextView) view.findViewById(R.id.subjectName)).setText(p.getSubjectName());
        ((TextView) view.findViewById(R.id.battleState)).setText(p.getBattleState());
        ((TextView) view.findViewById(R.id.battleScore)).setText(p.getBattleScore());

        return view;
    }

    // товар по позиции
    BattleItem getBattleItem(int position) {
        return ((BattleItem) getItem(position));
    }


}
