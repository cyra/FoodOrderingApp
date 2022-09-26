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

        if (this.getSize() == 0) {
            this.addAll();
        }
    }

    public int getSize() {
        return cartItems.size();
    }

    public void addCartItem(CartItem newCartItem) {
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

    public ArrayList<CartItem> getAllCartItems() {
        Cursor cursor = db.query(CartItemsTable.NAME, null, null, null, null, null, null);
        CartItemsDBCursor cartItemsDBCursor = new CartItemsDBCursor(cursor);
        ArrayList<CartItem> temp = new ArrayList<>();
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

    public void addAll() {
        this.addCartItem(new CartItem(getSize(), "Chicken Burger", "Chicken Burger", 10.00, "burger", 1, 1, 1, 10.00));
        System.out.println("added cartlist");
    }

}

