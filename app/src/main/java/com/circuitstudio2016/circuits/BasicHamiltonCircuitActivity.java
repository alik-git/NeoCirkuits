package com.circuitstudio2016.circuits;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.RelativeLayout;

public class BasicHamiltonCircuitActivity extends AppCompatActivity {
    HamiltonCircuit circuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hcircuits);
        Graph graph = new Graph();
        Vertex v1 = new Vertex(300, 100);
        Vertex v2 = new Vertex(100, 800);
        Vertex v3 = new Vertex(600, 800);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        v1.connect(v2);
        v2.connect(v3);
        v3.connect(v1);
        circuit = new HamiltonCircuit(graph);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_hcircuits);

        int count = 0;
        for(Vertex v: graph.getVertices()) {
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(100, 100);
            final Button vertex = new Button(this);
            layout.addView(vertex, lp);
            vertex.setText(Integer.toString(count));
            vertex.setX(v.getMarginLeft());
            vertex.setY(v.getMarginTop());
            vertex.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    circuit.tryActivate(circuit.getGraph().getVertices().get(Integer.parseInt(vertex.getText().toString())));
                    checkWon();
                }
            });
            count++;
        }
    }

    public void checkWon(){
        if(circuit.isFinished()){
            System.out.println("good job!");
        }
    }

    //show vertices on screen

    //draw lines between last Vertex and current location
    //and between "activated" vertices

    //mouse over a vertex will call circuit.tryActivate(Vertex)

    //lift finger will call circuit.reset()

}
