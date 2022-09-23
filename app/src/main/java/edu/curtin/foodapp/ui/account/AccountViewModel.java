package edu.curtin.foodapp.ui.account;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import edu.curtin.foodapp.User;

public class AccountViewModel extends ViewModel {

    private final MutableLiveData<User> user;

    public AccountViewModel() {
        user = new MutableLiveData<User>();
    }

    public String getName() { return user.getValue().getName(); }
    public String getEmail() { return user.getValue().getEmail(); }
    public String getAddress() { return user.getValue().getAddress(); }
    public String getPhone() { return user.getValue().getPhone(); }

    public void setUser(User user) { this.user.setValue(user); }
}