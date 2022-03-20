package com.example.carpoolbuddypro.audrey;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.carpoolbuddypro.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

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

    private String licensePlateString;
    private Bundle bundle;

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
        container.removeAllViews();
//        bundle = this.getArguments();
//        licensePlateString = bundle.getString("licensePlate");
        getParentFragmentManager().setFragmentResultListener("getLicensePlate", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                licensePlateString = result.getString("licensePlate");
            }
        });

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                licensePlateTextView = getView().findViewById(R.id.licensePlateTextViewVI);
                ownerTextView = getView().findViewById(R.id.ownerTextViewVI);
                modelTextView = getView().findViewById(R.id.modelTextViewVI);
                capacityTextView = getView().findViewById(R.id.capacityTextViewVI);
                basePriceTextView = getView().findViewById(R.id.basePriceTextViewVI);
                greenPointsTextView = getView().findViewById(R.id.greenPointsTextViewVI);
                bookVehicleButton = getView().findViewById(R.id.bookVehicleButton);
                chatWithDriverButton = getView().findViewById(R.id.chatWithDriverButton);
                seePickupLocationButton = getView().findViewById(R.id.seePickupLocationButton);
            }
        }, 100);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vehicle_info, container, false);
    }

}

