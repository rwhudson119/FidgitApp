package com.example.fidgitapp;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.view.View;
        import android.os.Bundle;
        import android.util.Log;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Main");
    }

    public void launchLibrary(View v){
        Intent i = new Intent(this, GameLibrary.class);
        String name = ((EditText)findViewById(R.id.source)).getText().toString();
        i.putExtra("name",name);
        startActivity(i);

    }
}