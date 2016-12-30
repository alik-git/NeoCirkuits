package com.circuitstudio2016.circuits;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class MakeActivity extends AppCompatActivity implements View.OnTouchListener, View.OnKeyListener{
    private Graph graph;
    private MakeDrawView drawView;
    private Vertex prev;
    private RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make);
        graph = new Graph();

        layout = (RelativeLayout) findViewById(R.id.activity_make);
        drawView = new MakeDrawView(this, graph);
        drawView.setBackgroundColor(Color.WHITE);
        drawView.setOnTouchListener(this);
        drawView.setOnKeyListener(this);
        layout.addView(drawView);
    }

    public void toggleBoundary(View w){
        drawView.toggleBoundary();
        drawView.invalidate();
    }

    public int distance(float x, float y, Vertex v){
        double distX = Math.abs(x - v.getX());
        double distY = Math.abs(y - v.getY());
        return (int) Math.sqrt(distX * distX + distY * distY);
    }

    public boolean nearAnyVertex(float x, float y){
        for(Vertex v: graph.getVertices()) {
            if(distance(x, y, v) <= 100){
                return true;
            }
        }
        return false;
    }

    public boolean overVertex(float x, float y, Vertex v){
        if(distance(x, y, v) <= 50){
            return true;
        }
        return false;
    }

    public boolean isProperGraph(){
        for (Vertex v: graph.getVertices()){
            if(v.getConnections().size() < 2){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(!nearAnyVertex(e.getX(), e.getY())){
                    Vertex vrtx = new Vertex((int) e.getX(), (int)e.getY(), 50);
                    graph.addVertex(vrtx);
                    prev = vrtx;
                }
                for(Vertex vrtx: graph.getVertices()){
                    if(overVertex(e.getX(), e.getY(), vrtx)){
                        prev = vrtx;
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                for(Vertex vrtx: graph.getVertices()) {
                    if (overVertex(e.getX(), e.getY(), vrtx) && prev != null && vrtx != prev) {
                        if(!vrtx.isConnected(prev)) {
                            vrtx.connect(prev);
                        }
                        prev = vrtx;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                prev = null;
                break;
        }
        drawView.invalidate();
        return true;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent e) {
        Log.i("key","" + keyCode);
        System.out.println(keyCode);
        if (keyCode == KeyEvent.KEYCODE_SPACE) {
            if (e.getAction() == KeyEvent.ACTION_DOWN ) {
                if(graph.getVertices().size() >= 1){
                    graph.removeVertex(graph.getVertices().get(graph.getVertices().size()-1));
                    drawView.invalidate();
                }
            }
            return true;
        }
        return false;
    }
}
