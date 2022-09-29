package edu.curtin.foodapp.model.cart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import edu.curtin.foodapp.database.DBSchema.CartItemsTable;
import edu.curtin.foodapp.database.carts.CartItemsDBCursor;
import edu.curtin.foodapp.database.carts.CartItemsDBHelper;

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

    public int addQuantity(int cartID) {
        int originalQty = getCartItemByID(cartID).getQuantity();
        int updateQty = originalQty + 1;
        double originalPrice = getCartItemByID(cartID).getTotalPrice();
        double updateTotal = originalPrice + getCartItemByID(cartID).getPrice();
        String stringID = String.valueOf(cartID);
        String stringTotal = String.valueOf(updateTotal);
        String stringQty = String.valueOf(updateQty);
        try {
            updateTotalItemPrice(cartID);
            String qtyQuery = "update " + CartItemsTable.NAME + " set " + CartItemsTable.Cols.QUANTITY + " = " + stringQty + " where " + CartItemsTable.Cols.ID + " = '" + stringID + "';";
            //String totalQuery = "update " + CartItemsTable.NAME + " set " + CartItemsTable.Cols.TOTALPRICE + " = " + stringTotal + " where " + CartItemsTable.Cols.ID + " = '" + stringID + "';";

            db.execSQL(qtyQuery);
            //db.execSQL(totalQuery);
            //db.close();
            Log.v("DB", "Quantity and Price increased");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return updateQty;
    }

    public int minusQuantity(int cartID) {
        int originalQty = getCartItemByID(cartID).getQuantity();
        int updateQty = originalQty - 1;
        double originalPrice = getCartItemByID(cartID).getTotalPrice();
        double updateTotal = originalPrice - getCartItemByID(cartID).getPrice();
        getCartItemByID(cartID).setQuantity(updateQty);
        getCartItemByID(cartID).setTotalPrice(updateTotal);
        String stringID = String.valueOf(cartID);
        String stringQty = String.valueOf(updateQty);
        String stringTotal = String.valueOf(updateTotal);


        // if quantity is 1, delete the item
        if (updateQty == 0) {
            deleteCartItem(cartID);
        } else {
            try {
                String qtyQuery = "update " + CartItemsTable.NAME + " set " + CartItemsTable.Cols.QUANTITY + " = " + stringQty + " where " + CartItemsTable.Cols.ID + " = '" + stringID + "';";
                //String totalQuery = "update " + CartItemsTable.NAME + " set " + CartItemsTable.Cols.TOTALPRICE + " = " + stringTotal + " where " + CartItemsTable.Cols.ID + " = '" + stringID + "';";
                updateTotalItemPrice(cartID);
                db.execSQL(qtyQuery);
                //db.execSQL(totalQuery);
                //db.close();
                Log.v("DB", "Quantity and Price decreased");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return updateQty;
    }

    public void deleteCartItem(int cartID) {
        String stringID = String.valueOf(cartID);
        try {
            String rawQuery = "delete from " + CartItemsTable.NAME + " where " + CartItemsTable.Cols.ID + " = '" + stringID + "';";
            updateTotalItemPrice(cartID);
            db.execSQL(rawQuery);
            //db.close();
            Log.v("DB", "Cart item deleted");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // minus total item price in db
    public void updateTotalItemPrice(int cartID) {
        //double originalPrice = getCartItemByID(cartID).getTotalPrice();
        double quantity = getCartItemByID(cartID).getQuantity();
        double price = getCartItemByID(cartID).getPrice();
        double updateTotal = quantity * price;
        getCartItemByID(cartID).setTotalPrice(updateTotal);
        String stringID = Integer.toString(cartID);
        String stringTotal = Double.toString(updateTotal);
        Log.v("DB", "Total price updated" + stringTotal);
        try {
            String totalQuery = "update " + CartItemsTable.NAME + " set " + CartItemsTable.Cols.TOTALPRICE + " = " + stringTotal + " where " + CartItemsTable.Cols.ID + " = '" + stringID + "';";
            db.execSQL(totalQuery);
            //db.close();
            Log.v("DB", "Item total price updated " + stringTotal);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // add total item price in db
    public void addTotalPrice(int cartID) {
        double originalPrice = getCartItemByID(cartID).getTotalPrice();
        double updateTotal = originalPrice + getCartItemByID(cartID).getPrice();
        getCartItemByID(cartID).setTotalPrice(updateTotal);
        String stringID = String.valueOf(cartID);
        String stringTotal = String.valueOf(updateTotal);
        try {
            String totalQuery = "update " + CartItemsTable.NAME + " set " + CartItemsTable.Cols.TOTALPRICE + " = " + stringTotal + " where " + CartItemsTable.Cols.ID + " = '" + stringID + "';";
            db.execSQL(totalQuery);
            //db.close();
            Log.v("DB", "Item total price increased " + stringTotal);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void addORUpdateItem(CartItem item) {
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

    // Calculate all cart total price and but check if the cart is empty
    public double getCartTotalPrice() {
        double totalPrice = 0.0;
        if (getAllCartItems().size() > 0) {
            for (CartItem item : this.getAllCartItems()) {
                totalPrice += item.getTotalPrice();
                Log.v("DB", "price: " + item.getTotalPrice());
                Log.v("DB", "Total price: " + totalPrice);
            }
        }
        return totalPrice;
    }
}

