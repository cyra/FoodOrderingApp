package edu.curtin.foodapp.model.restaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import edu.curtin.foodapp.MainActivity;
import edu.curtin.foodapp.database.DBSchema.RestaurantsTable;
import edu.curtin.foodapp.database.restaurants.RestaurantsDBCursor;
import edu.curtin.foodapp.database.restaurants.RestaurantsDBHelper;
import edu.curtin.foodapp.model.fooditems.FoodItem;

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

    public Restaurant getRestaurantByID(int id) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getID() == id) {
                return restaurant;
            }
        }
        return null;
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

    public Restaurant getRandomRestaurant() {
        // Set seed as current date
        LocalDate currentDate = LocalDate.now();
        Random rng = new Random(localDateToLong(currentDate));
        int n = rng.nextInt(restaurants.size());

        return restaurants.get(n);
    }

    private long localDateToLong(LocalDate date) {
        String dateStr = String.valueOf(date);
        return Long.parseLong(String.join("", dateStr.split("-")));
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
        this.addRestaurant(new Restaurant(getSize(), "Soup-a-bowl", "restaurant_soup_a_bowl"));
        this.addRestaurant(new Restaurant(getSize(), "Joust Kebabs", "restaurant_joust_kebabs"));
    }
}
