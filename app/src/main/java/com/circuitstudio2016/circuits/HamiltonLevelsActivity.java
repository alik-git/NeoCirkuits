package com.circuitstudio2016.circuits;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;


public class HamiltonLevelsActivity extends SelectActivity {

    private int hamilton_unlocked;
    private GraphList unlocked_graphs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        hamilton_unlocked = prefs.getInt(getResources().getString(R.string.current_hamilton_level), 45678);
        System.out.println("CURRENT HAM UNLOCKED IS: " + hamilton_unlocked);
        if (hamilton_unlocked == 45678) { hamilton_unlocked = 1; }

        unlocked_graphs = new GraphList();
        unlocked_graphs.setGraphs( new ArrayList<Graph> (getGraphs().getGraphs().subList(0, hamilton_unlocked - 1)) );

        GridView gridView = (GridView) findViewById(R.id.activity_select_grid);
        String[] type = {"Level ", "Hamilton"};
        final GraphsAdapter graphsAdapter =
                new GraphsAdapter(this, getGraphs().getGraphsArray(), type);
        gridView.setAdapter(graphsAdapter);
    }

    public int getHamilton_unlocked() {
        return hamilton_unlocked;
    }

}
