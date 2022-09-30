package edu.curtin.foodapp.ui.account.orderlistfragment;

import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

import edu.curtin.foodapp.MainActivity;
import edu.curtin.foodapp.databinding.FragmentOrderDetailsBinding;
import edu.curtin.foodapp.model.cart.CartItem;
import edu.curtin.foodapp.model.cart.CartItemList;
import edu.curtin.foodapp.model.fooditem.FoodItem;
import edu.curtin.foodapp.model.fooditem.FoodItemList;
import edu.curtin.foodapp.model.order.Order;
import edu.curtin.foodapp.model.order.OrderDetail;
import edu.curtin.foodapp.model.order.OrderList;
import edu.curtin.foodapp.model.restaurant.Restaurant;
import edu.curtin.foodapp.model.restaurant.RestaurantList;
import edu.curtin.foodapp.ui.account.AccountViewModel;


public class OrderDetailsFragment extends Fragment {
    private FragmentOrderDetailsBinding binding;

    TextView orderReceipt;

    private OrderList orders;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Context context = getContext();
        // Load orders
        orders = new OrderList();
        orders.load(context);

        orderReceipt = binding.orderReceipt;

        AccountViewModel accountViewModel = ((MainActivity) getActivity()).getAccountViewModel();
        Order order = orders.getOrderByID(accountViewModel.getOrderID().getValue());

        ArrayList<OrderDetail> orderDetails = order.getOrderDetails();

        // Create a stringbuilder for a receipt with restaurants, total prices and food names
        StringBuilder receipt = new StringBuilder();
        String dividers = "----------------------------------- \n";
        String title = printCenter("Order Receipt #" + order.getOrderID(), dividers.length());
        String date = printCenter("Date: " + order.getDate(), dividers.length());
        String total = printCenter("Total: $" + String.format("%.2f", order.getTotalPrice()), dividers.length());

        receipt.append(dividers);
        receipt.append(title);
        receipt.append(date);
        receipt.append(dividers);
        // --------

        // Initial detail
        String restaurantName = orderDetails.get(0).getRestaurantName();
        receipt.append("\n");
        String restaurantLine = printCenter("[" + orderDetails.get(0).getRestaurantName() + "]", dividers.length());
        receipt.append(restaurantLine);

        for (OrderDetail detail : orderDetails) {
            if (!restaurantName.equals(detail.getRestaurantName())) {
                receipt.append("\n");
                restaurantLine = printCenter("[" + detail.getRestaurantName() + "]", dividers.length());
                receipt.append(restaurantLine);
                restaurantName = detail.getRestaurantName();
            }

            String itemLine = printCenter(detail.getQuantity() + "x "+ detail.getItemName() + " - " +
                    "$" + String.format("%.2f", detail.getTotalPrice()), dividers.length());
            receipt.append(itemLine);
        }
        // End of receipt
        receipt.append(dividers);
        receipt.append(total);
        receipt.append(dividers);
        receipt.append("\n\n");

        orderReceipt.setText(receipt.toString());
        // Make scrollable
        orderReceipt.setMovementMethod(new ScrollingMovementMethod());

        return root;
    }

    public static String printCenter(String str, int span) {
        StringBuilder finalString = new StringBuilder();
        int spacesToAdd = (span - str.length()) / 2;
        for (int i = 0; i < spacesToAdd; i++) {
            finalString.append(" ");
        }
        finalString.append(str);
        finalString.append("\n");
        return finalString.toString();
    }


    // Columns
    public static String printColumns(String str1, String str2, int span) {
        StringBuilder finalString = new StringBuilder();
        finalString.append(str1);
        int spacesToAdd = (span - (str1.length() + str2.length()) / 2);
        for (int i = 0; i < spacesToAdd; i++) {
            finalString.append(" ");
        }
        finalString.append(str2);
        finalString.append("\n");
        return finalString.toString();
    }

    // Print strings in two columns


    public void onViewCreated(View view, Bundle savedInstanceState) {
        //
    }
}
