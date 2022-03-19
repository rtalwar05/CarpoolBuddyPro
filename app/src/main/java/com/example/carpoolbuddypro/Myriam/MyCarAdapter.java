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

    public MyCarAdapter(ArrayList<String> data)
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
        holder.liscenceText.setText(mData.get(position));
        holder.capText.setText("not found");
    }

    @Override
    public int getItemCount()
    {
        return mData.size();
    }
}
