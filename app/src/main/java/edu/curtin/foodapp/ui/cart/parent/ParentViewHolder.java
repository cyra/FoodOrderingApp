package edu.curtin.foodapp.ui.cart.parent;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.curtin.foodapp.databinding.SingleCartRestaurantBinding;
import edu.curtin.foodapp.databinding.SingleRestaurantBinding;

// Initialise parent (restaurant list) recyclerview
class ParentViewHolder
        extends RecyclerView.ViewHolder {

    SingleCartRestaurantBinding binding;
    TextView ParentItemTitle;
    RecyclerView ChildRecyclerView;

    public ParentViewHolder(@NonNull SingleCartRestaurantBinding binding) {
        super(binding.getRoot());
        ParentItemTitle = binding.restaurantName;
        ChildRecyclerView = binding.childRecyclerView;
        this.binding = binding;

    }
}