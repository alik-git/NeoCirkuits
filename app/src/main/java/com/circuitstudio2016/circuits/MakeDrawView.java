package com.circuitstudio2016.circuits;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.Arrays;

public class MakeDrawView extends View {
    private Paint paint = new Paint();
    private Graph graph;
    private boolean boundary;
    int screenX, screenY;

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

    public void setGraph(Graph graph){ this.graph = graph; }

    @Override
    public void onDraw(Canvas canvas) {
        //draw gridlines
        screenX = Resources.getSystem().getDisplayMetrics().widthPixels;
        screenY = Resources.getSystem().getDisplayMetrics().heightPixels;

        Integer[] strokeX =  { 1, 3, 5, 6, 8, 10};
        Integer[] strokeY = {1,3,5,7,8,10,12,14};

        //vertical lines
        for (int x = 0; x < screenX; x += screenX/12) {
            paint.setStrokeWidth(1);
            paint.setColor(Color.argb(200,0,191,250));
            int x2 = x/90;
            //make some lines bold for help
            if (Arrays.asList(strokeX).contains(x2)) { paint.setStrokeWidth(3);}
            canvas.drawLine(x+45, 90, x+45, 1350, paint);
            //System.out.println((x+45) + "   " + x/90);
        }

        //horizontal lines
        for (int y = 0; y <= screenY -360; y += screenX/12) {
            paint.setStrokeWidth(1);
            paint.setColor(Color.argb(200,0,191,250));
            int y2 = y/90;
            //make some lines bold for help
            if (Arrays.asList(strokeY).contains(y2)) { paint.setStrokeWidth(3);}
            canvas.drawLine(45, y, 1035, y, paint);
        }


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
            paint.setColor(v.getColor());
            canvas.drawCircle(v.getX(), v.getY(), v.getRadius(), paint);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(4);
            canvas.drawCircle(v.getX(), v.getY(), v.getRadius(), paint);
            if(boundary) {
                paint.setColor(Color.RED);
                paint.setStrokeWidth(1);
                canvas.drawCircle(v.getX(), v.getY(), v.getRadius()*2, paint);
            }
        }
    }
}
