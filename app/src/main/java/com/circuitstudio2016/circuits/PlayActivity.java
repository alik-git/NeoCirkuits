package com.circuitstudio2016.circuits;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
    }

    public void hCircuits(View w){
//        Intent intent = new Intent();
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("graph", Graph.makeSimpleGraph());
//        intent.putExtras(bundle);
//        intent.setClass(this, HamiltonActivity.class);
//        intent.setAction("circuit");
//        startActivity(intent);

        Intent intent = new Intent(this, HCircuitLevelsActivity.class);
        startActivity(intent);//lll
    }

    public void hPaths(View w){
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable("graph", Graph.makeSimpleGraph());
        intent.putExtras(bundle);
        intent.setClass(this, HamiltonActivity.class);
        intent.setAction("path");
        startActivity(intent);
    }

}
