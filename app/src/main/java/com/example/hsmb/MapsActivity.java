package com.example.hsmb;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    FusedLocationProviderClient client;
    private static final int REQUEST_CODE = 101;
    Location currentLocation;
    LatLng pilgrim = new LatLng(21.413776, 39.886360);
    Double distance ;
    SupportMapFragment mapFragment;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
         db= FirebaseFirestore.getInstance();
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        ArrayList<Double> d;
        db.collection("BoothLocation")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for(QueryDocumentSnapshot document : task.getResult()){

                       if(document.getString("State").equals("free")){
                            GeoPoint geo = document.getGeoPoint("geo");
                            double lat = geo.getLatitude();
                            double lng = geo.getLongitude();
                            LatLng latLng = new LatLng(lat, lng);
                           distance  = SphericalUtil.computeDistanceBetween(pilgrim, latLng);
                             if((distance  / 1000) <= 0.45) {
                            mMap.addMarker(new MarkerOptions().position(latLng).title("Free"));
                            mMap.moveCamera( CameraUpdateFactory.newLatLng(latLng));
                            mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
                            }


                        }
                        else  if(document.getString("State").equals("busy")){
                            GeoPoint geo = document.getGeoPoint("geo");
                            double lat = geo.getLatitude();
                            double lng = geo.getLongitude();
                            LatLng latLng = new LatLng(lat, lng);
                           distance  = SphericalUtil.computeDistanceBetween(pilgrim, latLng);
                             if((distance  / 1000) <= 0.45) {
                            mMap.addMarker(new MarkerOptions().position(latLng).title("Busy").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
                            mMap.moveCamera( CameraUpdateFactory.newLatLng(latLng));
                            mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
                            }
                        }
                        else  if(document.getString("State").equals("sterilization")){
                            GeoPoint geo = document.getGeoPoint("geo");
                            double lat = geo.getLatitude();
                            double lng = geo.getLongitude();
                            LatLng latLng = new LatLng(lat, lng);
                           distance  = SphericalUtil.computeDistanceBetween(pilgrim, latLng);
                             if((distance  / 1000) <= 0.45) {
                            mMap.addMarker(new MarkerOptions().position(latLng).title("Sterilization").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                            mMap.moveCamera( CameraUpdateFactory.newLatLng(latLng));
                            mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
                            }
                        }
                    }
                }
            }
        });



    }




    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.addMarker(new MarkerOptions().position(pilgrim).title("pilgrim").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
        mMap.moveCamera( CameraUpdateFactory.newLatLng(pilgrim));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));


        }
    }
