package com.example.carpoolbuddypro.silvia;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.carpoolbuddypro.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AddVehicleFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FirebaseFirestore mfStore;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private Spinner select;
    private TextInputEditText bigplate;
    private TextInputEditText bigmod;
    private TextInputEditText bigcap;
    private TextInputEditText bigbase;
    private TextInputEditText bigOwner;
    private Switch opened;
    private Switch green;
    private User curuser;
    private String lisplate;
    private String ownerString;
    private Boolean openornot;
    private Boolean greenornot;
    private Button addVehicleButton;

    public AddVehicleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        mfStore = FirebaseFirestore.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {

                addVehicleButton = getView().findViewById(R.id.addvehiclebutt);
                opened = getView().findViewById(R.id.switchAddVehicle);
                green = getView().findViewById(R.id.greenVehicleSwitch);
                bigplate = (TextInputEditText) getView().findViewById(R.id.liscenceplatenumber);
                bigmod = (TextInputEditText) getView().findViewById(R.id.model);
                bigbase = (TextInputEditText) getView().findViewById(R.id.baseprice);
                bigcap = (TextInputEditText) getView().findViewById(R.id.capacity);
                bigOwner = (TextInputEditText) getView().findViewById(R.id.ownerEditText);
                select = (Spinner) getView().findViewById(R.id.spinner);
                openornot = false;
                greenornot = false;

                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.energy, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                select.setAdapter(adapter);
                select.setOnItemSelectedListener(select.getOnItemSelectedListener());

                lisplate = bigplate.getText().toString();
                ownerString = bigOwner.getText().toString();

                addVehicleButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String lisplate = bigplate.getText().toString();
                        String model = bigmod.getText().toString();
                        int capac = Integer.parseInt(bigcap.getText().toString());
                        int basepri = Integer.parseInt(bigbase.getText().toString());

                        opened.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if (opened.isChecked())
                                {
                                    openornot = true;
                                    Log.i(TAG, "onCheckedChanged: vehicle is open");
                                }
                                else
                                {
                                    openornot = false;
                                }
                            }
                        });

                        green.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if (green.isChecked())
                                {
                                    greenornot = true;
                                }
                                else
                                {
                                    greenornot = false;
                                }
                            }
                        });

                        String energytype = select.getSelectedItem().toString();
                        Vehicle addedv = new Vehicle(lisplate, model, capac, new ArrayList<String>(),
                                openornot, basepri, greenornot, energytype, ownerString);
                        mfStore.collection("Vehicles").document(lisplate).set(addedv);
                    }
                });
            }
        }, 100);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_vehicle, container, false);
    }
    public void addtouser(FirebaseUser user)
    {
        mfStore.collection("Users").document(user.getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task)
            {
                if (task.isSuccessful())
                {
                    DocumentSnapshot bigu = task.getResult();
                    curuser = bigu.toObject(User.class);
                }
                else
                {
                    //toast thingy
                    //haha i'll do it for ya
                    Toast toast = Toast.makeText(getContext(), "error: user not added",
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        curuser.getOwnveh().add(lisplate);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
