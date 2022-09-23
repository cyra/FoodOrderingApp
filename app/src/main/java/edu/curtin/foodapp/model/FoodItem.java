package edu.curtin.foodapp.model;

public class FoodItem {
    int ID;
    String name;
    String description;
    float price;
    String img;

    public FoodItem(int foodId, String foodName, String description, float price, String foodImg) {
        this.ID = foodId;
        this.name = foodName;
        this.description = description;
        this.price = price;
        this.img = foodImg;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
