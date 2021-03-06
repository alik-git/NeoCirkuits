package com.circuitstudio2016.circuits;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Graph implements Parcelable, Serializable{
    private ArrayList<Vertex> vertices;

    public Graph(){
        vertices = new ArrayList<Vertex>();
    }

    public Graph(Graph g) {//
        vertices = new ArrayList<Vertex>();
        for (Vertex v: g.vertices) {
            Vertex copy = new Vertex(v);
            vertices.add(copy);
        }

        ArrayList<ArrayList<Integer>> allConnections = new ArrayList<ArrayList<Integer>>();
        for(Vertex v: g.vertices){
            ArrayList<Integer> vertexConnections = new ArrayList<Integer>();
            for(int i = 0; i < g.vertices.size(); i++){
                if(v.isConnected(g.vertices.get(i))){
                    vertexConnections.add(i);
                }
            }
            allConnections.add(vertexConnections);
        }

        for(int v = 0; v < allConnections.size(); v++){
            for(int c: allConnections.get(v)){
                if (! (vertices.get(v).isConnected(vertices.get(c))) ) {
                    vertices.get(v).connect(vertices.get(c));
                }
            }
        }
    }

    protected Graph(Parcel in) {
        vertices = in.readArrayList(Vertex.class.getClassLoader());
        ArrayList<ArrayList<Integer>> allConnections = in.readArrayList(ArrayList.class.getClassLoader());
        for(int v = 0; v < allConnections.size(); v++){
            for(int c: allConnections.get(v)){
                if (! (vertices.get(v).isConnected(vertices.get(c))) ) {
                    vertices.get(v).connect(vertices.get(c));
                }
            }
        }
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(vertices);
        ArrayList<ArrayList<Integer>> allConnections = new ArrayList<ArrayList<Integer>>();
        for(Vertex v: vertices){
            ArrayList<Integer> vertexConnections = new ArrayList<Integer>();
            for(int i = 0; i < vertices.size(); i++){
                if(v.isConnected(vertices.get(i))){
                    vertexConnections.add(i);
                }
            }
            allConnections.add(vertexConnections);
        }
        dest.writeList(allConnections);
    }

    @Override
    public int describeContents() {
        return vertices.size();
    }

    public static final Creator<Graph> CREATOR = new Creator<Graph>() {
        @Override
        public Graph createFromParcel(Parcel in) {
            return new Graph(in);
        }

        @Override
        public Graph[] newArray(int size) {
            return new Graph[size];
        }
    };

    public ArrayList<Vertex> getVertices(){
        return vertices;
    }

    public void addVertex(Vertex v){
        vertices.add(v);
    }

    public void removeVertex(Vertex v1){
        for(Vertex v2: vertices){
            v1.disconnect(v2);
        }
        vertices.remove(v1);
    }

    public static Graph makeSimpleGraph(){
        int screenX = Resources.getSystem().getDisplayMetrics().widthPixels;
        int screenY = Resources.getSystem().getDisplayMetrics().heightPixels;
        Graph graph = new Graph();
        Vertex v1 = new Vertex(screenX/2, screenY/7, screenX/24);
        Vertex v2 = new Vertex(screenX/3, screenY*5/7, screenX/24);
        Vertex v3 = new Vertex(screenX*2/3, screenY*5/7, screenX/24);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        v1.connect(v2);
        v2.connect(v3);
        v3.connect(v1);
        return graph;
    }

    public ArrayList<UnorderedPair<Vertex>> getEdges() {

        ArrayList<UnorderedPair<Vertex>> connections = new ArrayList<UnorderedPair<Vertex>>();
        for (Vertex v: vertices) {
            for (Vertex c: v.getConnections()) {
                if (v.isConnected(c)) {
                    UnorderedPair<Vertex> pair = new UnorderedPair<Vertex>(v, c);
                    if(!pair.inside(connections)) {
                        connections.add(pair);
                    }
                }
            }
        }
        return connections;
    }

    public String toString(){
        String s = "Graph(";
        s += Integer.toString(vertices.size());
        s += "):\n";

        for(Vertex v: vertices){
            s += v.toString();
            s += "\n";
        } s += "vdone\n";

        s += "Edges(";

        ArrayList<UnorderedPair<Vertex>> connections = new ArrayList<UnorderedPair<Vertex>>();
        for (Vertex v: vertices) {
            for (Vertex c: v.getConnections()) {
                if (v.isConnected(c)) {
                    UnorderedPair<Vertex> pair = new UnorderedPair<Vertex>(v, c);
                    if(!pair.inside(connections)) {
                        connections.add(pair);
                    }
                }
            }
        }
        s += connections.size() + "):\n";

//        for (UnorderedPair<Vertex> pair: connections) {
//            s += "[" + pair.getFirst().toString() + "-";
//            s += pair.getSecond().toString() + "], ";
//        }

        for (int i =0; i < connections.size(); i++) {
            s += "(" + vertices.indexOf(connections.get(i).getFirst()) + "to";
            s += vertices.indexOf(connections.get(i).getSecond()) + ")\n";
        }
        s+= "edone\n";

        return s;
    }

    public void proportion(int screenX, int screenY) {

        for (Vertex v : this.vertices) {
            v.proportion(screenX, screenY);
        }


    }

    public void center() {
        int screenX = Resources.getSystem().getDisplayMetrics().widthPixels;
        int screenY = Resources.getSystem().getDisplayMetrics().heightPixels;

        int minX = 100000;
        int maxX = 0;

        for (Vertex v: vertices) {
            if (v.getX() < minX) {
                minX = v.getX();
            }
            if (v.getX() > maxX) {
                maxX = v.getX();
            }
        }

        int graphWidth = maxX - minX;
        int buffer = (screenX - graphWidth)/2;

        int toMove = minX - buffer;
        int newX;
        for (Vertex v: vertices) {
            newX = v.getX() - toMove;
            v.setX( newX);
            v.setXx(newX);
        }

        int minY = 100000;
        int maxY = 0;

        for (Vertex v: vertices) {
            if (v.getY() < minY) {
                minY = v.getY();
            }
            if (v.getY() > maxY) {
                maxY = v.getY();
            }
        }

        int graphHieght = maxY - minY;
        int bufferY = (screenY - graphHieght)/2;

        int toMoveY = minY - bufferY;
        int newY;
        for (Vertex v: vertices) {
            newY = v.getY() - toMoveY;
            v.setY( newY);
            v.setYy(newY);
        }

    }

}
