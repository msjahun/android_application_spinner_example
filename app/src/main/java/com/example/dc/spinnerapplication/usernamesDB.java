package com.example.dc.spinnerapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class usernamesDB extends SQLiteOpenHelper {


    private static  final String _db="usernamesDB";
    private static final int version=2;

    public usernamesDB(Context context) {
        super(context, _db, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usernames_table(username TEXT, password TEXT, gender TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usernames_table");
        db.execSQL("CREATE TABLE usernames_table(username TEXT, password TEXT, gender TEXT);");
    }
}
