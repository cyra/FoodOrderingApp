package edu.curtin.foodapp.ui.browse.restaurantfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.curtin.foodapp.databinding.SingleRestaurantBinding;
import edu.curtin.foodapp.model.restaurant.Restaurant;

public class RestaurantViewAdapter extends RecyclerView.Adapter<RestaurantViewHolder> {
    private final Context context;
    private SingleRestaurantBinding binding;
    private final ArrayList<Restaurant> restaurantsList;

    public RestaurantViewAdapter(Context context, ArrayList<Restaurant> restaurants) {
        this.context = context;
        this.restaurantsList = restaurants;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = SingleRestaurantBinding.inflate(LayoutInflater.from(context), parent, false);
        return new RestaurantViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        // holder.itemName.setText(restaurants.getValue().get(position).getName());
        holder.itemName.setText(restaurantsList.get(position).getName());
        if (!restaurantsList.get(position).getImg().isEmpty()) {
            holder.itemImg.setImageResource(getImage(restaurantsList.get(position).getImg()));
        }
    }

    public int getImage(String imageName) {
        int drawableResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        return drawableResourceId;

    }

    @Override
    public int getItemCount() {
        return restaurantsList.size();
    }
}
