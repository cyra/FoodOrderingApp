package edu.curtin.foodapp.database;

public class DBSchema {
    // Users Table
    public static class UsersTable {
        public static final String NAME = "users";
        public static class Cols {
            public static final String ID = "id";
            public static final String EMAIL = "email";
            public static final String PASSWORD = "password";
            public static final String NAME = "name";
            public static final String ADDRESS = "address";
            public static final String PHONE = "phone";
        }
    }

    // Restaurants Table
    public static class RestaurantsTable {
        public static final String NAME = "restaurants";
        public static class Cols {
            public static final String ID = "id";
            public static final String NAME = "name";

        }
    }
    // Food items Table

    public static class FoodItemsTable {
        public static final String NAME = "fooditems";
        public static class Cols {
            public static final String ID = "id";
            public static final String NAME = "name";
            public static final String DETAILS = "details";
        }
    }

    // Orders Table
}
