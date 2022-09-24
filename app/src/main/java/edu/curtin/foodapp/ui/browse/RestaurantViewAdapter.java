package edu.curtin.foodapp.ui.browse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.curtin.foodapp.databinding.SingleRestaurantBinding;
import edu.curtin.foodapp.model.restaurant.Restaurant;
public class RestaurantViewAdapter extends RecyclerView.Adapter<RestaurantViewHolder> {
    private Context context;
    private SingleRestaurantBinding binding;
    ArrayList<Restaurant> restaurants;

    public RestaurantViewAdapter(Context context, ArrayList<Restaurant> restaurants){
        this.context = context;
        this.restaurants = restaurants;
    }


    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
       // View view = layoutInflater.inflate(R.layout.single_restaurant,parent,false);
       // RestaurantViewHolder myViewHolder = new RestaurantViewHolder(view);
       // return myViewHolder;

        binding = SingleRestaurantBinding.inflate(LayoutInflater.from(context),parent,false);
        RestaurantViewHolder holder = new RestaurantViewHolder(binding);
        return holder;
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
