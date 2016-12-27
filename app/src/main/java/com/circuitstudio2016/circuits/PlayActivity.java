package com.circuitstudio2016.circuits;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
    }

    protected void hCircuits(View w){
        Toast t1 = Toast.makeText(getApplicationContext(), "Start H circuits!", Toast.LENGTH_LONG);
        t1.show();
    }

    protected void hPaths(View w){
        Toast t1 = Toast.makeText(getApplicationContext(), "Start H paths!", Toast.LENGTH_LONG);
        t1.show();
    }

}
