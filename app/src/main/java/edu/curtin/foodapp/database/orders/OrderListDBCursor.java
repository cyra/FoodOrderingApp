package edu.curtin.foodapp.database.orders;

import android.database.Cursor;
import android.database.CursorWrapper;

import edu.curtin.foodapp.database.DBSchema.OrdersTable;
import edu.curtin.foodapp.model.orders.Order;
import edu.curtin.foodapp.model.orders.OrderList;

public class OrderListDBCursor extends CursorWrapper {
    public OrderListDBCursor(Cursor cursor) { super(cursor); }

    public Order getOrder() {
        int id = getInt(getColumnIndex(OrdersTable.Cols.ID));
        int userId = getInt(getColumnIndex(OrdersTable.Cols.USERID));
        String date = getString(getColumnIndex(OrdersTable.Cols.DATE));
        String description = getString(getColumnIndex(OrdersTable.Cols.DESCRIPTION));

        return new Order(id, userId, date, description);
    }
}
