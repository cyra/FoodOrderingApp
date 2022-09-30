package edu.curtin.foodapp.ui.login;

import static edu.curtin.foodapp.ui.account.AccountFragment.USER_REQUEST_CODE;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.curtin.foodapp.LoginActivity;
import edu.curtin.foodapp.R;
import edu.curtin.foodapp.model.user.User;
import edu.curtin.foodapp.model.user.UserList;
import edu.curtin.foodapp.ui.account.AccountFragment;

public class LoginFragment extends Fragment {

    private UserList users;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load database into users
        users = new UserList();
        users.load(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = getView();
        Button loginButton = (Button) view.findViewById(R.id.loginButton);
        Button registerAccountButton = (Button) view.findViewById(R.id.registerAccountButton);

        EditText emailField = (EditText) view.findViewById(R.id.emailLoginEditText);
        EditText passwordField = (EditText) view.findViewById(R.id.passwordLoginEditText);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();

                // For toast notifications
                Context context = getContext();

                // If email and password valid
                if (((LoginActivity) getActivity()).validateEmail(email) &&
                        ((LoginActivity) getActivity()).validatePassword(password)) {
                    // Search database for match
                    int index = users.findIndexByLogin(email, password);
                    // If match found
                    if (index != -1) {
                        // Return data
                        Intent returnData = new Intent();
                        returnData.putExtra("user", users.getUser(index));
                        getActivity().setResult(Activity.RESULT_OK, returnData);
                        // Display logged in notification
                        Toast.makeText(context, "Logged in", Toast.LENGTH_SHORT).show();
                        // Close activity
                        getActivity().finish();
                    }
                    else {
                        Toast.makeText(context, "Incorrect username/password", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (!((LoginActivity) getActivity()).validateEmail(email)) {
                    Toast.makeText(context, "Invalid email", Toast.LENGTH_SHORT).show();
                }
                else if (!((LoginActivity) getActivity()).validatePassword(password)) {
                    Toast.makeText(context, "Invalid password", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(context, "Unknown error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create account fragment
                ((LoginActivity) getActivity()).replaceLoginFragmentWithCreateAccountFragment();
            }
        });
    }
}