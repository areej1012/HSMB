package com.example.prototype;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.prototype.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent startLandingPageActivity = new Intent(MainActivity.this, TypeAccount.class);
                startActivity(startLandingPageActivity);
                finish(); //This closes current activity
            }
        }, 1000); //It means 1 seconds*/






    }
}