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
import edu.curtin.foodapp.model.orders.OrderItem;
import edu.curtin.foodapp.model.orders.RestaurantOrder;
import edu.curtin.foodapp.ui.cart.parent.ParentItemAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private SingleCartRestaurantBinding binding;
    //private FragmentCartBinding binding;
    ArrayList<RestaurantOrder> restaurants;

    public CartListFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecyclerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartListFragment newInstance(String param1, String param2) {
        CartListFragment fragment = new CartListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = SingleCartRestaurantBinding.inflate(inflater, container, false);
        //binding = FragmentCartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final RecyclerView rv = binding.childRecyclerView;
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        ParentItemAdapter rvAdapter = new ParentItemAdapter(getContext(),prepareData());
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
