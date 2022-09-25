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

import edu.curtin.foodapp.databinding.ListDailyFoodItemBinding;
import edu.curtin.foodapp.databinding.ListFoodItemBinding;
import edu.curtin.foodapp.model.fooditems.FoodItem;
import edu.curtin.foodapp.model.fooditems.FoodItemList;
import edu.curtin.foodapp.model.restaurant.RestaurantList;
import edu.curtin.foodapp.ui.browse.fooditemfragment.FoodItemViewAdapter;
import edu.curtin.foodapp.ui.browse.restaurantfragment.RestaurantListFragment;

public class DailyFoodItemListFragment extends Fragment {

    private ListDailyFoodItemBinding binding;
    //ArrayList<FoodItem> foodItems;
    FoodItemList foodItemList = new FoodItemList();

    public DailyFoodItemListFragment() {
        // Required empty public constructor
    }

    //public DailyFoodItemListFragment(ArrayList<FoodItem> foodItems) {this.foodItems = foodItems;}

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

        // Create random food items in fooditemslist
        //prepareData();
        // FoodItemViewAdapter rvAdapter = new FoodItemViewAdapter(getContext(),foodItems);
        /*ArrayList<FoodItem> allList = new ArrayList<>();

        for (FoodItem item: allList) {
            if (String.valueOf(item.getID()) == "1"){
                randomList.add(item);
            }
        }*/
        ArrayList<FoodItem> allList = new ArrayList<>(foodItemList.read(getContext()));
        FoodItemViewAdapter rvAdapter = new FoodItemViewAdapter(getContext(),allList);
        rv.setAdapter(rvAdapter);
        return root;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
