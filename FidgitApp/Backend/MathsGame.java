package com.example.fidgitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.util.Random;

public class MathsGame extends AppCompatActivity {
    private int inputs[] = new int[2];
    private boolean failure;
    private int score;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maths_game);
        gameStartResetInp();
        setTexts();
    }
    public void gameStartResetInp(){
        Log.d("startup","Starts");
        this.inputs[0] = -1;
        this.inputs[1] = -1;
        findViewById(R.id.option1).setBackgroundColor(Color.parseColor("#4962FF"));
        findViewById(R.id.option2).setBackgroundColor(Color.parseColor("#4962FF"));
        findViewById(R.id.option3).setBackgroundColor(Color.parseColor("#4962FF"));
    }

    public void resetInp(){
        score = score + 1;
        ((TextView) findViewById(R.id.score)).setText(String.valueOf(score));
        String [] oper = {"+","-","x","%"};
        int index = random.nextInt(4);
        ((TextView) findViewById(R.id.operation)).setText(oper[index]);
        if(this.inputs[0] != -1){
            findViewById(this.inputs[0]).setBackgroundColor(Color.parseColor("#4962FF"));
            this.inputs[0] = -1;
        }
        if(this.inputs[1] != -1){
            findViewById(this.inputs[1]).setBackgroundColor(Color.parseColor("#4962FF"));
            this.inputs[1] = -1;
        }
    }

    public void runGame(){
        this.failure = false;
        while(this.failure == false) {
            resetInp();
            setTexts();
        }
    }


    public void setTexts(){
        //addition
        int tempInputs[] = new int[3];
        int output = -1;
        String op = ((TextView)findViewById(R.id.operation)).getText().toString();

        Log.d("startup","start setting" + op);
        if(op.equals("+") || op.equals("-")) {
            Log.d("startup","op recognised");
            output = random.nextInt(97) + 3;

            tempInputs[0] = 1 + random.nextInt(output - 1);
            tempInputs[1] = output - tempInputs[0];
            tempInputs[2] = 2 + random.nextInt(output + 15);

            if (op.equals("-")) {
                int temp = output;
                output = tempInputs[0];
                tempInputs[0] = temp;
            }
        }else if(op.equals("x") || op.equals("%")) {
            output = random.nextInt(27) + 3;

            tempInputs[0] = 1 + random.nextInt(13);
            tempInputs[1] = output * tempInputs[0];
            tempInputs[2] = 2 + random.nextInt(output + tempInputs[0] - 2);

            if (op.equals("x")) {
                int temp = output;
                output = tempInputs[1];
                tempInputs[1] = temp;
            }
        }


        int index = 3 + random.nextInt(3);
        int incrementInd = 1 + random.nextInt(2);
        //assign values
        ((TextView) findViewById(R.id.option1)).setText(String.valueOf(tempInputs[index % 3]));
        ((TextView) findViewById(R.id.option2)).setText(String.valueOf(tempInputs[(index + incrementInd) % 3]));
        ((TextView) findViewById(R.id.option3)).setText(String.valueOf(tempInputs[(index - incrementInd) % 3]));
        ((TextView) findViewById(R.id.outcome)).setText(String.valueOf(output));

    }

    public String setInputs(int inputid) {
        Button t = findViewById(inputid);
        if(this.inputs[0] == -1){
            Log.d("input1", String.valueOf(inputs[0]) + "  " + String.valueOf(inputs[1]));
            inputs[0] = inputid;
            t.setBackgroundColor(Color.parseColor("#161D5A"));
            return "incomplete";
        }else if(this.inputs[1] == -1){
            Log.d("input1", String.valueOf(inputs[0]) + "  " + String.valueOf(inputs[1]));
            inputs[1] = inputid;
            t.setBackgroundColor(Color.parseColor("#161D5A"));
            return "complete";
        }
        return "incomplete";

    }

    public void checkAnswer(View v){
        String status = setInputs(v.getId());
        if(status.equals("complete")){
            boolean outcome = doMaths();
            if(outcome){
                Log.d("outputLog","CORRECT");
                Toast.makeText(this, "CORRECT",Toast.LENGTH_LONG).show();
                resetInp();
                setTexts();
            }else{
                Log.d("outputLog","INCORRECT");
                Toast.makeText(this, "INCORRECT",Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, MathGameFailure.class);
                i.putExtra("score",String.valueOf(score));

                startActivity(i);


            }

        }
    }

    public boolean doMaths(){
        int equ = Integer.parseInt(((TextView)findViewById(R.id.outcome)).getText().toString());
        int inp1 = Integer.parseInt(((TextView)findViewById(inputs[0])).getText().toString());
        int inp2 = Integer.parseInt(((TextView)findViewById(inputs[1])).getText().toString());
        String op = ((TextView)findViewById(R.id.operation)).getText().toString();
        Log.d("Operation",op);
        if(op.equals("+")){
            Log.d("MathsTest","addition");
            return(add(inp1,inp2,equ));
        }else if(op.equals("-")){
            Log.d("MathsTest","subtraction");
            return(sub(inp1,inp2,equ));
        }else if(op.equals("x")){
            Log.d("MathsTest","multiplication");
            return(mul(inp1,inp2,equ));
        }else if(op.equals("%")) {
            Log.d("MathsTest","division");
            return (div(inp1, inp2, equ));
        }Log.d("MathsTest","misses");
        return false;
    }
    public boolean add(int input1, int input2, int output){

        return ((input1 + input2) == output);
    }
    public boolean sub(int input1, int input2, int output){

        return ((input1 - input2) == output);
    }
    public boolean mul(int input1, int input2, int output){

        return ((input1 * input2) == output);
    }
    public boolean div(int input1, int input2, int output){
        return ((input1 / input2) == output);
    }



}