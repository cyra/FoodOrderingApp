package edu.curtin.foodapp.ui.browse.restaurantfragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.curtin.foodapp.MainActivity;
import edu.curtin.foodapp.databinding.ListRestaurantBinding;
import edu.curtin.foodapp.model.restaurant.RestaurantList;
import edu.curtin.foodapp.ui.browse.BrowseFragment;
import edu.curtin.foodapp.ui.browse.BrowseViewModel;

import java.util.ArrayList;


public class RestaurantListFragment extends Fragment {

    ListRestaurantBinding binding;

    RestaurantList restaurantList;

    private FragmentManager fm;

    public RestaurantListFragment(FragmentManager fm) {
        this.fm = fm;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        restaurantList = new RestaurantList();
        restaurantList.load(getContext());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ListRestaurantBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final RecyclerView rv = binding.recyclerview;
        // horizontal scrolling recyclerview
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        BrowseViewModel browseViewModel = new ViewModelProvider(getActivity(),
                (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory()).get(BrowseViewModel.class);

        // Make the recyclerview read the RestaurantList
        RestaurantViewAdapter rvAdapter = new RestaurantViewAdapter(getContext(), restaurantList.getAllRestaurants(),
                browseViewModel, fm);

        rv.setAdapter(rvAdapter);
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
