package edu.curtin.foodapp.model.orders;

public class OrderItem {
    String name;
    double price;
    int quantity;

    public OrderItem(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }


    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    // Get total price of the item with quantity
    public double getTotalPrice() {
        double price = this.price;
        int quantity = this.quantity;
        return price * (double) quantity;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
