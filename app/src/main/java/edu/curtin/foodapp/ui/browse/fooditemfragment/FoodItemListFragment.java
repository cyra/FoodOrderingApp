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

    private @NonNull FoodItemList foodItemList = new FoodItemList();
    ListFoodItemBinding binding;

    public FoodItemListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

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

        FoodItemViewAdapter rvAdapter = new FoodItemViewAdapter(getContext(),foodItemList.read(getContext()));
        rv.setAdapter(rvAdapter);
        return root;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
