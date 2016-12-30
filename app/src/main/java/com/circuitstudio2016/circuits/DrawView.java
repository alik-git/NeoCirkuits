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

    public DrawView(Context context, HamiltonPath path) {
        super(context);
        paint.setColor(Color.BLACK);
        this.path = path;
    }

    public void setMouseLocation(float x, float y){
        mouseX = x;
        mouseY = y;
    }

    @Override
    public void onDraw(Canvas canvas) {
        // draw to current "mouse" location
        if(path.getActivated().size() >= 1 && path.getActivated().size() <= path.getGraph().getVertices().size()){
            paint.setStrokeWidth(10);
            paint.setColor(Color.GREEN);
            Vertex v = path.getActivated().get(path.getActivated().size()-1);
            canvas.drawLine(v.getX(), v.getY(), mouseX, mouseY, paint);
        }
        // draw all connection
        for(Vertex v: path.getGraph().getVertices()) {
            for (Vertex vc : v.getConnections()) {
                paint.setStrokeWidth(2);
                paint.setColor(Color.BLACK);
                canvas.drawLine(v.getX(), v.getY(), vc.getX(), vc.getY(), paint);
            }
        }
        // draw all activated links
        for(int i = 0; i < path.getActivated().size()-1; i++){
            Vertex v1 = path.getActivated().get(i);
            Vertex v2 = path.getActivated().get(i+1);
            paint.setStrokeWidth(10);
            paint.setColor(Color.GREEN);
            canvas.drawLine(v1.getX(), v1.getY(), v2.getX(), v2.getY(), paint);
        }
        // draw all vertices
        for(Vertex v: path.getGraph().getVertices()) {
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(v.getColor());
            canvas.drawCircle(v.getX(), v.getY(), v.getRadius(), paint);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(4);
            canvas.drawCircle(v.getX(), v.getY(), v.getRadius(), paint);
        }
    }
}
