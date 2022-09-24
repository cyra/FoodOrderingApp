package edu.curtin.foodapp.ui.cart.cartfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

import edu.curtin.foodapp.R;
import edu.curtin.foodapp.databinding.FragmentCartBinding;
import edu.curtin.foodapp.model.orders.OrderItem;
import edu.curtin.foodapp.model.orders.Orders;
import edu.curtin.foodapp.model.orders.RestaurantOrder;

public class CartFragment extends Fragment {

    private FragmentCartBinding binding;
    private ArrayList<Orders> orders;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCartBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    // Used for nesting child fragments
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        insertNestedFragment();
    }

    // Embeds the child fragment dynamically
    private void insertNestedFragment() {
        Fragment CartListFragment = new CartListFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        // For multiple fragments in a fragment, use multiple transaction.replace() and then commit() after.
        transaction.replace(R.id.cartListFragment, CartListFragment);
        transaction.commit();

/*
        final RecyclerView rv = binding.cartListFragment;
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        orders = new ArrayList<>();
        orders = prepareData();
        CartAdapter rvAdapter = new CartAdapter(getContext(),orders);
        rv.setAdapter(rvAdapter);
        */


    }
    private ArrayList prepareData() {
        ArrayList<OrderItem> pizzaOrders = new ArrayList<>();
        ArrayList<OrderItem> pastaOrders = new ArrayList<>();
        ArrayList<RestaurantOrder> restaurants = new ArrayList<>();

        pizzaOrders.add(new OrderItem("pizza", 12.30, 1));
        pizzaOrders.add(new OrderItem("pizza", 12.30, 1));
        pizzaOrders.add(new OrderItem("pizza", 12.30, 1));
        pastaOrders.add(new OrderItem("pi", 12.30, 1));
        pastaOrders.add(new OrderItem("pa", 12.30, 1));

        RestaurantOrder newRestaurant = new RestaurantOrder("resto", pizzaOrders);
        RestaurantOrder pastarant = new RestaurantOrder("pasta", pastaOrders);

        restaurants.add(newRestaurant);
        restaurants.add(pastarant);
        Orders order = new Orders(1,"", "","");
        order.setOrders(restaurants);
        orders.add(order);
        return orders;

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}