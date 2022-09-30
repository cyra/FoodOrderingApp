package edu.curtin.foodapp.database.carts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.curtin.foodapp.database.DBSchema.CartItemsTable;

public class CartItemsDBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "cartitems.db";

    public CartItemsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + CartItemsTable.NAME + "(" +
                CartItemsTable.Cols.ID + " INTEGER, " +
                CartItemsTable.Cols.PRICE + " DOUBLE, " +
                CartItemsTable.Cols.QUANTITY + " TEXT)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Empty
    }
}
