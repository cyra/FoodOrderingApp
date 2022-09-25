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

    //ArrayList<FoodItem> foodItems;
    FoodItemList foodItemList = new FoodItemList();
    private @NonNull
    ListDailyFoodItemBinding binding;

    public DailyFoodItemListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ListDailyFoodItemBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final RecyclerView rv = binding.recyclerview;

        rv.setLayoutManager(new GridLayoutManager(getContext(), 2));

        FoodItemViewAdapter rvAdapter = new FoodItemViewAdapter(getContext(), foodItemList.select(getContext(), "Pasta"));
        rv.setAdapter(rvAdapter);
        return root;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
