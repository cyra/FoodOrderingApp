package edu.curtin.foodapp.ui.browse.fooditemfragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import edu.curtin.foodapp.MainActivity;
import edu.curtin.foodapp.databinding.ListFoodItemBinding;
import edu.curtin.foodapp.model.fooditems.FoodItem;
import edu.curtin.foodapp.model.fooditems.FoodItemList;
import edu.curtin.foodapp.ui.account.AccountViewModel;
import edu.curtin.foodapp.ui.browse.BrowseViewModel;
import edu.curtin.foodapp.ui.browse.restaurantfragment.RestaurantListFragment;

import java.util.ArrayList;

public class FoodItemListFragment extends Fragment {

    ListFoodItemBinding binding;

    private FoodItemList foodItemList;
    private int restaurantID;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        foodItemList = new FoodItemList();
        foodItemList.load(getContext());

        BrowseViewModel browseViewModel = new ViewModelProvider(getActivity(),
                (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory()).get(BrowseViewModel.class);
        restaurantID = browseViewModel.getRestaurantID();

        Toast.makeText(getContext(), String.valueOf(restaurantID), Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ListFoodItemBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final RecyclerView rv = binding.recyclerview;

        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        FoodItemViewAdapter rvAdapter = new FoodItemViewAdapter(getContext(),foodItemList.getRestaurantMenu(restaurantID));

        rv.setAdapter(rvAdapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
