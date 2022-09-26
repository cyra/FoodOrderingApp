package edu.curtin.foodapp.model.user;

import java.util.List;

public class User {
    private final int id;
    private final String email;
    private final String name;
    private final String address;
    private final String phone;
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
