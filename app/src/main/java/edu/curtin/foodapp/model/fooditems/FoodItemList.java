package edu.curtin.foodapp.model.fooditems;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import edu.curtin.foodapp.database.DBSchema;
import edu.curtin.foodapp.database.DBSchema.FoodItemsTable;
import edu.curtin.foodapp.database.fooditems.FoodItemsDBCursor;
import edu.curtin.foodapp.database.fooditems.FoodItemsDBHelper;

public class FoodItemList {
    private ArrayList<FoodItem> foodItems;
    // The database connection
    private SQLiteDatabase db;

    public FoodItemList() {
        foodItems = new ArrayList<>();

    }

    public void load(Context context) {
        // Open database
        this.db = new FoodItemsDBHelper(context.getApplicationContext())
                .getWritableDatabase();
        // Read database contents into foodItems
        foodItems = getAllFoodItems();
        this.addAll();
    }

    // make this method return a list (this) for the adapter
    public ArrayList<FoodItem> read(Context context) {
        // Open database
        this.db = new FoodItemsDBHelper(context.getApplicationContext())
                .getWritableDatabase();
        foodItems = getAllFoodItems();
        this.addAll();
        return foodItems;

    }
    // Find food items by name and return an array of matches
    public ArrayList<FoodItem> select(Context context, String toFind) {
        this.load(context);
        return matchName(toFind);

    }
    public ArrayList<FoodItem> matchName(String toMatch) {
        ArrayList<FoodItem> randomList = new ArrayList<>();
        for (int i = 0; i < this.getSize(); i++) {
            if (this.getFoodItem(i).getName().equals(toMatch)) {
                randomList.add(this.getFoodItem(i));
            }
        }
        return randomList;
    }

    public void addAll() {
        // Just to make sure db is cleared on app install
        deleteAllFoodItems();
        if (this.getSize() == 0) {
            this.addFoodItem(new FoodItem(getSize(), "Burger", "Delicious burger", 20.55, "burger", 1));
            this.addFoodItem(new FoodItem(getSize(), "Pizza", "Cool pizza", 20.55, "pizza", 1));
            this.addFoodItem(new FoodItem(getSize(), "Pasta", "A pasta", 20.55, "pasta", 1));
            this.addFoodItem(new FoodItem(getSize(), "Burrito", "A delicious burrito", 20.55, "burrito", 1));
        }
    }


    public void deleteAllFoodItems() {
        for (int i = 0; i < this.getSize(); i++) {
            this.deleteFoodItem(i);

        }
    }

    public boolean deleteFoodItem(int id) {
        return db.delete(DBSchema.FoodItemsTable.NAME, DBSchema.FoodItemsTable.Cols.ID + "=?", new String[]{String.valueOf(id)}) > 0;
    }

    public int getSize() {
        return foodItems.size();
    }

    public FoodItem getFoodItem(int index) {
        return foodItems.get(index);
    }

    public void addFoodItem(FoodItem newFoodItem) {
        // Add foodItem to list
        this.foodItems.add(newFoodItem);
        // Add foodItem to database
        ContentValues cv = new ContentValues();
        cv.put(FoodItemsTable.Cols.ID, newFoodItem.getID());
        cv.put(FoodItemsTable.Cols.NAME, newFoodItem.getName());
        cv.put(FoodItemsTable.Cols.DESCRIPTION, newFoodItem.getDescription());
        cv.put(FoodItemsTable.Cols.PRICE, newFoodItem.getPrice());
        cv.put(FoodItemsTable.Cols.IMG, newFoodItem.getImg());
        cv.put(FoodItemsTable.Cols.RESTAURANTREF, newFoodItem.getRestaurantRef());

        db.insert(FoodItemsTable.NAME, null, cv);
    }


    public ArrayList<FoodItem> getAllFoodItems() {
        Cursor cursor = db.query(DBSchema.FoodItemsTable.NAME, null, null, null, null, null, null);
        FoodItemsDBCursor foodItemsDBCursor = new FoodItemsDBCursor(cursor);

        try {
            foodItemsDBCursor.moveToFirst();
            while (!foodItemsDBCursor.isAfterLast()) {
                foodItems.add(foodItemsDBCursor.getFoodItem());
                foodItemsDBCursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return foodItems;
    }
}
