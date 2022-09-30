package edu.curtin.foodapp.model.cart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import edu.curtin.foodapp.database.DBSchema.CartItemsTable;
import edu.curtin.foodapp.database.carts.CartItemsDBCursor;
import edu.curtin.foodapp.database.carts.CartItemsDBHelper;

public class CartItemList {
    private ArrayList<CartItem> cartItems;
    // The database connection
    private SQLiteDatabase db;

    public CartItemList() {
        cartItems = new ArrayList<CartItem>();
    }

    // Load items from the database
    public void load(Context context) {
        // Open database
        this.db = new CartItemsDBHelper(context.getApplicationContext())
                .getWritableDatabase();
        // Read database contents into cartItems
        cartItems = getAllCartItems();
    }


    public CartItem getCartItemByID(int id) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getID() == id) {
                return cartItem;
            }
        }
        return null;
    }

    public int getSize() {
        return cartItems.size();
    }

    public double getCartTotalPrice() {
        double total = 0.0;

        for (CartItem item : cartItems) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public CartItem getCartItem(int index) {
        return cartItems.get(index);
    }

    public ArrayList<CartItem> getAllCartItems() {
        Cursor cursor = db.query(CartItemsTable.NAME, null, null, null, null, null, null);
        CartItemsDBCursor cartItemsDBCursor = new CartItemsDBCursor(cursor);
        ArrayList<CartItem> temp = new ArrayList<CartItem>();
        try {
            cartItemsDBCursor.moveToFirst();
            while (!cartItemsDBCursor.isAfterLast()) {
                temp.add(cartItemsDBCursor.getCartItem());
                cartItemsDBCursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return temp;
    }


    public void editCartItem(CartItem item) {
        ContentValues cv = new ContentValues();
        cv.put(CartItemsTable.Cols.ID, item.getID());
        cv.put(CartItemsTable.Cols.QUANTITY, item.getQuantity());
        cv.put(CartItemsTable.Cols.PRICE, item.getPrice());

        String[] whereID = { String.valueOf(item.getID()) };
        db.update(CartItemsTable.NAME, cv, CartItemsTable.Cols.ID + " = ?", whereID);
    }

    public void addCartItem(CartItem newCartItem) {
        ContentValues cv = new ContentValues();
        cv.put(CartItemsTable.Cols.ID, newCartItem.getID());
        cv.put(CartItemsTable.Cols.PRICE, newCartItem.getPrice());
        cv.put(CartItemsTable.Cols.QUANTITY, newCartItem.getQuantity());
        db.insert(CartItemsTable.NAME, null, cv);
    }

    public void removeCartItemByID(int id) {
        // Find item
        CartItem item = getCartItemByID(id);

        // Remove from list
        cartItems.remove(item);
        // Remove from database
        String[] whereID = { String.valueOf(id) };
        db.delete(CartItemsTable.NAME, CartItemsTable.Cols.ID + " = ?", whereID);
    }

    public void removeAllCartItems() {
        // Remove items from database
        for (int index = cartItems.size() - 1; index >= 0; index--) {
            removeCartItemByID(cartItems.get(index).getID());
        }
        // Remove items from list
        cartItems.clear();
    }
}

