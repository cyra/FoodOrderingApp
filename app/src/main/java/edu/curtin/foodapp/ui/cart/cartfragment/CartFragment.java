package edu.curtin.foodapp.ui.cart.cartfragment;

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
import edu.curtin.foodapp.ui.account.AccountViewModel;

// Cart Fragment
public class CartFragment extends Fragment {

    private FragmentCartBinding binding;

    private CartViewModel cartViewModel;
    private AccountViewModel accountViewModel;

    private ExtendedFloatingActionButton fab;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cartViewModel = ((MainActivity) getActivity()).getCartViewModel();
        accountViewModel = ((MainActivity) getActivity()).getAccountViewModel();

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
                if (cartViewModel.getTotalCart().getValue() > 0.01) {
                    // If user is logged in
                    if (accountViewModel.getLoggedIn()) {
                        // Save cart to orders database
                        // Clear cart
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