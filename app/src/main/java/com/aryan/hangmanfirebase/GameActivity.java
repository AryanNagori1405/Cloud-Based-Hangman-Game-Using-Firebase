package com.aryan.hangmanfirebase;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class GameActivity extends AppCompatActivity {

    TextView wordText,livesText;
    GridLayout grid;
    HangmanManager game;

    FirebaseFirestore db;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        wordText=findViewById(R.id.wordText);
        livesText=findViewById(R.id.livesText);
        grid=findViewById(R.id.lettersGrid);

        game=new HangmanManager();

        db=FirebaseFirestore.getInstance();
        uid= FirebaseAuth.getInstance().getUid();

        updateUI();
        createKeyboard();
    }

    void updateUI(){
        wordText.setText(game.getDisplayWord());
        livesText.setText("Lives: "+game.getLives());
    }

    void createKeyboard(){

        for(char c='A';c<='Z';c++){

            Button btn=new Button(this);
            btn.setText(String.valueOf(c));

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 0;
            params.height = GridLayout.LayoutParams.WRAP_CONTENT;
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED,1f);
            btn.setLayoutParams(params);

            char finalC = c;
            btn.setOnClickListener(v->{
                btn.setEnabled(false);

                game.guess(finalC);
                updateUI();

                if(game.isWin()){
                    addScore(10);
                    showEndDialog("You Won! +10 score");
                }

                if(game.isLose()){
                    showEndDialog("You Lost!");
                }
            });

            grid.addView(btn);
        }
    }


    void addScore(int points){
        db.collection("users").document(uid)
                .update("score", FieldValue.increment(points));
    }

    void showEndDialog(String msg){
        new AlertDialog.Builder(this)
                .setTitle(msg)
                .setCancelable(false)
                .setPositiveButton("Back", (d,w)->finish())
                .show();
    }
}
