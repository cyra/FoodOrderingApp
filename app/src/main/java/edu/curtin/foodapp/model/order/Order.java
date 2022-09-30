package edu.curtin.foodapp.model.order;

import java.time.LocalDate;

public class Order {
    int orderID;
    int userID;
    String date;
    String description;

    public Order(int orderID, int userID) {
        this.orderID = orderID;
        this.userID = userID;
        this.date = LocalDate.now().toString();
        // Set description somehow????
    }

    // Constructor for database helper
    public Order(int orderID, int userID, String date, String description) {
        this.orderID = orderID;
        this.userID = userID;
        this.date = date;
        this.description = description;
    }


    public int getOrderID() { return orderID; }
    public int getUserID() { return userID; }
    public String getDate() { return date; }
    public String getDescription() { return description; }
}
