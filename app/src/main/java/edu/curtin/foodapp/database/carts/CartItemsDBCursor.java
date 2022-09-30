package edu.curtin.foodapp.database.carts;

import android.database.Cursor;
import android.database.CursorWrapper;

import edu.curtin.foodapp.database.DBSchema.CartItemsTable;
import edu.curtin.foodapp.model.cart.CartItem;

// Cursor for CartItem
public class CartItemsDBCursor extends CursorWrapper {
    public CartItemsDBCursor(Cursor cursor) { super(cursor); }

    public CartItem getCartItem() {
        int id = getInt(getColumnIndex(CartItemsTable.Cols.ID));
        double price = getDouble(getColumnIndex(CartItemsTable.Cols.PRICE));
        int quantity = getInt(getColumnIndex(CartItemsTable.Cols.QUANTITY));

        return new CartItem(id, price, quantity);
    }
}
