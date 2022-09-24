package edu.curtin.foodapp.model.orders;

import java.util.ArrayList;

public class Orders {
    int orderID;
    String userID;
    String date;
    String description;
    ArrayList<RestaurantOrder>orders;

    public Orders(int orderID, String userID, String date, String description) {
        this.orderID = orderID;
        this.userID = userID;
        this.date = date;
        this.description = description;
    }

    // Setters and Getters
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    // Calculate total of all restaurants in this order
    public float getTotalOrderPrice(){
        float total = 0;
        for (RestaurantOrder order: getOrders()){
            float restaurantTotal = order.getRestaurantTotal();
            total += restaurantTotal;
        }
        return total;
    }

}
