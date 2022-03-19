package com.example.carpoolbuddypro.audrey;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carpoolbuddypro.R;

public class VehicleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    protected TextView ownerTextView;
    protected TextView capacityTextView;
    protected VehicleAdapter.OnItemClickListener onItemClickListener;

    public VehicleViewHolder(@NonNull View itemView, VehicleAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);

        ownerTextView = itemView.findViewById(R.id.ownerTextViewRV);
        capacityTextView = itemView.findViewById(R.id.capacityTextViewRV);
        this.onItemClickListener = onItemClickListener;

        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        onItemClickListener.onItemClick(getAdapterPosition());
    }
}

