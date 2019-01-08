package com.example.ronzi.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

public class SongActivity extends AppCompatActivity {

    ScrollView scrollView;
    TextView textView;
    DbManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        dbManager=new DbManager(this);

        scrollView=findViewById(R.id.songScrollView);
        textView=findViewById(R.id.songTextView);
        Intent intent = getIntent();
        String songId=intent.getStringExtra("songId");
        textView.setText(dbManager.GetSongTextById(songId));


    }
}
