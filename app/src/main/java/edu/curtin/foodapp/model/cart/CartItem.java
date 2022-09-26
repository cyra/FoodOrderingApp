package edu.curtin.foodapp.model.cart;

import edu.curtin.foodapp.model.fooditems.FoodItem;

public class CartItem extends FoodItem {
    int userID;
    int quantity;
    double totalPrice;

    public CartItem(int foodId, String foodName, String description, double price, String foodImg, int resRef, int userID, int quantity, double totalPrice) {
        super(foodId, foodName, description, price, foodImg, resRef);
        this.userID = userID;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

   
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

