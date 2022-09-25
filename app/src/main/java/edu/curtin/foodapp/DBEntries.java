package edu.curtin.foodapp;

import android.content.Context;

import java.util.ArrayList;

import edu.curtin.foodapp.model.restaurant.Restaurant;
import edu.curtin.foodapp.model.restaurant.RestaurantList;

import androidx.fragment.app.Fragment;

public class DBEntries {
    RestaurantList restaurantList;
    ArrayList<Restaurant> restaurants;

    public DBEntries() {
    }

    public ArrayList<Restaurant> addRestaurants(Context context, ArrayList<Restaurant> restaurants) {

        //restaurantList = new RestaurantList();
        restaurantList.load(context);
        // delete all restaurants
        if (restaurantList.getSize()>0) {
            restaurantList.addRestaurant(new Restaurant(restaurantList.getSize(), "frommain", ""));
            restaurantList.addRestaurant(new Restaurant(restaurantList.getSize(), "main2", ""));
        }
        restaurantList.addRestaurant(new Restaurant(0, "main2", ""));

        restaurants = restaurantList.getAllRestaurants();
        return restaurants;
    }
}
