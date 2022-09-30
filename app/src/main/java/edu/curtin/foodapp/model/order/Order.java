package edu.curtin.foodapp.model.order;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class Order {
    int orderID;
    int userID;
    String date;
    String description;

    public Order(int orderID, int userID, String description) {
        this.orderID = orderID;
        this.userID = userID;
        this.date = LocalDate.now().toString();
        this.description = description;
    }

    // Constructor for database helper
    public Order(int orderID, int userID, String date, String description) {
        this.orderID = orderID;
        this.userID = userID;
        this.date = date;
        this.description = description;
    }


    public int getOrderID() { return orderID; }
    public int getUserID() { return userID; }
    public String getDate() { return date; }
    public String getDescription() { return description; }

    public double getTotalPrice() {
        double total = 0.0;
        String[] tokens = description.split(",");

        for (int i = 2; i < tokens.length; i += 4) {
            total += Double.parseDouble(tokens[i]);
        }
        return total;
    }

    public ArrayList<OrderDetail> getOrderDetails() {
        ArrayList<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
        String[] tokens = description.split(",");

        for (int i = 0; i < tokens.length; i += 4) {
            String restaurantName = tokens[i];
            String itemName = tokens[i + 1];
            double totalPrice = Double.parseDouble(tokens[i + 2]);
            int quantity = Integer.parseInt(tokens[i + 3]);

            orderDetails.add(new OrderDetail(restaurantName, itemName, totalPrice, quantity));
        }

        orderDetails = sortOrderDetails(orderDetails);

        return orderDetails;
    }

    private ArrayList<OrderDetail> sortOrderDetails(ArrayList<OrderDetail> orderDetails) {
        for (int i = 1; i < orderDetails.size(); i++) {
            OrderDetail key = orderDetails.get(i);
            int j = i - 1;

            while (j >= 0 && orderDetails.get(j).getRestaurantName().compareTo(key.getRestaurantName()) > 0) {
                OrderDetail temp = orderDetails.remove(j + 1);
                orderDetails.add(j, temp);
                j--;
            }

            orderDetails.remove(j + 1);
            orderDetails.add(j + 1, key);
        }

        return orderDetails;
    }
}
