package com.aryan.hangmanfirebase;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileActivity extends AppCompatActivity {

    TextView name,score;
    FirebaseFirestore db;
    String uid;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name=findViewById(R.id.name);
        score=findViewById(R.id.score);

        db=FirebaseFirestore.getInstance();
        uid= FirebaseAuth.getInstance().getUid();

        db.collection("users").document(uid)
                .addSnapshotListener((value,error)->{

                    if(value!=null){
                        name.setText(value.getString("name"));
                        score.setText("Score: "+value.getLong("score"));
                    }
                });
    }
}
