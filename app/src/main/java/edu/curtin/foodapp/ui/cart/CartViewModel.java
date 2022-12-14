package edu.curtin.foodapp.ui.cart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CartViewModel extends ViewModel {

    private final MutableLiveData<Double> totalCartPrice;

    public CartViewModel() {
        totalCartPrice = new MutableLiveData<Double>();
        totalCartPrice.setValue(0.0);
    }

    public LiveData<Double> getTotalCart() { return totalCartPrice; }
    public void setTotalCart(double totalCartPrice) { this.totalCartPrice.setValue(totalCartPrice); }
}