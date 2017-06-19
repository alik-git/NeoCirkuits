package com.circuitstudio2016.circuits;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ECircuitLevelsActivity extends LevelsActivity {

    private GraphParser parser = new GraphParser();

    private Graph g1 = this.parser.parse("I: #0 Graph(4):\n" +
            "I: (270, 1125)\n" +
            "I: (540, 1305)\n" +
            "I: (720, 855)\n" +
            "I: (360, 585)\n" +
            "I: vdone\n" +
            "I: Edges(5):\n" +
            "I: (0to1)\n" +
            "I: (0to2)\n" +
            "I: (0to3)\n" +
            "I: (1to2)\n" +
            "I: (1to3)\n" +
            "I: edone");

    private Graph g2 = this.parser.parse("I: #0 Graph(7):\n" +
            "I: (180, 1305)\n" +
            "I: (180, 855)\n" +
            "I: (630, 855)\n" +
            "I: (630, 1305)\n" +
            "I: (360, 675)\n" +
            "I: (810, 675)\n" +
            "I: (810, 1125)\n" +
            "I: vdone\n" +
            "I: Edges(11):\n" +
            "I: (0to1)\n" +
            "I: (0to2)\n" +
            "I: (0to3)\n" +
            "I: (1to2)\n" +
            "I: (1to3)\n" +
            "I: (1to4)\n" +
            "I: (2to3)\n" +
            "I: (2to5)\n" +
            "I: (3to6)\n" +
            "I: (4to5)\n" +
            "I: (5to6)\n" +
            "I: edone");

//    private Graph g3 = this.parser.parse("");
//    private Graph g4 = this.parser.parse("");
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
        super.onCreate(savedInstanceState);
        System.out.println("load func: " + getGraphs());
    }
}
