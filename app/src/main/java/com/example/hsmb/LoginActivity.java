package com.example.hsmb;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    //refrence button and other control wedight on layout
    EditText emailEditText;
    EditText passwordEditText;
    Button loginButton;
    private FirebaseAuth mAuth;
    private String email;

    public String getEmail() {
        return email;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.submit);
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        mAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!email.isEmpty()) {
                    emailEditText.setError("Username ");
                    return;
                }  if (!password.isEmpty()) {
                    passwordEditText.setError("password");
                    return;
                }
                signIn(emailEditText.getText().toString(),passwordEditText.getText().toString(),savedInstanceState);

            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            currentUser.reload();


        }
    }

    private void updateUI(FirebaseUser user, Bundle savedInstanceState) {

        Intent nextScreen = new Intent(LoginActivity.this,  ActivityMain.class);

        startActivity(nextScreen);

    }

    private void signIn(String email, String password,Bundle savedInstanceState) {
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("auth", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user,savedInstanceState);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("auth", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }
}