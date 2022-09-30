package edu.curtin.foodapp.ui.home.fooditemfragment;

import edu.curtin.foodapp.model.cart.CartItem;
import edu.curtin.foodapp.model.cart.CartItemList;

import android.annotation.SuppressLint;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.curtin.foodapp.R;
import edu.curtin.foodapp.databinding.SingleFoodBinding;
import edu.curtin.foodapp.model.fooditem.FoodItem;


public class DailyFoodItemListAdapter extends RecyclerView.Adapter<DailyFoodItemListViewHolder> {
    private final Context context;
    private SingleFoodBinding binding;

    private final ArrayList<FoodItem> foodItems;
    private CartItemList cart;


    public DailyFoodItemListAdapter(Context context, ArrayList<FoodItem> foodItems) {
        this.context = context;
        this.foodItems = foodItems;

        this.cart = new CartItemList();
        cart.load(context);
    }

    @NonNull
    @Override
    public DailyFoodItemListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = SingleFoodBinding.inflate(LayoutInflater.from(context), parent, false);
        return new DailyFoodItemListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyFoodItemListViewHolder holder, @SuppressLint("RecyclerView") int position) {
        FoodItem item = foodItems.get(position);

        holder.itemName.setText(item.getName());
        holder.itemDescription.setText(item.getDescription());
        holder.itemPrice.setText(String.valueOf(item.getPrice()));
        if (!item.getImg().isEmpty()) {
            holder.itemImg.setImageResource(getImage(item.getImg()));
        }
        else {
            // Show placeholder image
        }

        // Clicking item opens Cart
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = item.getID();
                double price = item.getPrice();
                CartItem item;

                if (cart.getCartItemByID(id) == null) {
                    item = new CartItem(id, price);
                    cart.addCartItem(item);
                }
                else {
                    // If item already exists in cart, increase quantity
                    item = cart.getCartItemByID(id);
                    item.increaseQuantity();
                    cart.editCartItem(item);
                }

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
