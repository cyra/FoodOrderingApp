package edu.curtin.foodapp.ui.browse.fooditemfragment;

import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.curtin.foodapp.databinding.SingleFoodBinding;

public class FoodItemViewHolder extends RecyclerView.ViewHolder {
    SingleFoodBinding binding;
    TextView itemName;
    TextView itemDescription;
    TextView itemPrice;
    ImageView itemImg;
    public FoodItemViewHolder(@NonNull SingleFoodBinding binding) {
        super(binding.getRoot());
        itemName = binding.foodName;
        itemDescription = binding.foodDescription;
        itemPrice = binding.foodPrice;
        itemImg = binding.foodImg;
        this.binding = binding;
    }
}
