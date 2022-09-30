package edu.curtin.foodapp.ui.account.userdetailsfragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.curtin.foodapp.MainActivity;
import edu.curtin.foodapp.databinding.SingleUserDetailsBinding;
import edu.curtin.foodapp.ui.account.AccountViewModel;

public class UserDetailsFragment extends Fragment {

    private SingleUserDetailsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AccountViewModel accountViewModel = ((MainActivity) getActivity()).getAccountViewModel();

        binding = SingleUserDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Use the AccountViewModel
        final TextView nameTextView = binding.nameTextView;
        final TextView emailTextView = binding.emailTextView;
        final TextView addressTextView = binding.addressTextView;
        final TextView phoneTextView = binding.phoneTextView;

        // Set user details observers
        accountViewModel.getName().observe(getViewLifecycleOwner(), nameTextView::setText);
        accountViewModel.getEmail().observe(getViewLifecycleOwner(), emailTextView::setText);
        accountViewModel.getAddress().observe(getViewLifecycleOwner(), addressTextView::setText);
        accountViewModel.getPhone().observe(getViewLifecycleOwner(), phoneTextView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}