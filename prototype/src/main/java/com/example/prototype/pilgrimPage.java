package com.example.prototype;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ThrowOnExtraProperties;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class pilgrimPage extends AppCompatActivity {
    Button webex;
    Button logout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pilgrim);
        logout=findViewById(R.id.logout);
        webex=findViewById(R.id.button3);



      logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID=getIntent().getStringExtra("ID");

              FirebaseFirestore db =FirebaseFirestore.getInstance();
                db.collection("BoothLocation")
                        .document(ID)
                        .update("State","sterilization" , "idAccount","");
                Intent nextScreen = new Intent(pilgrimPage.this, LoginPilgrim.class);
                startActivity(nextScreen);

            }
        });

    }


    private void Logout() {
    }
}
