package edu.curtin.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.preference.PreferenceActivity;

// added imports

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import edu.curtin.foodapp.databinding.ActivityMainBinding;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_browse, R.id.navigation_cart, R.id.navigation_account)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);



        FragmentManager fm = getSupportFragmentManager();
           /*
       setContentView(R.layout.activity_main);

        // Create FooterFragment
        FooterFragment footer = (FooterFragment) fm.findFragmentById(R.id.footerFragment);
        if (footer == null) {
            footer = FooterFragment.newInstance();
            fm.beginTransaction().add(R.id.footerFragment, footer).commit();
        }
   */
        // Create HeaderFragment
        HeaderFragment header = (HeaderFragment) fm.findFragmentById(R.id.headerFragment);
        if (header == null) {
            header = HeaderFragment.newInstance();
            fm.beginTransaction().add(R.id.headerFragment, header).commit();
        }


    }
}