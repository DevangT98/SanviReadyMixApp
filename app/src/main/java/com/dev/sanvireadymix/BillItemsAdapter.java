package com.dev.sanvireadymix;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.sanvireadymix.database.ProductModel;

import java.util.List;

public class BillItemsAdapter extends RecyclerView.Adapter<BillItemsAdapter.ViewHolder> {

    List<Model> cartItems;
    Context context;
    int counter;
    private int qty = 1;

    public BillItemsAdapter(List<Model> cartItems, Context context) {
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
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        ProductModel.open();
        cartItems = ProductModel.getCartItems();
        final Model model = cartItems.get(position);
        holder.productImage.setImageResource(model.getProductImage());
        holder.productName.setText(model.getProductName());
        holder.productPrice.setText(model.getProductPrice());

        holder.addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.removeItem.setClickable(true);
                counter++;
                holder.productQty.setText(String.valueOf(counter));
                Log.i("YAY", "QTY INCREASED: " + String.valueOf(counter));

                if (counter > 9) {
                    holder.productQty.setText("10");
                    Toast.makeText(v.getContext(), "Max Quantity Reached", Toast.LENGTH_SHORT).show();
                    holder.addItem.setClickable(false);
                    holder.removeItem.setClickable(true);
                } else {
                    holder.addItem.setClickable(true);
                }
                qty = counter;
                holder.subTotal.setText(String.valueOf(Integer.parseInt(model.getProductPrice()) * qty));

            }
        });

        holder.removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.addItem.setClickable(true);
                counter--;
                holder.productQty.setText(String.valueOf(counter));
                Log.i("YAY", "QTY DECREASED: " + String.valueOf(counter));
                if (counter <= 1) {
                    holder.productQty.setText("1");
                    counter = 1;
                    holder.removeItem.setClickable(false);
                    Toast.makeText(v.getContext(), "Min Quantity Reached", Toast.LENGTH_SHORT).show();

                } else {
                    holder.removeItem.setClickable(true);
                }

                qty = counter;
                holder.subTotal.setText(String.valueOf(Integer.parseInt(model.getProductPrice()) * qty));
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
        public ImageView productImage, addItem, removeItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.price);
            productQty = itemView.findViewById(R.id.quantity);
            subTotal = itemView.findViewById(R.id.subTotal);
            productImage = itemView.findViewById(R.id.productImage);
            addItem = itemView.findViewById(R.id.btnAddItem);
            removeItem = itemView.findViewById(R.id.btnRemoveItem);

        }
    }
}
