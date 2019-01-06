package com.example.ronzi.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SongActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        textView=findViewById(R.id.textView);
        Intent intent = getIntent();
        String songId=intent.getStringExtra("songId");
        textView.setText(songId);


    }
}
