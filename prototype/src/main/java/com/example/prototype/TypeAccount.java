package com.example.prototype;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TypeAccount extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.type);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.doctor: {
                LoginDoctor();
                break;
            }
            case R.id.pilgrim: {
                LogPilgrim();
                break;
            }
        }
    }

    private void LogPilgrim() {
        Intent nextScreen = new Intent(TypeAccount.this,  LoginPilgrim.class);
        startActivity(nextScreen);
    }

    private void LoginDoctor() {
        Intent nextScreen = new Intent(TypeAccount.this,  LoginDoctor.class);
        startActivity(nextScreen);
    }
}
