package com.aryan.hangmanfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SignupActivity extends AppCompatActivity {

    EditText name,email,password;
    Button signupBtn;
    FirebaseAuth auth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        signupBtn=findViewById(R.id.signupBtn);

        auth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();

        signupBtn.setOnClickListener(v -> {

            String n = name.getText().toString().trim();
            String e = email.getText().toString().trim();
            String p = password.getText().toString().trim();

            if(n.isEmpty()){
                name.setError("Enter name");
                return;
            }

            if(e.isEmpty()){
                email.setError("Enter email");
                return;
            }

            if(p.length() < 6){
                password.setError("Password must be at least 6 characters");
                return;
            }

            signupBtn.setEnabled(false);

            FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(e,p)
                    .addOnCompleteListener(task -> {

                        signupBtn.setEnabled(true);

                        if(task.isSuccessful()){

                            String uid = task.getResult().getUser().getUid();

                            Map<String,Object> user = new HashMap<>();
                            user.put("name", n);
                            user.put("email", e);
                            user.put("score", 0);

                            FirebaseFirestore.getInstance()
                                    .collection("users")
                                    .document(uid)
                                    .set(user);

                            Toast.makeText(this,"Signup success",Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(SignupActivity.this, DashboardActivity.class));
                            finish();

                        }else{
                            Exception ex = task.getException();
                            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        });
    }
}


