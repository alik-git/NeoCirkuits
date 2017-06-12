package com.circuitstudio2016.circuits;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HCircuitLevelsActivity extends AppCompatActivity {

    private GraphList graphs = new GraphList();;
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
            "I: edone");

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hcircuit_levels);
        System.out.println("kskfsbksfbsk" +this.g1);
        this.graphs.addGraph(g1);
        this.graphs.addGraph(g2);
        System.out.println("load func: " + this.graphs);
    }

    public void load(int num) {

        if (this.graphs.getSize() > num) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            System.out.println("load func: " + this.graphs);
            System.out.println("load func graph: " + this.graphs.getGraph(num));
            System.out.println(num);
            bundle.putParcelable("graph", this.graphs.getGraph(num));
            intent.putExtras(bundle);
            String message = "Graph " + Integer.toString(num) + ": ";
            intent.putExtra("message", message);
            intent.setClass(this, HamiltonTestActivity.class);
            intent.setAction("circuit");
            startActivity(intent);
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
