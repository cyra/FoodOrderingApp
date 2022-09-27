package edu.curtin.foodapp.ui.cart.cartfragment;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import edu.curtin.foodapp.databinding.SingleCartFoodItemBinding;

public class CartListViewHolder extends RecyclerView.ViewHolder {
    SingleCartFoodItemBinding binding;
    TextView itemName;
    TextView itemPrice;
    TextView itemQuantity;
    TextView restaurantName;
    TextView itemTotal;
    ImageView itemImg;
    CardView minusButton;
    CardView plusButton;
    CardView cardView;

    public CartListViewHolder(@NonNull SingleCartFoodItemBinding binding) {
        super(binding.getRoot());
        cardView = binding.cardClick;
        itemName = binding.foodName;
        itemPrice = binding.foodPrice;
        itemImg = binding.foodImg;
        itemQuantity = binding.quantityNum;
        itemTotal = binding.totalPrice;
        restaurantName = binding.restoName;
        minusButton = binding.minus;
        plusButton = binding.add;

        this.binding = binding;
    }
}
