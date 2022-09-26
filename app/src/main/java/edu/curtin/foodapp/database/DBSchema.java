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
            public static final String IMG = "img";
            //public static final String MENU = "menu";

        }
    }
    // Food items Table

public static class FoodItemsTable {
        public static final String NAME = "fooditems";
        public static class Cols {
            public static final String ID = "id";
            public static final String NAME = "name";
            public static final String DESCRIPTION = "description";
            public static final String PRICE = "price";
            public static final String IMG = "img";
            public static final String RESTAURANTREF = "restaurantref";


        }
}
    // Orders Table

    public static class OrdersTable{
        public static final String NAME = "orders";
        public static class Cols {
            public static final String ID = "id";
            public static final String USERID = "userid";
            public static final String DATE = "date";
            public static final String DESCRIPTION = "description";

        }
    }

    // CartItems Table

    public static class CartItemsTable{
        public static final String NAME = "cartitems";
        public static class Cols {
            public static final String ID = "id";
            public static final String NAME = "name";
            public static final String DESCRIPTION = "description";
            public static final String PRICE = "price";
            public static final String IMG = "img";
            public static final String RESTAURANTREF = "restaurantref";
            public static final String USERID = "userid";
            public static final String QUANTITY = "quantity";
            public static final String TOTALPRICE = "totalprice";
        }
    }

}
