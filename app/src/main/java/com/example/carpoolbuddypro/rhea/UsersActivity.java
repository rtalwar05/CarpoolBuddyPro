package com.example.carpoolbuddypro.rhea;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import com.example.carpoolbuddypro.R;
import com.example.carpoolbuddypro.databinding.ActivityChatBinding;
import com.example.carpoolbuddypro.databinding.ActivityUsersBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.auth.User;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UsersActivity extends AppCompatActivity {

    private ActivityUsersBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding = ActivityUsersBinding.inflate(getLayoutInflater());
        //setListeners();
        getUsers();



    }

//    private void setListeners() {
//        binding.imageBack.setOnDragListener(v -> onBackPressed());
//
//    }


    private void getUsers () {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currUser = auth.getCurrentUser();

        loading(true);
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection("user")
                .get()
                .addOnCompleteListener(task -> {
                    loading(false);
                    String currentUserId = currUser.getUid();
                    if (task.isSuccessful() && task.getResult() != null) {
                        ArrayList<User> users = new ArrayList<>();
                        for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                            if (currentUserId.equals(queryDocumentSnapshot.getId())) {
                                continue;
                            }
                            com.example.carpoolbuddypro.silvia.User user = new com.example.carpoolbuddypro.silvia.User();
                            user.setName(queryDocumentSnapshot.getString("Name"));
                            user.setEmail(queryDocumentSnapshot.getString("Email"));
                            user.setToken(queryDocumentSnapshot.getString("Token"));

                        }

                        if (users.size() > 0) {
//                            UsersAdapter usersAdapter = new UsersAdapter(users);
//                            binding.usersRecyclerView.setAdapter(usersAdapter);
                            binding.usersRecyclerView.setVisibility(View.VISIBLE);
                        } else {
                            showErrorMessage();

                        }
                    } else {
                        showErrorMessage();
                    }
                });
    }





    private void showErrorMessage ()
    {
       Toast error = new Toast(this).makeText(
               getApplicationContext(),
               "No user Available",
               Toast.LENGTH_SHORT);
    }



    private void loading (Boolean isLoading)
    {
        if (isLoading) {
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.INVISIBLE   );
        }
    }




}