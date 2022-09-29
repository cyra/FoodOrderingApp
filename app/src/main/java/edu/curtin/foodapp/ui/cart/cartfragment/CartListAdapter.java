package edu.curtin.foodapp.ui.cart.cartfragment;

import edu.curtin.foodapp.databinding.SingleCartFoodItemBinding;
import edu.curtin.foodapp.model.cart.CartItem;
import edu.curtin.foodapp.model.cart.CartItemList;
import edu.curtin.foodapp.model.restaurant.RestaurantList;

import android.content.Context;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;


public class CartListAdapter extends RecyclerView.Adapter<CartListViewHolder> {
    final Context context;
    SingleCartFoodItemBinding binding;
    final ArrayList<CartItem> cartItems;
    //CartItemList cart = new CartItemList();
    RestaurantList restaurantList;
    private final CartViewModel cartViewModel;

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
        position = holder.getAdapterPosition();
        CartItem currentItem = cartItems.get(position);

        int restaurantNumber = currentItem.getRestaurantRef();
        String restaurantName = restaurantList.getRestaurant(restaurantNumber).getName();
        holder.itemName.setText(currentItem.getName());
        System.out.println("name set");
        holder.itemQuantity.setText(String.valueOf(currentItem.getQuantity()));
        holder.itemPrice.setText(String.valueOf(currentItem.getPrice()));
        holder.restaurantName.setText(restaurantName);
        currentItem.setTotalPrice(currentItem.getPrice() * currentItem.getQuantity());
        holder.itemTotal.setText(String.valueOf(currentItem.getTotalPrice()));

        updateTotalCartPrice(cart);

        holder.plusButton.setOnClickListener(view -> {
            CartItemList cartDB = new CartItemList();
            cartDB.load(view.getContext());
            addQuantity(cartDB, holder, holder.getAdapterPosition());
        });
        holder.minusButton.setOnClickListener(view -> {
            CartItemList cart1 = new CartItemList();
            cart1.load(view.getContext());
            minusQuantity(cart1, holder, holder.getAdapterPosition());
        });

        // If empty show placeholder
        if (!cartItems.get(position).getImg().isEmpty()) {
            holder.itemImg.setImageResource(getImage(cartItems.get(position).getImg()));
        }

    }

    private void minusQuantity(CartItemList cartDB, @NonNull CartListViewHolder holder, int position) {
        int quantity = Integer.parseInt(holder.itemQuantity.getText().toString());
        CartItem currentItem = cartItems.get(position);
        if (quantity > 1) {
            quantity--;
            //cart.minusQuantity(currentItem.getID());
            currentItem.setQuantity(cartDB.minusQuantity(currentItem.getID()));
            currentItem.setTotalPrice(currentItem.getTotalPrice() - currentItem.getPrice());
            holder.itemQuantity.setText(String.valueOf(quantity));
            String roundedItemTotal = String.format(Locale.ENGLISH, "%.2f", currentItem.getTotalPrice());
            holder.itemTotal.setText(roundedItemTotal);
            updateTotalCartPrice(cartDB);
        } else {
            cartDB.deleteCartItem(currentItem.getID());
            cartItems.remove(holder.getAdapterPosition());
            updateTotalCartPrice(cartDB);
            // somehow recyclerview crashes without this
            notifyDataSetChanged();
        }
    }


    private void updateTotalCartPrice(CartItemList cart) {
        cartViewModel.setTotalCart(cart.getCartTotalPrice());
    }

    private void addQuantity(CartItemList cartDB, @NonNull CartListViewHolder holder, int position) {
        int quantity = Integer.parseInt(holder.itemQuantity.getText().toString());
        quantity++;
        CartItem currentItem = cartItems.get(position);
        currentItem.setQuantity(cartDB.addQuantity(currentItem.getID()));
        currentItem.setTotalPrice(currentItem.getTotalPrice() + currentItem.getPrice());
        holder.itemQuantity.setText(String.valueOf(quantity));
        String roundedItemTotal = String.format(Locale.ENGLISH, "%.2f", currentItem.getTotalPrice());
        holder.itemTotal.setText(roundedItemTotal);
        updateTotalCartPrice(cartDB);

    }

    public int getImage(String imageName) {
        return context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }
}
