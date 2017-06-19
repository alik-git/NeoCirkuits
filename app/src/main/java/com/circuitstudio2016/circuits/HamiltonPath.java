package com.circuitstudio2016.circuits;

import java.util.ArrayList;

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
        for(Vertex v: graph.getVertices()){
            v.setUActivated(false);
        }
    }


    private void activate(Vertex v){
        v.setActivated(true);
        activated.add(v);
        //useless
    }
    private void Eactivate(Vertex v, Vertex other){
        v.setActivated(true);
        activated.add(v);
        v.disEconnect(other);
        //useless
    }

    private void removeLast(){
        activated.get(activated.size()-1).setActivated(false);
        activated.remove(activated.size()-1);
    }

    private void removeELast(Vertex v){
        if (activated.get(activated.size() - 1) != activated.get(0)) {
            activated.get(activated.size() - 1).setEActivated(false);
        }
        v.Econnect(activated.get(activated.size()-1));
        activated.remove(activated.size()-1);
    }

    public void tryActivate(Vertex v){
        System.out.println("NUM EDGES/VERTS IS yopoooooooooooooooooooooooooooo");
        if(activated.isEmpty()){
            activate(v);
        }
        else if( v == activated.get(0) && v.isConnected(activated.get(activated.size()-1)) ||
                (!v.isActivated() && v.isConnected(activated.get(activated.size()-1)))){
            activate(v);
        }
        else if(activated.size() >= 2 && v == activated.get(activated.size()-2)){
            removeLast();
        }
    }

    public void tryEulerActivate(Vertex v){
        if(activated.isEmpty()){
            activate(v);
        }

        else if(activated.size() >= 2 && v == activated.get(activated.size()-2)){
            removeELast(v);
        }
        else if(v.isEConnected(activated.get(activated.size()-1))){
            Eactivate(v, activated.get(activated.size()-1));
        }
    }

    public boolean isFinished(){
        System.out.println("yopoooooooooooooooooooooooooooo222222222225555555555555555555555555555");
        System.out.println("yopoooooooooooooooooooooooooooo" +
                "000000000000000000000000000000000000000000000000000000000" + graph.getVertices().size());
        if (activated.size() == graph.getVertices().size()) {
            System.out.println("NUM EDGES/VERTS IS " + graph.getVertices().size());
            return true;
        }
        return false;
    }

    public boolean isDone(){
        System.out.println("yopoooooooooooooooooooooooooooo222222222225555555555555555555555555555");
        System.out.println("yopoooooooooooooooooooooooooooo" +
                "000000000000000000000000000000000000000000000000000000000" + graph.getVertices().size());
        if (activated.size() == graph.getVertices().size() + 1) {
            System.out.println("NUM EDGES/VERTS IS " + graph.getVertices().size());
            return true;
        }
        return false;
    }

    public boolean isEulerFinished(){

        if (activated.size() == graph.getEdges().size() + 1) {
            System.out.println("NUM EDGES IS " + graph.getEdges().size());
            return true;
        }
        return false;
    }

}
