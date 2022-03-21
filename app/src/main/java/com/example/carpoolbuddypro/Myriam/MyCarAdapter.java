package com.example.carpoolbuddypro.Myriam;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carpoolbuddypro.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyCarAdapter extends RecyclerView.Adapter<MyVehicleViewHolder> {

    ArrayList<String> mData;
    ArrayList<String> names;
    ArrayList<String> capacities;

    public MyCarAdapter(ArrayList<String> data, ArrayList<String> data1)
    {
        mData = data;
    }

    @NonNull
    @Override
    public MyVehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.myvehicle_row_view, parent, false);

        MyVehicleViewHolder holder = new MyVehicleViewHolder(myView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyVehicleViewHolder holder, int position)
    {
        holder.liscenceText.setText(names.get(position));
        holder.capText.setText(capacities.get(position));
    }

    @Override
    public int getItemCount()
    {
        return mData.size();
    }

    public void setData(ArrayList<String> data1, ArrayList<String> data2)
    {
        names = data1;
        capacities = data2;
    }
}
