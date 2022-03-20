package com.example.carpoolbuddypro.Myriam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carpoolbuddypro.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class YourVehicleFragment extends Fragment
{

    View root;

    ArrayList<String> test;
    ArrayList<String> name;
    ArrayList<String> cap;

    private FirebaseFirestore firestore;
    private FirebaseAuth mAuth;

    MyCarAdapter myAdapter;


    RecyclerView recView1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_your_vehicle,container,false);

        recView1 = root.findViewById(R.id.recView1);

        name = new ArrayList<String>();
        cap = new ArrayList<String>();

        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        test = new ArrayList<>();
        test.add("myriam");
        test.add("audrey");
        test.add("rhea");
        test.add("silvia");
        myAdapter = new MyCarAdapter(name, cap);
        recView1.setAdapter(myAdapter);
        recView1.setLayoutManager(new LinearLayoutManager(root.getContext()));

        System.out.println("adapter" + recView1.getAdapter());

        getAndPopulateData();

        return root;

    }

    public void getAndPopulateData()
    {
        name = new ArrayList<String>();
        cap = new ArrayList<String>();


        //get information from firebase
        //DO: get for specific user
        firestore.collection("vehicles").get().addOnCompleteListener
                (new OnCompleteListener<QuerySnapshot>()
                {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot ds : task.getResult().getDocuments())
                        {
                            String result = "" + ds;
                            int cIntStart = result.indexOf("capacity");
                            int cIntEnd = result.indexOf("open");
                            int nIntStart = result.indexOf("owner");
                            int nIntEnd = result.length();

                            System.out.println(result);

                            String names = result.substring(cIntStart + 9, cIntEnd - 1);
                            System.out.println(names);
                            String capa = result.substring(nIntStart + 6, nIntEnd - 4);
                            System.out.println(capa);


                            //if the vehicle is open, save information in arraylist for recyclerview
                            cap.add(capa);
                            name.add(names);



                            //update recyclerview
                            myAdapter.setData(name, cap);
                            myAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}
