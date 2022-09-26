package edu.curtin.foodapp.database.fooditems;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.curtin.foodapp.database.DBSchema.FoodItemsTable;

public class FoodItemsDBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "fooditems.db";

    public FoodItemsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + FoodItemsTable.NAME + "(" +
                FoodItemsTable.Cols.ID + " INTEGER, " +
                FoodItemsTable.Cols.NAME + " TEXT, " +
                FoodItemsTable.Cols.DESCRIPTION + " TEXT, " +
                FoodItemsTable.Cols.PRICE + " DOUBLE, " +
                FoodItemsTable.Cols.IMG + " TEXT, " +
                FoodItemsTable.Cols.RESTAURANTREF + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int v1, int v2) {
        // Empty function needed
    }
}
