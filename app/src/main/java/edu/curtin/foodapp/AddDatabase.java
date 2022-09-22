package edu.curtin.foodapp;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddDatabase extends Fragment {

    private AddDatabaseViewModel mViewModel;

    public static AddDatabase newInstance() {
        return new AddDatabase();
    }

    EditText restaurantName, restaurantDetails;
    Button submit, display;
    Database database;
    SQLiteDatabase sqLiteDatabase;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        database = new Database(getContext());
        //findId();
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_add_database, container, false);
        restaurantName = (EditText) v.findViewById(R.id.name);
        restaurantDetails = (EditText) v.findViewById(R.id.details);
        submit = (Button) v.findViewById(R.id.btnsubmit);
        display = (Button) v.findViewById(R.id.btndisplay);
        //insertData();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = database.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("name", restaurantName.getText().toString());
                cv.put("details", restaurantDetails.getText().toString());

                Long recid = sqLiteDatabase.insert("restaurants", null, cv);
                if (recid != null) {
                    Toast.makeText(getActivity(), "inserted success", Toast.LENGTH_SHORT).show();
                    // clearData();
                    restaurantName.setText("");
                    restaurantDetails.setText("");
                } else {
                    Toast.makeText(getActivity(), "insert failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getChildFragmentManager();
                RestaurantRecyclerView recyclerView = (RestaurantRecyclerView) fm.findFragmentById(R.id.single_restaurant);

                if (recyclerView == null) {
                    recyclerView = new RestaurantRecyclerView();
                    fm.beginTransaction().add(R.id.single_restaurant, recyclerView).commit();
                }
            }
        });
        // clearData();
        restaurantName.setText("");
        restaurantDetails.setText("");
        return inflater.inflate(R.layout.fragment_add_database, container, false);
    }


    /*
    private void clearData() {
    }
        private void insertData() {
        }
        private void findId() {
            View v = inflater.inflate(R.layout.single_restaurant_fragment, container, false);
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_restaurant_fragment,parent,false);
            return new RecyclerViewAdapter.ViewHolder(view);
            restaurantName = (EditText) v.findViewById(R.id.name);
            restaurantDetails = (EditText) v.findViewById(R.id.details);
            submit = (Button) v.findViewById(R.id.btnsubmit);
            display = (Button) v.findViewById(R.id.btndisplay);
            return v;
        }
        */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AddDatabaseViewModel.class);
        // TODO: Use the ViewModel
    }
}