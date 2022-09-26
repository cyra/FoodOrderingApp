package edu.curtin.foodapp.model.cart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import edu.curtin.foodapp.database.DBSchema.CartItemsTable;
import edu.curtin.foodapp.database.carts.CartItemsDBCursor;
import edu.curtin.foodapp.database.carts.CartItemsDBHelper;
import edu.curtin.foodapp.model.cart.CartItem;

public class CartItemList {
    private ArrayList<CartItem> cartItems;
    // The database connection
    private SQLiteDatabase db;

    public CartItemList() {
        cartItems = new ArrayList<>();
    }

    // Load items from the database
    public void load(Context context) {
        // Open database
        this.db = new CartItemsDBHelper(context.getApplicationContext())
                .getWritableDatabase();
        // Read database contents into foodItems
        cartItems = getAllCartItems();
        this.addAll();
    }

    // make this method return a list (this) for the adapter
    public ArrayList<CartItem> read(Context context) {
        // Open database
        this.db = new CartItemsDBHelper(context.getApplicationContext())
                .getWritableDatabase();
        cartItems = getAllCartItems();
        this.addAll();
        return cartItems;

    }

    // Find cart items selected by name and return an array of matches
    public ArrayList<CartItem> selectCart(Context context, String toFind) {
        this.load(context);
        return matchName(toFind);

    }

    // Match name for cart item
    public ArrayList<CartItem> matchName(String toFind) {
        ArrayList<CartItem> matches = new ArrayList<>();
        for (CartItem item : cartItems) {
            if (item.getName().toLowerCase().contains(toFind.toLowerCase())) {
                matches.add(item);
            }
        }
        return matches;
    }

    public void addAll() {
        // Just to make sure db is cleared on app install
       deleteAllCartItems();
        if (this.getSize() == 0) {

            this.addCartItem(new CartItem(1, "Chicken Burger", "Chicken Burger", 10.00, "burger", 1, 1, 1, 10.00));
        }

    }

    // Delete All Cart Items
    public void deleteAllCartItems() {
        for (int i = 0; i < this.getSize(); i++) {
            this.deleteCartItem(i);
        }
    }

    // Delete CartItem from database
    public boolean deleteCartItem(int id) {
        return db.delete(CartItemsTable.NAME, CartItemsTable.Cols.ID + "=?", new String[]{String.valueOf(id)}) > 0;
    }

    // Get Cart Item Size
    public int getSize() {
        return cartItems.size();
    }

    // Get a Cart Item from database
    public CartItem getCartItem(int index) {
        return cartItems.get(index);
    }


// Add Cart item to database
    public void addCartItem(CartItem newCartItem){
        ContentValues cv = new ContentValues();
        cv.put(CartItemsTable.Cols.ID, newCartItem.getID());
        cv.put(CartItemsTable.Cols.NAME, newCartItem.getName());
        cv.put(CartItemsTable.Cols.DESCRIPTION, newCartItem.getDescription());
        cv.put(CartItemsTable.Cols.PRICE, newCartItem.getPrice());
        cv.put(CartItemsTable.Cols.IMG, newCartItem.getImg());
        cv.put(CartItemsTable.Cols.RESTAURANTREF, newCartItem.getRestaurantRef());
        cv.put(CartItemsTable.Cols.USERID, newCartItem.getUserID());
        cv.put(CartItemsTable.Cols.QUANTITY, newCartItem.getQuantity());
        cv.put(CartItemsTable.Cols.TOTALPRICE, newCartItem.getTotalPrice());
        db.insert(CartItemsTable.NAME, null, cv);

    }

    // Get all cart items
    public ArrayList<CartItem> getAllCartItems() {
        Cursor cursor = db.query(CartItemsTable.NAME, null, null, null, null, null, null);
        CartItemsDBCursor cartItemsDBCursor = new CartItemsDBCursor(cursor);
        try {
            cursor.moveToFirst();
            while (!cartItemsDBCursor.isAfterLast()) {
                cartItems.add(cartItemsDBCursor.getCartItem());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return cartItems;
    }
}

