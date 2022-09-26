package edu.curtin.foodapp.ui.browse.fooditemfragment;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import edu.curtin.foodapp.databinding.SingleFoodBinding;

public class FoodItemViewHolder extends RecyclerView.ViewHolder {
    public SingleFoodBinding binding;
    public TextView itemName;
    TextView itemDescription;
    TextView itemPrice;
    ImageView itemImg;
    CardView cardView;
    public FoodItemViewHolder(@NonNull SingleFoodBinding binding) {
        super(binding.getRoot());
        cardView = binding.cardClick;
        itemName = binding.foodName;
        itemDescription = binding.foodDescription;
        itemPrice = binding.foodPrice;
        itemImg = binding.foodImg;
        this.binding = binding;
    }
}
