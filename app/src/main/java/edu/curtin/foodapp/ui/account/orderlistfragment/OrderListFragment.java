package edu.curtin.foodapp.ui.account.orderlistfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.curtin.foodapp.databinding.ListOrdersBinding;
import edu.curtin.foodapp.model.order.OrderList;

public class OrderListFragment extends Fragment {
    private ListOrdersBinding binding;

    private OrderList orders;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        orders = new OrderList();
        orders.load(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ListOrdersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final RecyclerView rv = binding.recyclerview;

        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        OrderViewAdapter rvAdapter = new OrderViewAdapter(getContext(), orders.getAllOrders());
        rv.setAdapter(rvAdapter);
        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
