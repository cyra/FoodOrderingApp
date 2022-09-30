package edu.curtin.foodapp.ui.account;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.Serializable;

import edu.curtin.foodapp.model.user.User;

public class AccountViewModel extends ViewModel {

    private final MutableLiveData<User> user;
    private final MutableLiveData<String> name;
    private final MutableLiveData<String> email;
    private final MutableLiveData<String> address;
    private final MutableLiveData<String> phone;

    public AccountViewModel() {
        user = new MutableLiveData<User>();
        name = new MutableLiveData<String>();
        email = new MutableLiveData<String>();
        address = new MutableLiveData<String>();
        phone = new MutableLiveData<String>();
    }

    public LiveData<User> getUser() { return user; }
    public LiveData<String> getName() { return name; }
    public LiveData<String> getEmail() { return email; }
    public LiveData<String> getAddress() { return address; }
    public LiveData<String> getPhone() { return phone; }

    public boolean getLoggedIn() {
        if (user.getValue() != null) {
            return true;
        }
        return false;
    }

    public void setUser(User user) { this.user.setValue(user); }
    public void setName(String name) { this.name.setValue(name); }
    public void setEmail(String email) { this.email.setValue(email); }
    public void setAddress(String address) { this.address.setValue(address); }
    public void setPhone(String phone) { this.phone.setValue(phone); }
}