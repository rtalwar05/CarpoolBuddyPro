package com.example.carpoolbuddypro.audrey;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.carpoolbuddypro.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class VehicleInfoFragment extends Fragment {

    private FirebaseFirestore firestore;
    private FirebaseAuth mAuth;

    private TextView licensePlateTextView;
    private TextView ownerTextView;
    private TextView modelTextView;
    private TextView capacityTextView;
    private TextView basePriceTextView;
    private TextView greenPointsTextView;
    private Button bookVehicleButton;
    private Button chatWithDriverButton;
    private Button seePickupLocationButton;

    public VehicleInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        licensePlateTextView = getView().findViewById(R.id.licensePlateTextView);
        ownerTextView = getView().findViewById(R.id.ownerTextView);
        modelTextView = getView().findViewById(R.id.modelTextView);
        capacityTextView = getView().findViewById(R.id.capacityTextView);
        basePriceTextView = getView().findViewById(R.id.basePriceTextView);
        greenPointsTextView = getView().findViewById(R.id.greenPointsTextView);
        bookVehicleButton = getView().findViewById(R.id.bookVehicleButton);
        chatWithDriverButton = getView().findViewById(R.id.chatWithDriverButton);
        seePickupLocationButton = getView().findViewById(R.id.seePickupLocationButton);

        Bundle bundle = this.getArguments();
        String licensePlate = bundle.getString("licensePlate");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vehicle_info, container, false);
    }
}

