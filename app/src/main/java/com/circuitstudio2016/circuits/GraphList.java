package com.circuitstudio2016.circuits;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by aliqk on 6/2/2017.
 */

public class GraphList implements Parcelable, Serializable{

    private ArrayList<Graph> graphs;

    public GraphList() { graphs = new ArrayList<Graph>(); }

    protected GraphList(Parcel in) {
        graphs = in.readArrayList(Graph.class.getClassLoader());

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(graphs);
    }

    @Override
    public int describeContents() {return graphs.size(); }

    public static final Creator<GraphList> CREATOR = new Creator<GraphList>() {

        @Override
        public GraphList createFromParcel(Parcel in) {
            return new GraphList(in);
        }

        @Override
        public GraphList[] newArray(int size) {
            return new GraphList[size];
        }

    };

    public ArrayList<Graph> getGraphs() { return graphs; }

    public void setGraphs(ArrayList<Graph> graphs) {
        this.graphs = graphs;
    }

    public Graph[] getGraphsArray() { return graphs.toArray(new Graph[graphs.size()]); }

    public Graph getGraph(int num) { return graphs.get(num); }

    public void addGraph(Graph g) {
        Graph gcopy = new Graph(g);
        graphs.add(gcopy);
    }

    public void removeGraph(Graph graph) {
        for (int i = 0; i < graphs.size(); i++ ) {
            if (graph.toString().equals(graphs.get(i).toString())) {
                removeGraphNum(i);
                System.out.println("adtually deleted 7256056171065617603724014063207342607632" +
                        "570641076034103265107651405236017439024!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
        }
    }

    public Graph removeGraphNum(int num) { return graphs.remove(num); }


    public int getSize() { return graphs.size(); }

    public String toString() {
        String s = "------------\n";
        s += "GraphList(" + graphs.size() + "):\n";
        int i = 0;
        for (Graph g: graphs) {
            s += "#" + i + " " + g.toString() + "\n";
            i++;
        } s += "------------\n";
        return s;
    }

}
