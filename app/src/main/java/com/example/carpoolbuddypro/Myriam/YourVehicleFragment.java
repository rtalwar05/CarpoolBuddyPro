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

import java.util.ArrayList;

public class YourVehicleFragment extends Fragment
{

    View root;

    ArrayList<String> test;

    RecyclerView recView1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_your_vehicle,container,false);

        recView1 = root.findViewById(R.id.recView1);

        test = new ArrayList<>();
        test.add("myriam");
        test.add("audrey");
        test.add("rhea");
        test.add("silvia");
        MyCarAdapter myAdapter = new MyCarAdapter(test);
        recView1.setAdapter(myAdapter);
        recView1.setLayoutManager(new LinearLayoutManager(root.getContext()));

        System.out.println("adapter" + recView1.getAdapter());

        return inflater.inflate(R.layout.fragment_your_vehicle,container,false);

    }
}
