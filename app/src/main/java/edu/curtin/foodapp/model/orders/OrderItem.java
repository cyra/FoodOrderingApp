package edu.curtin.foodapp.model.orders;



public class OrderItem {
    String name;
    float price;
    int quantity;

    public OrderItem(String name, float price, int quantity) {
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

    public float getPrice() {
        return price;
    }

    // Get total price of the item with quantity
    public float getTotalPrice() {
        float price = this.price;
        int quantity = this.quantity;
        return price * (float) quantity;
    }
    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
