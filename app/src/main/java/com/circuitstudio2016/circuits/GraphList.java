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

    public Graph getGraph(int num) { return graphs.get(num); }

    public void addGraph(Graph g) {
        Graph gcopy = new Graph(g);
        graphs.add(gcopy);
    }

    public Graph removeGraph(int num) { return graphs.remove(num); }

    public int getSize() { return graphs.size(); }

    public String toString() {
        String s = "GraphList: ";
        for (Graph g: graphs) {
            s += g.toString();
        }
        return s;
    }

}
