package com.dev.sanvireadymix;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.sanvireadymix.database.ProductModel;

import java.util.List;

public class BillItemsAdapter extends RecyclerView.Adapter<BillItemsAdapter.ViewHolder> {

    List<CartItems> cartItems;
    Context context;
    //    int counter;
    private int qty = 1;
    int total = 0;

    public BillItemsAdapter(List<CartItems> cartItems, Context context) {
        this.cartItems = cartItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        ProductModel.open();
        cartItems = ProductModel.getCartItems();
        final CartItems model = cartItems.get(position);
        holder.productImage.setImageResource(model.getProductImage());
        holder.productName.setText(model.getProductName());
        holder.productPrice.setText(model.getProductPrice());
        holder.productQty.setText(model.getProductQty());
        holder.subTotal.setText("0");
//        counter = Integer.parseInt(model.getProductQty());
        holder.addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int addCounter = Integer.parseInt(holder.productQty.getText().toString().trim());
                holder.removeItem.setClickable(true);
                addCounter++;
                holder.productQty.setText(String.valueOf(addCounter));
                Log.i("YAY", "QTY INCREASED: " + String.valueOf(addCounter));

                if (addCounter > 9) {
                    holder.productQty.setText("10");
                    Toast.makeText(v.getContext(), "Max Quantity Reached", Toast.LENGTH_SHORT).show();
                    holder.addItem.setClickable(false);
                    holder.removeItem.setClickable(true);
                } else {
                    holder.addItem.setClickable(true);
                }
                qty = addCounter;
                holder.subTotal.setText(String.valueOf(Integer.parseInt(model.getProductPrice()) * qty));
                total = total + Integer.parseInt(holder.subTotal.getText().toString().trim());
                Log.i("YAY", "TOTAL PRICE: " + String.valueOf(total));
            }
        });

        holder.removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int subCounter = Integer.parseInt(holder.productQty.getText().toString().trim());
                holder.addItem.setClickable(true);
                subCounter--;
                holder.productQty.setText(String.valueOf(subCounter));
                Log.i("YAY", "QTY DECREASED: " + String.valueOf(subCounter));
                if (subCounter <= 1) {
                    holder.productQty.setText("1");
                    subCounter = 1;
                    holder.removeItem.setClickable(false);
                    Toast.makeText(v.getContext(), "Min Quantity Reached", Toast.LENGTH_SHORT).show();

                } else {
                    holder.removeItem.setClickable(true);
                }

                qty = subCounter;
                holder.subTotal.setText(String.valueOf(Integer.parseInt(model.getProductPrice()) * qty));
                total = total - Integer.parseInt(holder.subTotal.getText().toString().trim());
                Log.i("YAY", "TOTAL PRICE: " + String.valueOf(total));
            }
        });

        holder.removeFromCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ProductModel.open();
                ProductModel.deleteItem(cartItems.get(position).getProductId());
                ProductModel.close();
                cartItems.remove(cartItems.get(position));
                notifyItemRemoved(position);
            }
        });


        ProductModel.close();
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView productName, productPrice, productQty, subTotal;
        public ImageView productImage, addItem, removeItem, removeFromCart;
        Button checkOutButton, continueShoppingButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.price);
            productQty = itemView.findViewById(R.id.quantity);
            subTotal = itemView.findViewById(R.id.subTotal);
            productImage = itemView.findViewById(R.id.productImage);
            addItem = itemView.findViewById(R.id.btnAddItem);
            removeItem = itemView.findViewById(R.id.btnRemoveItem);
            removeFromCart = itemView.findViewById(R.id.btnRemoveFromCart);
            checkOutButton = itemView.findViewById(R.id.checkOutButton);
            continueShoppingButton = itemView.findViewById(R.id.continueShoppingButton);
        }
    }
}
