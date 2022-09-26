package edu.curtin.foodapp.ui.cart.cartfragment;

import edu.curtin.foodapp.databinding.SingleCartFoodItemBinding;
import edu.curtin.foodapp.model.cart.CartItem;
import android.content.Context;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CartListAdapter extends RecyclerView.Adapter<CartListViewHolder> {
    Context context;
    SingleCartFoodItemBinding binding;
    ArrayList<CartItem> cartItems;
    //CartItemList cart = new CartItemList();


    public CartListAdapter(Context context, ArrayList<CartItem> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = SingleCartFoodItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new CartListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListViewHolder holder, int position) {
        holder.itemName.setText(cartItems.get(position).getName());
        System.out.println("name set");
        holder.itemQuantity.setText(Double.toString(cartItems.get(position).getQuantity()));
        holder.itemPrice.setText(String.valueOf(cartItems.get(position).getPrice()));
        // If empty show placeholder
        if (!cartItems.get(position).getImg().isEmpty()) {
            holder.itemImg.setImageResource(getImage(cartItems.get(position).getImg()));
        }

    }


    public int getImage(String imageName) {
        int drawableResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        return drawableResourceId;
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }
}
