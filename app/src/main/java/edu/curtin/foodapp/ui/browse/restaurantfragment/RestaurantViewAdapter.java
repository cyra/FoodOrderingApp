package edu.curtin.foodapp.ui.browse.restaurantfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.curtin.foodapp.R;
import edu.curtin.foodapp.databinding.SingleRestaurantBinding;
import edu.curtin.foodapp.model.restaurant.Restaurant;
import edu.curtin.foodapp.ui.browse.BrowseViewModel;
import edu.curtin.foodapp.ui.browse.fooditemfragment.FoodItemListFragment;

public class RestaurantViewAdapter extends RecyclerView.Adapter<RestaurantViewHolder> {
    private final Context context;
    private SingleRestaurantBinding binding;
    private final ArrayList<Restaurant> restaurantsList;

    private BrowseViewModel browseViewModel;
    private FragmentManager fm;

    public RestaurantViewAdapter(Context context, ArrayList<Restaurant> restaurants,
                                 BrowseViewModel browseViewModel, FragmentManager fm) {
        this.context = context;
        this.restaurantsList = restaurants;
        this.browseViewModel = browseViewModel;
        this.fm = fm;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = SingleRestaurantBinding.inflate(LayoutInflater.from(context), parent, false);
        return new RestaurantViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        // holder.itemName.setText(restaurants.getValue().get(position).getName());
        holder.itemName.setText(restaurantsList.get(position).getName());
        if (!restaurantsList.get(position).getImg().isEmpty()) {
            holder.itemImg.setImageResource(getImage(restaurantsList.get(position).getImg()));
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Find restaurantIDByName(name)
                int id = findRestaurantIDByName(holder.itemName.getText().toString());
                // Change BrowseViewModel restaurantID
                browseViewModel.setRestaurantID(id);
                // Remove FoodItemListFragment
                FragmentTransaction transaction = fm.beginTransaction();
                //FoodItemListFragment foodItemListFragment = new FoodItemListFragment();
                FoodItemListFragment foodItemListFragment = (FoodItemListFragment) fm.findFragmentById(R.id.foodItemListFragment);
                transaction.remove(foodItemListFragment);
                transaction.commit();

                fm.executePendingTransactions();
                // Start FoodItemListFragment
                transaction = fm.beginTransaction();
                foodItemListFragment = new FoodItemListFragment();
                //FoodItemListFragment foodItemListFragment = (FoodItemListFragment) fm.findFragmentById(R.id.foodItemListFragment);
                transaction.add(R.id.foodItemListFragment, foodItemListFragment);
                transaction.commit();

                //transaction.replace(R.id.foodItemListFragment, foodItemListFragment);
                //transaction.commit();

                /*FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                // For multiple fragments in a fragment, use multiple transaction.replace() and then commit() after.
                transaction.replace(R.id.restaurantListFragment, RestaurantListFragment);
                transaction.replace(R.id.foodItemListFragment, FoodItemListFragment);
                transaction.commit();*/

                /*FragmentTransaction transaction = parentFragmentManager.beginTransaction();
                FoodItemListFragment foodItemListFragment = (FoodItemListFragment) parentFragmentManager.findFragmentById(R.id.foodItemListFragment);
                parentFragmentManager.beginTransaction().remove(foodItemListFragment);
                //foodItemListFragment = new FoodItemListFragment();
                //parentFragmentManager.beginTransaction().add(R.id.foodItemListFragment, foodItemListFragment);
                 */
            }
        });
    }

    private int findRestaurantIDByName(String name) {
        for (int i = 0; i < restaurantsList.size(); i++) {
            Restaurant temp = restaurantsList.get(i);

            if (temp.getName().equals(name)) {
                return temp.getID();
            }
        }
        return -1;
    }

    public int getImage(String imageName) {
        int drawableResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        return drawableResourceId;
    }

    @Override
    public int getItemCount() {
        return restaurantsList.size();
    }
}
