package edu.curtin.foodapp.ui.browse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import edu.curtin.foodapp.MainActivity;
import edu.curtin.foodapp.R;
import edu.curtin.foodapp.databinding.FragmentBrowseBinding;
import edu.curtin.foodapp.ui.browse.fooditemfragment.FoodItemListFragment;
import edu.curtin.foodapp.ui.browse.restaurantfragment.RestaurantListFragment;

public class BrowseFragment extends Fragment {

    private FragmentBrowseBinding binding;

    private BrowseViewModel browseViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        browseViewModel = ((MainActivity) getActivity()).getBrowseViewModel();

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
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        Fragment restaurantListFragment = new RestaurantListFragment(getChildFragmentManager());
        Fragment foodItemListFragment  = new FoodItemListFragment();
        // For multiple fragments in a fragment, use multiple transaction.replace() and then commit() after.
        transaction.replace(R.id.restaurantListFragment, restaurantListFragment);
        transaction.replace(R.id.foodItemListFragment, foodItemListFragment);
        transaction.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}