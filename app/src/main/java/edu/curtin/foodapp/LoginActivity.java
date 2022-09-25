package edu.curtin.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import edu.curtin.foodapp.model.user.User;
import edu.curtin.foodapp.model.user.UserList;
import edu.curtin.foodapp.ui.login.CreateAccountFragment;
import edu.curtin.foodapp.ui.login.LoginFragment;

public class LoginActivity extends AppCompatActivity {

    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Create login fragment
        FragmentManager fm = getSupportFragmentManager();
        LoginFragment loginFragment = (LoginFragment) fm.findFragmentById(R.id.loginFragment);
        if (loginFragment == null) {
            loginFragment = new LoginFragment();
            fm.beginTransaction().add(R.id.loginFragment, loginFragment).commit();
        }
    }

    public void replaceLoginFragmentWithCreateAccountFragment() {
        FragmentManager fm = getSupportFragmentManager();
        CreateAccountFragment createAccountFragment = (CreateAccountFragment) fm.findFragmentById(R.id.createAccountFragment);
        if (createAccountFragment == null) {
            createAccountFragment = new CreateAccountFragment();
            fm.beginTransaction().replace(R.id.loginFragment, createAccountFragment).commit();
        }
    }


    public boolean validateEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern) && email.length() > 0;
    }

    public boolean validatePassword(String password) {
        String passwordPattern = "^(?=\\S+$).{1,}$";
        return password.matches(passwordPattern) && password.length() > 0;
    }

    public void displayToast(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }


    public static Intent getIntent(Context c) {
        Intent intent = new Intent(c, LoginActivity.class);
        return intent;
    }

    public static User getUser(Intent intent) {
        return (User) intent.getSerializableExtra("user");
    }
}