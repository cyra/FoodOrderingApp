package edu.curtin.foodapp.ui.browse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import edu.curtin.foodapp.R;
import edu.curtin.foodapp.databinding.FragmentBrowseBinding;
import edu.curtin.foodapp.ui.browse.fooditemfragment.FoodItemListFragment;
import edu.curtin.foodapp.ui.browse.restaurantfragment.RestaurantListFragment;

public class BrowseFragment extends Fragment {

    private FragmentBrowseBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentBrowseBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    // Used for nesting child fragments
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        insertNestedFragment();
    }

    // Embeds the child fragment dynamically
    private void insertNestedFragment() {
        Fragment RestaurantListFragment = new RestaurantListFragment();
        Fragment FoodItemListFragment  = new FoodItemListFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        // For multiple fragments in a fragment, use multiple transaction.replace() and then commit() after.
        transaction.replace(R.id.restaurantListFragment, RestaurantListFragment);
        transaction.replace(R.id.foodItemListFragment, FoodItemListFragment);
        transaction.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}