package edu.curtin.foodapp.ui.cart.cartfragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CartViewModel extends ViewModel {

    private final MutableLiveData<String> totalCartPrice;

    public CartViewModel() {
        totalCartPrice = new MutableLiveData<String>();
    }

    public LiveData<String> getTotalCart() { return totalCartPrice; }

    public void setTotalCart(String totalCartPrice) { this.totalCartPrice.setValue(totalCartPrice); }
}