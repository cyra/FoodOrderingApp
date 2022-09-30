package edu.curtin.foodapp.database.restaurants;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.curtin.foodapp.database.DBSchema.RestaurantsTable;

public class RestaurantsDBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "restaurants.db";

    public RestaurantsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + RestaurantsTable.NAME + "(" +
                RestaurantsTable.Cols.ID + " INTEGER, " +
                RestaurantsTable.Cols.NAME + " TEXT, " +
                RestaurantsTable.Cols.IMG + " TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int v1, int v2) {
        // Empty function needed
    }
}
