package edu.curtin.foodapp.database.fooditems;

import android.database.Cursor;
import android.database.CursorWrapper;

import edu.curtin.foodapp.database.DBSchema.FoodItemsTable;
import edu.curtin.foodapp.model.fooditems.FoodItem;

public class FoodItemsDBCursor extends CursorWrapper {
    public FoodItemsDBCursor(Cursor cursor) { super(cursor); }

    public FoodItem getFoodItem() {
        int id = getInt(getColumnIndex(FoodItemsTable.Cols.ID));
        String name = getString(getColumnIndex(FoodItemsTable.Cols.NAME));
        String description = getString(getColumnIndex(FoodItemsTable.Cols.DESCRIPTION));
        float price = getInt(getColumnIndex(FoodItemsTable.Cols.PRICE));
        String img = getString(getColumnIndex(FoodItemsTable.Cols.IMG));

        return new FoodItem(id, name, description, price, img);
    }
}
