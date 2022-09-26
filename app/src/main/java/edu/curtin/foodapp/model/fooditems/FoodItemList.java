package edu.curtin.foodapp.model.fooditems;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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

    public ArrayList<FoodItem> getRestaurantMenu(int restaurantID) {
        ArrayList<FoodItem> menu = new ArrayList<FoodItem>();

        for (int i = 0; i < this.getSize(); i++) {
            FoodItem temp = foodItems.get(i);

            if (temp.getRestaurantRef() == restaurantID) {
                menu.add(temp);
            }
        }

        return menu;
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

    /**
     * When new restaurants join the app, their food items in the database is added here.
     * The app must be uninstalled/reinstalled or the database wiped to see the new changes.
     */
    public void addAll() {
        final int FORK_BE_WITH_YOU_ID = 0;
        final int GUGAS_KITCHEN_ID = 1;
        final int LIFE_OF_PI_ID = 2;
        final int LORD_OF_THE_WINGS_ID = 3;
        final int SPAGHETTEA_MONSTER_ID = 4;
        final int HUNGRY_ZAKS_ID = 5;
        final int SALAD_WORLD_ID = 6;
        final int COW_STILL_MOOING_ID = 7;
        final int SOUP_A_BOWL_ID = 8;
        final int JOUST_KEBABS_ID = 9;

        // Fork Be With You
        this.addFoodItem(new FoodItem(getSize(), "Hutt Dogs", "...", 7.40, "food_whatever", FORK_BE_WITH_YOU_ID));
        this.addFoodItem(new FoodItem(getSize(), "Jabba Juice", "...", 7.40, "food_whatever", FORK_BE_WITH_YOU_ID));
        this.addFoodItem(new FoodItem(getSize(), "Han Burger", "...", 7.40, "food_whatever", FORK_BE_WITH_YOU_ID));
        this.addFoodItem(new FoodItem(getSize(), "Yoda Soda", "...", 7.40, "food_whatever", FORK_BE_WITH_YOU_ID));
        this.addFoodItem(new FoodItem(getSize(), "Space Buns", "...", 7.40, "food_whatever", FORK_BE_WITH_YOU_ID));
        this.addFoodItem(new FoodItem(getSize(), "Imperial Wings", "...", 7.40, "food_whatever", FORK_BE_WITH_YOU_ID));
        this.addFoodItem(new FoodItem(getSize(), "Cuke Skywalker", "...", 7.40, "food_whatever", FORK_BE_WITH_YOU_ID));

        // Guga's Kitchen
        this.addFoodItem(new FoodItem(getSize(), "Wagyu A5 Steak", "...", 7.40, "food_whatever", GUGAS_KITCHEN_ID));
        this.addFoodItem(new FoodItem(getSize(), "Potatoes", "...", 7.40, "food_whatever", GUGAS_KITCHEN_ID));

        // Life of Pi
        this.addFoodItem(new FoodItem(getSize(), "3.14159", "...", 7.40, "food_whatever", LIFE_OF_PI_ID));

        // Lord of the Wings
        this.addFoodItem(new FoodItem(getSize(), "Hot Chicken Wings", "...", 7.40, "food_whatever", LORD_OF_THE_WINGS_ID));
        this.addFoodItem(new FoodItem(getSize(), "Onion Rings", "...", 7.40, "food_whatever", LORD_OF_THE_WINGS_ID));

        // Spaghettea Monster
        this.addFoodItem(new FoodItem(getSize(), "Bubble Tea", "...", 7.40, "food_whatever", SPAGHETTEA_MONSTER_ID));
        this.addFoodItem(new FoodItem(getSize(), "Spaghetti", "...", 7.40, "food_whatever", SPAGHETTEA_MONSTER_ID));
        this.addFoodItem(new FoodItem(getSize(), "Corndogs", "...", 7.40, "food_whatever", SPAGHETTEA_MONSTER_ID));
        this.addFoodItem(new FoodItem(getSize(), "Milk Tea", "...", 7.40, "food_whatever", SPAGHETTEA_MONSTER_ID));
        this.addFoodItem(new FoodItem(getSize(), "Honey Butter Fried Chicken", "...", 7.40, "food_whatever", SPAGHETTEA_MONSTER_ID));

        // Hungry Zak's
        this.addFoodItem(new FoodItem(getSize(), "Cheeseburger", "...", 7.40, "food_whatever", HUNGRY_ZAKS_ID));
        this.addFoodItem(new FoodItem(getSize(), "Omega-Whopper", "...", 7.40, "food_whatever", HUNGRY_ZAKS_ID));
        this.addFoodItem(new FoodItem(getSize(), "Baconator", "...", 7.40, "food_whatever", HUNGRY_ZAKS_ID));
        this.addFoodItem(new FoodItem(getSize(), "Tomato Terror", "...", 7.40, "food_whatever", HUNGRY_ZAKS_ID));
        this.addFoodItem(new FoodItem(getSize(), "Literally Just Chips", "...", 7.40, "food_whatever", HUNGRY_ZAKS_ID));
        this.addFoodItem(new FoodItem(getSize(), "FrainBreeze", "...", 7.40, "food_whatever", HUNGRY_ZAKS_ID));

        // Salad World
        this.addFoodItem(new FoodItem(getSize(), "Lettuce", "Some crisp lettuce", 3.60, "food_whatever", SALAD_WORLD_ID));
        this.addFoodItem(new FoodItem(getSize(), "Tomatoes", "Some sliced tomatoes", 4.10, "food_whatever", SALAD_WORLD_ID));
        this.addFoodItem(new FoodItem(getSize(), "Lettuce & Tomatoes", "Lettuce and tomatoes, but together", 5.60, "food_whatever", SALAD_WORLD_ID));

        // Cow Still Mooing
        this.addFoodItem(new FoodItem(getSize(), "Angus", "...", 3.60, "food_whatever", COW_STILL_MOOING_ID));
        this.addFoodItem(new FoodItem(getSize(), "Braford", "...", 3.60, "food_whatever", COW_STILL_MOOING_ID));
        this.addFoodItem(new FoodItem(getSize(), "Hereford", "...", 3.60, "food_whatever", COW_STILL_MOOING_ID));

        // Soup-a-bowl
        this.addFoodItem(new FoodItem(getSize(), "Chicken Soup", "...", 3.60, "food_whatever", SOUP_A_BOWL_ID));
        this.addFoodItem(new FoodItem(getSize(), "Pumpkin Soup", "...", 3.60, "food_whatever", SOUP_A_BOWL_ID));
        this.addFoodItem(new FoodItem(getSize(), "Tomato Soup", "...", 3.60, "food_whatever", SOUP_A_BOWL_ID));

        // Joust Kebabs
        this.addFoodItem(new FoodItem(getSize(), "Good Knight Kebab", "...", 3.60, "food_whatever", JOUST_KEBABS_ID));
        this.addFoodItem(new FoodItem(getSize(), "Lanced Lamb Kebab", "...", 3.60, "food_whatever", JOUST_KEBABS_ID));
    }

}
