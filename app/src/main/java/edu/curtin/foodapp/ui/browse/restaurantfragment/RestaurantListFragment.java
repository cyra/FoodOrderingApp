package edu.curtin.foodapp.ui.browse.restaurantfragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.curtin.foodapp.DBEntries;
import edu.curtin.foodapp.databinding.ListRestaurantBinding;
import edu.curtin.foodapp.model.restaurant.Restaurant;
import edu.curtin.foodapp.model.restaurant.RestaurantList;
import edu.curtin.foodapp.ui.account.AccountViewModel;

import java.util.ArrayList;


public class RestaurantListFragment extends Fragment {

     RestaurantList restaurantList;
     ListRestaurantBinding binding;
    ArrayList<Restaurant> restaurants;

    public RestaurantListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = ListRestaurantBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final RecyclerView rv = binding.recyclerview;
        // horizontal scrolling recyclerview
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        // Make the recyclerview read the RestaurantList
        restaurantList = new RestaurantList();
        RestaurantViewAdapter rvAdapter = new RestaurantViewAdapter(getContext(),restaurantList.read(getActivity()));

        rv.setAdapter(rvAdapter);
        return root;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
