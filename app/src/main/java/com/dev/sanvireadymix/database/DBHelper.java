package com.dev.sanvireadymix.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    String create_table = "create table " + Keys.TB_NAME + "(" + Keys.P_ID + " integer primary key autoincrement," + Keys.P_IMAGE+ " text not null,"+ Keys.P_NAME + " text not null,"
            + Keys.P_PRICE + " text not null," + Keys.P_TYPE + " text not null)";


    String create_cart_table = "create table " + Keys.CART_TABLE + "(" + Keys.Product_ID + " integer primary key autoincrement," + Keys.Product_IMAGE+ " text not null,"+ Keys.Product_NAME+ " text not null,"
            + Keys.Product_PRICE+ " text not null,"+ Keys.Product_QUANTITY+ " text not null," + Keys.Product_TYPE+ " text not null)";

    public DBHelper(Context context) {
        super(context, Keys.DB_NAME, null, Keys.DB_VERSION);
//        Log.i("YAY", create_table);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("YAY", create_table);
        Log.i("YAY", create_cart_table);
        db.execSQL(create_table);
        db.execSQL(create_cart_table);
        Log.i("YAY", "table created successfully");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

