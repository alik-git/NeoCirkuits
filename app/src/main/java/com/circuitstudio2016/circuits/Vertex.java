package com.circuitstudio2016.circuits;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Vertex implements Parcelable{
    private int x;
    private int y;
    private int radius;
    private boolean isActivated;
    private ArrayList<Vertex> connections;

    public Vertex(int x, int y, int radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
        connections = new ArrayList<Vertex>();
    }

    protected Vertex(Parcel in) {
        x = in.readInt();
        y = in.readInt();
        radius = in.readInt();
        isActivated = in.readByte() != 0;
        connections = new ArrayList<Vertex>();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(x);
        dest.writeInt(y);
        dest.writeInt(radius);
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

    public boolean isActivated(){
        return isActivated;
    }

    public void setActivated(Boolean b){
        this.isActivated = b;
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

}
