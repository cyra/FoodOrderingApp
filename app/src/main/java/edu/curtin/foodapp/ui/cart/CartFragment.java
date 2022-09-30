package edu.curtin.foodapp.ui.cart;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import edu.curtin.foodapp.MainActivity;
import edu.curtin.foodapp.R;
import edu.curtin.foodapp.databinding.FragmentCartBinding;
import edu.curtin.foodapp.ui.cart.cartfragment.CartListFragment;
import edu.curtin.foodapp.model.cart.CartItem;
import edu.curtin.foodapp.model.cart.CartItemList;
import edu.curtin.foodapp.model.fooditem.FoodItem;
import edu.curtin.foodapp.model.fooditem.FoodItemList;
import edu.curtin.foodapp.model.order.Order;
import edu.curtin.foodapp.model.order.OrderList;
import edu.curtin.foodapp.model.restaurant.Restaurant;
import edu.curtin.foodapp.model.restaurant.RestaurantList;
import edu.curtin.foodapp.ui.account.AccountViewModel;

// Cart Fragment
public class CartFragment extends Fragment {

    private FragmentCartBinding binding;

    private CartViewModel cartViewModel;
    private AccountViewModel accountViewModel;

    private OrderList orders;
    private CartItemList cart;
    private FoodItemList foodItemList;
    private RestaurantList restaurantList;

    private ExtendedFloatingActionButton fab;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cartViewModel = ((MainActivity) getActivity()).getCartViewModel();
        accountViewModel = ((MainActivity) getActivity()).getAccountViewModel();

        Context context = getContext();
        // Load orders database
        orders = new OrderList();
        orders.load(context);
        // Load cartItems database
        cart = new CartItemList();
        cart.load(context);
        // Load foodItems database
        foodItemList = new FoodItemList();
        foodItemList.load(context);
        // Load restaurants database
        restaurantList = new RestaurantList();
        restaurantList.load(context);

        binding = FragmentCartBinding.inflate(inflater, container, false);

        fab = binding.cartFab;

        cartViewModel.getTotalCart().observe((getViewLifecycleOwner()), total -> {
            String textTotal = "Checkout - $" + String.format("%.2f", total);
            fab.setText(textTotal);
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        insertNestedFragment();

        // If checkout button clicked
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If cart isn't empty
                if (cart.getSize() > 0) {
                    // If user is logged in
                    if (accountViewModel.getLoggedIn()) {
                        Order order = prepareOrder();
                        // Add order to database
                        orders.addOrder(order);
                        // Clear cart
                        cart.removeAllCartItems();
                        // Set checkout total
                        cartViewModel.setTotalCart(cart.getCartTotalPrice());
                    }
                    else {
                        Toast.makeText(getContext(), "Log in and try again", Toast.LENGTH_SHORT).show();
                    }

                    // Navigate to account page
                    Navigation.findNavController(view).navigate(R.id.action_navigation_cart_to_navigation_account);
                }
                else {
                    Toast.makeText(getContext(), "Cart empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Order prepareOrder() {
        int orderId = orders.getSize() + 1;
        int userId = accountViewModel.getUser().getValue().getID();
        String description = prepareOrderDescription();

        return new Order(orderId, userId, description);
    }

    private String prepareOrderDescription() {
        String description = "";

        for (CartItem item : cart.getAllCartItems()) {
            FoodItem foodItem = foodItemList.getFoodItemByID(item.getID());
            Restaurant restaurant = restaurantList.getRestaurantByID(foodItem.getRestaurantRef());

            String restaurantName = restaurant.getName();
            String foodName = foodItem.getName();
            String price = String.format("%.2f", item.getTotalPrice());
            String quantity = String.valueOf(item.getQuantity());

            description += restaurantName + "," + foodName + "," + price + "," + quantity + ",";
        }
        // Substring removes trailing comma
        return description.substring(0, description.length() - 1);
    }


    // Embeds the child fragment dynamically
    private void insertNestedFragment() {
        Fragment CartListFragment = new CartListFragment();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.cartListFragment, CartListFragment).commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}