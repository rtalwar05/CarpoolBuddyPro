package com.example.carpoolbuddypro.audrey;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carpoolbuddypro.R;

import java.util.ArrayList;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleViewHolder> {

    ArrayList<String> mData;

    public VehicleAdapter(ArrayList data)
    {
        mData = data;
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
        holder.nameText.setText(mData.get(position));
        holder.statusText.setText("set later");
    }

    @Override
    public int getItemCount()
    {
        return mData.size();
    }
}
