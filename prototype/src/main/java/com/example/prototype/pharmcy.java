package com.example.prototype;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class pharmcy extends AppCompatActivity {
ArrayList<PhacrmacyP> phacrmacyt = new ArrayList<>();
    String IDRAco;
    CheckBox Panadol,Fevadol,Asprine,Paracetmol,Citalopram;
    Button send;
    EditText numPandol,numFevedol,numAsprine,numParacetmol,numCitalopraam;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pharmacy);
        IDRAco=getIntent().getStringExtra("ID");
        Panadol=findViewById(R.id.Panadol);
        Fevadol=findViewById(R.id.Fevadol);
        Asprine=findViewById(R.id.Asprine);
        Paracetmol=findViewById(R.id.Paracetmol);
        Citalopram=findViewById(R.id.Citalopram);
        numPandol=findViewById(R.id.numberPanadol);
        numFevedol=findViewById(R.id.numberFevadol);
        numAsprine=findViewById(R.id.numberAsprine);
        numCitalopraam=findViewById(R.id.numberCitalopram);
        numParacetmol=findViewById(R.id.numberParacetmol);
        send=findViewById(R.id.send);

        db.collection("pharmacy")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot decoment : task.getResult()){
                                PhacrmacyP phacrmacy =decoment.toObject(PhacrmacyP.class);
                                phacrmacyt.add(phacrmacy);
                                Log.e("name",phacrmacyt.get(0).getName());
                            }
                            //check هf the expiration date of the medicine or quantity has expired
                            Calendar c= Calendar.getInstance();
                            int year=c.get(Calendar.YEAR);
                            int month =c.get(Calendar.MONTH);
                            int day= c.get(Calendar.DATE);
                            if(Integer.parseInt(phacrmacyt.get(0).getExpiryDate().substring(0,4)) < year | phacrmacyt.get(0).getNumber() ==0 ){
                                Asprine.setEnabled(false);
                                numAsprine.setEnabled(false);
                            }
                            if(Integer.parseInt(phacrmacyt.get(1).getExpiryDate().substring(0,4)) < year || phacrmacyt.get(1).getNumber() ==0 ){
                                Citalopram.setEnabled(false);
                                numCitalopraam.setEnabled(false);
                            }
                            if(Integer.parseInt(phacrmacyt.get(2).getExpiryDate().substring(0,4)) < year || phacrmacyt.get(2).getNumber() ==0 ){
                                Fevadol.setEnabled(false);
                                numFevedol.setEnabled(false);
                            }
                            if(Integer.parseInt(phacrmacyt.get(3).getExpiryDate().substring(0,4)) < year || phacrmacyt.get(3).getNumber() ==0 ){
                                Panadol.setEnabled(false);
                                numPandol.setEnabled(false);
                            }
                            if(Integer.parseInt(phacrmacyt.get(4).getExpiryDate().substring(0,4)) < year || phacrmacyt.get(4).getNumber() ==0 ){
                                Paracetmol.setEnabled(false);
                                numParacetmol.setEnabled(false);
                            }
                        }
                    }
                });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicine();
            }
        });
    }

    private void medicine() {
        String prescription="";
        if(Asprine.isChecked()){
            int number;
            if(!numAsprine.getText().toString().equals("")){

                number=Integer.parseInt(numAsprine.getText().toString());
            }
            else{
                number=1;
            }
            db.collection("pharmacy")
                    .document("Asprine")
                    .update("number", phacrmacyt.get(0).getNumber()-number);

            prescription+="Asprine "+number+" ,";

        }

        if(Citalopram.isChecked()){
            int number;
            if(!numCitalopraam.getText().toString().equals("")){
                number=Integer.parseInt(numCitalopraam.getText().toString());
            }
            else{

                number=1;
            }
            db.collection("pharmacy")
                    .document("Citalopram")
                    .update("number", phacrmacyt.get(1).getNumber()-number);
            prescription+="Citalopram "+number+" ,";
        }

        if(Fevadol.isChecked()){
            int number;
            if(!numFevedol.getText().toString().equals("")){

                number=Integer.parseInt(numFevedol.getText().toString());
            }
            else{
                number=1;
            }
            db.collection("pharmacy")
                    .document("Fevadol")
                    .update("number", phacrmacyt.get(2).getNumber()-number);
            prescription+="Fevadol "+number+" ,";
        }

        if(Panadol.isChecked()){
            int number;
            if(!numPandol.getText().toString().equals("")){
                number=Integer.parseInt(numPandol.getText().toString());
            }
            else{
                number=1;
            }
            db.collection("pharmacy")
                    .document("Panadol")
                    .update("number", phacrmacyt.get(3).getNumber()-number);
            prescription+="Panadol "+number+" ,";
        }

        if(Paracetmol.isChecked()){
            int number;
            if(!numParacetmol.getText().toString().equals("")){
                number=Integer.parseInt(numParacetmol.getText().toString());

            }
            else{
                number=1;
            }
            db.collection("pharmacy")
                    .document("Paracetmol")
                    .update("number", phacrmacyt.get(4).getNumber()-number);
            prescription+="Paracetmol "+number+" ,";
        }


        Map<String, Object> prescriptionPilgrim = new HashMap<>();
        prescriptionPilgrim.put("IdAccount", IDRAco);
        prescriptionPilgrim.put("Medicines", prescription);

        db.collection("prescription")
                .add(prescriptionPilgrim)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                        Toast.makeText(getApplicationContext(),"Prescription has been sent",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("TAG", "Error adding document", e);
                    }
                });
    }
}
