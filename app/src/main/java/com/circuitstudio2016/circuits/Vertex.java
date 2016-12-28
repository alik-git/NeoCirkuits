package com.circuitstudio2016.circuits;

import java.util.ArrayList;

/**
 * Created by Jacob on 2016-12-27.
 */

public class Vertex {
    private int marginLeft;
    private int marginTop;
    private boolean isActivated;
    private ArrayList<Vertex> connections;

    public Vertex(int x, int y){
        marginLeft = x;
        marginTop = y;
        connections = new ArrayList<Vertex>();
    }

    public int getMarginLeft(){ return marginLeft; }

    public int getMarginTop(){ return marginTop; }

    public boolean isActivated(){
        return isActivated;
    }

    public void setActivated(Boolean b){
        this.isActivated = b;
    }

    private void addConnection(Vertex other){
        connections.add(other);
    }

    public void connect(Vertex other){
        addConnection(other);
        other.addConnection(this);
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
