package com.dev.sanvireadymix.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dev.sanvireadymix.CartItems;
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
        String cartsql = "SELECT * FROM " + Keys.CART_TABLE;
        Cursor cr = database.rawQuery(sql, null);
        Cursor cr1 = database.rawQuery(cartsql, null);
        while (cr.moveToNext()) {
            Log.i("YAY", "P_ID => " + cr.getInt(cr.getColumnIndex(Keys.P_ID)));
            Log.i("YAY", "P_IMAGE => " + cr.getString(cr.getColumnIndex(Keys.P_IMAGE)));
            Log.i("YAY", "P_NAME => " + cr.getString(cr.getColumnIndex(Keys.P_NAME)));
            Log.i("YAY", "P_PRICE=> " + cr.getString(cr.getColumnIndex(Keys.P_PRICE)));
            Log.i("YAY", "P_TYPE => " + cr.getString(cr.getColumnIndex(Keys.P_TYPE)));
            Log.i("YAY", "-------------------------------------------------------------------");

        }

        while (cr1.moveToNext()) {
            Log.i("YAY", "Product_ID => " + cr1.getInt(cr1.getColumnIndex(Keys.Product_ID)));
            Log.i("YAY", "Product_IMAGE => " + cr1.getString(cr1.getColumnIndex(Keys.Product_IMAGE)));
            Log.i("YAY", "Product_NAME => " + cr1.getString(cr1.getColumnIndex(Keys.Product_NAME)));
            Log.i("YAY", "Product_PRICE=> " + cr1.getString(cr1.getColumnIndex(Keys.Product_PRICE)));
            Log.i("YAY", "Product_TYPE => " + cr1.getString(cr1.getColumnIndex(Keys.Product_TYPE)));
            Log.i("YAY", "Product_Qty => " + cr1.getString(cr1.getColumnIndex(Keys.Product_QUANTITY)));
            Log.i("YAY", "*************************************************************************");

        }


    }

    public static void insert(String image, String name, String category, String price, String quantity) {

        ContentValues cv = new ContentValues();
        cv.put(Keys.Product_IMAGE, image);
        cv.put(Keys.Product_NAME, name);
        cv.put(Keys.Product_PRICE, price);
        cv.put(Keys.Product_TYPE, category);
        cv.put(Keys.Product_QUANTITY, quantity);
        database.insert(Keys.CART_TABLE, null, cv);
        Log.i("DEV", "Values inserted successfully");
    }

    public static List<CartItems> getCartItems() {
        List<CartItems> cartItems = new ArrayList<>();
        String sql = "SELECT * FROM " + Keys.CART_TABLE;
        Cursor cr = database.rawQuery(sql, null);
        String name, price, category, quantity;
        int image,id;
        while (cr.moveToNext()) {
            id = Integer.parseInt(cr.getString(cr.getColumnIndex(Keys.Product_ID)));
            image = Integer.parseInt(cr.getString(cr.getColumnIndex(Keys.Product_IMAGE)));
            name = cr.getString(cr.getColumnIndex(Keys.Product_NAME));
            price = cr.getString(cr.getColumnIndex(Keys.Product_PRICE));
            category = cr.getString(cr.getColumnIndex(Keys.Product_TYPE));
            quantity = cr.getString(cr.getColumnIndex(Keys.Product_QUANTITY));
            cartItems.add(new CartItems(id,image, name, category, price, quantity));
        }
        return cartItems;

    }

    public static  List<String> cartItemNames(){
        List<String> cartItemName = new ArrayList<>();
        String sql = "SELECT "+Keys.Product_NAME+ " FROM "+Keys.CART_TABLE;
        Cursor cr = database.rawQuery(sql, null);
        String name;
        while (cr.moveToNext()){
            name = cr.getString(cr.getColumnIndex(Keys.Product_NAME));
            Log.i("HELLO","PRODUCT NAME : "+ name);
            cartItemName.add(name);
        }
        return  cartItemName;

    }

    public static void deleteItem(int productId) {

        String sql = "DELETE FROM " + Keys.CART_TABLE + " WHERE " + Keys.Product_ID+ "=" + productId;
        database.execSQL(sql);
        Log.i("YAY","ITEM REMOVED: "+productId);
    }

    public static void updateQuantity(int addCounter, int id) {

        String sql = "UPDATE "+Keys.CART_TABLE + " SET "+ Keys.Product_QUANTITY+ "="+String.valueOf(addCounter) +" WHERE " + Keys.Product_ID +"="+String.valueOf(id);
        database.execSQL(sql);
        Log.i("HELLO","UPDATED SUCCESSFULLY");

    }
}