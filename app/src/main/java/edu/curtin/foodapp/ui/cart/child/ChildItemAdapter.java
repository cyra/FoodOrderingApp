package edu.curtin.foodapp.ui.cart.child;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.curtin.foodapp.databinding.SingleCartFoodItemBinding;
import edu.curtin.foodapp.model.orders.OrderItem;

public class ChildItemAdapter extends RecyclerView.Adapter<ChildViewHolder> {

    private final ArrayList<OrderItem> orderItems;
    private SingleCartFoodItemBinding binding;
    private final Context context;

    // Constructor
    public ChildItemAdapter(Context context, ArrayList<OrderItem> orderItems) {
        this.context = context;
        this.orderItems = orderItems;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Here we inflate the corresponding layout of the child item
        binding = SingleCartFoodItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ChildViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder childViewHolder, int position) {


        OrderItem orderItem = orderItems.get(position);
        childViewHolder.ChildItemTitle.setText(orderItem.getName());
    }

    @Override
    public int getItemCount() {

        return orderItems.size();
    }
}