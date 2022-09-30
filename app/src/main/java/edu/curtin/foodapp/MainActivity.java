package edu.curtin.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

// added imports

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import edu.curtin.foodapp.databinding.ActivityMainBinding;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import edu.curtin.foodapp.model.restaurant.Restaurant;
import edu.curtin.foodapp.model.restaurant.RestaurantList;
import edu.curtin.foodapp.ui.cart.CartViewModel;
import edu.curtin.foodapp.ui.account.AccountViewModel;
import edu.curtin.foodapp.ui.browse.BrowseViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    RestaurantList restaurantList;
    ArrayList<Restaurant> restaurants;
    Context context;

    private BrowseViewModel browseViewModel;
    private CartViewModel cartViewModel;
    private AccountViewModel accountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        browseViewModel = new ViewModelProvider(this).get(BrowseViewModel.class);
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
    }

    public BrowseViewModel getBrowseViewModel() { return browseViewModel; }
    public CartViewModel getCartViewModel() { return cartViewModel; }
    public AccountViewModel getAccountViewModel() { return accountViewModel; }
}