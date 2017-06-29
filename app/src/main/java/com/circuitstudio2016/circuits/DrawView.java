package com.circuitstudio2016.circuits;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.View;

public class DrawView extends View {
    Paint paint = new Paint();
    HamiltonPath path;
    float mouseX;
    float mouseY;
    int blue = getResources().getColor(R.color.neon_blue);
    int green = getResources().getColor(R.color.neon_green);
    int tx, ty;
    int screenX, screenY;
    private boolean done = false;
    private boolean isEuler = false;

    public DrawView(Context context, HamiltonPath path) {
        super(context);
        paint.setColor(Color.BLACK);
        this.path = path;
    }

    public DrawView(Context context) {
        super(context);
        paint.setColor(Color.BLACK);
    }

    public void setEuler() {
        this.isEuler = true;
    }



    public void setMouseLocation(float x, float y){
        mouseX = x;
        mouseY = y;
    }

    public void beDone() {
        this.done = true;//
    }

    @Override
    public void onDraw(Canvas canvas) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenY = displayMetrics.heightPixels;
        screenX = displayMetrics.widthPixels;

//        screenX = Resources.getSystem().getDisplayMetrics().widthPixels;
//        screenY = Resources.getSystem().getDisplayMetrics().heightPixels;
        System.out.println("woooo scx!" + screenX);
        System.out.println("woooo scy!" + screenY);

        // step vertices

        for ( Vertex v: path.getGraph().getVertices()) {
            v.step();
        }

        // draw to current "mouse" location
        if(path.getActivated().size() >= 1 && (!done)){
            paint.setStrokeWidth(10);
            paint.setColor(blue);
            Vertex v = path.getActivated().get(path.getActivated().size()-1);
            tx = Math.round(screenX*v.getxProp());
            ty = Math.round(screenY*v.getyProp());
            canvas.drawLine(v.getXx(), v.getYy(), mouseX, mouseY, paint);
        }
        // draw all connection

        for(Vertex v: path.getGraph().getVertices()) {
            for (Vertex vc : v.getConnections()) {

                paint.setStrokeWidth(10);
                paint.setColor(green);
                tx = Math.round(screenX*v.getxProp());
                ty = Math.round(screenY*v.getyProp());
                canvas.drawLine(v.getXx(), v.getYy(), vc.getXx(), vc.getYy(), paint);
            }
        }
        // draw all activated links
        for(int i = 0; i < path.getActivated().size()-1; i++){
            Vertex v1 = path.getActivated().get(i);
            Vertex v2 = path.getActivated().get(i+1);
            paint.setStrokeWidth(10);
            paint.setColor(blue);
            tx = Math.round(screenX*v1.getxProp());
            ty = Math.round(screenY*v2.getyProp());
            canvas.drawLine(v1.getXx(), v1.getYy(), v2.getXx(), v2.getYy(), paint);
        }
        // draw all vertices
        if(!path.getActivated().isEmpty()) {
            path.getActivated().get(0).setColor(blue);
        }
        for(Vertex v: path.getGraph().getVertices()) {
            paint.setStyle(Paint.Style.FILL);
            if (v.isActivated()) {
                paint.setColor(blue);
            } else { paint.setColor(green); }
            canvas.drawCircle(v.getXx(), v.getYy(), v.getRadius(), paint);
            tx = Math.round(screenX*v.getxProp());
            ty = Math.round(screenY*v.getyProp());
            System.out.println("woooo Txprop!" + v.getxProp());
            System.out.println("woooo TYprop!" + v.getyProp());
            System.out.println("woooo Tx!" + tx);
            System.out.println("woooo TY!" + ty);
            //canvas.drawCircle(tx,ty, v.getRadius(), paint);
        }
        if (!isEuler) {
            if (!path.getActivated().isEmpty()) {
                Vertex first = path.getActivated().get(0);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(Math.round(first.getRadius() / 2.25));
                paint.setColor(Color.BLACK);
                tx = Math.round(screenX * first.getxProp());
                ty = Math.round(screenY * first.getyProp());
                //canvas.drawCircle(tx,ty, first.getRadius()/2, paint);
                canvas.drawCircle(first.getXx(), first.getYy(), first.getRadius() / 2, paint);
            }
        } else {
            if ( path.getActivated().size() > 1) {
                Vertex first = path.getActivated().get(path.getActivated().size() - 2);
                paint.setStyle(Paint.Style.FILL);
                //paint.setStrokeWidth(Math.round(first.getRadius() / 1.1));
                paint.setColor(Color.BLACK);
                tx = Math.round(screenX * first.getxProp());
                ty = Math.round(screenY * first.getyProp());
                //canvas.drawCircle(tx,ty, first.getRadius()/2, paint);
                canvas.drawCircle(first.getXx(), first.getYy(), Math.round(first.getRadius() / 2.5) , paint);
            }
        }
        invalidate();
    }
}
