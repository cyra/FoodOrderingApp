package edu.curtin.foodapp.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.curtin.foodapp.LoginActivity;
import edu.curtin.foodapp.R;
import edu.curtin.foodapp.model.user.User;
import edu.curtin.foodapp.model.user.UserList;

public class CreateAccountFragment extends Fragment {

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
        return inflater.inflate(R.layout.fragment_create_account, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = getView();
        Button createAccountButton = (Button) view.findViewById(R.id.createNewAccountButton);
        EditText emailField = (EditText) view.findViewById(R.id.createEmailEditText);
        EditText passwordField = (EditText) view.findViewById(R.id.createPasswordEditText);
        EditText nameField = (EditText) view.findViewById(R.id.createNameEditText);
        EditText addressField = (EditText) view.findViewById(R.id.createAddressEditText);
        EditText phoneField = (EditText) view.findViewById(R.id.createPhoneEditText);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();
                String name = nameField.getText().toString();
                String address = addressField.getText().toString();
                String phone = phoneField.getText().toString();

                if (((LoginActivity) getActivity()).validateEmail(email) &&
                    ((LoginActivity) getActivity()).validatePassword(password) &&
                    validateName(name) &&
                    validateAddress(address) &&
                    validatePhone(phone)) {
                    // Create new user
                    User newUser = new User(users.getSize(), email, name, address, phone, password);
                    // Add new user to database
                    users.addUser(newUser);
                    // Return data
                    Intent returnData = new Intent();
                    returnData.putExtra("user", newUser);
                    getActivity().setResult(Activity.RESULT_OK, returnData);
                    // Display notification
                    ((LoginActivity) getActivity()).displayToast("Account created");
                    // Close activity
                    getActivity().finish();
                }
                else if (!((LoginActivity) getActivity()).validateEmail(email)) {
                    ((LoginActivity) getActivity()).displayToast("Invalid email");
                }
                else if (!((LoginActivity) getActivity()).validatePassword(password)) {
                    ((LoginActivity) getActivity()).displayToast("Invalid password");
                }
                else if (!validateName(name)) {
                    ((LoginActivity) getActivity()).displayToast("Invalid name");
                }
                else if (!validateAddress(address)) {
                    ((LoginActivity) getActivity()).displayToast("Invalid address");
                }
                else if (!validatePhone(phone)) {
                    ((LoginActivity) getActivity()).displayToast("Invalid phone");
                }
                else {
                    ((LoginActivity) getActivity()).displayToast("Unknown error");
                }
            }
        });
    }

    private boolean validateName(String name) {
        String namePattern = "^([A-Za-z]+ ?)+[A-Za-z]+$";
        return name.matches(namePattern) && name.length() > 0;
    }

    private boolean validateAddress(String address) {
        String addressPattern = "^\\s*\\S+(?:\\s+\\S+){2}";
        return address.matches(addressPattern) && address.length() > 0;
    }

    private boolean validatePhone(String phone) {
        String phonePattern = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$";
        return phone.matches(phonePattern) && phone.length() > 0;
    }
}