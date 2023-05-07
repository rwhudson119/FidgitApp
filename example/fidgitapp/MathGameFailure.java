package com.example.fidgitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MathGameFailure extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_game_failure);
        Intent i = getIntent();
        String score = i.getStringExtra("score");

        ((TextView)findViewById(R.id.final_score)).setText("Score: " + score);
    }


    public void RestartMathsGame(View v) {
        Intent i = new Intent(this, MathsGame.class);
        startActivity(i);
    }
}