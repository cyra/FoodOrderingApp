package edu.curtin.foodapp.ui.account.orderlistfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.curtin.foodapp.R;
import edu.curtin.foodapp.databinding.SingleOrderhistoryitemBinding;
import edu.curtin.foodapp.model.order.Order;

public class OrderViewAdapter extends RecyclerView.Adapter<OrderViewHolder> {
    private final Context context;
    private SingleOrderhistoryitemBinding binding;

    private ArrayList<Order> orders;

    public OrderViewAdapter(Context context, ArrayList<Order> orders){
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = SingleOrderhistoryitemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new OrderViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order currentOrder = orders.get(position);
        String orderDetails = "Order # " + currentOrder.getOrderID() + " - " + currentOrder.getDate();
        holder.itemName.setText(orderDetails);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_navigation_account_to_navigation_order_details);
            }
        });

        holder.itemName.setText(orderDetails);
    }


    @Override
    public int getItemCount() {
        return orders.size();
    }
}
