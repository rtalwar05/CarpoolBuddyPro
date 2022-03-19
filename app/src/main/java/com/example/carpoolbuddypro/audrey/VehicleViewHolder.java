package com.example.carpoolbuddypro.audrey;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carpoolbuddypro.R;

public class VehicleViewHolder extends RecyclerView.ViewHolder {

    protected TextView ownerTextView;
    protected TextView capacityTextView;

    public VehicleViewHolder(@NonNull View itemView) {
        super(itemView);

        ownerTextView = itemView.findViewById(R.id.ownerTextViewRV);
        capacityTextView = itemView.findViewById(R.id.capacityTextViewRV);
    }


}
