package com.example.ronzi.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity {

    ListView lvMain;
    SearchView svMain;
    DbManager dbManager;
    SearchArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMain= findViewById(R.id.lvMain);
        svMain= findViewById(R.id.svMain);

        dbManager=new DbManager(this);

        String[] songs=dbManager.GetAllSongs();

        adapter=new SearchArrayAdapter(this,
                R.layout.list_item, songs);
        lvMain.setAdapter(adapter);

        ConfigureSearchView();

        ConfigureListView();
    }

    private void ConfigureListView() {
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String)lvMain.getItemAtPosition(position);
                String songId= item.split(" ")[0];
                Intent intent = new Intent(parent.getContext(), SongActivity.class);
                intent.putExtra("songId", songId);
                startActivity(intent);
        }
        });
    }

    private void ConfigureSearchView() {
        svMain.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String text) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {

                adapter.getFilter().filter(text);

                return false;
            }
        });
    }
}
