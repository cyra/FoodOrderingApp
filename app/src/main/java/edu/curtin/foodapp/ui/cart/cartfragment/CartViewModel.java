package edu.curtin.foodapp.ui.cart.cartfragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import edu.curtin.foodapp.model.cart.CartItem;

public class CartViewModel extends ViewModel {

    private final MutableLiveData<String> totalCartPrice;

    public CartViewModel() {
        totalCartPrice = new MutableLiveData<String>();
    }


    public String getTotalCart() { return totalCartPrice.getValue(); }
    public void setTotalCart(String total) { this.totalCartPrice.setValue(total); }
}