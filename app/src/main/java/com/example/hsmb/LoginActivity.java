package com.example.hsmb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.lifecycle.Observer;
import android.text.Editable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    //refrence button and other control wedight on layout
    EditText usernameEditText ;
    EditText passwordEditText;
    Button loginButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
         loginButton = findViewById(R.id.submit);
         String username=usernameEditText.getText().toString();
         String password=passwordEditText.getText().toString();





        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



             if(!username.isEmpty()){
                 usernameEditText.setError("Username ");
             }
             else if(!password.isEmpty()){
                 passwordEditText.setError("password");
             }
                DBconcation db =new DBconcation(LoginActivity.this);
                List<AccountPligrim> login= db.getAllRecoredLogin(usernameEditText.getText().toString());
                for (int i=0; login.size()>i;i++){
                    if(login.get(i).getUsername().equals(username) && login.get(i).getPassword().equals(password)){
                        Intent nextScreen = new Intent(LoginActivity.this,  ActivityMain.class);
                        startActivity(nextScreen);

                    }
                    else{
                        Toast.makeText(LoginActivity.this, "you aren't registered in application",Toast.LENGTH_LONG);
                    }
                }



            }
        });


    }
}
