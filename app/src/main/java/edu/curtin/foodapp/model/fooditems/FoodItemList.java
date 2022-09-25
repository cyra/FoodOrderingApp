package edu.curtin.foodapp.model.fooditems;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

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
        this.db = new FoodItemsDBHelper(context)
                .getWritableDatabase();
        // Read database contents into foodItems
        foodItems = getAllFoodItems();
    }


    // make this method return a list (this) for the adapter
    public ArrayList<FoodItem> read(Context context) {
        // Open database
        this.db = new FoodItemsDBHelper(context)
                .getWritableDatabase();

        this.getAllFoodItems();
        this.addAll();
        return this.foodItems;

    }


    public void addAll() {
        //if (this.getSize() == 0) {
        this.addFoodItem(new FoodItem(getSize(), "Pizza", "very nice pizza", 20.55, ""));
        this.addFoodItem(new FoodItem(getSize(), "Pizza", "very nice pizza", 20.55, ""));

        this.addFoodItem(new FoodItem(getSize(), "Pizza", "very nice pizza", 20.55, ""));

        this.addFoodItem(new FoodItem(getSize(), "Pizza", "very nice pizza", 20.55, ""));

        // }
    }


    public int getSize() {
        return foodItems.size();
    }

    public FoodItem getFoodItem(int index) {
        return foodItems.get(index);
    }

    public void addFoodItem(FoodItem newFoodItem) {
        // Add foodItem to list
        foodItems.add(newFoodItem);
        // Add foodItem to database
        ContentValues cv = new ContentValues();
        cv.put(FoodItemsTable.Cols.ID, newFoodItem.getID());
        cv.put(FoodItemsTable.Cols.NAME, newFoodItem.getName());
        cv.put(FoodItemsTable.Cols.DESCRIPTION, newFoodItem.getDescription());
        cv.put(FoodItemsTable.Cols.PRICE, newFoodItem.getPrice());
        cv.put(FoodItemsTable.Cols.IMG, newFoodItem.getImg());

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
