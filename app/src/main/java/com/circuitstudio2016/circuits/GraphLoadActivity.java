package com.circuitstudio2016.circuits;

import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

public class GraphLoadActivity extends SelectActivity {



//    private String[] type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GraphList tempGraphs = this.getIntent().getExtras().getParcelable("graphs");

//        ArrayList<Graph> graphs2 = tempGraphs.getGraphs();
//        System.out.println(tempGraphs.getGraphs() + graphs2.toString() + "\nhubhubhubhubhub");
        getMyGraphs().setGraphs(tempGraphs.getGraphs());

        GridView gridView = (GridView) findViewById(R.id.activity_select_grid);
        String[] type = {"Graph ", "Hamilton"};
        final GraphsAdapter graphsAdapter =
                new GraphsAdapter(this, getMyGraphs().getGraphsArray(), type);
        gridView.setAdapter(graphsAdapter);
    }

//    public void toggleMode(View w) {
//        if (type[1].equals("Hamilton")) {
//            type[1] = "Euler";
//        } else {
//            type[1] = "Hamilton";
//        }
//    }
//
//    public String[] getType() {
//        return type;
//    }
}