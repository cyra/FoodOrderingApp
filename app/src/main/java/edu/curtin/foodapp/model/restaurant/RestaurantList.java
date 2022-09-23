package edu.curtin.foodapp.model.restaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import edu.curtin.foodapp.database.DBSchema.RestaurantsTable;
import edu.curtin.foodapp.database.restaurants.RestaurantsDBCursor;
import edu.curtin.foodapp.database.restaurants.RestaurantsDBHelper;

public class RestaurantList {
    private ArrayList<Restaurant> restaurants;
    // The database connection
    private SQLiteDatabase db;

    public RestaurantList() {
        restaurants = new ArrayList<>();
    }

    public void load(Context context) {
        // Open database
        this.db = new RestaurantsDBHelper(context.getApplicationContext())
                .getWritableDatabase();
        // Read database contents into restaurants
        restaurants = getAllRestaurants();
    }

    public int getSize() { return restaurants.size(); }
    public Restaurant getRestaurant(int index) { return restaurants.get(index); }

    public void addRestaurant(Restaurant newRestaurant) {
        // Add restaurant to list
        restaurants.add(newRestaurant);
        // Add restaurant to database
        ContentValues cv = new ContentValues();
        cv.put(RestaurantsTable.Cols.ID, newRestaurant.getID());
        cv.put(RestaurantsTable.Cols.NAME, newRestaurant.getName());
        cv.put(RestaurantsTable.Cols.IMG, newRestaurant.getImg());
        db.insert(RestaurantsTable.NAME, null, cv);
    }


    private ArrayList<Restaurant> getAllRestaurants() {
        Cursor cursor = db.query(RestaurantsTable.NAME, null, null, null, null, null, null);
        RestaurantsDBCursor restaurantsDBCursor = new RestaurantsDBCursor(cursor);

        try {
            restaurantsDBCursor.moveToFirst();
            while (!restaurantsDBCursor.isAfterLast()) {
                restaurants.add(restaurantsDBCursor.getRestaurant());
                restaurantsDBCursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }

        return restaurants;
    }
}
