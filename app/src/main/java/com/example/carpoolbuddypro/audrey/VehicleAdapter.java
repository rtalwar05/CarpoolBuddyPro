package com.example.carpoolbuddypro.audrey;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carpoolbuddypro.R;
import com.example.carpoolbuddypro.silvia.Vehicle;

import java.util.ArrayList;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleViewHolder> {

    private ArrayList<Vehicle> vehicleArrayList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }

    public VehicleAdapter(ArrayList<Vehicle> data)
    {
        vehicleArrayList = data;
    }

    @NonNull
    @Override
    public VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_row_view, parent, false);

        VehicleViewHolder holder = new VehicleViewHolder(myView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleViewHolder holder, int position)
    {
        String ownerString  = vehicleArrayList.get(position).getOwner();
        String capacityString = String.valueOf(vehicleArrayList.get(position).getCapacity());

        holder.ownerTextView.setText(ownerString);
        holder.capacityTextView.setText(capacityString);
    }

    @Override
    public int getItemCount()
    {
        return vehicleArrayList.size();
    }
}
