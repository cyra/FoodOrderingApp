package edu.curtin.foodapp.ui.home;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import edu.curtin.foodapp.MainActivity;
import edu.curtin.foodapp.R;
import edu.curtin.foodapp.databinding.FragmentFeaturedRestaurantBinding;
import edu.curtin.foodapp.model.restaurant.Restaurant;
import edu.curtin.foodapp.model.restaurant.RestaurantList;
import edu.curtin.foodapp.ui.browse.BrowseViewModel;

public class FeaturedRestaurantFragment extends Fragment {

    private FragmentFeaturedRestaurantBinding binding;

    Restaurant randomRestaurant;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RestaurantList restaurantList = new RestaurantList();
        restaurantList.load(getContext());
        // Random restaurant each day
        randomRestaurant = restaurantList.getRandomRestaurant();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFeaturedRestaurantBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Context context = getContext();
        // Set featured image
        ImageView featuredImageView = binding.featuredRestaurantImg;
        int imageResourceID = context.getResources().getIdentifier(randomRestaurant.getImg(), "drawable", context.getPackageName());
        featuredImageView.setImageResource(imageResourceID);
        // Set featured name
        TextView featuredName = binding.featuredRestaurantName;
        featuredName.setText(randomRestaurant.getName());

        binding.featuredCardClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Take to browse
                Navigation.findNavController(view).navigate(R.id.action_navigation_home_to_navigation_browse);
                // Select randomRestaurant
                BrowseViewModel browseViewModel = ((MainActivity) getActivity()).getBrowseViewModel();
                browseViewModel.setRestaurantID(randomRestaurant.getID());
                Toast.makeText(context, randomRestaurant.getName() + " Menu", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
}