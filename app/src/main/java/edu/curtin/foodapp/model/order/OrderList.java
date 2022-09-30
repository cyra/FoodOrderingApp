package edu.curtin.foodapp.model.order;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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


    public void addOrder(Order newOrder) {
        // Add order to list
        this.orders.add(newOrder);
        // Add order to database
        ContentValues cv = new ContentValues();
        cv.put(OrdersTable.Cols.ID, newOrder.getOrderID());
        cv.put(OrdersTable.Cols.USERID, newOrder.getUserID());
        cv.put(OrdersTable.Cols.DATE, newOrder.getDate());
        cv.put(OrdersTable.Cols.DESCRIPTION, newOrder.getDescription());

        db.insert(OrdersTable.NAME, null, cv);
    }


    public int getSize() { return orders.size(); }

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

        return temp;
    }
}
