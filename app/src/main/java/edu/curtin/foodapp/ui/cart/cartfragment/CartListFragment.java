package edu.curtin.foodapp.ui.cart.cartfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import edu.curtin.foodapp.R;
import edu.curtin.foodapp.databinding.FragmentCartBinding;
import edu.curtin.foodapp.databinding.ListCartBinding;
import edu.curtin.foodapp.databinding.ListDailyFoodItemBinding;
import edu.curtin.foodapp.model.cart.CartItemList;
import edu.curtin.foodapp.model.fooditems.FoodItem;
import edu.curtin.foodapp.model.fooditems.FoodItemList;
import edu.curtin.foodapp.ui.account.AccountViewModel;
import edu.curtin.foodapp.ui.browse.fooditemfragment.FoodItemViewAdapter;
import edu.curtin.foodapp.ui.home.fooditemfragment.DailyFoodItemListAdapter;

public class CartListFragment extends Fragment {

    ListCartBinding binding;
    FragmentCartBinding cartBinding;
    ExtendedFloatingActionButton fab;
    private CartItemList cartItemList;
    private CartViewModel cartViewModel;
    private String totalPrice;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        cartItemList = new CartItemList();
        cartItemList.load(getContext());
        CartViewModel cartViewModel = new ViewModelProvider(getActivity(),
                (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory()).get(CartViewModel.class);
        totalPrice = cartViewModel.getTotalCart();

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = ListCartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final RecyclerView rv = binding.recyclerview;

        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        CartListAdapter rvAdapter = new CartListAdapter(getContext(), cartItemList.getAllCartItems(),cartViewModel);
        rv.setAdapter(rvAdapter);
        // Set fab text to checkout + cart total price
        fab = getActivity().findViewById(R.id.cart_fab);
        String totalText = "Checkout $" + totalPrice;
        fab.setText(totalText);

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
