package com.example.hsmb;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.hsmb.databinding.FragmentHomeBinding;
import com.example.hsmb.ui.gallery.GalleryFragment;
import com.example.hsmb.ui.home.HomeViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.data.HealthDataTypes;
import com.google.android.gms.fitness.data.HealthFields;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DataReadResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.google.android.gms.fitness.data.HealthDataTypes.TYPE_BLOOD_PRESSURE;
import static com.google.android.gms.fitness.data.HealthFields.BLOOD_PRESSURE_MEASUREMENT_LOCATION_LEFT_UPPER_ARM;
import static com.google.android.gms.fitness.data.HealthFields.BODY_POSITION_SITTING;
import static com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_PRESSURE_DIASTOLIC;
import static com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_PRESSURE_MEASUREMENT_LOCATION;
import static com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_PRESSURE_SYSTOLIC;
import static com.google.android.gms.fitness.data.HealthFields.FIELD_BODY_POSITION;
import static com.google.android.gms.fitness.data.HealthFields.FIELD_OXYGEN_SATURATION;
import static com.google.android.gms.fitness.data.HealthFields.FIELD_OXYGEN_SATURATION_MEASUREMENT_METHOD;
import static com.google.android.gms.fitness.data.HealthFields.FIELD_OXYGEN_SATURATION_SYSTEM;
import static com.google.android.gms.fitness.data.HealthFields.FIELD_OXYGEN_THERAPY_ADMINISTRATION_MODE;
import static com.google.android.gms.fitness.data.HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE;
import static com.google.android.gms.fitness.data.HealthFields.OXYGEN_SATURATION_MEASUREMENT_METHOD_PULSE_OXIMETRY;
import static com.google.android.gms.fitness.data.HealthFields.OXYGEN_SATURATION_SYSTEM_PERIPHERAL_CAPILLARY;
import static com.google.android.gms.fitness.data.HealthFields.OXYGEN_THERAPY_ADMINISTRATION_MODE_NASAL_CANULA;
import static com.google.android.gms.fitness.data.HealthFields.OXYGEN_THERAPY_ADMINISTRATION_MODE_NASAL_CANULA;
import static com.google.android.gms.fitness.data.HealthFields.*;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class HomeFragment extends Fragment implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener{

    private static final String AUTH_PENDING = "auth_state_pending";
    private boolean authInProgress = false;
    private HomeViewModel homeViewModel;
    FragmentHomeBinding binding;
    private GoogleApiClient GoogleApiClient;
    private static final int REQUEST_OAUTH = 1001;
    TextView blood_pressure;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding=FragmentHomeBinding.inflate(getLayoutInflater());


        return binding.getRoot();

    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       blood_pressure= getView().findViewById(R.id.blood_pressure);



        binding.location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment LocationBooth = new GalleryFragment();
                FragmentManager manager=getActivity().getSupportFragmentManager();
                FragmentTransaction Tre=manager.beginTransaction();
                Tre.replace(R.id.nav_host_fragment, LocationBooth);
                Tre.addToBackStack(null);
                Tre.commit();

            }
        });
    }

    private void WriteHealthData() {
        Calendar cal = Calendar.getInstance();
        Date now = new Date();
        cal.setTime(now);
        long endTime = cal.getTimeInMillis();
        cal.add(Calendar.HOUR_OF_DAY, -1);
        long startTime = cal.getTimeInMillis();

        DataSource bloodPressureSource = new DataSource.Builder()
                .setAppPackageName("com.example.hsmb")
                .setDataType(HealthDataTypes.TYPE_OXYGEN_SATURATION)
                .setStreamName("Oxygen Saturation")
                .setType(DataSource.TYPE_RAW)
                .build();
        DataSet set = DataSet.create(bloodPressureSource);
        DataPoint bloodPressure = set.createDataPoint().setTimeInterval(startTime, endTime, MILLISECONDS);
        bloodPressure.setTimestamp(System.currentTimeMillis(), MILLISECONDS);
        bloodPressure.getValue(FIELD_OXYGEN_SATURATION).setFloat(97.0f);
        bloodPressure.getValue(FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE).setFloat(3.0f);
        bloodPressure.getValue(FIELD_OXYGEN_THERAPY_ADMINISTRATION_MODE).setInt(OXYGEN_THERAPY_ADMINISTRATION_MODE_NASAL_CANULA);
        bloodPressure.getValue(FIELD_OXYGEN_SATURATION_SYSTEM)
                .setInt(OXYGEN_SATURATION_SYSTEM_PERIPHERAL_CAPILLARY);
        bloodPressure.getValue(FIELD_OXYGEN_SATURATION_MEASUREMENT_METHOD).setInt(OXYGEN_SATURATION_MEASUREMENT_METHOD_PULSE_OXIMETRY);
        set.add(bloodPressure);
        Fitness.HistoryApi.insertData(GoogleApiClient,set);
        Log.e("Dine ","insert");


    }

    private void accessGoogleFit() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        long endtime = cal.getTimeInMillis();
        cal.add(Calendar.YEAR, -1);
        long starttime = cal.getTimeInMillis();

        DataReadRequest readRequest = new DataReadRequest.Builder()
                .aggregate(DataType.TYPE_HEART_RATE_BPM, DataType.AGGREGATE_HEART_RATE_SUMMARY)
                .bucketByTime(1, TimeUnit.DAYS)
                .setTimeRange(starttime, endtime, TimeUnit.MILLISECONDS)
                .build();

        Fitness.getHistoryClient(getActivity(),GoogleSignIn.getLastSignedInAccount(getContext()))
                .readDailyTotal(DataType.TYPE_HEART_RATE_BPM)
                .addOnSuccessListener(new OnSuccessListener<DataSet>() {
                    @Override
                    public void onSuccess(DataSet dataSet) {
                        Log.e("Status","Success");
                        if(dataSet.isEmpty()){
                            Log.e("Value",String.valueOf(0));
                        }
                        else {
                            showDataSet(dataSet);
                        }


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Status","Failure",e);
                    }
                })
                .addOnCompleteListener(new OnCompleteListener<DataSet>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSet> task) {
                        Log.d("Status","Complete");

                    }
                });
    }
    public void BloodPressure() {
        long week =100*60*60*24*7;
        Date now = new Date();
        long endTime = now.getTime();
        long startTime = endTime -(week);
//Check how many steps were walked and recorded in the last 7 days
        DataReadRequest readRequest = new DataReadRequest.Builder()
                .aggregate(TYPE_BLOOD_PRESSURE,HealthDataTypes.AGGREGATE_BLOOD_PRESSURE_SUMMARY)
                .bucketByTime(1, TimeUnit.DAYS) //important thing
                .setTimeRange(startTime, endTime, MILLISECONDS)
                .enableServerQueries()
                .build();

        PendingResult pendingResult= Fitness.HistoryApi.readData(GoogleApiClient,readRequest);
        pendingResult.setResultCallback(
                new ResultCallback<DataReadResult>() {
                    @Override
                    public void onResult(@NonNull DataReadResult dataReadResult) {
                        if (dataReadResult.getBuckets().size() > 0) {
                            Log.e("History", "Number of buckets: " + dataReadResult.getBuckets().size());
                            for (Bucket bucket : dataReadResult.getBuckets()) {
                                List<DataSet> dataSets = bucket.getDataSets();
                                for (DataSet dataSet : dataSets) {
                                    showDataSet(dataSet);
                                }
                            }
                        }
//Used for non-aggregated data
                        else if (dataReadResult.getDataSets().size() > 0) {
                            Log.e("History", "Number of returned DataSets: " + dataReadResult.getDataSets().size());
                            for (DataSet dataSet : dataReadResult.getDataSets()) {
                                showDataSet(dataSet);
                            }
                        }
                    }
                }
        );
    }

    private void showDataSet(DataSet dataSet) {
        Log.e("History", "Data returned for Data type: " + dataSet.getDataType().getName());
        DateFormat dateFormat = DateFormat.getDateInstance();
        DateFormat timeFormat = DateFormat.getTimeInstance();

        for (DataPoint dp : dataSet.getDataPoints()) {
            Log.e("History", "Data point:");
            Log.e("History", "\tType: " + dp.getDataType().getName());
            Log.e("History", "\tStart: " + dateFormat.format(dp.getStartTime(MILLISECONDS)) + " " + timeFormat.format(dp.getStartTime(MILLISECONDS)));
            Log.e("History", "\tEnd: " + dateFormat.format(dp.getEndTime(MILLISECONDS)) + " " + timeFormat.format(dp.getStartTime(MILLISECONDS)));
            for(Field field : dp.getDataType().getFields()) {
                Log.e("History", "\tField: " + field.getName() +
                        " Value: " + dp.getValue(field));

            }
       /*     blood_pressure.setText(""+dp.getValue(dp.getDataType().getFields().get(0)).asFloat()+"/"
                    +dp.getValue(dp.getDataType().getFields().get(3)).asFloat()
            +" mmHg");*/
        }
    }


    @Override
    public void onStart() {
        super.onStart();

        GoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addApi(Fitness.HISTORY_API)
                .enableAutoManage(getActivity(),this)
                .addScope(new Scope(Scopes.FITNESS_BLOOD_PRESSURE_READ_WRITE))
                .addScope(new Scope(Scopes.FITNESS_BODY_TEMPERATURE_READ_WRITE))
                .addScope(new Scope(Scopes.FITNESS_OXYGEN_SATURATION_READ_WRITE))
                .addScope(new Scope(Scopes.FITNESS_BODY_READ_WRITE))
                .addConnectionCallbacks(this)
                .build();
        GoogleApiClient.connect();

    }

    //------ avoid memory leaks----------
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding=null;
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(AUTH_PENDING, authInProgress);
    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
Log.e("google fit","connected ");

        FitnessOptions fitnessOptions = FitnessOptions.builder()
                .addDataType(DataType.TYPE_HEART_RATE_BPM, FitnessOptions.ACCESS_READ)
                .addDataType(DataType.AGGREGATE_HEART_RATE_SUMMARY, FitnessOptions.ACCESS_READ)
                .build();

        if (!GoogleSignIn.hasPermissions(GoogleSignIn.getLastSignedInAccount(getContext()), fitnessOptions)) {
            GoogleSignIn.requestPermissions(
                    this, // your activity
                    1,
                    GoogleSignIn.getLastSignedInAccount(getContext()),
                    fitnessOptions);
        } else {
            accessGoogleFit();
        }

    /* Calendar cal = Calendar.getInstance();
        Date now = new Date();
        cal.setTime(now);
        long endTime = cal.getTimeInMillis();
        cal.add(Calendar.HOUR_OF_DAY, -1);
        long startTime = cal.getTimeInMillis();

        DataSource bloodPressureSource = new DataSource.Builder()
                .setAppPackageName("com.example.hsmb")
                .setDataType(TYPE_BLOOD_PRESSURE)
                .setStreamName("blood pressure")
                .setType(DataSource.TYPE_RAW)
                .build();
        DataSet set = DataSet.create(bloodPressureSource);
        DataPoint bloodPressure = set.createDataPoint().setTimeInterval(startTime, endTime, MILLISECONDS);
        bloodPressure.setTimestamp(System.currentTimeMillis(), MILLISECONDS);
        bloodPressure.getValue(FIELD_BLOOD_PRESSURE_SYSTOLIC).setFloat(120.0f);
        bloodPressure.getValue(FIELD_BLOOD_PRESSURE_DIASTOLIC).setFloat(80.0f);
        bloodPressure.getValue(FIELD_BODY_POSITION).setInt(BODY_POSITION_SITTING);
        bloodPressure.getValue(FIELD_BLOOD_PRESSURE_MEASUREMENT_LOCATION)
                .setInt(BLOOD_PRESSURE_MEASUREMENT_LOCATION_LEFT_UPPER_ARM);
        set.add(bloodPressure);
        Fitness.HistoryApi.insertData(GoogleApiClient,set);
        Log.e("done"," ir"+ set.isEmpty());*/
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if( !authInProgress ) {
            try {
                authInProgress = true;
                connectionResult.startResolutionForResult( getActivity(), REQUEST_OAUTH );
            } catch(IntentSender.SendIntentException e ) {

            }
        } else {
            Log.e( "GoogleFit", "authInProgress" );
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( requestCode == REQUEST_OAUTH ) {
            authInProgress = false;
            if( resultCode == RESULT_OK ) {
                if( !GoogleApiClient.isConnecting() && !GoogleApiClient.isConnected() ) {
                    GoogleApiClient.connect();
                }
            } else if( resultCode == RESULT_CANCELED ) {
                Log.e( "GoogleFit", "RESULT_CANCELED" );
            }
        } else {
            Log.e("GoogleFit", "requestCode NOT request_oauth");
        }
    }
}