package com.example.ronzi.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
    }
}
