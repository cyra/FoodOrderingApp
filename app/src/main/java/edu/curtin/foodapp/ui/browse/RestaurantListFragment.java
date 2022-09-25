package edu.curtin.foodapp.ui.browse;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.curtin.foodapp.databinding.FragmentRestaurantListBinding;
import edu.curtin.foodapp.databinding.FragmentUserDetailsBinding;
import edu.curtin.foodapp.model.restaurant.Restaurant;
import edu.curtin.foodapp.ui.account.AccountViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RestaurantListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RestaurantListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentRestaurantListBinding binding;
    ArrayList<Restaurant> restaurants;

    public RestaurantListFragment() {
        // Required empty public constructor
    }

    public RestaurantListFragment(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecyclerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RestaurantListFragment newInstance(String param1, String param2) {
        RestaurantListFragment fragment = new RestaurantListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRestaurantListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final RecyclerView rv = binding.recyclerview;
        // horizontal scrolling recyclerview
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        // default
        // rv.setLayoutManager(new LinearLayoutManager(getContext()));

        // For gridlayout
        // rv.setLayoutManager(new GridLayoutManager(getContext(), 2));

        prepareData();
        RestaurantViewAdapter rvAdapter = new RestaurantViewAdapter(getContext(),restaurants);
        rv.setAdapter(rvAdapter);
        return root;
        // Inflate the layout for this fragment
        // View view = inflater.inflate(R.layout.fragment_restaurant_list, container, false);
        // RecyclerView rv = view.findViewById(R.id.recyclerview);
        //return view;
    }

    private void prepareData() {
        restaurants = new ArrayList<>();

        // No images yet
        restaurants.add(new Restaurant(1, "Pizza place", ""));
        restaurants.add(new Restaurant(2, "Pasta place", ""));
        restaurants.add(new Restaurant(3, "Food place", ""));
        restaurants.add(new Restaurant(4, "Cool place", ""));
        restaurants.add(new Restaurant(5, "Fun place", ""));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}