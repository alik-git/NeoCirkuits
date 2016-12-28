package com.circuitstudio2016.circuits;

import java.util.ArrayList;

/**
 * Created by Jacob on 2016-12-27.
 */

public class HamiltonCircuit extends HamiltonPath{

    public HamiltonCircuit(Graph graph){
        super(graph);
    }

    // copied function to keep it private
    private void activate(Vertex v){
        v.setActivated(true);
        getActivated().add(v);
    }

    public void tryActivate(Vertex v){
        System.out.println("PRESSED! AND ACTIVATED!");
        super.tryActivate(v);
        if(super.isFinished() && v == getActivated().get(0)){
            activate(v);
        }
    }

    public boolean isFinished(){
        if(!getActivated().isEmpty()){
            return getActivated().size() == getGraph().getVertices().size() + 1
                    && getActivated().get(0) == getActivated().get(getActivated().size()-1);
        }
        return false;
    }

}
