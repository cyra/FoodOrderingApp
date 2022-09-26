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
        String name = getString(getColumnIndex(CartItemsTable.Cols.NAME));
        String description = getString(getColumnIndex(CartItemsTable.Cols.DESCRIPTION));
        double price = getDouble(getColumnIndex(CartItemsTable.Cols.PRICE));
        String img = getString(getColumnIndex(CartItemsTable.Cols.IMG));
        int restaurantRef = getInt(getColumnIndex(CartItemsTable.Cols.RESTAURANTREF));
        int userID = getInt(getColumnIndex(CartItemsTable.Cols.USERID));
        int quantity = getInt(getColumnIndex(CartItemsTable.Cols.QUANTITY));
        int totalPrice = getInt(getColumnIndex(CartItemsTable.Cols.TOTALPRICE));

        return new CartItem(id, name, description, price, img, restaurantRef, userID, quantity, totalPrice);
    }
}
