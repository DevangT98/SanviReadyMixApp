package com.dev.sanvireadymix.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dev.sanvireadymix.Model;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class ProductModel {
    public static DBHelper dbHelper;
    public static SQLiteDatabase database;

    public static DBHelper getInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = new DBHelper(context);

        }
        return dbHelper;
    }


    public static void open() {
        database = dbHelper.getWritableDatabase();
    }

    public static void close() {
        database.close();
    }


    public static void showAll() {

        String sql = "SELECT * FROM " + Keys.TB_NAME;
        Cursor cr = database.rawQuery(sql, null);

        while (cr.moveToNext()) {
            Log.i("YAY", "P_ID => " + cr.getInt(cr.getColumnIndex(Keys.P_ID)));
            Log.i("YAY", "P_IMAGE => " + cr.getString(cr.getColumnIndex(Keys.P_IMAGE)));
            Log.i("YAY", "P_NAME => " + cr.getString(cr.getColumnIndex(Keys.P_NAME)));
            Log.i("YAY", "P_PRICE=> " + cr.getString(cr.getColumnIndex(Keys.P_PRICE)));
            Log.i("YAY", "P_TYPE => " + cr.getString(cr.getColumnIndex(Keys.P_TYPE)));
            Log.i("YAY", "-------------------------------------------------------------------");

        }


    }

    public static void insert(String image, String name, String category, String price) {

        ContentValues cv = new ContentValues();
        cv.put(Keys.P_IMAGE, image);
        cv.put(Keys.P_NAME, name);
        cv.put(Keys.P_PRICE, price);
        cv.put(Keys.P_TYPE, category);
        database.insert(Keys.TB_NAME, null, cv);
        Log.i("DEV", "Values inserted successfully");
    }

    public static List<Model> getCartItems() {
        List<Model> cartItems = new ArrayList<>();
        String sql = "SELECT * FROM " + Keys.TB_NAME;
        Cursor cr = database.rawQuery(sql, null);
        String  name, price, category;
        int image;
        while (cr.moveToNext()) {
            image = Integer.parseInt(cr.getString(cr.getColumnIndex(Keys.P_IMAGE)));
            name = cr.getString(cr.getColumnIndex(Keys.P_NAME));
            price = cr.getString(cr.getColumnIndex(Keys.P_PRICE));
            category = cr.getString(cr.getColumnIndex(Keys.P_TYPE));

            cartItems.add(new Model(image, name, price, category));
        }
        return cartItems;

    }
}