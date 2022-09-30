package edu.curtin.foodapp.ui.cart.cartfragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CartViewModel extends ViewModel {

    private final MutableLiveData<Double> totalCartPrice;

    public CartViewModel() {
        totalCartPrice = new MutableLiveData<Double>();
    }

    public LiveData<Double> getTotalCart() { return totalCartPrice; }
    public void setTotalCart(double totalCartPrice) { this.totalCartPrice.setValue(totalCartPrice); }
}