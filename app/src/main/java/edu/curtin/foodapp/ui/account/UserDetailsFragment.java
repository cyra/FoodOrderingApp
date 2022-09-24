package edu.curtin.foodapp.ui.account;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import edu.curtin.foodapp.R;
import edu.curtin.foodapp.databinding.FragmentAccountBinding;
import edu.curtin.foodapp.databinding.FragmentBrowseBinding;
import edu.curtin.foodapp.databinding.FragmentUserDetailsBinding;
import edu.curtin.foodapp.ui.browse.BrowseViewModel;

public class UserDetailsFragment extends Fragment {

    private FragmentUserDetailsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AccountViewModel accountViewModel =
                new ViewModelProvider(getParentFragment()).get(AccountViewModel.class);

        binding = FragmentUserDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Use the AccountViewModel
        final TextView nameTextView = binding.nameTextView;
        final TextView emailTextView = binding.emailTextView;
        final TextView addressTextView = binding.addressTextView;
        final TextView phoneTextView = binding.phoneTextView;

        if (accountViewModel.getLoggedIn()) {
            nameTextView.setText(accountViewModel.getName());
            emailTextView.setText(accountViewModel.getEmail());
            addressTextView.setText(accountViewModel.getAddress());
            phoneTextView.setText(accountViewModel.getPhone());
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}