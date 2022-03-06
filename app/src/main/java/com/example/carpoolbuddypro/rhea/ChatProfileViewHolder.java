package com.example.carpoolbuddypro.rhea;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carpoolbuddypro.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;


public class ChatProfileViewHolder extends RecyclerView.ViewHolder {

    TextView nameTV;
    ImageView imageView;
    CardView cardView;
    Button sendMsgButton;

    public ChatProfileViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void setProfile (FragmentActivity fragmentActivity, String name, String uid, String url)
    {
        nameTV = itemView.findViewById(R.id.name_chat_item_tv);
        imageView = itemView.findViewById(R.id.image_chat_item);
        sendMsgButton = itemView.findViewById(R.id.send_msg_item_btn)



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String UID = user.getUid();

        if (uid.equals(uid))
        {

        }else {

        }

        //If we want image
        // Picasso.get().load(url).into(imageView)

        nameTV.setText(name);


    }










}
