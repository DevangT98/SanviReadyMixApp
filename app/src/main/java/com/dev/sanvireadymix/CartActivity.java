package com.dev.sanvireadymix;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.sanvireadymix.database.ProductModel;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    //  private List<Model> cartItems;
    RecyclerView recyclerView;
    BillItemsAdapter billItemsAdapter;
    TextView totalBill, subtotalbill;
    int subTotal;
    String total;
    SharedPreferences sp;
    Button checkOutButton, continueShoppingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        sp = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        totalBill = findViewById(R.id.totalBill);
        subtotalbill = findViewById(R.id.subtotalBill);
        ProductModel.getInstance(getApplicationContext());
        ProductModel.open();
        final List<CartItems> cartItems = ProductModel.getCartItems();
        Log.i("YAY", "CART SIZE: " + cartItems.size());
        ProductModel.close();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        billItemsAdapter = new BillItemsAdapter(cartItems, getApplicationContext());
        recyclerView.setAdapter(billItemsAdapter);

        checkOutButton = findViewById(R.id.checkOutButton);
        continueShoppingButton = findViewById(R.id.continueShoppingButton);
        for (int i = 0; i < cartItems.size(); i++) {
            int qty = Integer.parseInt(cartItems.get(i).getProductQty());
            int price = Integer.parseInt(cartItems.get(i).getProductPrice());
            subTotal = subTotal + price * qty;
        }

        subtotalbill.setText(String.valueOf(subTotal));
        total = String.format("%.1f", subTotal + (subTotal * 2.8 / 100));
        totalBill.setText(total);

        continueShoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CartActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
       /* Intent i = getIntent();
        finish();
        startActivity(i);*/
//        total = sp.getString("total","");
//        totalBill.setText(total);
/*        ProductModel.open();
        cartItems = ProductModel.getCartItems();
        Log.i("YAY", "CART ITEMS" + cartItems.get(0).getProductImage());
        Log.i("YAY", "CART ITEMS" + cartItems.get(0).getProductName());
        Log.i("YAY", "CART ITEMS" + cartItems.get(0).getProductPrice());
        Log.i("YAY", "CART ITEMS" + cartItems.get(0).getProductCategory());
        ProductModel.close();*/
    }
}
