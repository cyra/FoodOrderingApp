package edu.curtin.foodapp.model.fooditems;

public class FoodItem {
    int ID;
    String name;
    String description;
    double price;
    String img;
    int restaurantRef;

    public FoodItem(int foodId, String foodName, String description, double price, String foodImg, int resRef) {
        this.ID = foodId;
        this.name = foodName;
        this.description = description;
        this.price = price;
        this.img = foodImg;
        this.restaurantRef = resRef;
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
