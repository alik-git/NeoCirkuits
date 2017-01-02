package com.circuitstudio2016.circuits;


public class UndoVertex implements UndoCommand {
    private Vertex vertex;
    private Graph graph;

    public UndoVertex(Vertex vertex, Graph graph){
        this.vertex = vertex;
        this.graph = graph;
    }

    @Override
    public void execute() {
        graph.removeVertex(vertex);
    }
}
