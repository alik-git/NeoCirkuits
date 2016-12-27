package com.circuitstudio2016.circuits;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void play(View w){
        Intent intent = new Intent(getApplicationContext(), PlayActivity.class);
        startActivity(intent);
    }

    protected void exit(View w){
        Toast t1 = Toast.makeText(getApplicationContext(), "Exit game here!", Toast.LENGTH_LONG);
        t1.show();
    }
}
