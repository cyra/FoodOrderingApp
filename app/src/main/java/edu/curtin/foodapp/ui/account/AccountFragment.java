package edu.curtin.foodapp.ui.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import edu.curtin.foodapp.LoginActivity;
import edu.curtin.foodapp.MainActivity;
import edu.curtin.foodapp.R;
import edu.curtin.foodapp.model.user.User;
import edu.curtin.foodapp.model.user.UserList;
import edu.curtin.foodapp.databinding.FragmentAccountBinding;

public class AccountFragment extends Fragment {

    // For receiving data from LoginActivity
    private static final int USER_REQUEST_CODE = 0;

    private FragmentAccountBinding binding;

    private User currentUser;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        if (currentUser == null) {
            // Open LoginActivity
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivityForResult(intent, USER_REQUEST_CODE);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AccountViewModel accountViewModel =
                new ViewModelProvider(this).get(AccountViewModel.class);

        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Use the AccountViewModel
        //accountViewModel.setUser(currentUser);

        return root;
    }

    // Used for nesting child fragments
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        insertUserDetailsFragment();
        insertOrderHistoryFragment();
    }

    private void insertUserDetailsFragment() {
        Fragment userDetailsFragment = new UserDetailsFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.userDetailsFragment, userDetailsFragment).commit();
    }

    private void insertOrderHistoryFragment() {
        Fragment orderHistoryFragment = new OrderHistoryFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.orderHistoryFragment, orderHistoryFragment).commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}