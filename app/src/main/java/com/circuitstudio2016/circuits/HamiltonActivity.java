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

public class HamiltonActivity extends AppCompatActivity implements View.OnTouchListener {
    private HamiltonPath path;



    private DrawView drawView;
    private RelativeLayout layout;
    int screenX, screenY;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hcircuits);

        Graph graph = this.getIntent().getExtras().getParcelable("graph");
        if(this.getIntent().getAction().equals("circuit")) {
            init(new HamiltonCircuit(graph));
        }
        else{
            init( new HamiltonPath(graph));
        }
    }

    public DrawView getDrawView() {
        return drawView;
    }

    public void init(HamiltonPath path){
        this.path = path;
        drawView = new DrawView(this, path);

        layout = (RelativeLayout) findViewById(R.id.activity_hcircuits);

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

    public boolean nearVertex(float x, float y, Vertex v){
        double distX = Math.abs(x - v.getX());
        double distY = Math.abs(y - v.getY());
        return Math.sqrt(distX*distX + distY*distY) <= v.getRadius()*2;
    }

    public void activateVertex(float x, float y){
        for(Vertex vrtx: path.getGraph().getVertices()){
            if(nearVertex(x,y, vrtx)){
                path.tryActivate(vrtx);
                System.out.println("yopoooooooooooooooooooooooooooo");
                checkWon();
            }
        }
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
