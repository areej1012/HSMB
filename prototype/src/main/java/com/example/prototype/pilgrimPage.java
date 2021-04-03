package com.example.prototype;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.ThrowOnExtraProperties;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class pilgrimPage extends AppCompatActivity {
    Button webex;
    Button logout;
    VideoView videoView;
    String IDBooth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pilgrim);
        logout=findViewById(R.id.logout);
        webex=findViewById(R.id.button3);
        videoView=findViewById(R.id.video_view);
        String ID=getIntent().getStringExtra("ID");
      logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              FirebaseFirestore db =FirebaseFirestore.getInstance();
                db.collection("BoothLocation")
                        .document(ID)
                        .update("State","sterilization" , "idAccount","");
                Intent nextScreen = new Intent(pilgrimPage.this, LoginPilgrim.class);
                startActivity(nextScreen);

            }
        });

      webex.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) { FirebaseFirestore db =FirebaseFirestore.getInstance();

          db.collection("BoothLocation").document(ID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
              @Override
              public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                  IDBooth=task.getResult().getString("IDLocation");
              }
          });

             db.collection("Account_For_Doctor")
                      .get()
                      .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                          @Override
                          public void onComplete(@NonNull Task<QuerySnapshot> task) {
                              if(task.isSuccessful());
                              for(QueryDocumentSnapshot decment : task.getResult()){
                                  if(decment.get("idLocation").toString().equals(IDBooth)){
                                      Uri uri = Uri.parse(decment.getString("webex"));
                                      Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                      startActivity(intent);
                                  }
                              }
                          }
                      });
          }
      });

    }


    private void video() {
       /*
        String videoPath="android.resource://"+getPackageName()+"/"+R.raw.video;
        Uri uri=Uri.parse(videoPath);

        videoView.setVideoURI(uri);
        MediaController mediaController=new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);*/
    }


}
