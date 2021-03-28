package com.example.prototype;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button3: {

                break;
            }
            case R.id.logout: {
                Logout();
                break;
            }
        }
    }

    private void Logout() {
    }
}
