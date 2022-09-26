package edu.curtin.foodapp.model.restaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

import edu.curtin.foodapp.MainActivity;
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

        restaurants = getAllRestaurants();

        if (getSize() == 0) {
            addAll();
        }
    }

    public int getSize() {
        return restaurants.size();
    }

    public Restaurant getRestaurant(int index) {
        return restaurants.get(index);
    }

    public void addRestaurant(Restaurant newRestaurant) {
        restaurants.add(newRestaurant);
        // Add restaurant to database
        ContentValues cv = new ContentValues();
        cv.put(RestaurantsTable.Cols.ID, newRestaurant.getID());
        cv.put(RestaurantsTable.Cols.NAME, newRestaurant.getName());
        cv.put(RestaurantsTable.Cols.IMG, newRestaurant.getImg());
        //cv.put(RestaurantsTable.Cols.MENU, newRestaurant.getMenu());
        db.insert(RestaurantsTable.NAME, null, cv);
    }

    public ArrayList<Restaurant> getAllRestaurants() {
        Cursor cursor = db.query(RestaurantsTable.NAME, null, null, null, null, null, null);
        RestaurantsDBCursor restaurantsDBCursor = new RestaurantsDBCursor(cursor);

        try {
            restaurantsDBCursor.moveToFirst();
            while (!restaurantsDBCursor.isAfterLast()) {
                restaurants.add(restaurantsDBCursor.getRestaurant());
                restaurantsDBCursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return restaurants;
    }


    public void addAll() {
        // Add restaurants here
        this.addRestaurant(new Restaurant(getSize(), "Pizza Place", "pizza"));
        this.addRestaurant(new Restaurant(getSize(), "Pasta Place", "pasta"));
        this.addRestaurant(new Restaurant(getSize(), "Burger Place", "burger"));
    }
}
