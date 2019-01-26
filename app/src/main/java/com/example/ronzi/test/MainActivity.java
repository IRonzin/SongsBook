package com.example.ronzi.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    ListView lvMain;
    SearchView svMain;
    DbManager blessSongsDbManager;
    DbManager youngSongsDbManager;
    SearchArrayAdapter adapter;
    SongsBooksSet connectedSongsBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMain= findViewById(R.id.lvMain);
        svMain= findViewById(R.id.svMain);

        blessSongsDbManager =new DbManager(this, getText(R.string.BlessSongsDb).toString());
        youngSongsDbManager = new DbManager(this, getText(R.string.YoungSongsDb).toString());

        SetConnectedSongsBook(SongsBooksSet.YoungSongsBook);

        ConfigureSearchView();

        ConfigureListView();
    }

    private void UpdateActivity() {
        String[] songs;
        switch (connectedSongsBook) {
            case YoungSongsBook:  songs = youngSongsDbManager.GetAllSongs();
                setTitle(R.string.YoungSongs);
                break;
            case BlessSongsBook:  songs = blessSongsDbManager.GetAllSongs();
                setTitle(R.string.BlessSongs);
                break;
            default: songs= new String[]{};
                break;
            }

        adapter=new SearchArrayAdapter(this,
                R.layout.list_item, songs);
        adapter.getFilter().filter(svMain.getQuery());
        lvMain.setAdapter(adapter);

    }

    private String GetSongTextById(String songId)
    {
        String songText;
        switch (connectedSongsBook) {
            case YoungSongsBook:  songText = youngSongsDbManager.GetSongTextById(songId);
                setTitle(R.string.YoungSongs);
                break;
            case BlessSongsBook:  songText = blessSongsDbManager.GetSongTextById(songId);
                setTitle(R.string.BlessSongs);
                break;
            default: songText= "";
                break;
        }
        return songText;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.YoungSongs:
                SetConnectedSongsBook(SongsBooksSet.YoungSongsBook);
                return true;
            case R.id.BlessSongs:
                SetConnectedSongsBook(SongsBooksSet.BlessSongsBook);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void SetConnectedSongsBook(SongsBooksSet songsBook)
    {
        if(connectedSongsBook==songsBook)
            return;

        connectedSongsBook=songsBook;
        UpdateActivity();
    }
    private void ConfigureListView() {
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String)lvMain.getItemAtPosition(position);
                String songId= item.split(" ")[0];
                Intent intent = new Intent(parent.getContext(), SongActivity.class);
                intent.putExtra("songTitle", item);
                intent.putExtra("songText", GetSongTextById(songId));
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
