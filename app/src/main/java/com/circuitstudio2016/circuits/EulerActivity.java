package com.circuitstudio2016.circuits;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class EulerActivity extends HamiltonActivity {

//    private EulerPath path;
//    private DrawView drawView;
//    private RelativeLayout layout;
//    int screenX, screenY;
//    String message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void activateVertex(float x, float y){
        for(Vertex vrtx: super.getPath().getGraph().getVertices()){
            if(nearVertex(x,y, vrtx)){
                super.getPath().tryEulerActivate(vrtx);
                checkWon();
            }
        }
    }

    public void checkWon(){
        if(super.getPath().isEulerFinished()){
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
            super.getLayout().addView(endButton, lp);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent e) {
        if(!super.getPath().isEulerFinished()) {
            switch (e.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    super.getDrawView().setMouseLocation(e.getX(), e.getY());
                    activateVertex(e.getX(), e.getY());
                    break;
                case MotionEvent.ACTION_MOVE:
                    super.getDrawView().setMouseLocation(e.getX(), e.getY());
                    activateVertex(e.getX(), e.getY());
                    break;
                case MotionEvent.ACTION_UP:
                    super.getPath().reset();
                    break;
            }
            super.getDrawView().invalidate();
        }
        return true;
    }
}
