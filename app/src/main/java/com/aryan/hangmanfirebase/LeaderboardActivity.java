package com.aryan.hangmanfirebase;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class LeaderboardActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LeaderboardAdapter adapter;
    ArrayList<Player> playerList;
    FirebaseFirestore db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        // Initialize views
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize list + adapter
        playerList = new ArrayList<>();
        adapter = new LeaderboardAdapter(this, playerList);
        recyclerView.setAdapter(adapter);

        // Firebase
        db = FirebaseFirestore.getInstance();

        loadLeaderboard();
    }

    private void loadLeaderboard() {

        db.collection("users")
                .orderBy("score", Query.Direction.DESCENDING)
                .addSnapshotListener((value, error) -> {

                    if (error != null || value == null)
                        return;

                    playerList.clear();

                    for (DocumentSnapshot doc : value) {
                        Player player = doc.toObject(Player.class);
                        if (player != null)
                            playerList.add(player);
                    }

                    adapter.notifyDataSetChanged();
                });
    }
}
