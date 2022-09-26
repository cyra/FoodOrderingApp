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

        if (this.getSize() == 0) {
            this.addAll();
        }
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

        ArrayList<FoodItem> temp = new ArrayList<FoodItem>();

        try {
            foodItemsDBCursor.moveToFirst();
            while (!foodItemsDBCursor.isAfterLast()) {
                temp.add(foodItemsDBCursor.getFoodItem());
                foodItemsDBCursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return temp;
    }

    public void addAll() {
        this.addFoodItem(new FoodItem(getSize(), "Burger", "Delicious burger", 20.55, "burger", 1));
        this.addFoodItem(new FoodItem(getSize(), "Pizza", "Cool pizza", 20.55, "pizza", 1));
        this.addFoodItem(new FoodItem(getSize(), "Pasta", "A pasta", 20.55, "pasta", 1));
        this.addFoodItem(new FoodItem(getSize(), "Burrito", "A delicious burrito", 20.55, "burrito", 1));
    }
}
