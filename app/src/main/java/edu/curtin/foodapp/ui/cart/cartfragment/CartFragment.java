package edu.curtin.foodapp.ui.cart.cartfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import edu.curtin.foodapp.MainActivity;
import edu.curtin.foodapp.R;
import edu.curtin.foodapp.databinding.FragmentCartBinding;

// Cart Fragment
public class CartFragment extends Fragment {

    private FragmentCartBinding binding;

    private CartViewModel cartViewModel;

    private ExtendedFloatingActionButton fab;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cartViewModel = ((MainActivity) getActivity()).getCartViewModel();

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
                if (cartViewModel.getTotalCart().getValue() > 0.01) {
                    // Add to orders database
                    // Empty cart
                    // Take to account
                }
                else {
                    Toast.makeText(getContext(), "Cart empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
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