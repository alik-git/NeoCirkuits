package com.circuitstudio2016.circuits;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LevelsActivity extends AppCompatActivity {

    private GraphList graphs = new GraphList();
    private GraphParser parser = new GraphParser();
    private String level_string, type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        System.out.println("IN BUISNESSS YOOOOOOOOOOO");
        level_string = this.getIntent().getStringExtra("level_string");
        type = this.getIntent().getStringExtra("type");

        if (this.getIntent().hasExtra("rush")) {

            int toload = this.getIntent().getIntExtra("rush", 1);
            System.out.println("TRYING TO RUSH TO: " + toload);
            load(toload);
            finish();
        }


    }

    public void addGraph(Graph g) {
        this.graphs.addGraph(g);
    }

    public GraphList getGraphs() {
        return this.graphs;
    }

    public void load(int num) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int clevel = prefs.getInt(level_string, 45678);
        System.out.println("CURRENT NUM IS: " + num + " AND THE OTHER ONE " +
                "IS: " + clevel + "\n");
        if (clevel == 45678) { clevel = 1; }
        if (clevel > num) {
            System.out.println("HAPPENENKENIKFJI IESOFJSOJFSOJFOFS");
            System.out.println(this.graphs);
            if (this.graphs.getSize() > num) {
                System.out.println("HAPPENENKENIKFJI IESOFJSOJFSOJFOFS2222222222222");
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                System.out.println("load func: " + this.graphs);
                System.out.println("load func graph: " + this.graphs.getGraph(num));
                System.out.println(num);
                bundle.putParcelable("graph", this.graphs.getGraph(num));
                intent.putExtras(bundle);
                String message = "Level " + (Integer.toString(num + 1));
                intent.putExtra("message", message);
                if (type.equals("hamilton")) {
                    intent.setClass(this, HamiltonPlayActivity.class);
                } else {
                    intent.setClass(this, EulerPlayActivity.class);
                }
                //intent.setAction("circuit");
                startActivity(intent);

            }
        }

    }

    public void graph1(View w) {
        load(0);
    }

    public void graph2(View w) {
        load(1);
    }

    public void graph3(View w) {
        load(2);
    }

    public void graph4(View w) {
        load(3);
    }

    public void graph5(View w) {
        load(4);
    }

    public void graph6(View w) {
        load(5);
    }

    public void graph7(View w) {
        load(6);
    }

    public void graph8(View w) {
        load(7);
    }

    public void graph9(View w) {
        load(8);
    }
}

