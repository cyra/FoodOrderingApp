package edu.curtin.foodapp.ui.browse.restaurantfragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import edu.curtin.foodapp.model.restaurant.Restaurant;
import edu.curtin.foodapp.model.restaurant.RestaurantList;

public class RestaurantViewModel extends ViewModel {

    public static MutableLiveData<Restaurant> restaurant;
    public MutableLiveData<ArrayList<Restaurant>> restaurants;
    public RestaurantViewModel() {
        restaurant = new MutableLiveData<Restaurant>();
    }
    /*
    MutableLiveData<ArrayList<Restaurant>> getRestaurants() {
        if (restaurants == null) {
            restaurants = new MutableLiveData<>();
            loadRestaurants();
        }
    //loadRestaurants();
        return restaurants;
    }
    private void loadRestaurants(){
        ArrayList<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(new Restaurant(1,"hi",""));
        restaurantList.add(new Restaurant(1,"ho",""));

        this.restaurants.setValue(restaurantList);
        for (Restaurant resto: restaurantList){
            setRestaurant(resto);
        }
        restaurants.setValue(restaurantList);
    }
*/
    //public ArrayList getName() { return restaurants.getValue().get(i).getName(); }
    //public String getMenu() { return restaurant.getValue().getMenu(); }
    public MutableLiveData<ArrayList<Restaurant>> getRestaurants() {
        if (restaurants == null) {
            restaurants = new MutableLiveData<>();
            //loadRestaurants();
        }
        //loadRestaurants();
        return restaurants;
    }

    public void setRestaurant(Restaurant restaurant) { this.restaurant.setValue(restaurant); }
}