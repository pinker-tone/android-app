package com.pinkertone.pinkercardsmvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RatingActivity extends AppCompatActivity {

    String[] usersRating = {
            "1     Let45fc        192",
            "2     Umbrien        153",
            "3     Askobio        124",
            "4     Borjomi         96",
            "5     Citizen         81",
            "6     Donald          13",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        ListView ratingLV = (ListView) findViewById(R.id.ratingLV);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, usersRating);
        ratingLV.setAdapter(adapter);
    }
}
