package edu.curtin.foodapp.database.fooditems;

import android.database.Cursor;
import android.database.CursorWrapper;

import edu.curtin.foodapp.database.DBSchema.FoodItemsTable;
import edu.curtin.foodapp.model.fooditem.FoodItem;

public class FoodItemsDBCursor extends CursorWrapper {
    public FoodItemsDBCursor(Cursor cursor) { super(cursor); }

    public FoodItem getFoodItem() {
        int id = getInt(getColumnIndex(FoodItemsTable.Cols.ID));
        String name = getString(getColumnIndex(FoodItemsTable.Cols.NAME));
        String description = getString(getColumnIndex(FoodItemsTable.Cols.DESCRIPTION));
        double price = getDouble(getColumnIndex(FoodItemsTable.Cols.PRICE));
        String img = getString(getColumnIndex(FoodItemsTable.Cols.IMG));
        int restaurantRef = getInt(getColumnIndex(FoodItemsTable.Cols.RESTAURANTREF));

        return new FoodItem(id, name, description, price, img, restaurantRef);
    }
}
