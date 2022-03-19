package com.example.carpoolbuddypro.audrey;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carpoolbuddypro.R;
import com.example.carpoolbuddypro.silvia.Vehicle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class AvailableVehiclesFragment extends Fragment {

    private RecyclerView vehicleRecyclerView;
    private VehicleAdapter vehicleAdapter;
    private ArrayList<Vehicle> vehiclesArrayList;
    private FirebaseFirestore firestore;
    private Vehicle vehicleClicked;
    View rootView;


    public AvailableVehiclesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firestore = FirebaseFirestore.getInstance();
        vehiclesArrayList = new ArrayList<>();

        vehicleAdapter = new VehicleAdapter(vehiclesArrayList);
        getDataFromFireStore();
        Log.d(TAG, "adapter size: " + vehicleAdapter.getItemCount());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_available_vehicles, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vehicleRecyclerView = rootView.findViewById(R.id.availableRecView);

        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        vehicleRecyclerView.setLayoutManager(llm);
        vehicleRecyclerView.setAdapter(vehicleAdapter);

        vehicleAdapter.notifyDataSetChanged();
        System.out.println("notify");

        vehicleAdapter.setOnItemClickListener(new VehicleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                vehicleClicked = vehiclesArrayList.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("licensePlate", vehicleClicked.getLiscenseplate()); //to send

                VehicleInfoFragment vehicleInfoFragment = new VehicleInfoFragment();
                vehicleInfoFragment.setArguments(bundle); //send bundle with info

                FragmentManager fragmentManager = getParentFragment().getChildFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.addToBackStack(null);
                transaction.replace(R.id.availableVehiclesFragment, VehicleInfoFragment.class, null);
                transaction.commit();
            }
        });
    }

    public void getDataFromFireStore()
    {
        //Get all of the vehicles from the database that are open.
        //Use document.toObject(Vehicle.class). This will deserialize the contents of the database
        // information and give you a Vehicle object. Add all vehicles to the vehicles ArrayList.
        //On completion of task for fetching all vehicles, set new RecyclerViewAdapter with the
        // list of vehicles fetched.

        firestore.collection("/vehicles")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful())
                        {
                            for(QueryDocumentSnapshot document : task.getResult())
                            {
                                Vehicle vehicle = document.toObject(Vehicle.class);
                                if(vehicle.isOpen())
                                {
                                    vehiclesArrayList.add(vehicle);
                                }
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                            vehicleRecyclerView.setAdapter(vehicleAdapter); //info into recycler view
                        }
                        else
                        {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}
