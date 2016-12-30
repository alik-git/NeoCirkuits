package com.circuitstudio2016.circuits;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class BasicHamiltonCircuitActivity extends AppCompatActivity implements View.OnTouchListener {
    private HamiltonCircuit circuit;
    private DrawView drawView;
    private RelativeLayout layout;
    private int screenX, screenY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hcircuits);
        Graph graph = new Graph();
        screenX = Resources.getSystem().getDisplayMetrics().widthPixels;
        screenY = Resources.getSystem().getDisplayMetrics().heightPixels;
        Vertex v1 = new Vertex(100, 200, screenX/24);
        Vertex v2 = new Vertex(400, 300, screenX/24);
        Vertex v3 = new Vertex(400, 600, screenX/24);
        Vertex v4 = new Vertex(100, 600, screenX/24);
        Vertex v5 = new Vertex(300, 350, screenX/24);
        Vertex v6 = new Vertex(250, 400, screenX/24);
        Vertex v7 = new Vertex(200, 200, screenX/24);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
        graph.addVertex(v6);
        graph.addVertex(v7);
        v1.connect(v2);
        circuit = new HamiltonCircuit(graph);
        layout = (RelativeLayout) findViewById(R.id.activity_hcircuits);

        drawView = new DrawView(this, circuit);
        drawView.setBackgroundColor(Color.WHITE);
        drawView.setOnTouchListener(this);
        layout.addView(drawView);
    }

    public void checkWon(){
        if(circuit.isFinished()){
            Button endButton = new Button(this);
            endButton.setText("Go Back");
            endButton.setX(screenX/3);
            endButton.setY(screenY*2/5);
            endButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), PlayActivity.class);
                    startActivity(intent);
                }
            });
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(screenX/3, screenY/12);
            layout.addView(endButton, lp);
        }
    }

    public boolean overVertex(float x, float y, Vertex v){
        double distX = Math.abs(x - v.getX());
        double distY = Math.abs(y - v.getY());
        return Math.sqrt(distX*distX + distY*distY) <= 50;
    }

    public void activateVertex(float x, float y){
        for(Vertex vrtx: circuit.getGraph().getVertices()){
            if(overVertex(x,y, vrtx)){
                circuit.tryActivate(vrtx);
                checkWon();
            }
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent e) {
        if(!circuit.isFinished()) {
            switch (e.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    drawView.setMouseLocation(e.getX(), e.getY());
                    activateVertex(e.getX(), e.getY());
                    break;
                case MotionEvent.ACTION_MOVE:
                    drawView.setMouseLocation(e.getX(), e.getY());
                    activateVertex(e.getX(), e.getY());
                    break;
                case MotionEvent.ACTION_UP:
                    circuit.reset();
                    break;
            }
            drawView.invalidate();
        }
        return true;
    }

}
