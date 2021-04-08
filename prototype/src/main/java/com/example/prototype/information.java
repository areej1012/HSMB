package com.example.prototype;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class information extends AppCompatActivity {
    EditText name;
    EditText age;
    EditText Chronice;
    EditText ST;
    EditText HR;
    EditText BP;
    EditText SpO2;
    String IDBooth;
    Button pharmacy;
    String IDR;
    Button ref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
         name= findViewById(R.id.name);
         age=findViewById(R.id.Age);
         Chronice=findViewById(R.id.chor);
         ST=findViewById(R.id.ST);
         HR=findViewById(R.id.HR);
         BP=findViewById(R.id.BP);
         SpO2=findViewById(R.id.SpO2);
         pharmacy=findViewById(R.id.pharmacy);
         ref=findViewById(R.id.refesh);

         ref.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 getBooth();
             }
         });
         getBooth();
         pharmacy.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent nextScreen = new Intent(information.this, pharmcy.class);
                 nextScreen.putExtra("ID",IDR);
                 startActivity(nextScreen);
             }
         });

    }

    private void getBooth() {
        IDBooth=getIntent().getStringExtra("IDBooth");
        ArrayList<BoothLocation> arrayList=new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("BoothLocation")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot decoment: task.getResult()){
                                BoothLocation booth = decoment.toObject(BoothLocation.class);
                                arrayList.add(booth);
                            }

                            for (int i =0; arrayList.size() > i;i++){
                                if(IDBooth.equals(arrayList.get(i).getIDLocation())){
                                    IDR=arrayList.get(i).getIDAccount();
                                    getPilgrim(arrayList.get(i).getIDAccount());
                                }
                            }
                        }
                    }
                });
    }

    private void getPilgrim(String idAccount) {
        ArrayList<AccountPilgrim> pilgrim =new ArrayList<>();
        FirebaseFirestore db =FirebaseFirestore.getInstance();
        db.collection("Account_For_pilgrim")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot decoument: task.getResult()){
                                if(decoument.getId().equals(idAccount)){
                                    AccountPilgrim acco =decoument.toObject(AccountPilgrim.class);
                                    name.setText("Name: "+acco.getFirstName()+" "+acco.getLastName());
                                    age.setText("Age: "+acco.getAge());
                                    Chronice.setText("Chronice: "+acco.getMedical_background());
                                    ST.setText("ST: "+acco.getST()+" \u2103");
                                    HR.setText("HR: "+acco.getHR()+" Bpm");
                                    BP.setText("BP: "+acco.getBP()+" mmHg");
                                    SpO2.setText("SpO2: "+acco.getSpO2()+" %");

                                }
                            }
                        }
                    }
                });
        ArrayList<VitalSigns> vitalSigns= new ArrayList<>();
        db.collection("VitalSigns").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
           if(task.isSuccessful()){
               for(QueryDocumentSnapshot docment : task.getResult()){
                   VitalSigns v= docment.toObject(VitalSigns.class);
                   vitalSigns.add(v);
               }
               Random rand = new Random();
               int i= rand.nextInt(vitalSigns.size());

           }
            }
        });
    }
}
