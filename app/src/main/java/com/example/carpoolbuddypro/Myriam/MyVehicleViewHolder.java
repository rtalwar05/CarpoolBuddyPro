package com.example.carpoolbuddypro.Myriam;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carpoolbuddypro.R;

public class MyVehicleViewHolder extends RecyclerView.ViewHolder{

    protected TextView liscenceText;
    protected TextView capText;

    public MyVehicleViewHolder(@NonNull View itemView) {
        super(itemView);

        liscenceText = itemView.findViewById(R.id.lisPlateText);
        capText = itemView.findViewById(R.id.capTextView);
    }
}
