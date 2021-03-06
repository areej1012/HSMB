package com.example.prototype;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginDoctor  extends AppCompatActivity {
    EditText emailEditText;
    EditText passwordEditText;
    Button loginButton;
    String ID;
    String IdAccount;
    ArrayList<AcoountDoctor> account = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        emailEditText = findViewById(R.id.name);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.log_in);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emailEditText.getText().toString().isEmpty()) {
                    emailEditText.setError("Please write your email! ");
                    return;

                }
                if (passwordEditText.getText().toString().isEmpty()) {
                    passwordEditText.setError("Please write your password!");
                    return;

                }
                Login(emailEditText.getText().toString(), passwordEditText.getText().toString());

            }
        });

    }

    private void Login(String email, String password) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Account_For_Doctor")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot decoment : task.getResult()) {
                                AcoountDoctor accountDoctor = decoment.toObject(AcoountDoctor.class);
                                account.add(accountDoctor);
                            }
                            for (int i = 0; account.size() > i; i++) {
                                if (email.equals(account.get(i).getEmail())) {
                                    if (password.equals(account.get(i).getPassword())) {

                                        Intent nextScreen = new Intent(LoginDoctor.this, information.class);
                                        nextScreen.putExtra("IDBooth",account.get(i).getIdLocation());
                                        Log.e("login",account.get(i).getIdLocation());
                                        startActivity(nextScreen);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "The password is wrong", Toast.LENGTH_LONG).show();
                                        emailEditText.setError("Write the right password.");
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "The Email is wrong", Toast.LENGTH_LONG).show();

                                }
                            }
                        }
                    }
                });
    }
}
