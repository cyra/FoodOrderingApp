package edu.curtin.foodapp.database.restaurants;

import android.database.Cursor;
import android.database.CursorWrapper;

import edu.curtin.foodapp.model.restaurant.Restaurant;
import edu.curtin.foodapp.database.DBSchema.RestaurantsTable;

public class RestaurantsDBCursor extends CursorWrapper {
    public RestaurantsDBCursor(Cursor cursor) { super(cursor); }

    public Restaurant getRestaurant() {
        int id = getInt(getColumnIndex(RestaurantsTable.Cols.ID));
        String name = getString(getColumnIndex(RestaurantsTable.Cols.NAME));
        String img = getString(getColumnIndex(RestaurantsTable.Cols.IMG));

        return new Restaurant(id, name, img);
    }
}
