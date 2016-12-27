package com.circuitstudio2016.circuits;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BasicHamiltonCircuitActivity extends AppCompatActivity {
    HamiltonCircuit circuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hcircuits);
        Graph graph = new Graph();
        Vertex v1 = new Vertex(220, 40);
        Vertex v2 = new Vertex(350, 400);
        Vertex v3 = new Vertex(50, 400);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        v1.connect(v2);
        v2.connect(v3);
        v3.connect(v1);
        circuit = new HamiltonCircuit(graph);

    }

    //show vertices on screen

    //draw lines between last Vertex and current location
    //and between "activated" vertices

    //mouse over a vertex will call circuit.tryActivate(Vertex)

    //lift finger will call circuit.reset()

}
