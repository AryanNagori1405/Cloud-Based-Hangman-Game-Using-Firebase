package com.aryan.hangmanfirebase;

public class Player {

    private String name;
    private long score;

    public Player() {}   // Required for Firestore

    public String getName() {
        return name == null ? "Unknown" : name;
    }

    public long getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(long score) {
        this.score = score;
    }
}
