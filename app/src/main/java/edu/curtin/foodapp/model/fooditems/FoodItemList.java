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
        this.addFoodItem(new FoodItem(getSize(), "Hutt Dogs", "Hutt Dogs by Jabba!", 7.40, "food_hutt_dogs", FORK_BE_WITH_YOU_ID));
        this.addFoodItem(new FoodItem(getSize(), "Jabba Juice", "A Green Juice", 6.50, "food_jabba_juice", FORK_BE_WITH_YOU_ID));
        this.addFoodItem(new FoodItem(getSize(), "Han Burger", "The Fastest Burger in Space", 9.50, "food_han_burger", FORK_BE_WITH_YOU_ID));
        this.addFoodItem(new FoodItem(getSize(), "Yoda Soda", "Thirsty Are You?", 5.20, "food_yoda_soda", FORK_BE_WITH_YOU_ID));
        this.addFoodItem(new FoodItem(getSize(), "Space Buns", "...", 4.50, "food_space_buns", FORK_BE_WITH_YOU_ID));
        this.addFoodItem(new FoodItem(getSize(), "Imperial Wings", "Dark Meat", 12.00, "food_imperial_wings", FORK_BE_WITH_YOU_ID));
        this.addFoodItem(new FoodItem(getSize(), "Cuke Skywalker", "Cuke Salad", 9.75, "food_cuke_skywalker", FORK_BE_WITH_YOU_ID));

        // Guga's Kitchen
        this.addFoodItem(new FoodItem(getSize(), "Wagyu A5 Steak", "S, P & G", 55.70, "food_wagyu_a5", GUGAS_KITCHEN_ID));
        this.addFoodItem(new FoodItem(getSize(), "Potatoes", "A Comforting Side Dish", 5.50, "food_potatoes", GUGAS_KITCHEN_ID));

        // Life of Pi
        this.addFoodItem(new FoodItem(getSize(), "3.14159", "A Pie of Pi", 3.14, "food_pi", LIFE_OF_PI_ID));

        // Lord of the Wings
        this.addFoodItem(new FoodItem(getSize(), "Hot Chicken Wings", "Beware.", 8.75, "food_hot_wings", LORD_OF_THE_WINGS_ID));
        this.addFoodItem(new FoodItem(getSize(), "Onion Rings", "My Precious", 6.50, "food_onion_rings", LORD_OF_THE_WINGS_ID));

        // Spaghettea Monster
        this.addFoodItem(new FoodItem(getSize(), "Bubble Tea", "A Bubble Tea that doesn't bite!", 6.50, "food_bubble_tea", SPAGHETTEA_MONSTER_ID));
        this.addFoodItem(new FoodItem(getSize(), "Spaghetti", "Innocent Spaghetti", 12.30, "food_spaghetti", SPAGHETTEA_MONSTER_ID));
        this.addFoodItem(new FoodItem(getSize(), "Corndogs", "Surprise Each bite!", 7.30, "food_corndogs", SPAGHETTEA_MONSTER_ID));
        this.addFoodItem(new FoodItem(getSize(), "Milk Tea", "A Simple Milk Tea", 5.50, "food_milktea", SPAGHETTEA_MONSTER_ID));
        this.addFoodItem(new FoodItem(getSize(), "Honey Butter Chicken", "Umami Madness!", 13.50, "food_honey_chicken", SPAGHETTEA_MONSTER_ID));

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
