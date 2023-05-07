package com.example.fidgitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class GameLibrary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_library);
        setTitle("Game Library");
        Intent i = getIntent();
        String name = i.getStringExtra("name");
        ((TextView)findViewById(R.id.textView)).setText("Hello " + name);
    }

    public void launchMathsGame(View v){
        Intent i = new Intent(this, MathsGame.class);
        startActivity(i);
    }
    public void launch2048Game(View v){
        Intent i = new Intent(this, Game2048.class);
        startActivity(i);
    }
    public void launchLockpickGame(View v){
        Intent i = new Intent(this, LockpickGame.class);
        startActivity(i);
    }
}