package com.circuitstudio2016.circuits;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DrawView extends View {
    Paint paint = new Paint();
    HamiltonPath path;
    float mouseX;
    float mouseY;
    int blue = getResources().getColor(R.color.neon_blue);
    int green = getResources().getColor(R.color.neon_green);

    public DrawView(Context context, HamiltonPath path) {
        super(context);
        paint.setColor(Color.BLACK);
        this.path = path;
    }

    public DrawView(Context context) {
        super(context);
        paint.setColor(Color.BLACK);
    }



    public void setMouseLocation(float x, float y){
        mouseX = x;
        mouseY = y;
    }

    @Override
    public void onDraw(Canvas canvas) {
        // step vertices

        for ( Vertex v: path.getGraph().getVertices()) {
            v.step();
        }

        // draw to current "mouse" location
        if(path.getActivated().size() >= 1 && (!path.isDone() || !path.isEulerFinished())){
            paint.setStrokeWidth(10);
            paint.setColor(blue);
            Vertex v = path.getActivated().get(path.getActivated().size()-1);
            canvas.drawLine(v.getXx(), v.getYy(), mouseX, mouseY, paint);
        }
        // draw all connection

        for(Vertex v: path.getGraph().getVertices()) {
            for (Vertex vc : v.getConnections()) {

                paint.setStrokeWidth(10);
                paint.setColor(green);
                canvas.drawLine(v.getXx(), v.getYy(), vc.getXx(), vc.getYy(), paint);
            }
        }
        // draw all activated links
        for(int i = 0; i < path.getActivated().size()-1; i++){
            Vertex v1 = path.getActivated().get(i);
            Vertex v2 = path.getActivated().get(i+1);
            paint.setStrokeWidth(10);
            paint.setColor(blue);
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
        }

        if(!path.getActivated().isEmpty()) {
            Vertex first = path.getActivated().get(0);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(20);
            paint.setColor(Color.BLACK);
            canvas.drawCircle(first.getXx(), first.getYy(), first.getRadius()/2, paint);
        }
        invalidate();
    }
}
