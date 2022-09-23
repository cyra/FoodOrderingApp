package edu.curtin.foodapp.ui.account;

import android.os.Bundle;
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

import edu.curtin.foodapp.R;
import edu.curtin.foodapp.User;
import edu.curtin.foodapp.UserList;
import edu.curtin.foodapp.databinding.FragmentAccountBinding;

public class AccountFragment extends Fragment {

    private FragmentAccountBinding binding;

    private UserList users;
    private User currentUser;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        users = new UserList();
        users.load(getActivity());

        users.addUser(new User(users.getSize(), String.valueOf(users.getSize()) + "@test.com", "person personson", "123 Fake Street", "404"));
        currentUser = users.getUser(users.getSize() - 1);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View v = getView();

        // TODO: Event listeners below!
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AccountViewModel accountViewModel =
                new ViewModelProvider(this).get(AccountViewModel.class);

        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Use the AccountViewModel
        accountViewModel.setUser(currentUser);

        return root;
    }

    // Used for nesting child fragments
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        insertNestedFragment();
    }

    // Embeds the child fragment dynamically
    private void insertNestedFragment() {
        Fragment userDetailsFragment = new UserDetailsFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.userDetailsFragment, userDetailsFragment).commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}