package com.example.carpoolbuddypro.audrey;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.carpoolbuddypro.R;
import com.example.carpoolbuddypro.silvia.Vehicle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AvailableVehiclesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AvailableVehiclesFragment extends Fragment {

    RecyclerView vehiclesRecyclerView;
    VehicleAdapter vehicleAdapter;
    ArrayList<Vehicle> vehiclesArrayList;
    FirebaseFirestore firestore;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AvailableVehiclesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AvailableVehiclesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AvailableVehiclesFragment newInstance(String param1, String param2) {
        AvailableVehiclesFragment fragment = new AvailableVehiclesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        firestore = FirebaseFirestore.getInstance();

        vehiclesRecyclerView = getView().findViewById(R.id.vehiclesRecyclerView);
        vehiclesRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        vehiclesArrayList = new ArrayList<>();
        vehicleAdapter = new VehicleAdapter(vehiclesArrayList);


//        vehicleAdapter.setOnItemClickListener(new VehicleAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                vehicleClicked = vehiclesArrayList.get(position);
//                Intent vehicleProfileActivityIntent = new Intent(VehiclesInfoActivity.this, VehicleProfileActivity.class);
//                vehicleProfileActivityIntent.putExtra("vehicleID", vehicleClicked.getVehicleID());
//                startActivity(vehicleProfileActivityIntent);
//                finish();
//            }
//        });

        //to new fragment
//        Fragment2 fragment2=new Fragment2();
//        FragmentManager fragmentManager=getActivity().getFragmentManager();
//        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.content_main,fragment2,"tag");
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
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
                            vehiclesRecyclerView.setAdapter(vehicleAdapter);
                        }
                        else
                        {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}