package com.example.hsmb;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.hsmb.ui.gallery.GalleryFragment;
import com.example.hsmb.ui.gallery.GalleryViewModel;
import com.example.hsmb.ui.home.HomeViewModel;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class ActivityMain extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    private String eamil;

    public String getEamil() {
        return eamil;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //this.eamil=getIntent().getStringExtra("email");
//        Log.e("email",eamil);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.nav_gallery).setDrawerLayout(drawer).build();
        NavHostFragment navHostFragment =  (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }
    @Override
public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==R.id.nav_logout){
            Intent intent2=new Intent(ActivityMain.this,Welcom.class);
            startActivity(intent2);
            return true;}
        else if(id==R.id.nav_gallery){
            Intent intent1=new Intent(ActivityMain.this,MapsActivity.class);
            startActivity(intent1);
            return true;}
        else if(id==R.id.nav_home){
            Intent intent=new Intent(ActivityMain.this,HomeFragment.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);

}
  /*  public void to_vital(View v){
        Intent intent=new Intent(ActivityMain.this,HomeFragment.class);
        startActivity(getIntent());
    }
    public void to_map(View view1){
        Intent intent1=new Intent(ActivityMain.this,MapsActivity.class);
        startActivity(getIntent());
    }*/
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
