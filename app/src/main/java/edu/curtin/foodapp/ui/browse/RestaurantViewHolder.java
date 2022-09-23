package edu.curtin.foodapp.ui.browse;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.curtin.foodapp.databinding.SingleRestaurantBinding;

public class RestaurantViewHolder extends RecyclerView.ViewHolder {
    SingleRestaurantBinding binding;
    TextView itemName;
    Button itemButton;
    public RestaurantViewHolder(@NonNull SingleRestaurantBinding binding) {
        super(binding.getRoot());
        itemName = binding.restaurantName;
        this.binding = binding;
    }
}
