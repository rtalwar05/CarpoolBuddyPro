package com.example.carpoolbuddypro.audrey;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carpoolbuddypro.R;

public class VehicleViewHolder extends RecyclerView.ViewHolder {

    protected TextView nameText;
    protected TextView statusText;

    public VehicleViewHolder(@NonNull View itemView) {
        super(itemView);

        nameText = itemView.findViewById(R.id.nameTextView);
        statusText = itemView.findViewById(R.id.statusTextView);
    }


}
