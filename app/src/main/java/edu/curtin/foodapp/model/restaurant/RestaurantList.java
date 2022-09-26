package edu.curtin.foodapp.model.restaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.lang.reflect.Array;
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
        // Read database contents into restaurants
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

        ArrayList<Restaurant> temp = new ArrayList<Restaurant>();

        try {
            restaurantsDBCursor.moveToFirst();
            while (!restaurantsDBCursor.isAfterLast()) {
                temp.add(restaurantsDBCursor.getRestaurant());
                restaurantsDBCursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return temp;
    }


    /**
     * When new restaurants join the app, their entry in the database is added here.
     * The app must be uninstalled/reinstalled or the database wiped to see the new changes.
     */
    public void addAll() {
        this.addRestaurant(new Restaurant(getSize(), "Fork Be With You", "restaurant_fork_be_with_you"));
        this.addRestaurant(new Restaurant(getSize(), "Guga's Kitchen", "restaurant_gugas_kitchen"));
        this.addRestaurant(new Restaurant(getSize(), "Life of Pi", "restaurant_life_of_pi"));
        this.addRestaurant(new Restaurant(getSize(), "Lord of the Wings", "restaurant_lord_of_the_wings"));
        this.addRestaurant(new Restaurant(getSize(), "Spaghettea Monster", "restaurant_spaghettea_monster"));
        this.addRestaurant(new Restaurant(getSize(), "Hungry Zak's", "restaurant_hungry_zaks"));
        this.addRestaurant(new Restaurant(getSize(), "Salad World", "restaurant_salad_world"));
        this.addRestaurant(new Restaurant(getSize(), "Cow Still Mooing (DIY)", "restaurant_cow_still_mooing"));
        this.addRestaurant(new Restaurant(getSize(), "Burritos in a Box", "restaurant_burritos_in_a_box"));
        this.addRestaurant(new Restaurant(getSize(), "Joust Kebabs", "restaurant_joust_kebabs"));
    }
}
