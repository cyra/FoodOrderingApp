package edu.curtin.foodapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import edu.curtin.foodapp.R;
import edu.curtin.foodapp.databinding.FragmentHomeBinding;
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
        insertNestedFragment();
    }

    // Embeds the child fragment dynamically
    private void insertNestedFragment() {
        Fragment DailyFoodItemListFragment  = new DailyFoodItemListFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        // For multiple fragments in a fragment, use multiple transaction.replace() and then commit() after.
        transaction.replace(R.id.foodItemListFragment, DailyFoodItemListFragment);
        transaction.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}