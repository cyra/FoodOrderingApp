package edu.curtin.foodapp.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.InvalidPropertiesFormatException;

import edu.curtin.foodapp.R;
import edu.curtin.foodapp.databinding.FragmentHomeBinding;
import edu.curtin.foodapp.ui.browse.restaurantfragment.RestaurantListFragment;
import edu.curtin.foodapp.ui.home.fooditemfragment.DailyFoodItemListFragment;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    // Used for nesting child fragments
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        try {
            checkLargeLayout(view);
            // If large layout is being used
            insertNestedTabletFragments();
        }
        catch (NullPointerException ex) {
            // If non-large layout is being used
            insertNestedPhoneFragments();
        }
    }

    // Embeds the child fragment dynamically
    private void insertNestedPhoneFragments() {
        Fragment dailyFoodItemListFragment  = new DailyFoodItemListFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        // For multiple fragments in a fragment, use multiple transaction.replace() and then commit() after.
        transaction.replace(R.id.foodItemListFragment, dailyFoodItemListFragment);
        transaction.commit();
    }

    // Embeds the child fragment dynamically
    private void insertNestedTabletFragments() {
        Fragment dailyFoodItemListFragment  = new DailyFoodItemListFragment();
        Fragment featuredRestaurantFragment = new FeaturedRestaurantFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        // For multiple fragments in a fragment, use multiple transaction.replace() and then commit() after.
        transaction.replace(R.id.foodItemListFragment, dailyFoodItemListFragment);
        transaction.replace(R.id.featuredRestaurantFragment, featuredRestaurantFragment);
        transaction.commit();
    }

    private void checkLargeLayout(View view) {
        view.findViewById(R.id.featuredRestaurantTitle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Used to trigger an exception if large layout not being used
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}