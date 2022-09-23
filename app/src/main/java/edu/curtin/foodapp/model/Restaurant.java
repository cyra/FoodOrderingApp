package edu.curtin.foodapp.model;

import java.util.ArrayList;

public class Restaurant {
    int id;
    String name;
    String img;
    ArrayList<FoodItem>menu;



    public Restaurant(int restaurantID, String restaurantName, String restaurantImg) {
        this.id = restaurantID;
        this.name = restaurantName;
        this.img = restaurantImg;
        // this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public ArrayList<FoodItem> getMenu() {
        return menu;
    }

    public void setMenu(ArrayList<FoodItem> menu) {
        this.menu = menu;
    }


}
