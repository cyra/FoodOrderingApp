package edu.curtin.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

// added imports

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import edu.curtin.foodapp.databinding.ActivityMainBinding;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import edu.curtin.foodapp.model.restaurant.Restaurant;
import edu.curtin.foodapp.model.restaurant.RestaurantList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    RestaurantList restaurantList;
    ArrayList<Restaurant> restaurants;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*
        // Create Restaurants
        RestaurantList restaurantList = new RestaurantList();
        restaurantList.load(getApplicationContext());
        restaurantList.deleteAllRestaurants();
        restaurantList.deleteRestaurant(7);
        restaurantList.deleteRestaurant(8);
        restaurantList.deleteRestaurant(2);

        restaurantList.addRestaurant(new Restaurant(restaurantList.getSize(), "new", ""));
        restaurantList.addRestaurant(new Restaurant(restaurantList.getSize(), "new2", ""));

        Toast toast = Toast.makeText(MainActivity.this, "A restaurant is added", Toast.LENGTH_SHORT);
        toast.show();
*/



        // Menu Bar UI
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

        /*
        DBEntries addEntries = new DBEntries();
        restaurants = addEntries.addRestaurants(context,restaurantList);
        restaurantList.load(context);
        restaurants = restaurantList.getAllRestaurants();
        */


    }
}