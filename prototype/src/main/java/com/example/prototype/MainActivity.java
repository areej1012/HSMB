package com.example.prototype;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.prototype.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent startLandingPageActivity = new Intent(MainActivity.this, TypeAccount.class);
                startActivity(startLandingPageActivity);
                finish(); //This closes current activity
            }
        }, 1000); //It means 1 seconds



    }
}