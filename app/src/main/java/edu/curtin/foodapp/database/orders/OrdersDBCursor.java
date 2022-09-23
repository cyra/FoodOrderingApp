package edu.curtin.foodapp.database.orders;

import android.database.Cursor;
import android.database.CursorWrapper;

import edu.curtin.foodapp.database.DBSchema.OrdersTable;
import edu.curtin.foodapp.model.orders.Orders;

public class OrdersDBCursor extends CursorWrapper {
    public OrdersDBCursor(Cursor cursor) { super(cursor); }

    public Orders getOrders() {
        int id = getInt(getColumnIndex(OrdersTable.Cols.ID));
        String userId = getString(getColumnIndex(OrdersTable.Cols.USERID));
        String date = getString(getColumnIndex(OrdersTable.Cols.DATE));
        String description = getString(getColumnIndex(OrdersTable.Cols.DESCRIPTION));

        return new Orders(id, userId, date, description);
    }
}
