package com.example.carpoolbuddypro.rhea;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carpoolbuddypro.databinding.ItemContainerRecievedBinding;
import com.example.carpoolbuddypro.databinding.ItemContainerSentBinding;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final ArrayList<ChatMessage> chatMessages;
    private final String senderId;

    public ChatAdapter(ArrayList<ChatMessage> chatMessages, String senderId) {
        this.chatMessages = chatMessages;
        this.senderId = senderId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class SentMessageViewHolder extends RecyclerView.ViewHolder{

        private final ItemContainerSentBinding binding;

        SentMessageViewHolder(ItemContainerSentBinding itemContainerSentBinding)
        {
            super (itemContainerSentBinding.getRoot());
            binding = itemContainerSentBinding;
        }

        void setData (ChatMessage chatMessage)
        {
            binding.textMessage.setText(chatMessage.message);
            binding.textDateTime.setText(chatMessage.dateTime);
        }

    }

    static class RecievedMessageViewHolder extends RecyclerView.ViewHolder {

        private final ItemContainerRecievedBinding binding;

        RecievedMessageViewHolder(ItemContainerRecievedBinding itemContainerRecievedBinding){
            super (itemContainerRecievedBinding.getRoot());
            binding = itemContainerRecievedBinding;
        }

        void setData (ChatMessage chatMessage)
        {
//            binding.textMessage(chatMessage.message);
//            binding.


        }




    }






}
