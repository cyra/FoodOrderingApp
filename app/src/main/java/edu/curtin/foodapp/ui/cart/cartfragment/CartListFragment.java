package edu.curtin.foodapp.ui.cart.cartfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.curtin.foodapp.databinding.ListCartBinding;
import edu.curtin.foodapp.databinding.ListDailyFoodItemBinding;
import edu.curtin.foodapp.model.cart.CartItemList;
import edu.curtin.foodapp.model.fooditems.FoodItem;
import edu.curtin.foodapp.model.fooditems.FoodItemList;
import edu.curtin.foodapp.ui.browse.fooditemfragment.FoodItemViewAdapter;
import edu.curtin.foodapp.ui.home.fooditemfragment.DailyFoodItemListAdapter;

public class CartListFragment extends Fragment {

    ListCartBinding binding;

    private CartItemList cartItemList;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        cartItemList = new CartItemList();
        cartItemList.load(getContext());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ListCartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final RecyclerView rv = binding.recyclerview;

        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        CartListAdapter rvAdapter = new CartListAdapter(getContext(), cartItemList.getAllCartItems());
        rv.setAdapter(rvAdapter);
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
