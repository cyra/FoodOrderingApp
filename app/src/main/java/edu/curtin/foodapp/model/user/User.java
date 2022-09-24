package edu.curtin.foodapp.model.user;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private int id;
    private String email;
    private String name;
    private String address;
    private String phone;
    //private ArrayList<Order> orderHistory;
    //private boolean loggedIn;

    public User(int id, String email, String name, String address, String phone) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public int getID() { return id; }
    public String getEmail() { return email; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }

}
