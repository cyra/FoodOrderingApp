package edu.curtin.foodapp.ui.cart.cartfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.curtin.foodapp.MainActivity;
import edu.curtin.foodapp.databinding.ListCartBinding;
import edu.curtin.foodapp.model.cart.CartItemList;
import edu.curtin.foodapp.ui.cart.CartViewModel;

public class CartListFragment extends Fragment {

    ListCartBinding binding;
    private CartItemList cart;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        cart = new CartItemList();
        cart.load(getContext());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        CartViewModel cartViewModel = ((MainActivity) getActivity()).getCartViewModel();

        binding = ListCartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final RecyclerView rv = binding.recyclerview;

        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        CartListAdapter rvAdapter = new CartListAdapter(getContext(), cart, cartViewModel);
        rv.setAdapter(rvAdapter);

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
