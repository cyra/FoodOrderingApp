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
    ImageView itemImg;
    CardView cardView;

    public CartListViewHolder(@NonNull SingleCartFoodItemBinding binding) {
        super(binding.getRoot());
        cardView = binding.cardClick;
        itemName = binding.foodName;
        itemPrice = binding.foodPrice;
        itemImg = binding.foodImg;
        itemQuantity = binding.quantityNum;
        this.binding = binding;
    }
}
