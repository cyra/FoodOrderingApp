package edu.curtin.foodapp.ui.cart.cartfragment;

import edu.curtin.foodapp.MainActivity;
import edu.curtin.foodapp.databinding.SingleCartFoodItemBinding;
import edu.curtin.foodapp.model.cart.CartItem;
import edu.curtin.foodapp.model.cart.CartItemList;
import edu.curtin.foodapp.model.fooditems.FoodItem;
import edu.curtin.foodapp.model.fooditems.FoodItemList;
import edu.curtin.foodapp.model.restaurant.Restaurant;
import edu.curtin.foodapp.model.restaurant.RestaurantList;

import android.annotation.SuppressLint;
import android.content.Context;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;


public class CartListAdapter extends RecyclerView.Adapter<CartListViewHolder> {
    Context context;
    SingleCartFoodItemBinding binding;

    private CartItemList cart;
    private FoodItemList foodItemList;
    private RestaurantList restaurantList;

    private CartViewModel cartViewModel;

    public CartListAdapter(Context context, CartItemList cart, CartViewModel cartViewModel) {
        this.context = context;
        this.cart = cart;
        this.cartViewModel = cartViewModel;

        foodItemList = new FoodItemList();
        foodItemList.load(context);

        restaurantList = new RestaurantList();
        restaurantList.load(context);
    }

    @NonNull
    @Override
    public CartListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = SingleCartFoodItemBinding.inflate(LayoutInflater.from(context), parent, false);

        return new CartListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CartItem item = cart.getCartItem(position);
        FoodItem foodItem = foodItemList.getFoodItemByID(item.getID());
        Restaurant restaurant = restaurantList.getRestaurantByID(foodItem.getRestaurantRef());

        // Set food image
        holder.itemImg.setImageResource(getImage(foodItem.getImg()));
        // Set restaurant name
        holder.restaurantName.setText(restaurant.getName());
        // Set food name
        holder.itemName.setText(foodItemList.getFoodItemByID(item.getID()).getName());
        // Set price
        holder.itemPrice.setText(String.valueOf(item.getPrice()));
        // Set quantity
        holder.itemQuantity.setText(String.valueOf(item.getQuantity()));
        // Set total
        holder.itemTotal.setText(String.valueOf(item.getTotalPrice()));

        // Update checkout total
        cartViewModel.setTotalCart(cart.getCartTotalPrice());

        // Increase quantity button
        holder.plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Update cart database
                item.increaseQuantity();
                cart.editCartItem(item);

                // Update quantity
                holder.itemQuantity.setText(String.valueOf(item.getQuantity()));
                // Update total
                holder.itemTotal.setText(String.valueOf(item.getTotalPrice()));

                // Update checkout total
                cartViewModel.setTotalCart(cart.getCartTotalPrice());
            }
        });

        // Decrease quantity button
        holder.minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Update cart database
                item.decreaseQuantity();

                if (item.getQuantity() > 0) {
                    cart.editCartItem(item);

                    // Update quantity
                    holder.itemQuantity.setText(String.valueOf(item.getQuantity()));
                    // Update total
                    holder.itemTotal.setText(String.valueOf(item.getTotalPrice()));
                }
                else {
                    cart.removeCartItemByID(item.getID());
                    notifyDataSetChanged();
                }

                // Update checkout total
                cartViewModel.setTotalCart(cart.getCartTotalPrice());
            }
        });
    }


    public int getImage(String imageName) {
        int drawableResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        return drawableResourceId;
    }

    @Override
    public int getItemCount() {
        return cart.getSize();
    }
}
