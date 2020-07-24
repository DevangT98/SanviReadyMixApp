package com.dev.sanvireadymix;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.sanvireadymix.database.ProductModel;

import java.util.List;

public class CartActivity extends AppCompatActivity {

//  private List<Model> cartItems;
    RecyclerView recyclerView;
    BillItemsAdapter billItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ProductModel.getInstance(getApplicationContext());
        ProductModel.open();
        final List<Model> cartItems = ProductModel.getCartItems();
        Log.i("YAY","CART SIZE: "+cartItems.size());
        ProductModel.close();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new  LinearLayoutManager(getApplicationContext()));
        billItemsAdapter = new BillItemsAdapter(cartItems,getApplicationContext());
        recyclerView.setAdapter(billItemsAdapter);






/*        ProductModel.open();
        cartItems = ProductModel.getCartItems();
        Log.i("YAY", "CART ITEMS" + cartItems.get(0).getProductImage());
        Log.i("YAY", "CART ITEMS" + cartItems.get(0).getProductName());
        Log.i("YAY", "CART ITEMS" + cartItems.get(0).getProductPrice());
        Log.i("YAY", "CART ITEMS" + cartItems.get(0).getProductCategory());
        ProductModel.close();*/
    }
}
