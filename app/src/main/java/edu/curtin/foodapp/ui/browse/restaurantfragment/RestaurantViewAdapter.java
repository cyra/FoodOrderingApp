package edu.curtin.foodapp.ui.browse.restaurantfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.curtin.foodapp.databinding.SingleRestaurantBinding;
import edu.curtin.foodapp.model.restaurant.Restaurant;
public class RestaurantViewAdapter extends RecyclerView.Adapter<RestaurantViewHolder> {
    private final Context context;
    private SingleRestaurantBinding binding;
    ArrayList<Restaurant> restaurants;

    public RestaurantViewAdapter(Context context, ArrayList<Restaurant> restaurants){
        this.context = context;
        this.restaurants = restaurants;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = SingleRestaurantBinding.inflate(LayoutInflater.from(context),parent,false);
        return new RestaurantViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {

        holder.itemName.setText(restaurants.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }
}
