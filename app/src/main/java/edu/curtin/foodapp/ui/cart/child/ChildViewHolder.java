package edu.curtin.foodapp.ui.cart.child;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.curtin.foodapp.databinding.SingleCartFoodItemBinding;

class ChildViewHolder
        extends RecyclerView.ViewHolder {

    TextView ChildItemTitle;
    TextView ChildItemPrice;
    TextView ChildItemQuantity;
    SingleCartFoodItemBinding binding;

    ChildViewHolder(@NonNull SingleCartFoodItemBinding binding) {
        super(binding.getRoot());
        ChildItemTitle = binding.foodName;
        ChildItemPrice = binding.foodPrice;
        ChildItemQuantity = binding.quantityNum;

        this.binding = binding;
    }
}
