package edu.curtin.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import edu.curtin.foodapp.model.user.User;
import edu.curtin.foodapp.model.user.UserList;
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

    public static Intent getIntent(Context c) {
        Intent intent = new Intent(c, LoginActivity.class);
        return intent;
    }

    public static User getUser(Intent intent) {
        return (User) intent.getSerializableExtra("user");
    }
}