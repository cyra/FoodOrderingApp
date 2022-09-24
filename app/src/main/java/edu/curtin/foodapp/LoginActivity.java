package edu.curtin.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import edu.curtin.foodapp.model.user.UserList;
import edu.curtin.foodapp.ui.login.LoginFragment;

public class LoginActivity extends AppCompatActivity {

    private UserList users;

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
}