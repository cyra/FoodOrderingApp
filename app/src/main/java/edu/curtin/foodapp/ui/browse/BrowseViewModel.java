package edu.curtin.foodapp.ui.browse;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import edu.curtin.foodapp.model.restaurant.Restaurant;

public class BrowseViewModel extends ViewModel {

    private final MutableLiveData<Restaurant> restaurant;

    public BrowseViewModel() {
        restaurant = new MutableLiveData<Restaurant>();
    }
    public String getName() {return restaurant.getValue().getName(); }

    public void setRestaurant(Restaurant restaurant) {this.restaurant.setValue(restaurant);}
}