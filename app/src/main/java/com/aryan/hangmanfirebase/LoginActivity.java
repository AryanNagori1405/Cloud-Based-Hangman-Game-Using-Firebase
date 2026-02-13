package com.aryan.hangmanfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText email,password;
    Button loginBtn;
    TextView goSignup;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        loginBtn=findViewById(R.id.loginBtn);
        goSignup=findViewById(R.id.goSignup);

        auth=FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(v -> {

            String e = email.getText().toString().trim();
            String p = password.getText().toString().trim();

            if(e.isEmpty() || p.isEmpty()){
                Toast.makeText(this,"Enter email & password",Toast.LENGTH_SHORT).show();
                return;
            }

            auth.signInWithEmailAndPassword(e,p)
                    .addOnSuccessListener(result -> {
                        Toast.makeText(this,"Login successful",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this,DashboardActivity.class));
                        finish();
                    })
                    .addOnFailureListener(err ->
                            Toast.makeText(this,"Wrong email or password",Toast.LENGTH_SHORT).show());
        });

        goSignup.setOnClickListener(v ->
                startActivity(new Intent(this,SignupActivity.class)));
    }
}

