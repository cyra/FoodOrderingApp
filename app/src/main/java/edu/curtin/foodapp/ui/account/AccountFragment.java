package edu.curtin.foodapp.ui.account;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
import edu.curtin.foodapp.ui.login.LoginFragment;

public class AccountFragment extends Fragment {

    // For receiving data from LoginActivity
    public static final String USER_REQUEST_CODE = "userCode";

    private FragmentAccountBinding binding;

    private User currentUser;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        Log.d("Account", "hello");

        if (bundle != null) {
            Log.d("Account", "Bundle is not null");
            currentUser = (User) bundle.getSerializable("user");
        }

        if (currentUser == null) {
            // Create result listener
            getParentFragmentManager()
                    .setFragmentResultListener(USER_REQUEST_CODE, this, new FragmentResultListener() {
                        @Override
                        public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                            User userResult = (User) bundle.getSerializable("user");
                            currentUser = userResult;
                            bundle.putSerializable("user", currentUser);

                            Log.d("Account", "User returned from LoginActivity");
                        }
                    });
            // Open LoginActivity
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
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