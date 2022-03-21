package com.example.carpoolbuddypro.rhea;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.carpoolbuddypro.R;
import com.example.carpoolbuddypro.databinding.ActivityChatBinding;
import com.example.carpoolbuddypro.silvia.User;

public class TextingActivity extends AppCompatActivity {

    private ActivityChatBinding binding;
    // the binding gets a turns items into addresses
    private User receiverUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_texting);
        setListeners();
        //loadRecieverDetails();

    }

    private void loadReceiverDetails() {
        receiverUser = (User) getIntent().getSerializableExtra("user");
        //binding.setText(receiverUser.getName());


    }
    private void setListeners() {
        //binding.imageBack.setOnClickListener(v -> onBackPressed ));
    }






}