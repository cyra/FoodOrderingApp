package edu.curtin.foodapp.ui.browse.fooditemfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.curtin.foodapp.databinding.ListFoodItemBinding;
import edu.curtin.foodapp.model.fooditems.FoodItem;
import edu.curtin.foodapp.ui.browse.restaurantfragment.RestaurantListFragment;

import java.util.ArrayList;

public class FoodItemListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListFoodItemBinding binding;
    ArrayList<FoodItem> foodItems;

    public FoodItemListFragment() {
        // Required empty public constructor
    }

    public FoodItemListFragment(ArrayList<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }


    public static RestaurantListFragment newInstance(String param1, String param2) {
        RestaurantListFragment fragment = new RestaurantListFragment();
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
        binding = ListFoodItemBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final RecyclerView rv = binding.recyclerview;

        // default
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        // For gridlayout
        // rv.setLayoutManager(new GridLayoutManager(getContext(), 2));

        prepareData();
        FoodItemViewAdapter rvAdapter = new FoodItemViewAdapter(getContext(),foodItems);
        rv.setAdapter(rvAdapter);
        return root;

    }
    private void prepareData() {
        foodItems = new ArrayList<>();

        // No images yet
        foodItems.add(new FoodItem(1, "Pizza","very nice pizza",20.55,""));
        foodItems.add(new FoodItem(2, "Pasta","very nice pasta",25.55,""));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
