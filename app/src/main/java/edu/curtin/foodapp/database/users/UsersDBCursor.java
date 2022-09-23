package edu.curtin.foodapp.database.users;

import android.database.Cursor;
import android.database.CursorWrapper;

import edu.curtin.foodapp.model.user.User;
import edu.curtin.foodapp.database.DBSchema.UsersTable;

public class UsersDBCursor extends CursorWrapper {
    public UsersDBCursor(Cursor cursor) { super(cursor); }

    public User getUser() {
        int id = getInt(getColumnIndex(UsersTable.Cols.ID));
        String email = getString(getColumnIndex(UsersTable.Cols.EMAIL));
        String name = getString(getColumnIndex(UsersTable.Cols.NAME));
        String address = getString(getColumnIndex(UsersTable.Cols.ADDRESS));
        String phone = getString(getColumnIndex(UsersTable.Cols.PHONE));

        return new User(id, email, name, address, phone);
    }
}
