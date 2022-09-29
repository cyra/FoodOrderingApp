package edu.curtin.foodapp.model.cart;

import edu.curtin.foodapp.model.fooditems.FoodItem;
import edu.curtin.foodapp.model.fooditems.FoodItemList;

public class CartItem {
    int id;
    double price;
    int quantity;

    public CartItem(int id, double price, int quantity) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getID() { return id; }
    public double getPrice() { return price; }
    public int getQuantity() {
        return quantity;
    }
    public double getTotalPrice() {
        return price * quantity;
    }
}