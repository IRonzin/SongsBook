package com.example.ronzi.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

public class SongActivity extends AppCompatActivity {

    ScrollView scrollView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        scrollView=findViewById(R.id.songScrollView);
        textView=findViewById(R.id.songTextView);
        Intent intent = getIntent();
        setTitle(intent.getStringExtra("songTitle"));
        textView.setText(intent.getStringExtra("songText"));


    }
}
