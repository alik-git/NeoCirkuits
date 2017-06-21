package com.circuitstudio2016.circuits;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.widget.GridView;


public class GraphLevelsActivity extends SelectActivity {

    private int levels_unlocked;
    private GraphList unlocked_graphs;

    private GraphList hamiltonGraphs = new GraphList();
    private GraphList eulerGraphs = new GraphList();
    private GraphParser parser = new GraphParser();

    private Graph hg1 = this.parser.parse("I: #0 Graph(3):\n" +
            "I: (540, 675)\n" +
            "I: (810, 1215)\n" +
            "I: (270, 1215)\n" +
            "I: vdone\n" +
            "I: Edges(3):\n" +
            "I: (0to1)\n" +
            "I: (0to2)\n" +
            "I: (1to2)\n" +
            "I: edone");

    private Graph hg2 = this.parser.parse("I: #1 Graph(4):\n" +
            "I: (180, 675)\n" +
            "I: (810, 675)\n" +
            "I: (540, 1305)\n" +
            "I: (450, 855)\n" +
            "I: vdone\n" +
            "I: Edges(6):\n" +
            "I: (0to1)\n" +
            "I: (0to2)\n" +
            "I: (0to3)\n" +
            "I: (1to2)\n" +
            "I: (1to3)\n" +
            "I: (2to3)\n" +
            "I: edone");

    private Graph hg3 = this.parser.parse("I: #0 Graph(5):\n" +
            "I: (540, 1305)\n" +
            "I: (180, 1035)\n" +
            "I: (450, 585)\n" +
            "I: (810, 765)\n" +
            "I: (810, 1035)\n" +
            "I: vdone\n" +
            "I: Edges(6):\n" +
            "I: (0to1)\n" +
            "I: (0to2)\n" +
            "I: (0to4)\n" +
            "I: (1to3)\n" +
            "I: (2to4)\n" +
            "I: (3to4)\n" +
            "I: edone");

    private Graph hg4 = this.parser.parse("I: #2 Graph(4):\n" +
            "I: (270, 1215)\n" +
            "I: (720, 675)\n" +
            "I: (720, 1215)\n" +
            "I: (270, 675)\n" +
            "I: vdone\n" +
            "I: Edges(5):\n" +
            "I: (0to1)\n" +
            "I: (0to2)\n" +
            "I: (0to3)\n" +
            "I: (1to3)\n" +
            "I: (2to3)\n" +
            "I: edone");

//    private Graph hg4 = this.parser.parse("I: #0 Graph(6):\n" +
//            "I: (360, 1125)\n" +
//            "I: (270, 675)\n" +
//            "I: (720, 585)\n" +
//            "I: (900, 1215)\n" +
//            "I: (270, 1395)\n" +
//            "I: (810, 855)\n" +
//            "I: vdone\n" +
//            "I: Edges(7):\n" +
//            "I: (0to2)\n" +
//            "I: (0to3)\n" +
//            "I: (0to4)\n" +
//            "I: (1to2)\n" +
//            "I: (1to3)\n" +
//            "I: (2to5)\n" +
//            "I: (4to5)\n" +
//            "I: edone");

    private Graph hg5 = this.parser.parse("I: #0 Graph(7):\n" +
            "I: (540, 945)\n" +
            "I: (270, 675)\n" +
            "I: (810, 675)\n" +
            "I: (810, 1305)\n" +
            "I: (270, 1305)\n" +
            "I: (540, 405)\n" +
            "I: (540, 1575)\n" +
            "I: vdone\n" +
            "I: Edges(10):\n" +
            "I: (0to1)\n" +
            "I: (0to3)\n" +
            "I: (0to5)\n" +
            "I: (0to6)\n" +
            "I: (1to4)\n" +
            "I: (1to5)\n" +
            "I: (2to3)\n" +
            "I: (2to5)\n" +
            "I: (3to6)\n" +
            "I: (4to6)\n" +
            "I: edone");

//    private Graph hg5 = this.parser.parse("I: #2 Graph(7):\n" +
//            "I: (270, 855)\n" +
//            "I: (720, 585)\n" +
//            "I: (810, 1215)\n" +
//            "I: (360, 1305)\n" +
//            "I: (540, 1035)\n" +
//            "I: (180, 495)\n" +
//            "I: (900, 855)\n" +
//            "I: vdone\n" +
//            "I: Edges(9):\n" +
//            "I: (0to1)\n" +
//            "I: (0to3)\n" +
//            "I: (0to6)\n" +
//            "I: (1to2)\n" +
//            "I: (2to3)\n" +
//            "I: (2to4)\n" +
//            "I: (3to4)\n" +
//            "I: (4to5)\n" +
//            "I: (5to6)\n" +
//            "I: edone\n");


    private Graph hg6 = this.parser.parse("I: #1 Graph(5):\n" +
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
    private Graph hg7 = this.parser.parse("I: #0 Graph(6):\n" +
            "I: (360, 1305)\n" +
            "I: (180, 945)\n" +
            "I: (630, 675)\n" +
            "I: (810, 1035)\n" +
            "I: (360, 495)\n" +
            "I: (720, 1395)\n" +
            "I: vdone\n" +
            "I: Edges(8):\n" +
            "I: (0to1)\n" +
            "I: (0to2)\n" +
            "I: (0to3)\n" +
            "I: (1to2)\n" +
            "I: (1to4)\n" +
            "I: (2to3)\n" +
            "I: (3to5)\n" +
            "I: (4to5)\n" +
            "I: edone");
    private Graph hg8 = this.parser.parse("I: #2 Graph(7):\n" +
            "I: (270, 855)\n" +
            "I: (720, 585)\n" +
            "I: (810, 1215)\n" +
            "I: (360, 1305)\n" +
            "I: (540, 1035)\n" +
            "I: (180, 495)\n" +
            "I: (900, 855)\n" +
            "I: vdone\n" +
            "I: Edges(9):\n" +
            "I: (0to1)\n" +
            "I: (0to3)\n" +
            "I: (0to6)\n" +
            "I: (1to2)\n" +
            "I: (2to3)\n" +
            "I: (2to4)\n" +
            "I: (3to4)\n" +
            "I: (4to5)\n" +
            "I: (5to6)\n" +
            "I: edone\n");
    private Graph hg9 = this.parser.parse("I: #0 Graph(6):\n" +
            "I: (360, 1125)\n" +
            "I: (270, 675)\n" +
            "I: (720, 585)\n" +
            "I: (900, 1215)\n" +
            "I: (270, 1395)\n" +
            "I: (810, 855)\n" +
            "I: vdone\n" +
            "I: Edges(7):\n" +
            "I: (0to2)\n" +
            "I: (0to3)\n" +
            "I: (0to4)\n" +
            "I: (1to2)\n" +
            "I: (1to3)\n" +
            "I: (2to5)\n" +
            "I: (4to5)\n" +
            "I: edone");
    private Graph hg10 = this.parser.parse("I: #0 Graph(6):\n" +
            "I: (810, 690)\n" +
            "I: (540, 420)\n" +
            "I: (270, 690)\n" +
            "I: (810, 1230)\n" +
            "I: (270, 1230)\n" +
            "I: (540, 1500)\n" +
            "I: vdone\n" +
            "I: Edges(8):\n" +
            "I: (0to1)\n" +
            "I: (0to2)\n" +
            "I: (1to2)\n" +
            "I: (1to5)\n" +
            "I: (2to3)\n" +
            "I: (3to4)\n" +
            "I: (3to5)\n" +
            "I: (4to5)\n" +
            "I: edone");
    private Graph hg11 = this.parser.parse("I: #0 Graph(9):\n" +
            "I: (540, 960)\n" +
            "I: (270, 960)\n" +
            "I: (810, 960)\n" +
            "I: (720, 690)\n" +
            "I: (360, 690)\n" +
            "I: (540, 510)\n" +
            "I: (720, 1230)\n" +
            "I: (360, 1230)\n" +
            "I: (540, 1410)\n" +
            "I: vdone\n" +
            "I: Edges(11):\n" +
            "I: (0to1)\n" +
            "I: (0to2)\n" +
            "I: (0to6)\n" +
            "I: (0to7)\n" +
            "I: (1to3)\n" +
            "I: (2to4)\n" +
            "I: (2to7)\n" +
            "I: (3to5)\n" +
            "I: (4to5)\n" +
            "I: (6to8)\n" +
            "I: (7to8)\n" +
            "I: edone");
    private Graph hg12 = this.parser.parse("I: #1 Graph(9):\n" +
            "I: (540, 960)\n" +
            "I: (270, 960)\n" +
            "I: (810, 960)\n" +
            "I: (720, 690)\n" +
            "I: (360, 690)\n" +
            "I: (540, 510)\n" +
            "I: (720, 1230)\n" +
            "I: (360, 1230)\n" +
            "I: (540, 1410)\n" +
            "I: vdone\n" +
            "I: Edges(13):\n" +
            "I: (0to3)\n" +
            "I: (0to4)\n" +
            "I: (0to5)\n" +
            "I: (0to8)\n" +
            "I: (1to6)\n" +
            "I: (1to7)\n" +
            "I: (2to4)\n" +
            "I: (2to7)\n" +
            "I: (3to4)\n" +
            "I: (3to5)\n" +
            "I: (4to5)\n" +
            "I: (6to8)\n" +
            "I: (7to8)\n" +
            "I: edone");
    private Graph hg13 = this.parser.parse("Graph(9):\n" +
            "I: (360, 915)\n" +
            "I: (720, 915)\n" +
            "I: (810, 1185)\n" +
            "I: (270, 1185)\n" +
            "I: (540, 1365)\n" +
            "I: (270, 735)\n" +
            "I: (810, 735)\n" +
            "I: (540, 555)\n" +
            "I: (540, 1095)\n" +
            "I: vdone\n" +
            "I: Edges(13):\n" +
            "I: (0to1)\n" +
            "I: (0to3)\n" +
            "I: (0to5)\n" +
            "I: (0to8)\n" +
            "I: (1to2)\n" +
            "I: (1to6)\n" +
            "I: (2to4)\n" +
            "I: (2to8)\n" +
            "I: (3to4)\n" +
            "I: (4to8)\n" +
            "I: (5to7)\n" +
            "I: (6to7)\n" +
            "I: (7to8)\n" +
            "I: edone");
    private Graph hg14 = this.parser.parse("I: Graph(8):\n" +
            "I: (270, 735)\n" +
            "I: (810, 735)\n" +
            "I: (810, 1455)\n" +
            "I: (270, 1455)\n" +
            "I: (450, 1185)\n" +
            "I: (630, 915)\n" +
            "I: (450, 465)\n" +
            "I: (810, 1185)\n" +
            "I: vdone\n" +
            "I: Edges(11):\n" +
            "I: (0to5)\n" +
            "I: (0to3)\n" +
            "I: (0to6)\n" +
            "I: (1to6)\n" +
            "I: (1to5)\n" +
            "I: (2to7)\n" +
            "I: (2to3)\n" +
            "I: (3to4)\n" +
            "I: (4to7)\n" +
            "I: (4to6)\n" +
            "I: (5to7)\n" +
            "I: edone");
    private Graph hg15 = this.parser.parse("I: Graph(9):\n" +
            "I: (810, 1095)\n" +
            "I: (270, 1095)\n" +
            "I: (450, 1455)\n" +
            "I: (720, 1455)\n" +
            "I: (540, 1095)\n" +
            "I: (810, 645)\n" +
            "I: (360, 735)\n" +
            "I: (270, 465)\n" +
            "I: (540, 465)\n" +
            "I: vdone\n" +
            "I: Edges(12):\n" +
            "I: (0to3)\n" +
            "I: (0to8)\n" +
            "I: (1to2)\n" +
            "I: (1to5)\n" +
            "I: (1to6)\n" +
            "I: (2to4)\n" +
            "I: (2to3)\n" +
            "I: (3to4)\n" +
            "I: (4to5)\n" +
            "I: (5to7)\n" +
            "I: (6to7)\n" +
            "I: (6to8)\n" +
            "I: edone");
    private Graph hg16 = this.parser.parse("I: Graph(8):\n" +
            "I: (855, 735)\n" +
            "I: (855, 1185)\n" +
            "I: (315, 1365)\n" +
            "I: (225, 1005)\n" +
            "I: (405, 465)\n" +
            "I: (675, 645)\n" +
            "I: (495, 1095)\n" +
            "I: (675, 1455)\n" +
            "I: vdone\n" +
            "I: Edges(11):\n" +
            "I: (0to3)\n" +
            "I: (0to1)\n" +
            "I: (0to5)\n" +
            "I: (1to4)\n" +
            "I: (1to7)\n" +
            "I: (2to6)\n" +
            "I: (2to4)\n" +
            "I: (2to3)\n" +
            "I: (3to5)\n" +
            "I: (5to7)\n" +
            "I: (6to7)\n" +
            "I: edone");
    private Graph hg17 = this.parser.parse("I: Graph(8):\n" +
            "I: (540, 825)\n" +
            "I: (540, 1275)\n" +
            "I: (270, 915)\n" +
            "I: (810, 915)\n" +
            "I: (810, 465)\n" +
            "I: (360, 465)\n" +
            "I: (180, 1455)\n" +
            "I: (900, 1185)\n" +
            "I: vdone\n" +
            "I: Edges(11):\n" +
            "I: (0to1)\n" +
            "I: (0to4)\n" +
            "I: (0to2)\n" +
            "I: (1to6)\n" +
            "I: (1to7)\n" +
            "I: (2to7)\n" +
            "I: (2to6)\n" +
            "I: (2to5)\n" +
            "I: (3to5)\n" +
            "I: (3to7)\n" +
            "I: (4to5)\n" +
            "I: edone");
    private Graph hg18 = this.parser.parse("I: Graph(8):\n" +
            "I: (225, 870)\n" +
            "I: (675, 870)\n" +
            "I: (405, 1140)\n" +
            "I: (225, 1410)\n" +
            "I: (675, 1410)\n" +
            "I: (765, 600)\n" +
            "I: (315, 510)\n" +
            "I: (855, 1140)\n" +
            "I: vdone\n" +
            "I: Edges(11):\n" +
            "I: (0to1)\n" +
            "I: (0to6)\n" +
            "I: (0to3)\n" +
            "I: (1to5)\n" +
            "I: (1to4)\n" +
            "I: (2to6)\n" +
            "I: (2to3)\n" +
            "I: (2to4)\n" +
            "I: (3to4)\n" +
            "I: (4to7)\n" +
            "I: (5to7)\n" +
            "I: edone");
    private Graph hg19 = this.parser.parse("I: Graph(8):\n" +
            "I: (225, 870)\n" +
            "I: (675, 870)\n" +
            "I: (405, 1140)\n" +
            "I: (225, 1410)\n" +
            "I: (675, 1410)\n" +
            "I: (765, 600)\n" +
            "I: (315, 510)\n" +
            "I: (855, 1140)\n" +
            "I: vdone\n" +
            "I: Edges(11):\n" +
            "I: (0to7)\n" +
            "I: (0to5)\n" +
            "I: (0to3)\n" +
            "I: (1to4)\n" +
            "I: (1to7)\n" +
            "I: (1to2)\n" +
            "I: (2to6)\n" +
            "I: (2to3)\n" +
            "I: (3to4)\n" +
            "I: (5to6)\n" +
            "I: (5to7)\n" +
            "I: edone");
    private Graph hg20 = this.parser.parse("I: Graph(9):\n" +
            "I: (585, 915)\n" +
            "I: (855, 825)\n" +
            "I: (315, 645)\n" +
            "I: (855, 1365)\n" +
            "I: (225, 1185)\n" +
            "I: (495, 1455)\n" +
            "I: (585, 1185)\n" +
            "I: (585, 465)\n" +
            "I: (855, 555)\n" +
            "I: vdone\n" +
            "I: Edges(12):\n" +
            "I: (0to6)\n" +
            "I: (0to1)\n" +
            "I: (0to4)\n" +
            "I: (1to2)\n" +
            "I: (1to8)\n" +
            "I: (2to7)\n" +
            "I: (2to4)\n" +
            "I: (2to8)\n" +
            "I: (3to4)\n" +
            "I: (3to7)\n" +
            "I: (4to5)\n" +
            "I: (5to6)\n" +
            "I: edone");
    private Graph hg21 = this.parser.parse("I: Graph(6):\n" +
            "I: (810, 690)\n" +
            "I: (540, 420)\n" +
            "I: (270, 690)\n" +
            "I: (540, 1500)\n" +
            "I: (810, 1230)\n" +
            "I: (270, 1230)\n" +
            "I: vdone\n" +
            "I: Edges(9):\n" +
            "I: (0to1)\n" +
            "I: (0to2)\n" +
            "I: (0to5)\n" +
            "I: (1to3)\n" +
            "I: (1to2)\n" +
            "I: (2to5)\n" +
            "I: (3to4)\n" +
            "I: (3to5)\n" +
            "I: (4to5)\n" +
            "I: edone");
    private Graph hg22 = this.parser.parse("");
    private Graph hg23 = this.parser.parse("");
    private Graph hg24 = this.parser.parse("");
    private Graph hg25 = this.parser.parse("");
    private Graph hg26 = this.parser.parse("");
    private Graph hg27 = this.parser.parse("");
    private Graph hg28 = this.parser.parse("");
    private Graph hg29 = this.parser.parse("");
    private Graph hg30 = this.parser.parse("");
    private Graph hg31 = this.parser.parse("");
    private Graph hg32 = this.parser.parse("");
    private Graph hg33 = this.parser.parse("");
    private Graph hg34 = this.parser.parse("");
    private Graph hg35 = this.parser.parse("");
    private Graph hg36 = this.parser.parse("");
    private Graph hg37 = this.parser.parse("");
    private Graph hg38 = this.parser.parse("");
    private Graph hg39 = this.parser.parse("");


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

    private Graph eg2 = this.parser.parse("I: #1 Graph(6):\n" +
            "I: (540, 945)\n" +
            "I: (270, 675)\n" +
            "I: (810, 675)\n" +
            "I: (810, 1305)\n" +
            "I: (270, 1305)\n" +
            "I: (540, 1305)\n" +
            "I: vdone\n" +
            "I: Edges(8):\n" +
            "I: (0to1)\n" +
            "I: (0to2)\n" +
            "I: (0to4)\n" +
            "I: (0to5)\n" +
            "I: (1to4)\n" +
            "I: (2to3)\n" +
            "I: (3to5)\n" +
            "I: (4to5)\n" +
            "I: edone");

    private Graph eg3 = this.parser.parse("I: #2 Graph(6):\n" +
            "I: (450, 1035)\n" +
            "I: (180, 765)\n" +
            "I: (180, 1395)\n" +
            "I: (720, 585)\n" +
            "I: (810, 1035)\n" +
            "I: (810, 1395)\n" +
            "I: vdone\n" +
            "I: Edges(9):\n" +
            "I: (0to1)\n" +
            "I: (0to2)\n" +
            "I: (0to4)\n" +
            "I: (0to5)\n" +
            "I: (1to3)\n" +
            "I: (1to4)\n" +
            "I: (2to5)\n" +
            "I: (3to4)\n" +
            "I: (4to5)\n" +
            "I: edone");
    private Graph eg4 = this.parser.parse("I: #0 Graph(5):\n" +
            "I: (540, 945)\n" +
            "I: (810, 1215)\n" +
            "I: (270, 1215)\n" +
            "I: (810, 585)\n" +
            "I: (270, 585)\n" +
            "I: vdone\n" +
            "I: Edges(7):\n" +
            "I: (0to1)\n" +
            "I: (0to2)\n" +
            "I: (0to3)\n" +
            "I: (0to4)\n" +
            "I: (1to2)\n" +
            "I: (1to3)\n" +
            "I: (2to4)\n" +
            "I: edone");
    private Graph eg5 = this.parser.parse("I: #3 Graph(7):\n" +
            "I: (720, 495)\n" +
            "I: (270, 495)\n" +
            "I: (270, 855)\n" +
            "I: (720, 855)\n" +
            "I: (720, 1215)\n" +
            "I: (270, 1215)\n" +
            "I: (180, 1485)\n" +
            "I: vdone\n" +
            "I: Edges(8):\n" +
            "I: (0to1)\n" +
            "I: (1to2)\n" +
            "I: (2to3)\n" +
            "I: (2to4)\n" +
            "I: (3to4)\n" +
            "I: (4to5)\n" +
            "I: (4to6)\n" +
            "I: (5to6)\n" +
            "I: edone");
    private Graph eg6 = this.parser.parse("I: #4 Graph(6):\n" +
            "I: (540, 855)\n" +
            "I: (360, 1305)\n" +
            "I: (810, 1305)\n" +
            "I: (810, 675)\n" +
            "I: (270, 585)\n" +
            "I: (180, 945)\n" +
            "I: vdone\n" +
            "I: Edges(8):\n" +
            "I: (0to1)\n" +
            "I: (0to2)\n" +
            "I: (0to4)\n" +
            "I: (0to5)\n" +
            "I: (1to4)\n" +
            "I: (2to3)\n" +
            "I: (3to4)\n" +
            "I: (4to5)\n" +
            "I: edone");
    private Graph eg7 = this.parser.parse("I: #0 Graph(7):\n" +
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
    private Graph eg8 = this.parser.parse("");
    private Graph eg9 = this.parser.parse("");
    private Graph eg10 = this.parser.parse("");
    private Graph eg11 = this.parser.parse("");
    private Graph eg12 = this.parser.parse("");
    private Graph eg13 = this.parser.parse("");
    private Graph eg14 = this.parser.parse("");
    private Graph eg15 = this.parser.parse("");
    private Graph eg16 = this.parser.parse("");
    private Graph eg17 = this.parser.parse("");
    private Graph eg18 = this.parser.parse("");
    private Graph eg19 = this.parser.parse("");
    private Graph eg20 = this.parser.parse("");
    private Graph eg21 = this.parser.parse("");
    private Graph eg22 = this.parser.parse("");
    private Graph eg23 = this.parser.parse("");
    private Graph eg24 = this.parser.parse("");
    private Graph eg25 = this.parser.parse("");
    private Graph eg26 = this.parser.parse("");
    private Graph eg27 = this.parser.parse("");
    private Graph eg28 = this.parser.parse("");
    private Graph eg29 = this.parser.parse("");
    private Graph eg30 = this.parser.parse("");
    private Graph eg31 = this.parser.parse("");
    private Graph eg32 = this.parser.parse("");
    private Graph eg33 = this.parser.parse("");
    private Graph eg34 = this.parser.parse("");
    private Graph eg35 = this.parser.parse("");
    private Graph eg36 = this.parser.parse("");
    private Graph eg37 = this.parser.parse("");
    private Graph eg38 = this.parser.parse("");
    private Graph eg39 = this.parser.parse("");


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        addHamiltonGraph(hg1);
        addHamiltonGraph(hg2);
        addHamiltonGraph(hg3);
        addHamiltonGraph(hg4);
        addHamiltonGraph(hg5);
        addHamiltonGraph(hg6);
        addHamiltonGraph(hg7);
        addHamiltonGraph(hg8);
        addHamiltonGraph(hg9);
        addHamiltonGraph(hg10);
        addHamiltonGraph(hg11);
        addHamiltonGraph(hg12);
        addHamiltonGraph(hg13);
        addHamiltonGraph(hg14);
        addHamiltonGraph(hg15);
        addHamiltonGraph(hg16);
        addHamiltonGraph(hg17);
        addHamiltonGraph(hg18);
        addHamiltonGraph(hg19);
        addHamiltonGraph(hg20);
        addHamiltonGraph(hg21);
        addHamiltonGraph(hg22);
        addHamiltonGraph(hg23);
        addHamiltonGraph(hg24);
        addHamiltonGraph(hg25);
        addHamiltonGraph(hg26);
        addHamiltonGraph(hg27);
        addHamiltonGraph(hg28);
        addHamiltonGraph(hg29);
        addHamiltonGraph(hg30);
        addHamiltonGraph(hg31);
        addHamiltonGraph(hg32);
        addHamiltonGraph(hg33);
        addHamiltonGraph(hg34);
        addHamiltonGraph(hg35);
        addHamiltonGraph(hg36);
        addHamiltonGraph(hg37);
        addHamiltonGraph(hg38);
        addHamiltonGraph(hg39);

        addEulerGraph(eg1);
        addEulerGraph(eg2);
        addEulerGraph(eg3);
        addEulerGraph(eg4);
        addEulerGraph(eg5);
        addEulerGraph(eg6);
        addEulerGraph(eg7);
        addEulerGraph(eg8);
        addEulerGraph(eg9);
        addEulerGraph(eg10);
        addEulerGraph(eg11);
        addEulerGraph(eg12);
        addEulerGraph(eg13);
        addEulerGraph(eg14);
        addEulerGraph(eg15);
        addEulerGraph(eg16);
        addEulerGraph(eg17);
        addEulerGraph(eg18);
        addEulerGraph(eg19);
        addEulerGraph(eg20);
        addEulerGraph(eg21);
        addEulerGraph(eg22);
        addEulerGraph(eg23);
        addEulerGraph(eg24);
        addEulerGraph(eg25);
        addEulerGraph(eg26);
        addEulerGraph(eg27);
        addEulerGraph(eg28);
        addEulerGraph(eg29);
        addEulerGraph(eg30);
        addEulerGraph(eg31);
        addEulerGraph(eg32);
        addEulerGraph(eg33);
        addEulerGraph(eg34);
        addEulerGraph(eg35);
        addEulerGraph(eg36);
        addEulerGraph(eg37);
        addEulerGraph(eg38);
        addEulerGraph(eg39);


        super.onCreate(savedInstanceState);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String unlocked = this.getIntent().getStringExtra("unlocked");
        levels_unlocked = prefs.getInt(unlocked, 85678);
        System.out.println("CURRENT RESPECTIVE LEVEL UNLOCKED IS: " + unlocked + "\n" + levels_unlocked);
        if (levels_unlocked == 85678) { levels_unlocked = 1; }

        levels_unlocked = 100;
        GraphList tempGraphs;
        if ( this.getIntent().getStringExtra("type").equals("Hamilton")) {
            tempGraphs = hamiltonGraphs;
        } else {
            tempGraphs = eulerGraphs;
        }

//        ArrayList<Graph> graphs2 = tempGraphs.getGraphs();
//        System.out.println(tempGraphs.getGraphs() + graphs2.toString() + "\nhubhubhubhubhub");
        getMyGraphs().setGraphs(tempGraphs.getGraphs());



        unlocked_graphs = getMyGraphs().getUpto(levels_unlocked);

        GridView gridView = (GridView) findViewById(R.id.activity_select_grid);
        String[] type = {"Level ", this.getIntent().getStringExtra("type")};
        final LevelsGraphsAdapter levelsGraphsAdapter =
                new LevelsGraphsAdapter(this, unlocked_graphs.getGraphsArray(), type);
        gridView.setAdapter(levelsGraphsAdapter);

        if (this.getIntent().hasExtra("rush")) {

            int toload = this.getIntent().getIntExtra("rush", 1);
            System.out.println("TRYING TO RUSH TO: " + toload);
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putParcelable("graph", this.unlocked_graphs.getGraph(toload));
            intent.putExtras(bundle);
            String message = "Level " + (Integer.toString(toload + 1));
            intent.putExtra("message", message);
            intent.putExtra("currentNum", toload + 1);
            if (type[1].equals("Hamilton")) {
                intent.setClass(this, HamiltonPlayActivity.class);
            } else {
                intent.setClass(this, EulerPlayActivity.class);
            }
            //intent.setAction("circuit");
            startActivity(intent);
            finish();
        }
    }

    public int getLevels_unlocked() {
        return levels_unlocked;
    }

    public GraphList getUnlocked_graphs() {
        return unlocked_graphs;
    }

    public void addHamiltonGraph(Graph g) {
        this.hamiltonGraphs.addGraph(g);
    }

    public void addEulerGraph(Graph g) {this.eulerGraphs.addGraph(g);}

}
