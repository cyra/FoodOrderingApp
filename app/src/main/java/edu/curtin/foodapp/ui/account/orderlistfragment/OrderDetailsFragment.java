package edu.curtin.foodapp.ui.account.orderlistfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

import edu.curtin.foodapp.databinding.FragmentOrderDetailsBinding;
import edu.curtin.foodapp.model.cart.CartItem;
import edu.curtin.foodapp.model.cart.CartItemList;
import edu.curtin.foodapp.model.fooditem.FoodItem;
import edu.curtin.foodapp.model.fooditem.FoodItemList;
import edu.curtin.foodapp.model.restaurant.Restaurant;
import edu.curtin.foodapp.model.restaurant.RestaurantList;


public class OrderDetailsFragment extends Fragment {
    private FragmentOrderDetailsBinding binding;
    TextView orderReceipt;
    private CartItemList cart;
    private FoodItemList foodItemList;
    private RestaurantList restaurantList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        orderReceipt = binding.orderReceipt;

        // Create a stringbuilder for a receipt with restaurants, total prices and food names

        StringBuilder receipt = new StringBuilder();
        String dividers = "----------------------------------- \n";
        String title = printCenter("Order Receipt #", dividers.length());
        String date = printCenter("Date: 2021-05-01", dividers.length());
        String total = printCenter("Total: $", dividers.length());
        String restaurant = printCenter("Billy's Restaurant", dividers.length());
        receipt.append(dividers);
        receipt.append(title);
        receipt.append(dividers);
        receipt.append(date);
        receipt.append(dividers);
        // --------
        foodItemList = new FoodItemList();
        foodItemList.load(getContext());

        restaurantList = new RestaurantList();
        restaurantList.load(getContext());



        // End of receipt
        receipt.append(total);
        receipt.append(dividers);


        orderReceipt.setText(receipt.toString());

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

    }
}
