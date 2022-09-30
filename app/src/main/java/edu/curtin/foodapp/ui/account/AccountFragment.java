package edu.curtin.foodapp.ui.account;

import static android.app.Activity.RESULT_OK;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import edu.curtin.foodapp.LoginActivity;
import edu.curtin.foodapp.MainActivity;
import edu.curtin.foodapp.R;
import edu.curtin.foodapp.model.user.User;
import edu.curtin.foodapp.model.user.UserList;
import edu.curtin.foodapp.databinding.FragmentAccountBinding;
import edu.curtin.foodapp.ui.cart.cartfragment.CartListFragment;
import edu.curtin.foodapp.ui.login.LoginFragment;
import edu.curtin.foodapp.ui.account.orderlistfragment.OrderListFragment;
import edu.curtin.foodapp.ui.account.userdetailsfragment.UserDetailsFragment;

public class AccountFragment extends Fragment {

    // For receiving data from LoginActivity
    public static final int USER_REQUEST_CODE = 444;

    private FragmentAccountBinding binding;

    private AccountViewModel accountViewModel;
    private User currentUser;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        // User persistence
        accountViewModel = ((MainActivity) getActivity()).getAccountViewModel();

        if (accountViewModel.getLoggedIn()) {
            currentUser = accountViewModel.getUser().getValue();
        }

        if (currentUser == null) {
            startActivityForResult(LoginActivity.getIntent(getContext()), USER_REQUEST_CODE);
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    // Used for nesting child fragments
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        insertChildFragments();
    }

    private void insertChildFragments() {
        // Embeds the child fragments dynamically
        Fragment orderListFragment = new OrderListFragment();
        Fragment userDetailsFragment = new UserDetailsFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.userDetailsFragment, userDetailsFragment);
        transaction.replace(R.id.orderListFragment, orderListFragment);
        transaction.commit();
    }

    // Embeds the order details fragment
    private void insertOrderDetailsFragment() {
        Fragment CartListFragment = new CartListFragment();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.cartListFragment, CartListFragment).commit();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == USER_REQUEST_CODE && resultCode == RESULT_OK) {
            currentUser = LoginActivity.getUser(data);

            accountViewModel.setUser(currentUser);
            accountViewModel.setName(currentUser.getName());
            accountViewModel.setEmail(currentUser.getEmail());
            accountViewModel.setAddress(currentUser.getAddress());
            accountViewModel.setPhone(currentUser.getPhone());

            ((MainActivity) getActivity()).refreshOrderListFragment();
        }
    }
}