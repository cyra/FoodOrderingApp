package edu.curtin.foodapp.model.cart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import edu.curtin.foodapp.database.DBSchema.CartItemsTable;
import edu.curtin.foodapp.database.carts.CartItemsDBCursor;
import edu.curtin.foodapp.database.carts.CartItemsDBHelper;
import edu.curtin.foodapp.ui.browse.BrowseViewModel;
import edu.curtin.foodapp.ui.cart.cartfragment.CartListViewHolder;
import edu.curtin.foodapp.ui.cart.cartfragment.CartViewModel;

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

        /* dont need to add all in cart
        if (this.getSize() == 0) {
            this.addAll();
        }*/
    }


    public CartItem getCartItem(int index) {
        return cartItems.get(index);
    }

    // get cartitem by id
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

    public void addQuantity(int cartID) {
        int originalQty = getCartItemByID(cartID).getQuantity();
        int updateQty = originalQty + 1;
        double originalPrice = getCartItemByID(cartID).getTotalPrice();
        double updateTotal = originalPrice + getCartItemByID(cartID).getPrice();
        String stringID = String.valueOf(cartID);
        try {
            String qtyQuery = "update " + CartItemsTable.NAME + " set " + CartItemsTable.Cols.QUANTITY + " = " + updateQty + " where " + CartItemsTable.Cols.ID + " = '" + stringID + "';";
            String totalQuery = "update " + CartItemsTable.NAME + " set " + CartItemsTable.Cols.TOTALPRICE + " = " + updateTotal + " where " + CartItemsTable.Cols.ID + " = '" + stringID + "';";
            db.execSQL(qtyQuery);
            db.execSQL(totalQuery);
            db.close();
            Log.v("DB", "Quantity and Price increased");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void minusQuantity(int cartID) {
        int originalQty = getCartItemByID(cartID).getQuantity();
        int updateQty = originalQty - 1;
        double originalPrice = getCartItemByID(cartID).getTotalPrice();
        double updateTotal = originalPrice - getCartItemByID(cartID).getPrice();
        String stringID = String.valueOf(cartID);
        // if quantity is 1, delete the item
        if (updateQty == 0) {
            deleteCartItem(cartID);
        } else {
            try {
                String qtyQuery = "update " + CartItemsTable.NAME + " set " + CartItemsTable.Cols.QUANTITY + " = " + updateQty + " where " + CartItemsTable.Cols.ID + " = '" + stringID + "';";
                String totalQuery = "update " + CartItemsTable.NAME + " set " + CartItemsTable.Cols.TOTALPRICE + " = " + updateTotal + " where " + CartItemsTable.Cols.ID + " = '" + stringID + "';";
                db.execSQL(qtyQuery);
                db.execSQL(totalQuery);
                db.close();
                Log.v("DB", "Quantity and Price decreased");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void deleteCartItem(int cartID) {
        String stringID = String.valueOf(cartID);
        try {
            String rawQuery = "delete from " + CartItemsTable.NAME + " where " + CartItemsTable.Cols.ID + " = '" + stringID + "';";
            db.execSQL(rawQuery);
            db.close();
            Log.v("DB", "Cart item deleted");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addORUpdateItem(CartItem item) {
        //String[] mAllColumns = {CartItemsTable.Cols.ID,CartItemsTable.Cols.NAME, CartItemsTable.Cols.DESCRIPTION, CartItemsTable.Cols.PRICE, CartItemsTable.Cols.IMG, CartItemsTable.Cols.RESTAURANTREF, CartItemsTable.Cols.USERID, CartItemsTable.Cols.QUANTITY, CartItemsTable.Cols.TOTALPRICE};
        //if the item does not exist, then create item id with item count as 0
        Cursor cur = db.rawQuery("SELECT * FROM " + CartItemsTable.NAME + " WHERE " + CartItemsTable.Cols.ID + "= '" + Integer.toString(item.getID()) + "'", null);
        if (cur.getCount() > 0) { // This will get the number of rows
            addQuantity(item.getID());
            Log.v("DB", "Item updated");
        } else {
            //insert
            addCartItem(item);
            Log.v("DB", "Item inserted");
        }
        cur.close();

    }

    public void addAll() {
        this.addCartItem(new CartItem(getSize(), "Chicken Burger", "Chicken Burger", 10.00, "burger", 1, 1, 1, 10.00));
        System.out.println("added cartlist");
    }

    // Calculate all cart total price and but check if the cart is empty
    public double getCartTotalPrice() {
        double totalPrice = 0;
        if (getAllCartItems().size() > 0) {
            for (CartItem item : getAllCartItems()) {
                totalPrice += item.getTotalPrice();
            }
        }
        return totalPrice;
    }


}

