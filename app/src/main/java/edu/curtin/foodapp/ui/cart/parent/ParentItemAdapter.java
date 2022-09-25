package edu.curtin.foodapp.ui.cart.parent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import edu.curtin.foodapp.databinding.SingleCartRestaurantBinding;
import edu.curtin.foodapp.model.orders.RestaurantOrder;
import edu.curtin.foodapp.ui.cart.child.ChildItemAdapter;

import java.util.ArrayList;

public class ParentItemAdapter extends RecyclerView.Adapter<ParentViewHolder> {

    // An object of RecyclerView.RecycledViewPool is created to share the Views between the child and the parent RecyclerViews
    private SingleCartRestaurantBinding binding;
    private final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private final ArrayList<RestaurantOrder> restaurants;
    private final Context context;

    public ParentItemAdapter(Context context, ArrayList<RestaurantOrder> restaurants) {
        this.context = context;
        this.restaurants = restaurants;
    }

    @NonNull
    @Override
    public ParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = SingleCartRestaurantBinding.inflate(LayoutInflater.from(context), parent, false);

        return new ParentViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull ParentViewHolder parentViewHolder, int position) {

        RestaurantOrder restaurant = restaurants.get(position);

        // Create a layout manager for the reyclerview
        LinearLayoutManager layoutManager = new LinearLayoutManager(parentViewHolder.ChildRecyclerView.getContext());

        // Define child item prefetch size
        layoutManager.setInitialPrefetchItemCount(restaurant.getOrderItems().size());

        // Create instance of child item adapter and set layout
        ChildItemAdapter childItemAdapter = new ChildItemAdapter(context,restaurant.getOrderItems());
        parentViewHolder.ParentItemTitle.setText(restaurant.getRestaurantName());
        parentViewHolder.ChildRecyclerView.setLayoutManager(layoutManager);
        parentViewHolder.ChildRecyclerView.setAdapter(childItemAdapter);
        parentViewHolder.ChildRecyclerView.setRecycledViewPool(viewPool);


    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }


}