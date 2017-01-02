package com.circuitstudio2016.circuits;

public class UndoConnection implements UndoCommand {
    private Vertex v1;
    private Vertex v2;

    public UndoConnection(Vertex v1, Vertex v2){
        this.v1 = v1;
        this.v2 = v2;
    }

    @Override
    public void execute() {
        v1.disconnect(v2);
    }
}
