package com.circuitstudio2016.circuits;

import java.util.ArrayList;

public class Graph {
    private ArrayList<Vertex> vertices;

    public Graph(){
        vertices = new ArrayList<Vertex>();
    }

    public ArrayList<Vertex> getVertices(){
        return vertices;
    }

    public void addVertex(Vertex v){
        vertices.add(v);
    }

}
