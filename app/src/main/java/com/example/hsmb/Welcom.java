package com.example.hsmb;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.app.AppCompatActivity;

public class Welcom extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent startLandingPageActivity = new Intent(Welcom.this, MapsActivity.class);
                startActivity(startLandingPageActivity);
                finish(); //This closes current activity
            }
        }, 1000); //It means 1 seconds


        

    }

}