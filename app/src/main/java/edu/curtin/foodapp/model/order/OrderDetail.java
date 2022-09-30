package edu.curtin.foodapp.model.order;

public class OrderDetail {
    private String restaurantName;
    private String itemName;
    private double totalPrice;
    private int quantity;

    public OrderDetail(String restaurantName, String itemName, double totalPrice, int quantity) {
        this.restaurantName = restaurantName;
        this.itemName = itemName;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
    }


    public String getRestaurantName() { return restaurantName; }
    public String getItemName() { return itemName; }
    public double getTotalPrice() { return totalPrice; }
    public int getQuantity() { return quantity; }
}