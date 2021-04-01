package com.example.prototype;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TypeAccount extends AppCompatActivity {

    Button loginButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.type);

        loginButton=findViewById(R.id.doctor);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(TypeAccount.this,  LoginDoctor.class);
                startActivity(nextScreen);
            }
        });
    }

    public void onClick(View v) {
        switch(v.getId()) {

            case R.id.pilgrim: {
                LogPilgrim();
                break;
            }
        }
    }

    private void LogPilgrim() {
        Intent nextScreen = new Intent(TypeAccount.this,  Camera.class);
        startActivity(nextScreen);
    }


}
