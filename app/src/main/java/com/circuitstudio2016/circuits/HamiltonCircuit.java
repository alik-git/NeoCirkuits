package com.circuitstudio2016.circuits;

public class HamiltonCircuit extends HamiltonPath{

    public HamiltonCircuit(Graph graph){
        super(graph);
    }

    // copied function to keep it private
    private void activate(Vertex v){
        v.setActivated(true);
        getActivated().add(v);
    }

    @Override
    public boolean isFinished(){
        return (super.getActivated().size() == super.getGraph().getEdges().size() &&
                super.getActivated().get(0) ==
                        super.getActivated().get(super.getActivated().size() -1));

    }

}
