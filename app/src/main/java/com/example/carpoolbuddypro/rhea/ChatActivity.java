package com.example.carpoolbuddypro.rhea;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//import com.example.carpoolbuddypro.databinding.ActivityChatBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Base64;

public class ChatActivity extends AppCompatActivity {

//    private ActivityChatBinding binding;
    public SharedPreferences preferenceManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        binding = ActivityChatBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
        preferenceManager = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        loadUserDetails();
        getToken();

    }


    private void setListeners()
    {

    }


    private void loadUserDetails()
    {
//        binding.textName.setText(preferenceManager.getString("name"));
//        byte [] bytes = Base64.getDecoder().decode(preferenceManager.getString("image"),Base64.DEFAULT);
//        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//        binding.imageProfile.setImageBitmap(bitmap);


    }


    private void showToast(String message )
    {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

    }

    private void getToken(){
        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(this::updateToken);
    }

    private void updateToken (String token){
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser curruser = auth.getCurrentUser();
        DocumentReference documentReference = database.collection("users").document("students").collection("y12").document(curruser.getUid());
        documentReference.update("token", token).addOnCompleteListener(unused->showToast("token Updated successfully"))
                .addOnFailureListener(e->showToast("Unable to update token"));


    }




}
