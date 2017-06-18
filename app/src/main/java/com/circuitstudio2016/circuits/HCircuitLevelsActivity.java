package com.circuitstudio2016.circuits;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HCircuitLevelsActivity extends AppCompatActivity {

    private GraphList graphs = new GraphList();;
    private GraphParser parser = new GraphParser();
    private int clevel;

    private Graph g1 = this.parser.parse("I: #0 Graph(4):\n" +
            "I: (360, 855)\n" +
            "I: (720, 1125)\n" +
            "I: (900, 675)\n" +
            "I: (540, 495)\n" +
            "I: vdone\n" +
            "I: Edges(4):\n" +
            "I: (0to1)\n" +
            "I: (0to3)\n" +
            "I: (1to2)\n" +
            "I: (2to3)\n" +
            "I: edone\n");

    private Graph g2 = this.parser.parse("I: #1 Graph(5):\n" +
            "I: (720, 1035)\n" +
            "I: (270, 765)\n" +
            "I: (630, 405)\n" +
            "I: (900, 675)\n" +
            "I: (270, 1035)\n" +
            "I: vdone\n" +
            "I: Edges(7):\n" +
            "I: (0to1)\n" +
            "I: (0to2)\n" +
            "I: (0to4)\n" +
            "I: (1to2)\n" +
            "I: (1to4)\n" +
            "I: (2to3)\n" +
            "I: (3to4)\n" +
            "I: edone");

    private Graph g3 = this.parser.parse("I: #0 Graph(6):\n" +
            "I: (270, 1035)\n" +
            "I: (180, 585)\n" +
            "I: (810, 495)\n" +
            "I: (810, 1125)\n" +
            "I: (360, 1485)\n" +
            "I: (630, 765)\n" +
            "I: vdone\n" +
            "I: Edges(8):\n" +
            "I: (0to2)\n" +
            "I: (0to4)\n" +
            "I: (1to2)\n" +
            "I: (1to3)\n" +
            "I: (2to3)\n" +
            "I: (3to4)\n" +
            "I: (3to5)\n" +
            "I: (4to5)\n" +
            "I: edone\n");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hcircuit_levels);
        System.out.println("kskfsbksfbsk" +this.g1);
        this.graphs.addGraph(g1);
        this.graphs.addGraph(g2);
        this.graphs.addGraph(g3);
        System.out.println("load func: " + this.graphs);

        if (this.getIntent().hasExtra("rush")) {

            int toload = this.getIntent().getIntExtra("rush", 1);
            System.out.println("TRYING TO RUSH TO: " + toload);
            load(toload);
        }
    }

    public void load(int num) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        clevel = prefs.getInt(getResources().getString(R.string.current_level), 1);
        System.out.println("CURRENT NUM IS: " + num + " AND THE OTHER ONE " +
                "IS: " + clevel + "\n");
        if (clevel > num) {

            if (this.graphs.getSize() > num) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                System.out.println("load func: " + this.graphs);
                System.out.println("load func graph: " + this.graphs.getGraph(num));
                System.out.println(num);
                bundle.putParcelable("graph", this.graphs.getGraph(num));
                intent.putExtras(bundle);
                String message = "Level " + (Integer.toString(num + 1));
                intent.putExtra("message", message);
                intent.setClass(this, HamiltonPlayActivity.class);
                intent.setAction("circuit");
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
