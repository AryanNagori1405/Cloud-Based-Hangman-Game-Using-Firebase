package com.aryan.hangmanfirebase;

import java.util.Random;

public class HangmanManager {

    private final String[] words = {
            "ANDROID","FIREBASE","JAVA","MOBILE","DATABASE","OBJECT","STRING"
    };

    private String word;
    private char[] guessed;
    private int lives = 6;

    public HangmanManager(){
        word = words[new Random().nextInt(words.length)];
        guessed = new char[word.length()];
        for(int i=0;i<guessed.length;i++) guessed[i]='_';
    }

    public String getDisplayWord(){
        StringBuilder sb=new StringBuilder();
        for(char c:guessed) sb.append(c).append(" ");
        return sb.toString();
    }

    public boolean guess(char letter){
        boolean correct=false;

        for(int i=0;i<word.length();i++){
            if(word.charAt(i)==letter){
                guessed[i]=letter;
                correct=true;
            }
        }

        if(!correct) lives--;
        return correct;
    }

    public boolean isWin(){
        return word.equals(new String(guessed));
    }

    public boolean isLose(){
        return lives<=0;
    }

    public int getLives(){ return lives; }
}