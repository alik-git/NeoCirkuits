package com.circuitstudio2016.circuits;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;

public class GraphLoadActivity extends SelectActivity {



//    private String[] type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GridView gridView = (GridView) findViewById(R.id.activity_select_grid);
        String[] type = {"Graph ", "Hamilton"};
        final GraphsAdapter graphsAdapter =
                new GraphsAdapter(this, getGraphs().getGraphsArray(), type);
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