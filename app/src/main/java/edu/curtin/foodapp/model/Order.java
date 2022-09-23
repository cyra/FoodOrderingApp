package edu.curtin.foodapp.model;

import java.util.ArrayList;

public class Order {
    int orderID;
    String date;
    ArrayList<RestaurantOrder>orders;
    String orderDescription;

    public Order(int orderID, String date, ArrayList<RestaurantOrder> orders) {
        this.orderID = orderID;
        this.date = date;
        this.orders = orders;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<RestaurantOrder> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<RestaurantOrder> orders) {
        this.orders = orders;
    }



}
