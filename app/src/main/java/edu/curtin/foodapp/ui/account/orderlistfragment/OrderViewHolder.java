package edu.curtin.foodapp.ui.account.orderlistfragment;

import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.curtin.foodapp.databinding.SingleOrderhistoryitemBinding;

public class OrderViewHolder extends RecyclerView.ViewHolder {
    SingleOrderhistoryitemBinding binding;
    TextView itemName;

    public OrderViewHolder(@NonNull SingleOrderhistoryitemBinding binding) {
        super(binding.getRoot());
        itemName = binding.orderDetails;
        this.binding = binding;
    }
}
