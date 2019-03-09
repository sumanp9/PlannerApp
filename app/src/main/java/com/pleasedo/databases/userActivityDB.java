package com.pleasedo.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class userActivityDB extends SQLiteOpenHelper {
    private static final  int DATABASE_VERSION =2;
    private static final String DATABASE_NAME = ".db";

    public userActivityDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, String dbName) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
