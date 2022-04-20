package com.example.carpoolbuddypro.rhea;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import com.example.carpoolbuddypro.R;
import com.example.carpoolbuddypro.databinding.ActivityChatBinding;
import com.example.carpoolbuddypro.databinding.ActivityTextingBinding;
import com.example.carpoolbuddypro.silvia.User;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class TextingActivity extends AppCompatActivity {

    private ActivityTextingBinding binding;
    // the binding gets a turns items into addresses
    private User receiverUser;
    private ArrayList <ChatMessage> chatMessages;
    private ChatAdapter chatAdapter;
    private PreferenceManager preferenceManager;
    private FirebaseFirestore database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_texting);
        setListeners();
        //loadRecieverDetails();
        init();
        listenMessages();

    }

    private void init (){
        //check to see if the preference manager is right?
        preferenceManager = this.getSharedPreferences();
        chatMessages = new ArrayList<>();
        chatAdapter = new ChatAdapter(
                chatMessages,
                preferenceManager.getPreferenceDataStore().getString("user ID"));

//        binding.chatRecyclerView.setAdapter(chatAdapter);

        database = FirebaseFirestore.getInstance();


    }

    private void sendMessage (){
        HashMap<String,Object> message = new HashMap<>();
        message.put("senderId", preferenceManager.getPreferenceDataStore().getString("userId"));
        message.put("receiverId", receiverUser.getUid());
        message.put("message", binding.inputMessage.getText().toString());
        message.put("timestamp", new Date());
        database.collection("chat").add(message);
        binding.inputMessage.setText(null);


    }

    private void listenMessages() {
        database.collection("chat")
                .whereEqualTo("senderId", preferenceManager.getString("userId"))
                .whereEqualTo("receiverId", receiverUser.getUid())
                .addSnapshotListener(eventListener);
        database.collection("chat")
                .whereEqualTo("senderId", receiverUser.getUid())
                .whereEqualTo("receiverId",preferenceManager.getString("userId"))
                .addSnapshotListener(eventListener);

    }

    private final EventListener<QuerySnapshot> eventListener = (value, error) -> {
        if (error != null){
            return;
        }
        if (value != null){
            int count = chatMessages.size();
            for (DocumentChange documentChange : value.getDocumentChanges()){
                if (documentChange.getType() == DocumentChange.Type.ADDED){
                    ChatMessage chatMessage = new ChatMessage();
                    chatMessage.senderId = documentChange.getDocument().getString("senderId");
                    chatMessage.recieverId = documentChange.getDocument().getString("receiverId");
                    chatMessage.message = documentChange.getDocument().getString("message");
                    chatMessage.dateTime = getReadableDateTime(documentChange.getDocument().getDate("timestamp"));
                    chatMessage.dateObjectl = documentChange.getDocument().getDate("timestamp");
                    chatMessages.add(chatMessage);
                }


            }
            Collections.sort(chatMessages, (obj1, obj2) -> obj1.dateObjectl.compareTo(obj2.dateObjectl));
            if (count == 0 ) {
                chatAdapter.notifyDataSetChanged();
            }else {
                chatAdapter.notifyItemRangeInserted(chatMessages.size(), chatMessages.size());
                binding.chatRecyclerView.smoothScrollToPosition (chatMessages.size()-1);

            }
            binding.chatRecyclerView.setVisibility(View.VISIBLE);

        }
        binding.progressBar.setVisibility(View.GONE);
    };


    private void loadReceiverDetails() {
        receiverUser = (User) getIntent().getSerializableExtra("user");
        //binding.setText(receiverUser.getName());


    }
    private void setListeners() {
        //binding.imageBack.setOnClickListener(v -> onBackPressed ));
        binding.layoutSend.setOnClickListener (v -> sendMessage());

    }

    private String getReadableDateTime (Date date){
        return new SimpleDateFormat("MMMM dd, yy - hh:mm a", Locale.getDefault()).format(date);
    }






}