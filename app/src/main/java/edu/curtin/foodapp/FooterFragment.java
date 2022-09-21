package edu.curtin.foodapp;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FooterFragment extends Fragment {

    private FooterViewModel mViewModel;

    public static FooterFragment newInstance() {
        return new FooterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_footer, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(getActivity(), (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory()).get(FooterViewModel.class);
        // Use the ViewModel
        View v = getView();
        Button featured = v.findViewById(R.id.featuredButton);
        Button browse = v.findViewById(R.id.browseButton);
        Button cart = v.findViewById(R.id.cartButton);
        Button account = v.findViewById(R.id.accountButton);

        // Click Featured
        featured.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.setTitle("Featured");
            }
        });

        // Click Browse
        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.setTitle("Browse");
            }
        });

        // Click Cart
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.setTitle("Cart");
            }
        });

        // Click Account
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.setTitle("Account");
            }
        });
    }

}