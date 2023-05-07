package com.example.fidgitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Game2048Failure extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2048_failure);
        Intent i = getIntent();
        String score = i.getStringExtra("score");

        ((TextView)findViewById(R.id.game2048_final_score)).setText("Score: " + score);
    }


    public void Restart2048Game(View v) {
        Intent i = new Intent(this, Game2048.class);
        startActivity(i);
    }
}