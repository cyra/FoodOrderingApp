package edu.curtin.foodapp.ui.browse.fooditemfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.curtin.foodapp.databinding.SingleFoodBinding;
import edu.curtin.foodapp.model.fooditems.FoodItem;

public class FoodItemViewAdapter extends RecyclerView.Adapter<FoodItemViewHolder> {
    private final Context context;
    private SingleFoodBinding binding;
    ArrayList<FoodItem> foodItems;

    public FoodItemViewAdapter(Context context, ArrayList<FoodItem> foodItems){
        this.context = context;
        this.foodItems = foodItems;
    }

    @NonNull
    @Override
    public FoodItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = SingleFoodBinding.inflate(LayoutInflater.from(context),parent,false);
        return new FoodItemViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull FoodItemViewHolder holder, int position) {

        holder.itemName.setText(foodItems.get(position).getName());
        holder.itemDescription.setText(foodItems.get(position).getDescription());
        holder.itemPrice.setText(String.valueOf(foodItems.get(position).getPrice()));

    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }
}
