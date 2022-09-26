package edu.curtin.foodapp.ui.cart.cartfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.curtin.foodapp.databinding.SingleCartRestaurantBinding;
import edu.curtin.foodapp.model.cart.CartItemList;
import edu.curtin.foodapp.model.orders.OrderItem;
import edu.curtin.foodapp.model.orders.RestaurantOrder;
import edu.curtin.foodapp.model.restaurant.RestaurantList;
import edu.curtin.foodapp.ui.cart.parent.ParentItemAdapter;


public class CartListFragment extends Fragment {

    private SingleCartRestaurantBinding binding;
    //private FragmentCartBinding binding;
    ArrayList<RestaurantOrder> restaurants;
    CartItemList cartList = new CartItemList();

    public CartListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = SingleCartRestaurantBinding.inflate(inflater, container, false);
        //binding = FragmentCartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final RecyclerView rv = binding.childRecyclerView;
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        ParentItemAdapter rvAdapter = new ParentItemAdapter(getContext(),cartList.read(getContext()));
        rv.setAdapter(rvAdapter);

        return root;



    }

    private ArrayList<RestaurantOrder> prepareData() {
        ArrayList<RestaurantOrder> restaurants = new ArrayList<>();
        ArrayList<OrderItem> pizzaOrders = new ArrayList<>();
        ArrayList<OrderItem> pastaOrders = new ArrayList<>();
        pizzaOrders.add(new OrderItem("pizza", 12.30, 1));
        pizzaOrders.add(new OrderItem("pizza", 12.30, 1));
        pizzaOrders.add(new OrderItem("pizza", 12.30, 1));
        pastaOrders.add(new OrderItem("pi", 12.30, 1));
        pastaOrders.add(new OrderItem("pa", 12.30, 1));

        RestaurantOrder newRestaurant = new RestaurantOrder("resto", pizzaOrders);
        RestaurantOrder pastarant = new RestaurantOrder("pasta", pastaOrders);


        restaurants.add(newRestaurant);
        restaurants.add(pastarant);
        return restaurants;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
