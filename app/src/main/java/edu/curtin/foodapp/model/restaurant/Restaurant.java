package edu.curtin.foodapp.model.restaurant;

import java.util.ArrayList;

import edu.curtin.foodapp.model.fooditems.FoodItem;

public class Restaurant {
    int ID;
    String name;
    String img;
    ArrayList<FoodItem> menu;



    public Restaurant(int restaurantID, String restaurantName, String restaurantImg) {
        this.ID = restaurantID;
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
