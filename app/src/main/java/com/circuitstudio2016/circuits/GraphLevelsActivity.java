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

    private Graph hg1 = this.parser.parse("I: #0 Graph(4):\n" +
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

    private Graph hg2 = this.parser.parse("I: #1 Graph(5):\n" +
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

    private Graph hg3 = this.parser.parse("I: #0 Graph(6):\n" +
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

    private Graph hg4 = this.parser.parse("I: #0 Graph(6):\n" +
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


    private Graph hg5 = this.parser.parse("I: #2 Graph(7):\n" +
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


//    private Graph hg6 = this.parser.parse("");
//    private Graph hg7 = this.parser.parse("");
//    private Graph hg8 = this.parser.parse("");
//    private Graph hg9 = this.parser.parse("");
//    private Graph hg10 = this.parser.parse("");
//    private Graph hg11 = this.parser.parse("");
//    private Graph hg12 = this.parser.parse("");
//    private Graph hg13 = this.parser.parse("");
//    private Graph hg14 = this.parser.parse("");
//    private Graph hg15 = this.parser.parse("");
//    private Graph hg16 = this.parser.parse("");
//    private Graph hg17 = this.parser.parse("");
//    private Graph hg18 = this.parser.parse("");
//    private Graph hg19 = this.parser.parse("");
//    private Graph hg20 = this.parser.parse("");
//    private Graph hg21 = this.parser.parse("");
//    private Graph hg22 = this.parser.parse("");
//    private Graph hg23 = this.parser.parse("");
//    private Graph hg24 = this.parser.parse("");
//    private Graph hg25 = this.parser.parse("");
//    private Graph hg26 = this.parser.parse("");
//    private Graph hg27 = this.parser.parse("");
//    private Graph hg28 = this.parser.parse("");
//    private Graph hg29 = this.parser.parse("");
//    private Graph hg30 = this.parser.parse("");
//    private Graph hg31 = this.parser.parse("");
//    private Graph hg32 = this.parser.parse("");
//    private Graph hg33 = this.parser.parse("");
//    private Graph hg34 = this.parser.parse("");
//    private Graph hg35 = this.parser.parse("");
//    private Graph hg36 = this.parser.parse("");
//    private Graph hg37 = this.parser.parse("");
//    private Graph hg38 = this.parser.parse("");
//    private Graph hg39 = this.parser.parse("");


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

        addHamiltonGraph(hg1);
        addHamiltonGraph(hg2);
        addHamiltonGraph(hg3);
        addHamiltonGraph(hg4);
        addHamiltonGraph(hg5);

        addEulerGraph(eg1);
        addEulerGraph(eg2);


        super.onCreate(savedInstanceState);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String unlocked = this.getIntent().getStringExtra("unlocked");
        levels_unlocked = prefs.getInt(unlocked, 85678);
        System.out.println("CURRENT RESPECTIVE LEVEL UNLOCKED IS: " + unlocked + "\n" + levels_unlocked);
        if (levels_unlocked == 85678) { levels_unlocked = 1; }
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
