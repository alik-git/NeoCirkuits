package com.circuitstudio2016.circuits;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

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

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_hcircuits);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        final Button vertex1 = new Button(this);
        layout.addView(vertex1, lp);
        vertex1.setText("1");
        vertex1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                circuit.tryActivate(circuit.getGraph().getVertices().get(Integer.parseInt(vertex1.getText().toString())));
            }
        });
    }

    public void checkWon(){
        if(circuit.isFinished()){
            System.out.println("good job!");
        }
    }

    public void vertex1click(View w){
        circuit.tryActivate(circuit.getGraph().getVertices().get(0));
        checkWon();
    }
    public void vertex2click(View w){
        circuit.tryActivate(circuit.getGraph().getVertices().get(1));
        checkWon();
    }
    public void vertex3click(View w){
        circuit.tryActivate(circuit.getGraph().getVertices().get(2));
        checkWon();
    }

    //show vertices on screen

    //draw lines between last Vertex and current location
    //and between "activated" vertices

    //mouse over a vertex will call circuit.tryActivate(Vertex)

    //lift finger will call circuit.reset()

}
