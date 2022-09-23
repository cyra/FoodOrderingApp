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
import androidx.lifecycle.ViewModelProvider;

import edu.curtin.foodapp.R;
import edu.curtin.foodapp.model.User;
import edu.curtin.foodapp.model.UserList;
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
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View v = getView();
        Button button = v.findViewById(R.id.createUserButton);
        TextView text = v.findViewById(R.id.userName);

        if (users.getSize() > 0) {
            text.setText(users.getUser(0).getName());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                users.addUser(new User(1234, "something@mail.com", "Test Account", "123 Fake Street", "0440 440 404"));
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AccountViewModel accountViewModel =
                new ViewModelProvider(this).get(AccountViewModel.class);

        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}