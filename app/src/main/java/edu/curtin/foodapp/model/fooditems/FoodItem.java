package edu.curtin.foodapp.model.fooditems;

import edu.curtin.foodapp.model.restaurant.Restaurant;

public class FoodItem extends Restaurant {
    int ID;
    String name;
    String description;
    double price;
    String img;
    int restaurantRef;

    public FoodItem(int foodId, String foodName, String description, double price, String foodImg, int restaurantID) {
        super(restaurantID, null, null);
        this.ID = foodId;
        this.name = foodName;
        this.description = description;
        this.price = price;
        this.img = foodImg;
        this.restaurantRef = restaurantID;
    }


    // Setters and Getters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getRestaurantRef() {return restaurantRef;}

    public void setRestaurantRef(int restaurantRef) {
        this.restaurantRef = restaurantRef;
    }
}
