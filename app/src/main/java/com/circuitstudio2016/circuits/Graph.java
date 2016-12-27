package com.circuitstudio2016.circuits;

import java.util.ArrayList;

/**
 * Created by Jacob on 2016-12-27.
 */

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
