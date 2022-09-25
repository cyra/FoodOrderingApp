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

    private ListFoodItemBinding binding;
    ArrayList<FoodItem> foodItems;

    public FoodItemListFragment() {
        // Required empty public constructor
    }

    public FoodItemListFragment(ArrayList<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        foodItems.add(new FoodItem(1, "Pizza","very nice pizza",20.55,"", 1));
        foodItems.add(new FoodItem(2, "Pasta","very nice pasta",25.55,"burger", 1));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
