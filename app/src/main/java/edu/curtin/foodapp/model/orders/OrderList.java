package edu.curtin.foodapp.model.orders;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

import edu.curtin.foodapp.database.DBSchema.OrdersTable;
import edu.curtin.foodapp.database.orders.OrderListDBHelper;
import edu.curtin.foodapp.database.orders.OrderListDBCursor;

public class OrderList {
    private ArrayList<Order> orders;
    // The database connection
    private SQLiteDatabase db;

    public OrderList() {
        orders = new ArrayList<Order>();
    }

    // Load items from the database
    public void load(Context context) {
        // Open database
        this.db = new OrderListDBHelper(context.getApplicationContext())
                .getWritableDatabase();
        // Read database contents into orders
        orders = getAllOrders();
    }

    public ArrayList<Order> getAllOrders() {
        Cursor cursor = db.query(OrdersTable.NAME, null, null, null, null, null, null);
        OrderListDBCursor orderListDBCursor = new OrderListDBCursor(cursor);

        ArrayList<Order> temp = new ArrayList<Order>();
        try {
            orderListDBCursor.moveToFirst();
            while (!orderListDBCursor.isAfterLast()) {
                temp.add(orderListDBCursor.getOrder());
                orderListDBCursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }

        // TODO: remove this
        if (temp.isEmpty()) {
            temp.add(new Order(1, 42));
        }

        return temp;
    }
}
