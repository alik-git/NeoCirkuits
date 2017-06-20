package com.circuitstudio2016.circuits;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


public class HamiltonLevelsActivity extends SelectActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GridView gridView = (GridView) findViewById(R.id.activity_select_grid);
        String[] type = {"Level ", "Hamilton"};
        final GraphsAdapter graphsAdapter =
                new GraphsAdapter(this, getGraphs().getGraphsArray(), type);
        gridView.setAdapter(graphsAdapter);
    }
}
