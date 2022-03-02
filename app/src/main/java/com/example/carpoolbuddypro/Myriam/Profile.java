package com.example.carpoolbuddypro.Myriam;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carpoolbuddypro.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment {

    private FirebaseFirestore firestore;
    private FirebaseAuth mAuth;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView pointsTextView;
    private EditText usernameInput;
    private EditText nameInput;
    private EditText passInput;

    private String username;
    private String name;
    private String type;
    private String points;
    private String pass;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profile.
     */
    // TODO: Rename and change types and number of parameters
    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
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

        textView1 = (TextView)getView().findViewById(R.id.username);
        textView2 = (TextView)getView().findViewById(R.id.name);
        textView3 = (TextView)getView().findViewById(R.id.type);
        textView4 = (TextView)getView().findViewById(R.id.password2);
        textView5 = (TextView)getView().findViewById(R.id.points);

        passInput = (EditText) getView().findViewById(R.id.passwordInput);
        nameInput = (EditText) getView().findViewById(R.id.nameInput);
        usernameInput = (EditText) getView().findViewById(R.id.usernameInput);

        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currUser = mAuth.getCurrentUser();
        String email = currUser.getEmail();

        username = "error";
        name = "error";
        type = "error";
        points = "error";
        pass = "error";

        //DO: is this the right collection part
        firestore.collection("users/students/y12").whereEqualTo("email", email).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
                {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot ds : task.getResult().getDocuments())
                        {
                            //get information from profile

                            //DO: are the variable type name true??
                            if(ds.get("username") == null)
                                username = "error";
                            else
                                username = ""+ ds.get("username");
                            if(ds.get("type") == null)
                                type = "error";
                            else
                                type = ""+ ds.get("type");
                            if(ds.get("name") == null)
                                name = "error";
                            else
                                name = ""+ ds.get("name");
                            if(ds.get("points") == null)
                                points = "error";
                            else
                                points = "" + ds.get("points");
                            if(ds.get("password") == null)
                                pass = "error";
                            else
                                pass = "" + ds.get("password");

                            //update profile
                            if(username.equals("error"))
                                textView3.setText("not set");
                            else
                                textView3.setText(username);
                            if(name.equals("error"))
                                textView2.setText("not set");
                            else
                                textView2.setText(name);
                            //DO: how to set the spinner??????
                            /*if(type.equals("error"))
                                textView4.setText("not set");
                            else
                                textView4.setText(type);*/
                            if(points.equals("error"))
                                pointsTextView.setText("0");
                            else
                                pointsTextView.setText(points);
                        }
                    }
                });
    }

    public void updateInfo(View vi)
    {
        //get input from user
        String username = usernameInput.getText().toString();
        String name = nameInput.getText().toString();
        //DO: GET SPINNER INFO!
        //String type = typeInput.getText().toString();
        String password = passInput.getText().toString();

        //if all EditTexts are complete, update database

        //DO: check type
        //if(!password.isEmpty() && !name.isEmpty() && !type.isEmpty() && !username.isEmpty())
        if(!password.isEmpty() && !name.isEmpty() && !username.isEmpty())
        {
            FirebaseUser currUser = mAuth.getCurrentUser();

            //update database with new information
            firestore.collection("users/students/y12").document(currUser.getEmail())
                    .update("name", name);
            firestore.collection("users/students/y12").document(currUser.getEmail())
                    .update("password", password);
            firestore.collection("users/students/y12").document(currUser.getEmail())
                    .update("username", username);

            //DO: update type
            /*firestore.collection("users/students/y12").document(currUser.getEmail())
                    .update("type", type);*/

            //display new profile information
            textView3.setText(type);
            textView4.setText(password);
            textView2.setText(name);
            textView1.setText(username);

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}