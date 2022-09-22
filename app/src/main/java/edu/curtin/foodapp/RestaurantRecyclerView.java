package edu.curtin.foodapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RestaurantRecyclerView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RestaurantRecyclerView extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RestaurantRecyclerView() {
        // Required empty public constructor
    }

    Database database;
    SQLiteDatabase sqLiteDatabase;
    ArrayList<RestaurantViewModel> modelArrayList;
    RecyclerViewAdapter viewAdapter;
    RecyclerView recyclerView;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RestaurantRecyclerView.
     */
    // TODO: Rename and change types and number of parameters
    public static RestaurantRecyclerView newInstance(String param1, String param2) {
        RestaurantRecyclerView fragment = new RestaurantRecyclerView();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        modelArrayList = new ArrayList<>();
        database = new Database(getActivity());

        findId();
        modelArrayList = displayData();
        viewAdapter = new RecyclerViewAdapter(modelArrayList, getActivity());

        // do gridlayout
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
    recyclerView.setLayoutManager(linearLayoutManager);
    recyclerView.setAdapter(viewAdapter);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private ArrayList<RestaurantViewModel> displayData() {
        sqLiteDatabase = database.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from restaurants", null);
        ArrayList<RestaurantViewModel> modelArrayList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                modelArrayList.add(new RestaurantViewModel(cursor.getString(1),
                        cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return modelArrayList;
    }

    private void findId() {
        // might need a fix
        recyclerView=getView().findViewById(R.id.textviewname);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant_recycler_view, container, false);
    }
}