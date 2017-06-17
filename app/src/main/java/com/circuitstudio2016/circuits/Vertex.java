package com.circuitstudio2016.circuits;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

import android.graphics.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Vertex implements Parcelable, Serializable{
    private int x;
    private int y;
    int newx =0;
    int newy =0;
    int stepNum = 0;



    private int xx;
    private int yy;
    private int radius = (Resources.getSystem().getDisplayMetrics().widthPixels)/24;
    private int color;
    private boolean isActivated;
    private ArrayList<Vertex> connections;



    public Vertex(int x, int y, int radius){
        //this snaps the vertices to the grid-lines
        if (x%90 == 0) {
            this.x = x;
            this.xx = this.x;
        } else {
            this.x = (90*(Math.round(x/90))+90);
            this.xx = this.x;
        }
        if ((y - 45)%90 == 0) {
            this.y = y;
            this.yy = this.y;
        } else {
            this.y = (90*(Math.round(y/90))+45);
            this.yy = this.y;
        }
        this.radius = radius;
        this.color = Color.parseColor("#40ff70");
        connections = new ArrayList<Vertex>();
    }

    public Vertex(int x, int y){
        //this snaps the vertices to the grid-lines
        if (x%90 == 0) {
            this.x = x;
            this.xx = this.x;
        } else {
            this.x = (90*(Math.round(x/90))+90);
            this.xx = this.x;
        }
        if ((y - 45)%90 == 0) {
            this.y = y;
            this.yy = this.y;
        } else {
            this.y = (90*(Math.round(y/90))+45);
            this.yy = this.y;
        }
        this.color = Color.parseColor("#40ff70");
        connections = new ArrayList<Vertex>();
    }

    public Vertex(Vertex v) {
        this.x = v.getX();
        this.xx = this.x;
        this.y = v.getY();
        this.yy = this.y;
        this.radius = v.getRadius();
        this.color = v.getColor();
        connections = new ArrayList<Vertex>();
    }

    protected Vertex(Parcel in) {
        x = in.readInt();
        xx = in.readInt();
        y = in.readInt();
        yy = in.readInt();
        radius = in.readInt();
        color = in.readInt();
        isActivated = in.readByte() != 0;
        connections = new ArrayList<Vertex>();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(x);
        dest.writeInt(xx);
        dest.writeInt(y);
        dest.writeInt(yy);
        dest.writeInt(radius);
        dest.writeInt(color);
        dest.writeByte((byte) (isActivated ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Vertex> CREATOR = new Creator<Vertex>() {
        @Override
        public Vertex createFromParcel(Parcel in) {
            return new Vertex(in);
        }

        @Override
        public Vertex[] newArray(int size) {
            return new Vertex[size];
        }
    };

    public int getXx() {
        return xx;
    }

    public void setXx(int xx) {
        this.xx = xx;
    }

    public int getYy() {
        return yy;
    }

    public void setYy(int yy) {
        this.yy = yy;
    }
    public int getX(){ return x; }

    public int getY(){ return y; }

    public int getRadius(){ return radius; }

    public int getColor() { return color; }

    public boolean isActivated(){
        return isActivated;
    }

    public void setColor(int c) { this.color = c; }

    public void setActivated(Boolean b){
        this.isActivated = b;
        if (b) {this.color = Color.GREEN;}
        else   {this.color = Color.RED;}
    }

    private void addConnection(Vertex other){
        connections.add(other);
    }

    private void removeConnection(Vertex other) { connections.remove(other); }

    public void connect(Vertex other){
        addConnection(other);
        other.addConnection(this);
    }

    public void disconnect(Vertex other){
        if(isConnected(other)){
            removeConnection(other);
            other.removeConnection(this);
        }
    }

    public void step() {
//        Random rn = new Random();
//        int step = rn.nextInt(10);
//        this.newx = rn.nextInt(3) - 1;
//        this.newy = rn.nextInt(3) - 1;
//        System.out.println(newx);
//        System.out.println(newy);
//        if (step == 0) {
//            this.xx += newx;
//        } else if (step == 1) { this.yy += newy; }
//
//        double distancefromBoundary = Math.sqrt((x-xx)*(x-xx) + (y-yy)*(y-yy));
//        while (distancefromBoundary > this.radius*3) {
//            //System.out.println("woooo step!");
//            if (step == 0) {
//                this.xx += 2*((-1)*newx);
//            } else if (step == 1) { this.yy += 2*((-1)*newy); }
//            distancefromBoundary = Math.sqrt((x-xx)*(x-xx) + (y-yy)*(y-yy));
//        }

        Random rn = new Random();
        stepNum = rn.nextInt(4);
        if (this.newx ==0 && this.newy== 0) {
            this.newx = rn.nextInt(3) - 1;
            this.newy = rn.nextInt(3) - 1;
        }
        if (stepNum == 0) {
            this.xx += this.newx;
            this.yy += this.newy;
        }
        double distancefromBoundary = Math.sqrt((x-xx)*(x-xx) + (y-yy)*(y-yy));

        if (distancefromBoundary >= this.radius) {
            this.xx += 2*((-1)*newx);
            this.yy += 2*((-1)*newy);

            this.newx = rn.nextInt(3) - 1;
            this.newy = rn.nextInt(3) - 1;

        }


    }

    public ArrayList<Vertex> getConnections(){
        return connections;
    }

    public boolean isConnected(Vertex other){
        for(Vertex v: connections){
            if(v == other){
                return true;
            }
        }
        return false;
    }

    public String toString(){
        String s = "";
        s+= "(" + x + ", ";
        s+= y + ")";
        return s;
    }
}
