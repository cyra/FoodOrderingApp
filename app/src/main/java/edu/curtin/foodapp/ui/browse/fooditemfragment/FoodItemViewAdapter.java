package edu.curtin.foodapp.ui.browse.fooditemfragment;

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
import edu.curtin.foodapp.model.cart.CartItem;
import edu.curtin.foodapp.model.cart.CartItemList;
import edu.curtin.foodapp.model.fooditem.FoodItem;

public class FoodItemViewAdapter extends RecyclerView.Adapter<FoodItemViewHolder> {
    private final Context context;
    private SingleFoodBinding binding;

    private final ArrayList<FoodItem> foodItems;
    private CartItemList cart;

    public FoodItemViewAdapter(Context context, ArrayList<FoodItem> foodItems) {
        this.context = context;
        this.foodItems = foodItems;

        this.cart = new CartItemList();
        cart.load(context);
    }

    @NonNull
    @Override
    public FoodItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = SingleFoodBinding.inflate(LayoutInflater.from(context), parent, false);
        return new FoodItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodItemViewHolder holder, int position) {
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
                } else {
                    // If item already exists in cart, increase quantity
                    item = cart.getCartItemByID(id);
                    item.increaseQuantity();
                    cart.editCartItem(item);
                }

                Navigation.findNavController(view).navigate(R.id.action_navigation_browse_to_navigation_cart);
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
