package edu.curtin.foodapp.ui.cart.cartfragment;

import edu.curtin.foodapp.MainActivity;
import edu.curtin.foodapp.databinding.SingleCartFoodItemBinding;
import edu.curtin.foodapp.model.cart.CartItem;
import edu.curtin.foodapp.model.cart.CartItemList;
import edu.curtin.foodapp.model.restaurant.RestaurantList;

import android.content.Context;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;


public class CartListAdapter extends RecyclerView.Adapter<CartListViewHolder> {
    Context context;
    SingleCartFoodItemBinding binding;
    ArrayList<CartItem> cartItems;
    //CartItemList cart = new CartItemList();
    RestaurantList restaurantList;
    private CartViewModel cartViewModel;

    public CartListAdapter(Context context, ArrayList<CartItem> cartItems, CartViewModel cartViewModel) {
        this.context = context;
        this.cartItems = cartItems;
        this.cartViewModel = cartViewModel;
    }

    @NonNull
    @Override
    public CartListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = SingleCartFoodItemBinding.inflate(LayoutInflater.from(context), parent, false);

        return new CartListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListViewHolder holder, int position) {
        // save into database
        // load context first before adding
        CartItemList cart = new CartItemList();
        cart.load(context);

        RestaurantList restaurantList = new RestaurantList();
        restaurantList.load(context);

        int restaurantNumber = cartItems.get(position).getRestaurantRef();
        String restaurantName = restaurantList.getRestaurant(restaurantNumber).getName();
        holder.itemName.setText(cartItems.get(position).getName());
        System.out.println("name set");
        holder.itemQuantity.setText(String.valueOf(cartItems.get(position).getQuantity()));
        holder.itemPrice.setText(String.valueOf(cartItems.get(position).getPrice()));
        holder.restaurantName.setText(restaurantName);
        holder.itemTotal.setText(String.valueOf(cartItems.get(position).getTotalPrice()));
        String totalCartPrice = String.valueOf(cart.getCartTotalPrice());
        cartViewModel.setTotalCart(totalCartPrice);

        holder.plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartItemList cart = new CartItemList();
                cart.load(view.getContext());
                int quantity = Integer.parseInt(holder.itemQuantity.getText().toString());
                quantity++;
                cart.addQuantity(cartItems.get(position).getID());
                cartItems.get(position).setQuantity(quantity);
                cartItems.get(position).setTotalPrice(cartItems.get(position).getTotalPrice() + cartItems.get(position).getPrice());
                holder.itemQuantity.setText(String.valueOf(quantity));
                String roundedItemTotal = String.format(Locale.ENGLISH, "%.2f", cartItems.get(position).getTotalPrice());
                holder.itemTotal.setText(roundedItemTotal);
                String totalCartPrice = String.valueOf(cart.getCartTotalPrice());
                System.out.println("total price: "+totalCartPrice);
                cartViewModel.setTotalCart(totalCartPrice);
            }
        });
        holder.minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartItemList cart = new CartItemList();
                cart.load(view.getContext());
                // if quantity is 1, remove item from recyclerview
                int quantity = Integer.parseInt(holder.itemQuantity.getText().toString());
                if (quantity > 1) {
                    quantity--;
                    cart.minusQuantity(cartItems.get(position).getID());
                    cartItems.get(position).setQuantity(quantity);
                    cartItems.get(position).setTotalPrice(cartItems.get(position).getTotalPrice() - cartItems.get(position).getPrice());
                    holder.itemQuantity.setText(String.valueOf(quantity));
                    String roundedItemTotal = String.format(Locale.ENGLISH,"%.2f", cartItems.get(position).getTotalPrice());
                    holder.itemTotal.setText(roundedItemTotal);
                    String totalCartPrice = String.valueOf(cart.getCartTotalPrice());
                    cartViewModel.setTotalCart(totalCartPrice);


                } else {
                    cart.deleteCartItem(cartItems.get(position).getID());
                    cartItems.remove(position);
                    String totalCartPrice = String.valueOf(cart.getCartTotalPrice());
                    cartViewModel.setTotalCart(totalCartPrice);

                    // somehow recyclerview crashes without this
                    notifyDataSetChanged();
                }
            }
        });

        // If empty show placeholder
        if (!cartItems.get(position).getImg().isEmpty()) {
            holder.itemImg.setImageResource(getImage(cartItems.get(position).getImg()));
        }

    }


    public int getImage(String imageName) {
        int drawableResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        return drawableResourceId;
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }
}
