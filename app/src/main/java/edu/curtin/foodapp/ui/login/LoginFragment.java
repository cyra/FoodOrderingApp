package edu.curtin.foodapp.ui.login;

import static edu.curtin.foodapp.ui.account.AccountFragment.USER_REQUEST_CODE;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
        Button createAccountButton = (Button) view.findViewById(R.id.createAccountButton);

        EditText emailField = (EditText) view.findViewById(R.id.emailLoginEditText);
        EditText passwordField = (EditText) view.findViewById(R.id.passwordLoginEditText);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();

                // If email and password valid
                if (validateEmail(email) && validatePassword(password)) {
                    // Search database for match
                    int index = users.findIndexByLogin(email, password);
                    // If match found
                    if (index != -1) {
                        // Return data
                        Intent returnData = new Intent();
                        returnData.putExtra("user", users.getUser(index));
                        getActivity().setResult(Activity.RESULT_OK, returnData);
                        // Display logged in notification
                        displayToast("Logged in");
                        // Close activity
                        getActivity().finish();
                    }
                    else {
                        displayToast("Incorrect username/password");
                    }
                }
                else if (!validateEmail(email)) {
                    displayToast("Invalid email");
                }
                else if (!validatePassword(password)) {
                    displayToast("Invalid password");
                }
                else {
                    displayToast("Unknown error");
                }
            }
        });

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Replace this fragment with CreateAccountFragment
            }
        });
    }


    private boolean validateEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern) && email.length() > 0;
    }

    private boolean validatePassword(String password) {
        String passwordPattern = "^(?=\\S+$).{1,}$";
        return password.matches(passwordPattern) && password.length() > 0;
    }


    private void displayToast(String message) {
        Context context = getContext().getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }
}