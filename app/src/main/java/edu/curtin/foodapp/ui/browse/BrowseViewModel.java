package edu.curtin.foodapp.ui.browse;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BrowseViewModel extends ViewModel {
    private final MutableLiveData<Integer> restaurantID;

    public BrowseViewModel() {
        restaurantID = new MutableLiveData<Integer>();
        restaurantID.setValue(0);
    }

    public int getRestaurantID() { return restaurantID.getValue(); }
    public void setRestaurantID(int restaurantID) { this.restaurantID.setValue(restaurantID); }
}

