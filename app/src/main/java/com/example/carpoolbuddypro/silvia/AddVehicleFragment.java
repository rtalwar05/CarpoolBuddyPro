package com.example.carpoolbuddypro.silvia;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.carpoolbuddypro.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddVehicleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddVehicleFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FirebaseFirestore mfStore;
    private FirebaseUser mUser;
    private Spinner select;
    private TextInputEditText bigplate;
    private TextInputEditText bigmod;
    private TextInputEditText bigcap;
    private TextInputEditText bigbase;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddVehicleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddVehicleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddVehicleFragment newInstance(String param1, String param2) {
        AddVehicleFragment fragment = new AddVehicleFragment();
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
        bigplate = (TextInputEditText) getView().findViewById(R.id.liscenceplatenumber);
        bigmod = (TextInputEditText) getView().findViewById(R.id.model);
        bigbase = (TextInputEditText) getView().findViewById(R.id.baseprice);
        bigcap = (TextInputEditText) getView().findViewById(R.id.capacity);
        select = (Spinner) getView().findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),R.array.energy, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select.setAdapter(adapter);
        select.setOnItemSelectedListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_vehicle, container, false);
    }
    public void addvehi (View v)
    {
        String lisplate = bigplate.getText().toString();
        String model = bigmod.getText().toString();
        String capac = bigcap.getText().toString();
        String basepri = bigbase.getText().toString();
//public Vehicle(String owner, String model, String vID, int capacity, ArrayList<String> ridersUID, boolean open, String type, double basePrice, boolean isGreen)
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}