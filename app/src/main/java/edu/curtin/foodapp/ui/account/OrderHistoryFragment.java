package edu.curtin.foodapp.ui.account;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.curtin.foodapp.R;
import edu.curtin.foodapp.databinding.FragmentOrderHistoryBinding;
import edu.curtin.foodapp.databinding.FragmentUserDetailsBinding;

public class OrderHistoryFragment extends Fragment {

    private FragmentOrderHistoryBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AccountViewModel accountViewModel =
                new ViewModelProvider(getParentFragment()).get(AccountViewModel.class);

        binding = FragmentOrderHistoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Use the AccountViewModel
        // If user has order history display recyclerview of orders
        // Else display text

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}