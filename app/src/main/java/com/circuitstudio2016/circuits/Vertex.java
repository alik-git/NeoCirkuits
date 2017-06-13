package com.circuitstudio2016.circuits;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

import android.graphics.Color;

import java.io.Serializable;
import java.util.ArrayList;

public class Vertex implements Parcelable, Serializable{
    private int x;
    private int y;
    private int radius = (Resources.getSystem().getDisplayMetrics().widthPixels)/24;
    private int color;
    private boolean isActivated;
    private ArrayList<Vertex> connections;



    public Vertex(int x, int y, int radius){
        //this snaps the vertices to the grid-lines
        if (x%90 == 0) {
            this.x = x;
        } else {
            this.x = (90*(Math.round(x/90))+90);
        }
        if ((y - 45)%90 == 0) {
            this.y = y;
        } else {
            this.y = (90*(Math.round(y/90))+45);
        }
        this.radius = radius;
        this.color = Color.parseColor("#40ff70");
        connections = new ArrayList<Vertex>();
    }

    public Vertex(int x, int y){
        //this snaps the vertices to the grid-lines
        if (x%90 == 0) {
            this.x = x;
        } else {
            this.x = (90*(Math.round(x/90))+90);
        }
        if ((y - 45)%90 == 0) {
            this.y = y;
        } else {
            this.y = (90*(Math.round(y/90))+45);
        }
        this.color = Color.parseColor("#40ff70");
        connections = new ArrayList<Vertex>();
    }

    public Vertex(Vertex v) {
        this.x = v.getX();
        this.y = v.getY();
        this.radius = v.getRadius();
        this.color = v.getColor();
        connections = new ArrayList<Vertex>();
    }

    protected Vertex(Parcel in) {
        x = in.readInt();
        y = in.readInt();
        radius = in.readInt();
        color = in.readInt();
        isActivated = in.readByte() != 0;
        connections = new ArrayList<Vertex>();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(x);
        dest.writeInt(y);
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
