package com.example.ronzi.test;

import android.app.IntentService;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ListView lvMain;
    DbManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMain= findViewById(R.id.lvMain);

        dbManager=new DbManager(this);

        String[] songs=dbManager.GetAllSongs();

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
                R.layout.list_item, songs);
        lvMain.setAdapter(adapter);

        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView item = (TextView)lvMain.getItemAtPosition(position);
                String songId=item.getText().toString().split(" ")[0];
                Intent intent = new Intent(parent.getContext(), SongActivity.class);
                intent.putExtra("songId", songId);
                startActivity(intent);
        }
        });
    }
}
