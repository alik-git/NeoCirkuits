package com.circuitstudio2016.circuits;

import java.util.ArrayList;

/**
 * Created by Jacob on 2016-12-27.
 */

public class HamiltonPath {
    private Graph graph;
    private ArrayList<Vertex> activated; // list of vertices that have been drawn over. (in order)

    public HamiltonPath(Graph graph){
        this.graph = graph;
        activated = new ArrayList<Vertex>();
    }

    public Graph getGraph(){
        return graph;
    }
    public ArrayList<Vertex> getActivated(){
        return activated;
    }
    public void reset(){
        activated = new ArrayList<Vertex>();
    }


    private void activate(Vertex v){
        v.setActivated(true);
        activated.add(v);
    }

    private void removeLast(){
        activated.get(activated.size()-1).setActivated(false);
        activated.remove(activated.size()-1);
    }

    public void tryActivate(Vertex v){
        if(activated.isEmpty()){
            activate(v);
        }
        else if(!v.isActivated() && v.isConnected(activated.get(activated.size()-1))){
            activate(v);
        }
        else if(v == activated.get(activated.size()-2)){
            removeLast();
        }
    }

    public boolean isFinished(){
        return activated.size() == graph.getVertices().size();
    }

}
