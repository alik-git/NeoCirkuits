package com.circuitstudio2016.circuits;

import java.util.ArrayList;

public class Vertex {
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
