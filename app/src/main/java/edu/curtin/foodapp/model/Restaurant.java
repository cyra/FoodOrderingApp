package edu.curtin.foodapp.model;

import java.util.ArrayList;

public class Restaurant {
    String restaurantName;

    int restaurantID;
    String restaurantImg;
    ArrayList<FoodItem>menu;



    public Restaurant(String restaurantName, int restaurantID, String restaurantImg, ArrayList<FoodItem> menu) {
        this.restaurantName = restaurantName;
        this.restaurantID = restaurantID;
        this.restaurantImg = restaurantImg;
        this.menu = menu;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getRestaurantImg() {
        return restaurantImg;
    }

    public void setRestaurantImg(String restaurantImg) {
        this.restaurantImg = restaurantImg;
    }

    public ArrayList<FoodItem> getMenu() {
        return menu;
    }

    public void setMenu(ArrayList<FoodItem> menu) {
        this.menu = menu;
    }


}
