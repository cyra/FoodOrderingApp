package edu.curtin.foodapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
private ArrayList<RestaurantViewModel>modelArrayList;
private Context context;

// constructor


    public RecyclerViewAdapter(ArrayList<RestaurantViewModel> modelArrayList, Context context) {
        this.modelArrayList = modelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_restaurant_fragment,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
    RestaurantViewModel rvModel = modelArrayList.get(position);
    holder.txtname.setText(rvModel.getSname());
    holder.txtdetails.setText(rvModel.getSdetails());
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtname, txtdetails;
        private ImageView img;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtname=(TextView)itemView.findViewById(R.id.textviewname);
            txtdetails=(TextView)itemView.findViewById(R.id.textviewdetails);
            img=(ImageView)itemView.findViewById(R.id.thumbnail);

        }

    }
}
