package edu.curtin.foodapp.ui.account;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import edu.curtin.foodapp.model.user.User;

public class AccountViewModel extends ViewModel {

    private final MutableLiveData<String> name;
    private final MutableLiveData<String> email;
    private final MutableLiveData<String> address;
    private final MutableLiveData<String> phone;

    public AccountViewModel() {
        name = new MutableLiveData<String>();
        email = new MutableLiveData<String>();
        address = new MutableLiveData<String>();
        phone = new MutableLiveData<String>();

        /*name.setValue("name loading...");
        email.setValue("email loading...");
        address.setValue("address loading...");
        phone.setValue("phone loading...");*/
    }

    public LiveData<String> getName() { return name; }
    public LiveData<String> getEmail() { return email; }
    public LiveData<String> getAddress() { return address; }
    public LiveData<String> getPhone() { return phone; }

    public void setName(String name) { this.name.setValue(name); }
    public void setEmail(String email) { this.email.setValue(email); }
    public void setAddress(String address) { this.address.setValue(address); }
    public void setPhone(String phone) { this.phone.setValue(phone); }
}