package com.circuitstudio2016.circuits;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ECircuitLevelsActivity extends LevelsActivity {

    private GraphParser parser = new GraphParser();

    private Graph eg1 = this.parser.parse("I: #0 Graph(4):\n" +
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

    private Graph eg2 = this.parser.parse("I: #0 Graph(7):\n" +
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

//    private Graph eg3 = this.parser.parse("");
//    private Graph eg4 = this.parser.parse("");
//    private Graph eg5 = this.parser.parse("");
//    private Graph eg6 = this.parser.parse("");
//    private Graph eg7 = this.parser.parse("");
//    private Graph eg8 = this.parser.parse("");
//    private Graph eg9 = this.parser.parse("");
//    private Graph eg10 = this.parser.parse("");
//    private Graph eg11 = this.parser.parse("");
//    private Graph eg12 = this.parser.parse("");
//    private Graph eg13 = this.parser.parse("");
//    private Graph eg14 = this.parser.parse("");
//    private Graph eg15 = this.parser.parse("");
//    private Graph eg16 = this.parser.parse("");
//    private Graph eg17 = this.parser.parse("");
//    private Graph eg18 = this.parser.parse("");
//    private Graph eg19 = this.parser.parse("");
//    private Graph eg20 = this.parser.parse("");
//    private Graph eg21 = this.parser.parse("");
//    private Graph eg22 = this.parser.parse("");
//    private Graph eg23 = this.parser.parse("");
//    private Graph eg24 = this.parser.parse("");
//    private Graph eg25 = this.parser.parse("");
//    private Graph eg26 = this.parser.parse("");
//    private Graph eg27 = this.parser.parse("");
//    private Graph eg28 = this.parser.parse("");
//    private Graph eg29 = this.parser.parse("");
//    private Graph eg30 = this.parser.parse("");
//    private Graph eg31 = this.parser.parse("");
//    private Graph eg32 = this.parser.parse("");
//    private Graph eg33 = this.parser.parse("");
//    private Graph eg34 = this.parser.parse("");
//    private Graph eg35 = this.parser.parse("");
//    private Graph eg36 = this.parser.parse("");
//    private Graph eg37 = this.parser.parse("");
//    private Graph eg38 = this.parser.parse("");
//    private Graph eg39 = this.parser.parse("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        addGraph(eg1);
        addGraph(eg2);
        super.onCreate(savedInstanceState);
        System.out.println("load func: " + getGraphs());
    }
}
