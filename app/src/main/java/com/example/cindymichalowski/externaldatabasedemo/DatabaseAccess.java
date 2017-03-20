package com.example.cindymichalowski.externaldatabasedemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cindymichalowski on 3/20/17.
 */

public class DatabaseAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    // private constructor ro avoid object creation from outside classes
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    // return a singleton instance of DatabaseAccess
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    // open the database connection
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    // close the database connection
    public void close() {
        this.database.close();
    }

    // read all quotes from the database
    public List<String> getQuotes() {
        List<String> list = new ArrayList<String>();
        Cursor cursor = database.rawQuery("SELECT * FROM LEMONICEKING", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}
