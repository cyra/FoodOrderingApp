package edu.curtin.foodapp.ui.browse.restaurantfragment;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import edu.curtin.foodapp.databinding.SingleRestaurantBinding;

public class RestaurantViewHolder extends RecyclerView.ViewHolder {
    SingleRestaurantBinding binding;
    TextView itemName;
    ImageView itemImg;
    CardView cardView;

    public RestaurantViewHolder(@NonNull SingleRestaurantBinding binding) {
        super(binding.getRoot());
        cardView = binding.cardClick;
        itemName = binding.restaurantName;
        itemImg = binding.restaurantImg;
        this.binding = binding;
    }
}
