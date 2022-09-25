package edu.curtin.foodapp.model.user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import edu.curtin.foodapp.database.DBSchema.UsersTable;
import edu.curtin.foodapp.database.users.UsersDBCursor;
import edu.curtin.foodapp.database.users.UsersDBHelper;

public class UserList {
    private ArrayList<User> users;
    // The database connection
    private SQLiteDatabase db;

    public UserList() {
        users = new ArrayList<User>();
    }

    public void load(Context context) {
        // Open database
        this.db = new UsersDBHelper(context.getApplicationContext())
                .getWritableDatabase();

        // Read database contents into users
        users = getAllUsers();
    }

    public int getSize() { return users.size(); }
    public User getUser(int index) { return users.get(index); }

    public void addUser(User newUser) {
        // Add user to list
        users.add(newUser);
        // Add user to database
        ContentValues cv = new ContentValues();
        cv.put(UsersTable.Cols.ID, newUser.getID());
        cv.put(UsersTable.Cols.EMAIL, newUser.getEmail());
        cv.put(UsersTable.Cols.PASSWORD, newUser.getPassword());
        cv.put(UsersTable.Cols.NAME, newUser.getName());
        cv.put(UsersTable.Cols.ADDRESS, newUser.getAddress());
        cv.put(UsersTable.Cols.PHONE, newUser.getPhone());
        db.insert(UsersTable.NAME, null, cv);
    }

    public int findIndexByLogin(String email, String password) {
        for (int i = 0; i < users.size(); i++) {
            User temp = users.get(i);

            if (temp.getEmail().equals(email)) {
                if (temp.getPassword().equals(password)) {
                    return i;
                }
                else {
                    return -1;
                }
            }
        }
        return -1;
    }


    private ArrayList<User> getAllUsers() {
        Cursor cursor = db.query(UsersTable.NAME, null, null, null, null, null, null);
        UsersDBCursor usersDBCursor = new UsersDBCursor(cursor);

        try {
            usersDBCursor.moveToFirst();
            while (!usersDBCursor.isAfterLast()) {
                users.add(usersDBCursor.getUser());
                usersDBCursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }

        return users;
    }
}
