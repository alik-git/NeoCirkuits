package com.circuitstudio2016.circuits;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MakeDrawView extends View {
    private Paint paint = new Paint();
    private Graph graph;
    private boolean boundary;

    public MakeDrawView(Context context, Graph graph) {
        super(context);
        paint.setColor(Color.BLACK);
        this.graph = graph;
        boundary = true;
    }

    public void toggleBoundary(){
        if(boundary){
            boundary = false;
        }
        else{
            boundary = true;
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        // draw all connection
        for(Vertex v: graph.getVertices()) {
            for (Vertex vc : v.getConnections()) {
                paint.setStrokeWidth(2);
                paint.setColor(Color.BLACK);
                canvas.drawLine(v.getX(), v.getY(), vc.getX(), vc.getY(), paint);
            }
        }
        // draw all vertices
        for(Vertex v: graph.getVertices()) {
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.GREEN);
            canvas.drawCircle(v.getX(), v.getY(), v.getRadius(), paint);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(4);
            canvas.drawCircle(v.getX(), v.getY(), v.getRadius(), paint);
            if(boundary) {
                paint.setColor(Color.RED);
                paint.setStrokeWidth(1);
                canvas.drawCircle(v.getX(), v.getY(), 100, paint);
            }
        }
    }
}
