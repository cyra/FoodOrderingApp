package edu.curtin.foodapp.ui.account.orderlistfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.curtin.foodapp.databinding.SingleOrderhistoryitemBinding;
import edu.curtin.foodapp.model.orders.Orders;

public class OrderViewAdapter extends RecyclerView.Adapter<OrderViewHolder> {
    private final Context context;
    private SingleOrderhistoryitemBinding binding;
    ArrayList<Orders> orders;

    public OrderViewAdapter(Context context, ArrayList<Orders> orders){
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = SingleOrderhistoryitemBinding.inflate(LayoutInflater.from(context),parent,false);
        return new OrderViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Orders currentOrder = orders.get(position);
        String orderDetails = "Order # " + currentOrder.getOrderID()+ " - " +  currentOrder.getDate();
        holder.itemName.setText(orderDetails);

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
