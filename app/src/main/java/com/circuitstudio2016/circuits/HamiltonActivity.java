package com.circuitstudio2016.circuits;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class HamiltonActivity extends AppCompatActivity implements View.OnTouchListener {
    private HamiltonPath path;



    private DrawView drawView;
    private RelativeLayout layout;
    int screenX, screenY;
    String message;
    private Graph graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hcircuits);

        graph = this.getIntent().getExtras().getParcelable("graph");
        //init(new HamiltonCircuit(graph), (RelativeLayout) findViewById(R.id.activity_hcircuits));
    }

    public DrawView getDrawView() {
        return drawView;
    }

    public void deleteGraph(View w) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Deleting Graph");
        builder.setMessage("Are you sure you want to delete this Graph?\n" +
                "It cannot be recovered.");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                deleteGraphIn();
                finish();
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

    public void deleteGraphIn(){
        FileOutputStream fos = null;
        try {
            FileInputStream fis = this.openFileInput("graphsaves");
            ObjectInputStream ois = new ObjectInputStream(fis);
            GraphList graphs = (GraphList) ois.readObject();
            ois.close();
            fis.close();

            graphs.removeGraph(graph);

            fos = this.openFileOutput("graphsaves", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            Graph gcopy = new Graph(graph);
//            graphs.addHamiltonGraph(gcopy);
//            System.out.println("added graph to list" + graphs);
            oos.writeObject(graphs);
            oos.close();
            fos.close();
            System.out.println("Graph " + graph + "\n deleted");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("excepted");
        }
    }

    public void init(HamiltonPath path, RelativeLayout layout){
        this.path = path;
        drawView = new DrawView(this, path);
        drawView = new DrawView(this, path);
        drawView.setBackgroundColor(Color.BLACK);
        drawView.setOnTouchListener(this);
        layout.addView(drawView);

        screenX = Resources.getSystem().getDisplayMetrics().widthPixels;
        screenY = Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public HamiltonPath getPath(){ return path; }
    public RelativeLayout getLayout(){ return layout; }

    public void checkWon(){
        if(path.isFinished()){
            Button endButton = new Button(this);
            endButton.setText("Go Back");
            endButton.setX(screenX/3);
            endButton.setY(screenY*2/5);
            endButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(screenX/3, screenY/12);
            layout.addView(endButton, lp);
        }
    }

    public void goBack(View v) {
        finish();
    }

    public boolean nearVertex(float x, float y, Vertex v){
        double distX = Math.abs(x - v.getX());
        double distY = Math.abs(y - v.getY());
        return Math.sqrt(distX*distX + distY*distY) <= v.getRadius()*2;
    }

    public void activateVertex(float x, float y){
        for(Vertex vrtx: path.getGraph().getVertices()){
            if(nearVertex(x,y, vrtx)){
                path.tryActivate(vrtx);
                //System.out.println("yopoooooooooooooooooooooooooooo");
                checkWon();
            }
        }
    }

    public Graph getGraph() {
        return graph;
    }

    @Override
    public boolean onTouch(View v, MotionEvent e) {
        if(!path.isDone()) {
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
                    path.reset();
                    break;
            }
            drawView.invalidate();
        }
        return true;
    }

}
