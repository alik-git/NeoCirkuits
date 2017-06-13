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

    public void setMouseLocation(float x, float y){
        mouseX = x;
        mouseY = y;
    }

    @Override
    public void onDraw(Canvas canvas) {
        // draw to current "mouse" location
        if(path.getActivated().size() >= 1 && path.getActivated().size() <= path.getGraph().getVertices().size()){
            paint.setStrokeWidth(10);
            paint.setColor(blue);
            Vertex v = path.getActivated().get(path.getActivated().size()-1);
            canvas.drawLine(v.getX(), v.getY(), mouseX, mouseY, paint);
        }
        // draw all connection

        for(Vertex v: path.getGraph().getVertices()) {
            for (Vertex vc : v.getConnections()) {
                paint.setStrokeWidth(10);
                paint.setColor(green);
                canvas.drawLine(v.getX(), v.getY(), vc.getX(), vc.getY(), paint);
            }
        }
        // draw all activated links
        for(int i = 0; i < path.getActivated().size()-1; i++){
            Vertex v1 = path.getActivated().get(i);
            Vertex v2 = path.getActivated().get(i+1);
            paint.setStrokeWidth(10);
            paint.setColor(blue);
            canvas.drawLine(v1.getX(), v1.getY(), v2.getX(), v2.getY(), paint);
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
            canvas.drawCircle(v.getX(), v.getY(), v.getRadius(), paint);
        }
    }
}
