package edu.curtin.foodapp.ui.home.fooditemfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.curtin.foodapp.databinding.ListDailyFoodItemBinding;
import edu.curtin.foodapp.model.fooditems.FoodItem;
import edu.curtin.foodapp.model.fooditems.FoodItemList;
import edu.curtin.foodapp.ui.browse.fooditemfragment.FoodItemViewAdapter;

public class DailyFoodItemListFragment extends Fragment {

    ListDailyFoodItemBinding binding;

    private FoodItemList foodItemList;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        foodItemList = new FoodItemList();
        foodItemList.load(getContext());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ListDailyFoodItemBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final RecyclerView rv = binding.recyclerview;

        rv.setLayoutManager(new GridLayoutManager(getContext(), 2));

        final int NUM_RANDOM_FOOD_ITEMS = 10;
        DailyFoodItemListAdapter rvAdapter = new DailyFoodItemListAdapter(getContext(), foodItemList.getRandomFoodItems(NUM_RANDOM_FOOD_ITEMS));
        rv.setAdapter(rvAdapter);
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
