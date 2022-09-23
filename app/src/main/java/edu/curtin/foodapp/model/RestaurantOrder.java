package edu.curtin.foodapp.model;

import java.util.ArrayList;

public class RestaurantOrder {
    String restaurantName;
    ArrayList<OrderItem> orderItems;

    public RestaurantOrder(String restaurantName, ArrayList<OrderItem> orderItems) {
        this.restaurantName = restaurantName;
        this.orderItems = orderItems;
    }

    // Setters and Getters
    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
