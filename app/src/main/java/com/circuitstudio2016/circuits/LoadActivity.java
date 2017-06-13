package com.circuitstudio2016.circuits;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoadActivity extends AppCompatActivity {

    private GraphList graphs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        graphs = this.getIntent().getExtras().getParcelable("graphs");
    }

    public void load(int num) {

        if (graphs.getSize() > num) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            System.out.println("load func: " + graphs);
            System.out.println("load func graph: " + graphs.getGraph(num));
            System.out.println(num);
            bundle.putParcelable("graph", graphs.getGraph(num));
            intent.putExtras(bundle);
            String message = "Graph " + (Integer.toString(num + 1));
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
