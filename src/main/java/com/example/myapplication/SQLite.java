package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLite extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "A5_db.db";
    public static final String TABLE_NAME = "manager";
    public static final String id = "ID";
    public static final String item = "ITEM";
    public static final String date = "DATE";
    public static final String price = "PRICE";
    public SQLite(Context context){
        super(context, DATABASE_NAME, null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, ITEM varchar(250), DATE varchar(250), PRICE DOUBLE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int NewVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean createTransaction(sample sample){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(item, sample.aItem);
        contentValues.put(date, sample.aDate);
        contentValues.put(price, sample.aPrice);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) { return false; }
        return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return result;
    }
}