package edu.curtin.foodapp.model.user;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private int id;
    private String email;
    private String name;
    private String address;
    private String phone;
    private String password;
    //private ArrayList<Order> orderHistory;

    public User(int id, String email, String name, String address, String phone, String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.password = password;
    }

    public int getID() { return id; }
    public String getEmail() { return email; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }

    // Forgive me java, I have sinned.
    public String getPassword() { return password; }
}
