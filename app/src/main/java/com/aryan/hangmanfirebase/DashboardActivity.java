package com.aryan.hangmanfirebase;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        findViewById(R.id.playBtn).setOnClickListener(v ->
                startActivity(new Intent(this, GameActivity.class)));

        findViewById(R.id.profileBtn).setOnClickListener(v ->
                startActivity(new Intent(this, ProfileActivity.class)));

        findViewById(R.id.leaderBtn).setOnClickListener(v ->
                startActivity(new Intent(this, LeaderboardActivity.class)));

        findViewById(R.id.logoutBtn).setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        });
    }
}
