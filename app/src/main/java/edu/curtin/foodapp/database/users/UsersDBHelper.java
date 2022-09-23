package edu.curtin.foodapp.database.users;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.curtin.foodapp.database.DBSchema.UsersTable;

public class UsersDBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "users.db";

    public UsersDBHelper(Context context) { super(context, DATABASE_NAME, null, VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + UsersTable.NAME + "(" +
                UsersTable.Cols.ID + " INTEGER, " +
                UsersTable.Cols.EMAIL + " TEXT, " +
                UsersTable.Cols.PASSWORD + " TEXT, " +
                UsersTable.Cols.NAME + " TEXT, " +
                UsersTable.Cols.ADDRESS + " TEXT, " +
                UsersTable.Cols.PHONE + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int v1, int v2) {
        // Empty function needed
    }
}
