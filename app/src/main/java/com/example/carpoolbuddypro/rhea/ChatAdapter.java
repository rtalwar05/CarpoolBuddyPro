package com.example.carpoolbuddypro.rhea;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carpoolbuddypro.databinding.ItemContainerRecievedBinding;
import com.example.carpoolbuddypro.databinding.ItemContainerSentBinding;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final ArrayList<ChatMessage> chatMessages;
    private final String senderId;

    public static final int VIEW_TYPE_SENT = 1;
    public static final int VIEW_TYPE_RECEIVED = 2;

    public ChatAdapter(ArrayList<ChatMessage> chatMessages, String senderId) {
        this.chatMessages = chatMessages;
        this.senderId = senderId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_SENT){
            return new SentMessageViewHolder(
                    ItemContainerSentBinding.inflate(
                            LayoutInflater.from(parent.getContext()),
                            parent,
                            false)
            );

        }
        else {
            return new RecievedMessageViewHolder(
                    ItemContainerRecievedBinding.inflate(
                            LayoutInflater.from(parent.getContext()),
                            parent,
                            false
                    )
            );

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position)== VIEW_TYPE_SENT) {
            ((SentMessageViewHolder) holder).setData(chatMessages.get(position));
        }else {
            ((RecievedMessageViewHolder) holder).setData(chatMessages.get(position));

        }



    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (chatMessages.get(position).senderId.equals((senderId))) {
            return VIEW_TYPE_SENT;
        }
        else {
            return VIEW_TYPE_RECEIVED;
        }
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
//            binding.textDateTime(chatMessage.dateTime);


        }




    }






}
