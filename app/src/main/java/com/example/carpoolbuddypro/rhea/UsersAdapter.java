package com.example.carpoolbuddypro.rhea;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carpoolbuddypro.databinding.ItemContainerUserBinding;
import com.example.carpoolbuddypro.silvia.User;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder>{

    private final ArrayList<User> users;
    private final UserListener userListener;

    public UsersAdapter(List<User> users, UserListener userListener ) {

        this.users = users;
        this.userListener = userListener;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContainerUserBinding itemContainerUserBinding = ItemContainerUserBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new UsersViewHolder(itemContainerUserBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        holder.setUserData(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder{

        ItemContainerUserBinding binding;

        UsersViewHolder(ItemContainerUserBinding itemContainerUserBinding){
            super(itemContainerUserBinding.getRoot());

            binding = itemContainerUserBinding;

        }

        void setUserData(User user)
        {
            binding.TextName.setText(user.getName());
            binding.textEmail.setText(user.getEmail());
            binding.getRoot().setOnClickListener(v -> userListener.onUserClicked(user));

        }


    }

    private Bitmap getUserImage (String encodedImage) {
//        byte[] bytes = Base64.getDecoder().decode(encodedImage, Base64.DEFAULT);
//
//        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return null;
    }











}
