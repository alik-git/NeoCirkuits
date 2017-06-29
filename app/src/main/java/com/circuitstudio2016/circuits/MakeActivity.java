package com.circuitstudio2016.circuits;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MakeActivity extends AppCompatActivity implements View.OnTouchListener {
    private Graph graph;
    private MakeDrawView drawView;
    private Vertex prev;
    private ConstraintLayout layout;
    private int screenX, screenY;
    private ArrayList<UndoCommand> undos;
    private GraphList graphs;
    private boolean deleteMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make);
        graph = new Graph();
        layout = (ConstraintLayout) findViewById(R.id.activity_make_const2);
        drawView = new MakeDrawView(this, graph);
        drawView.setBackgroundColor(Color.BLACK);
        drawView.setOnTouchListener(this);
        layout.addView(drawView);
        screenX = Resources.getSystem().getDisplayMetrics().widthPixels;
        screenY = Resources.getSystem().getDisplayMetrics().heightPixels;
        undos = new ArrayList<UndoCommand>();
        graphs = new GraphList();
    }

    public void testPath(View w){
        test("Euler");
    }

    public void testCircuit(View w){
        test("Hamilton");
    }

    public void test(String type){
        //if(isProperGraph()) {
        //I commented out your tester for now -Ali
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            graph.center();
            bundle.putParcelable("graph", graph);
            intent.putExtras(bundle);
            if (type.equals("Hamilton")) {
                intent.setClass(this, HamiltonTestActivity.class);
            } else { intent.setClass(this, EulerTestActivity.class); }
            //intent.setAction(type);
            startActivity(intent);
        //}
        /*else{
            Toast t1 = Toast.makeText(getApplicationContext(), "2 connections minimum", Toast.LENGTH_LONG);
            t1.show();
        }*/
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

    public boolean nearAnyVertex(float x, float y, int factor){
        for(Vertex v: graph.getVertices()) {
            if(nearVertex(x, y, v, factor)){
                return true;
            }
        }
        return false;
    }

    public void deleteNearVertex(float x, float y, int factor){
        for(Vertex v: graph.getVertices()) {
            if(nearVertex(x, y, v, factor)){
                graph.removeVertex(v);
                return;
            }
        }
    }

    public boolean nearVertex(float x, float y, Vertex v, int factor){
        if(distance(x, y, v) <= screenX/factor){
            return true;
        }
        return false;
    }

    public boolean isProperGraph(){
        if(graph.getVertices().isEmpty()){
            return false;
        }
        for (Vertex v: graph.getVertices()){
            if(v.getConnections().size() < 2){
                return false;
            }
        }
        return true;
    }

    public boolean withinBounds(float x, float y) {
        if (x >= (screenX/12) && x <= (screenX - (screenX/12)*2) ) {
            if (y >= ((screenX/12)*4) && y <= (screenY - ((screenX/12)*4))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (deleteMode) {
                    deleteNearVertex(e.getX(), e.getY(), screenX/180);
                    break;
                } else {
                    if(!nearAnyVertex(e.getX(), e.getY(), screenX/180) && withinBounds(e.getX(), e.getY())){
                        Vertex vrtx = new Vertex((int) e.getX(), (int)e.getY(), screenX/24);
                        graph.addVertex(vrtx);
                        undos.add(new UndoVertex(vrtx, graph));
                        prev = vrtx;
                    }
                    for(Vertex vrtx: graph.getVertices()){
                        if(nearVertex(e.getX(), e.getY(), vrtx, screenX/180)){
                            prev = vrtx;
                        }
                    }
                    break;

                }

            case MotionEvent.ACTION_MOVE:
                for(Vertex vrtx: graph.getVertices()) {
                    if (nearVertex(e.getX(), e.getY(), vrtx, screenX/90) && prev != null && vrtx != prev) {
                        if(!vrtx.isConnected(prev)) {
                            vrtx.connect(prev);
                            undos.add(new UndoConnection(vrtx, prev));
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

    public void save(View w){
        FileOutputStream fos = null;
        try {
            FileInputStream fis = this.openFileInput("graphsaves");
            ObjectInputStream ois = new ObjectInputStream(fis);
            graphs = (GraphList) ois.readObject();
            ois.close();
            fis.close();
            fos = this.openFileOutput("graphsaves", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            graph.center();
            Graph gcopy = new Graph(graph);
            graphs.addGraph(gcopy);
            System.out.println("added graph to list-----------------\n" + graph);
            System.out.println("added graph to list-----------------");
            oos.writeObject(graphs);
            oos.close();
            fos.close();
            System.out.println("saved");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("excepted");
        }
    }

    public void load(View w){
        try {
            FileInputStream fis = this.openFileInput("graphsaves");
            ObjectInputStream ois = new ObjectInputStream(fis);
            graphs = (GraphList) ois.readObject();
            graph = graphs.getGraph(graphs.getSize() - 2);
            drawView.setGraph(graph);
            drawView.invalidate();
            ois.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void better_load(View w) {
        try {
            FileInputStream fis = this.openFileInput("graphsaves");
            ObjectInputStream ois = new ObjectInputStream(fis);
            graphs = (GraphList) ois.readObject();
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putParcelable("graphs", graphs);
            intent.putExtras(bundle);
            intent.setClass(this, GraphLoadActivity.class);
            startActivity(intent);
            //finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public void clear(View w) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Deleting Graph");
        builder.setMessage("Are you sure you want to clear the canvas?\n" +
            "All unsaved work will be lost.");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                graph = new Graph();
                drawView.setGraph(graph);
                drawView.invalidate();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void undo(View v) {
        if (!undos.isEmpty()) {
            undos.get(undos.size()-1).execute();
            undos.remove(undos.size()-1);
            drawView.invalidate();
        }
    }

    public void toggleDeleteMode(View w) {
        deleteMode = !deleteMode;
    }
}
