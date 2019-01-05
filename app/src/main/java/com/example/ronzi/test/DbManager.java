package com.example.ronzi.test;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DbManager extends SQLiteAssetHelper{

    public static final int DbVersion = 1;
    public static final String DbName = "SongsDb.db";
    public static final String SongsTable = "Songs";

    public static final String SongNumber = "id";
    public static final String SongName = "name";
    public static final String SongText = "text";

    public DbManager(Context context) {
        super(context, DbName, null, DbVersion);
    }


    public String[] GetAllSongs() {
        SQLiteDatabase songsDb=getReadableDatabase();
        Cursor cursor = songsDb.query(DbManager.SongsTable, new String[]{SongNumber, SongName},
                null,null,null,null, SongNumber);
        String[] songs=arrayFromCursor(cursor);
        cursor.close();

        return songs;
    }

    private String[] arrayFromCursor(Cursor cursor) {

        int length = cursor.getCount();
        String[] array = new String[length];

        if (cursor.moveToFirst()) {
            for (int i = 0; i < length; i++) {
                array[i] = String.valueOf(cursor.getInt(cursor.getColumnIndex(SongNumber))) + " " +
                        cursor.getString(cursor.getColumnIndex(SongName));
                cursor.moveToNext();
            }
        }
        return array;
    }
}

