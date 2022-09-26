package edu.curtin.foodapp.ui.cart.parent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import edu.curtin.foodapp.databinding.SingleCartRestaurantBinding;
import edu.curtin.foodapp.model.cart.CartItem;
import edu.curtin.foodapp.model.cart.CartItemList;
import edu.curtin.foodapp.model.restaurant.RestaurantList;
import edu.curtin.foodapp.ui.cart.child.ChildItemAdapter;

import java.util.ArrayList;

public class ParentItemAdapter extends RecyclerView.Adapter<ParentViewHolder> {

    // An object of RecyclerView.RecycledViewPool is created to share the Views between the child and the parent RecyclerViews
    private SingleCartRestaurantBinding binding;
    private final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private final ArrayList<CartItem> cartItems;
    private final Context context;
    RestaurantList restaurantList = new RestaurantList();
    CartItemList cartItemList = new CartItemList();


    public ParentItemAdapter(Context context, ArrayList<CartItem> restaurants) {
        this.context = context;
        this.cartItems = restaurants;
    }

    @NonNull
    @Override
    public ParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = SingleCartRestaurantBinding.inflate(LayoutInflater.from(context), parent, false);

        return new ParentViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull ParentViewHolder parentViewHolder, int position) {

        CartItem cartItem = cartItems.get(position);

        // Create a layout manager for the reyclerview
        LinearLayoutManager layoutManager = new LinearLayoutManager(parentViewHolder.ChildRecyclerView.getContext());

        // Define child item prefetch size
        layoutManager.setInitialPrefetchItemCount(cartItems.size());

        // Create instance of child item adapter and set layout
        restaurantList.load(context);
        cartItemList.load(context);
        String restaurantName = restaurantList.getRestaurant(cartItem.getRestaurantRef()).getName();
        parentViewHolder.ParentItemTitle.setText(restaurantName);
        ChildItemAdapter childItemAdapter = new ChildItemAdapter(context,cartItemList.read(context));
        parentViewHolder.ChildRecyclerView.setLayoutManager(layoutManager);
        parentViewHolder.ChildRecyclerView.setAdapter(childItemAdapter);
        parentViewHolder.ChildRecyclerView.setRecycledViewPool(viewPool);


    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }


}