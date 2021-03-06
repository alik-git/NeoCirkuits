package com.circuitstudio2016.circuits;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.View;

public class HCircuitLevelsActivity extends LevelsActivity {

    private GraphParser parser = new GraphParser();

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

    private Graph g4 = this.parser.parse("I: #0 Graph(6):\n" +
            "I: (270, 1035)\n" +
            "I: (540, 1395)\n" +
            "I: (900, 1035)\n" +
            "I: (810, 495)\n" +
            "I: (270, 585)\n" +
            "I: (720, 855)\n" +
            "I: vdone\n" +
            "I: Edges(8):\n" +
            "I: (0to2)\n" +
            "I: (0to3)\n" +
            "I: (0to5)\n" +
            "I: (1to2)\n" +
            "I: (1to4)\n" +
            "I: (1to5)\n" +
            "I: (2to3)\n" +
            "I: (3to4)\n" +
            "I: edone");
//    private Graph g5 = this.parser.parse("");
//    private Graph g6 = this.parser.parse("");
//    private Graph g7 = this.parser.parse("");
//    private Graph g8 = this.parser.parse("");
//    private Graph g9 = this.parser.parse("");
//    private Graph g10 = this.parser.parse("");
//    private Graph g11 = this.parser.parse("");
//    private Graph g12 = this.parser.parse("");
//    private Graph g13 = this.parser.parse("");
//    private Graph g14 = this.parser.parse("");
//    private Graph g15 = this.parser.parse("");
//    private Graph g16 = this.parser.parse("");
//    private Graph g17 = this.parser.parse("");
//    private Graph g18 = this.parser.parse("");
//    private Graph g19 = this.parser.parse("");
//    private Graph g20 = this.parser.parse("");
//    private Graph g21 = this.parser.parse("");
//    private Graph g22 = this.parser.parse("");
//    private Graph g23 = this.parser.parse("");
//    private Graph g24 = this.parser.parse("");
//    private Graph g25 = this.parser.parse("");
//    private Graph g26 = this.parser.parse("");
//    private Graph g27 = this.parser.parse("");
//    private Graph g28 = this.parser.parse("");
//    private Graph g29 = this.parser.parse("");
//    private Graph g30 = this.parser.parse("");
//    private Graph g31 = this.parser.parse("");
//    private Graph g32 = this.parser.parse("");
//    private Graph g33 = this.parser.parse("");
//    private Graph g34 = this.parser.parse("");
//    private Graph g35 = this.parser.parse("");
//    private Graph g36 = this.parser.parse("");
//    private Graph g37 = this.parser.parse("");
//    private Graph g38 = this.parser.parse("");
//    private Graph g39 = this.parser.parse("");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        addGraph(g1);
        addGraph(g2);
        addGraph(g3);
        addGraph(g4);
        super.onCreate(savedInstanceState);
        System.out.println("load func: " + getGraphs());
    }
}
