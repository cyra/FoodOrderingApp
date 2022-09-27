package edu.curtin.foodapp.ui.home.fooditemfragment;

import edu.curtin.foodapp.database.fooditems.FoodItemsDBHelper;
import edu.curtin.foodapp.model.cart.CartItem;
import edu.curtin.foodapp.model.cart.CartItemList;
import edu.curtin.foodapp.model.fooditems.FoodItemList;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.curtin.foodapp.R;
import edu.curtin.foodapp.databinding.SingleFoodBinding;
import edu.curtin.foodapp.model.fooditems.FoodItem;


public class DailyFoodItemListAdapter extends RecyclerView.Adapter<DailyFoodItemListViewHolder> {
    private final Context context;
    private SingleFoodBinding binding;
    private final ArrayList<FoodItem> foodItems;
    private CartItemList cart;


    public DailyFoodItemListAdapter(Context context, ArrayList<FoodItem> foodItems) {
        this.context = context;
        this.foodItems = foodItems;
    }

    @NonNull
    @Override
    public DailyFoodItemListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = SingleFoodBinding.inflate(LayoutInflater.from(context), parent, false);
        return new DailyFoodItemListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyFoodItemListViewHolder holder, int position) {
        holder.itemName.setText(foodItems.get(position).getName());
        holder.itemDescription.setText(foodItems.get(position).getDescription());
        holder.itemPrice.setText(String.valueOf(foodItems.get(position).getPrice()));
        // If empty show placeholder
        if (!foodItems.get(position).getImg().isEmpty()) {
            holder.itemImg.setImageResource(getImage(foodItems.get(position).getImg()));
        }

        // onclick item opens cart fragment
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = holder.itemName.getText().toString();
                double price = Double.parseDouble(holder.itemPrice.getText().toString());
                // save into database
                // load context first before adding
                CartItemList cart = new CartItemList();
                cart.load(view.getContext());
                int foodId = foodItems.get(position).getID();
                String foodName = foodItems.get(position).getName();
                double foodPrice = foodItems.get(position).getPrice();
                String foodImg = foodItems.get(position).getImg();
                int restaurantRef = foodItems.get(position).getRestaurantRef();
                System.out.println("added food item " + title + " " + price);
                CartItem newCartItem = new CartItem(foodId, foodName, "", foodPrice, foodImg, restaurantRef, 1, 1, foodPrice);
                cart.addORUpdateItem(newCartItem);
                Navigation.findNavController(view).navigate(R.id.action_navigation_home_to_navigation_cart);
            }
        });
    }


    public int getImage(String imageName) {
        int drawableResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        return drawableResourceId;
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }
}
