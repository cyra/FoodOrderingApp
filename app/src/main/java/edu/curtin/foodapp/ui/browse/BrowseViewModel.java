package edu.curtin.foodapp.ui.browse;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import edu.curtin.foodapp.model.restaurant.Restaurant;
import edu.curtin.foodapp.model.fooditems.FoodItem;

public class BrowseViewModel extends ViewModel {
    /**
     * This file doesn't get used
     */
    private final MutableLiveData<Restaurant> restaurant;
    private final MutableLiveData<FoodItem> fooditem;

    public BrowseViewModel() {
        restaurant = new MutableLiveData<>();
        fooditem = new MutableLiveData<>();
    }

    public String getName() {return restaurant.getValue().getName(); }

    public void setRestaurant(Restaurant restaurant) {this.restaurant.setValue(restaurant);}
}