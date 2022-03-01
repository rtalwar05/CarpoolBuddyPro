package com.example.carpoolbuddypro.rhea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.carpoolbuddypro.MainActivity;
import com.example.carpoolbuddypro.R;
import com.example.carpoolbuddypro.silvia.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class LogInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    private EditText emailField;
    private EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        emailField = findViewById(R.id.emailEditText);
        passwordField = findViewById(R.id.passwordEditText);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    /**
     * This method takes the user's email and password and log into their already existing account.
     *
     * @param v the screen at which this occurs.
     */
    public void logIn(View v) {
        String emailString = emailField.getText().toString();
        String passString = passwordField.getText().toString();

        mAuth.signInWithEmailAndPassword(emailString, passString)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("LogIn", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);



                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("FailedLogIn", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LogInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });


    }

    /**
     * This method allows a new user to create an account with an email and a password.
     *
     * @param v the screen at which this method occurs.
     */

    public void signUp(View v) {
        String emailString = emailField.getText().toString();
        String passString = passwordField.getText().toString();


        mAuth.createUserWithEmailAndPassword(emailString, passString).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d("Sign Up", "User successfully Signed up!");

                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);

                    User newUser = new User(user.getUid(), emailString);

                    firestore.collection("users/students/y12").document(newUser.getUid()).set(newUser);

                    Log.d("USERS" ,"User added");

                } else {

                    Log.w("SignUp", "createUserWithEmail:failure", task.getException());

                    updateUI(null);

                }

            }
        });

    }

    /**
     * This method first checks to make sure the current user exists and that they have signed into their account,
     * then it changes the UI from the authentication screen to the main screen.
     *
     * @param currUser The account of the current user.
     */

    public void updateUI(FirebaseUser currUser) {

        if (currUser != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }








}