package com.example.fidgitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;



public class Game2048 extends AppCompatActivity {

    Random random = new Random();
    public boolean movement = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2048);
        resetGame();

        findViewById(R.id.game2048TableLayout).setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                swipeLeft();
            }
            public void onSwipeRight() {
                swipeRight();
            }
            public void onSwipeTop() {
                swipeUp();
            }
            public void onSwipeBottom() {
                swipeDown();
            }
        });
    }
    public void resetGame(){
        TableLayout t = findViewById(R.id.game2048TableLayout);
        for(int row = 0; row < 4; row ++) {
            TableRow r = (TableRow) t.getChildAt(row);
            for(int col = 0; col < 4; col ++) {
                TextView current = (TextView) r.getChildAt(col);
                current.setText("_");
            }
        }
        setInitialVals(2);
        setInitialVals(2);

    }

    public void setInitialVals(int val){
        TableLayout t = findViewById(R.id.game2048TableLayout);
        TableRow r = (TableRow) t.getChildAt(random.nextInt(4));
        TextView current = (TextView) r.getChildAt(random.nextInt(4));
        if(current.getText().toString().equals("_")){
            current.setText(Integer.toString(val));
        }else {
            setInitialVals(val);
        }
    }

    public void swipeRight(){
        for(int row = 0; row < 4; row ++) {
            for(int start = 2; start >= 0; start--) {
                TableLayout t = findViewById(R.id.game2048TableLayout);
                TextView current = (TextView) ((TableRow) t.getChildAt(row)).getChildAt(start);
                if (!current.getText().toString().equals("_")) {
                    for (int col = start; col < 3; col++) {
                        Log.d("Swipe", "row " + row + " col: " + col);
                        if(swipeHelper(row, col, row, col + 1)){
                            break;
                        }
                    }
                }
            }
        }
        if(movement == true){
            setInitialVals(2);
            movement = false;
        }
    }
    public void swipeLeft(){
        //startcol
        TableLayout t = findViewById(R.id.game2048TableLayout);

        for (int row = 0; row < 4; row++) {
            for(int start = 1; start < 4; start++) {
                TextView current = (TextView) ((TableRow) t.getChildAt(row)).getChildAt(start);
                if(!current.getText().toString().equals("_")) {
                    for (int col = start; col > 0; col--) {
                        Log.d("Swipe", "row " + row + " col: " + col);
                        if(swipeHelper(row, col, row, col - 1)){
                            break;
                        }
                    }
                }
            }
        }
        if(movement == true){
            setInitialVals(2);
            movement = false;
        }
    }
    public void swipeDown(){
        for(int col = 0; col < 4; col ++) {
            for (int start = 2; start >= 0; start--) {
                TableLayout t = findViewById(R.id.game2048TableLayout);
                TextView current = (TextView) ((TableRow) t.getChildAt(start)).getChildAt(col);
                if(!current.getText().toString().equals("_")) {
                    for (int row = start; row < 3; row++) {
                        Log.d("SwipeDown", "row " + row + " col: " + col);
                        if(swipeHelper(row, col, row + 1, col)){
                            break;
                        }
                    }
                }
            }
        }
        if(movement == true){
            setInitialVals(2);
            movement = false;
        }
    }
    public void swipeUp(){
        for(int col = 0; col < 4; col ++) {
            for (int start = 1; start < 4; start++) {
                TableLayout t = findViewById(R.id.game2048TableLayout);
                TextView current = (TextView) ((TableRow) t.getChildAt(start)).getChildAt(col);
                if (!current.getText().toString().equals("_")) {
                    for (int row = start; row > 0; row--) {
                        Log.d("Swipe", "row " + row + " col: " + col);
                        if(swipeHelper(row, col, row - 1, col)){
                            break;
                        }
                    }
                }
            }
        }
        if(movement == true){
            setInitialVals(2);
            movement = false;
        }else if(checkBoard()){
            //gameOver
            String score = ((TextView)findViewById(R.id.game2048Score)).getText().toString();
            Intent i = new Intent(this, Game2048Failure.class);
            i.putExtra("score",String.valueOf(score));
            startActivity(i);
        }
    }

    public boolean checkBoard(){
        for(int i = 0; i < 4; i ++){
            for(int j=0; j < 4; j++){
                TableLayout t = findViewById(R.id.game2048TableLayout);
                TextView current = (TextView) ((TableRow) t.getChildAt(i)).getChildAt(j);
                if(current.getText().toString().equals("_")){
                    return false;
                }
            }
        }
        return true;
    }

    public void takePiece(TextView current, TextView next){
        movement = true;
        String val = current.getText().toString();
        int newVal = Integer.parseInt(val) * 2;
        next.setText(Integer.toString(newVal));
        current.setText("_");
        TextView scoreView = findViewById(R.id.game2048Score);
        int score = Integer.parseInt(scoreView.getText().toString());
        scoreView.setText(Integer.toString((score + newVal)));
    }

    public boolean swipeHelper(int row, int col, int nextRow, int nextCol){
        TableLayout t = findViewById(R.id.game2048TableLayout);
        TableRow r = (TableRow) t.getChildAt(row);
        TextView current = (TextView) r.getChildAt(col);
        if(!current.getText().toString().equals("_")){
            Log.d("SwipeDown","OBJECT DETECTED");
            TableRow nextTabRow = (TableRow) t.getChildAt(nextRow);
            TextView next = (TextView) nextTabRow.getChildAt(nextCol);
            if(next.getText().toString().equals("_")){
                movement = true;
                next.setText(current.getText().toString());
                current.setText("_");
            }else if(next.getText().toString().equals(current.getText().toString())){
                takePiece(current, next);
                return true;
            }
        }
        return false;
    }

    public void Restart2048Game(View v) {
        //Intent i = new Intent(this, Game2048.class);
        //startActivity(i);
        resetGame();
    }
}