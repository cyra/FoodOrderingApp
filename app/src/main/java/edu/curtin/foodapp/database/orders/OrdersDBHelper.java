package edu.curtin.foodapp.database.orders;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.curtin.foodapp.database.DBSchema.OrdersTable;

public class OrdersDBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "orders.db";

    public OrdersDBHelper(Context context) { super(context, DATABASE_NAME, null, VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + OrdersTable.NAME + "(" +
                OrdersTable.Cols.ID + " INTEGER, " +
                OrdersTable.Cols.USERID + " TEXT, " +
                OrdersTable.Cols.DATE + " TEXT, " +
                OrdersTable.Cols.DESCRIPTION + "TEXT) "
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int v1, int v2) {
        // Empty function needed
    }
}
