package edu.curtin.foodapp.ui.browse.fooditemfragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.curtin.foodapp.databinding.ListFoodItemBinding;
import edu.curtin.foodapp.model.fooditems.FoodItem;
import edu.curtin.foodapp.model.fooditems.FoodItemList;
import edu.curtin.foodapp.ui.browse.restaurantfragment.RestaurantListFragment;

import java.util.ArrayList;

public class FoodItemListFragment extends Fragment {

    ListFoodItemBinding binding;

    private FoodItemList foodItemList;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        foodItemList = new FoodItemList();
        foodItemList.load(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ListFoodItemBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final RecyclerView rv = binding.recyclerview;

        // default
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        FoodItemViewAdapter rvAdapter = new FoodItemViewAdapter(getContext(),foodItemList.getAllFoodItems());

        rv.setAdapter(rvAdapter);
        return root;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
