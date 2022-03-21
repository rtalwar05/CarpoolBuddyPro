package com.example.carpoolbuddypro.Myriam;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.carpoolbuddypro.R;
import com.example.carpoolbuddypro.rhea.LogInActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class UserProfileFragment extends Fragment implements View.OnClickListener
{
    //create necessary variables and layout items
    private FirebaseFirestore firestore;
    private FirebaseAuth mAuth;
    private View root;
    private String uemail;
    private String name;
    private String type;
    private String points;
    private String userID;
    private String uType;
    private Button b;
    private Button logoutB;
    private EditText emailInput;
    private EditText nameInput;
    private EditText passInput;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_user_profile,container,false);

        //get layout items
        b = (Button)root.findViewById(R.id.button3);
        b.setOnClickListener(this);

        logoutB = (Button)root.findViewById(R.id.buttonLog);
        logoutB.setOnClickListener(this);

        TextView userEmail = (TextView) root.findViewById(R.id.text_email4);
        TextView userName = (TextView) root.findViewById(R.id.text_name3);
        TextView userType = (TextView) root.findViewById(R.id.type2);
        TextView userPoints = (TextView) root.findViewById(R.id.points2);
        nameInput = root.findViewById(R.id.nameInput2);

        //setup spinner
        Spinner s = root.findViewById(R.id.spin);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(root.getContext(),R.array.types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                uType = (String) parent.getItemAtPosition(position);
                System.out.println(uType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        uemail = "error";
        name = "error";
        type = "error";
        points = "error";
        userID = "";

        //get current user information
        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currUser = mAuth.getCurrentUser();
        String email = currUser.getEmail();

        //get user info from database and update profile textviews
        firestore.collection("users/students/y12").whereEqualTo("email", email).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
                {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot ds : task.getResult().getDocuments())
                        {
                            userID = "" + ds.get("uid");
                            //get information from profile
                            if(ds.get("email") == null)
                                uemail = "error";
                            else
                                uemail = ""+ ds.get("email");
                            if(ds.get("type") == null)
                                type = "error";
                            else
                                type = ""+ ds.get("type");
                            if(ds.get("name") == null)
                                name = "error";
                            else
                                name = ""+ ds.get("name");
                            if(ds.get("balance") == null)
                                points = "error";
                            else
                                points = "" + ds.get("balance");

                            //update profile
                            if(uemail.equals("error"))
                            {
                                userEmail.setText("not set");
                            }
                            else
                            {
                                userEmail.setText(uemail);
                            }
                            if(name.equals("error"))
                            {
                                userName.setText("not set");
                            }
                            else
                            {
                                userName.setText(name);
                            }
                            if(type.equals("error"))
                            {
                                userType.setText("not set");
                            }
                            else
                            {
                                userType.setText(type);
                            }
                            if(points.equals("error"))
                            {
                                userPoints.setText("0");
                            }
                            else
                            {
                                userPoints.setText(points);
                            }
                        }
                    }
                });
        return root;
    }

    public void updateInfo()
    {
        //get input from user
        String name = nameInput.getText().toString();
        TextView userName = (TextView) root.findViewById(R.id.text_name3);
        TextView userType = (TextView) root.findViewById(R.id.type2);

        //if all EditTexts are complete, update database
        if(!name.isEmpty())
        {
            FirebaseUser currUser = mAuth.getCurrentUser();

            //update database with new information
            firestore.collection("users/students/y12").document(userID)
                    .update("name", name);

            //DO: update type
            firestore.collection("users/students/y12").document(userID)
                    .update("type", uType);

            //display new profile information
            userType.setText(uType);
            userName.setText(name);

            Toast messageToUser = Toast.makeText(getActivity(), "Success", Toast.LENGTH_LONG);
            messageToUser.show();
        }
        else
        {
            //if all EditTexts are not complete, display error message
            Toast messageToUser = Toast.makeText(getActivity(), "Please try again",
                    Toast.LENGTH_LONG);
            messageToUser.show();
        }
    }

    @Override
    public void onClick(View view)
    {
        //depending on clicked button, complete corresponding action
        switch(view.getId())
        {
            //update profile
            case R.id.button3:
                updateInfo();
                break;
            //sign out
            case R.id.buttonLog:
            {
                mAuth.signOut();
                Intent intent = new Intent(getActivity(), LogInActivity.class);
                startActivity(intent);
                System.out.println("signing out");
                break;
            }
        }
    }
}