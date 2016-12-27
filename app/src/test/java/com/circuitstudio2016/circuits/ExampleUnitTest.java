package com.circuitstudio2016.circuits;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void HamiltonCircuitIsCorrect() throws Exception {
        Graph graph = new Graph();
        Vertex v1 = new Vertex(220, 40);
        Vertex v2 = new Vertex(350, 400);
        Vertex v3 = new Vertex(50, 400);
        Vertex v4 = new Vertex(100, 100);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        v1.connect(v2);
        v2.connect(v3);
        v3.connect(v1);
        v1.connect(v4);
        v2.connect(v4);

        HamiltonCircuit circuit = new HamiltonCircuit(graph);
        ArrayList<Vertex> list = new ArrayList<Vertex>();

            circuit.tryActivate(v1);
            list.add(v1);
        assertEquals("First vertex should always activate", list, circuit.getActivated());

            circuit.tryActivate(v3);
            list.add(v3);
        assertEquals("a connected vertex should activate", list, circuit.getActivated());

            circuit.tryActivate(v4);
        assertEquals("an unconnected vertex shouldn't activate", list, circuit.getActivated());

            circuit.tryActivate(v1);
            list.remove(1);
        assertEquals("activating previous vertex should de-activate current one", list, circuit.getActivated());

            circuit.tryActivate(v3);
            circuit.tryActivate(v2);
        assertEquals("non-completed circuit, should not be finished", false, circuit.isFinished());

            circuit.tryActivate(v4);
        assertEquals("ending at different vertex than start, should not be finished", false, circuit.isFinished());


        graph = new Graph();
        v1 = new Vertex(0,0);
        v2 = new Vertex(0,0);
        v3 = new Vertex(0,0);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        v1.connect(v2);
        v2.connect(v3);
        v3.connect(v1);

        circuit = new HamiltonCircuit(graph);
            circuit.tryActivate(v1);
            circuit.tryActivate(v2);
            circuit.tryActivate(v3);
            circuit.tryActivate(v1);
        assertEquals("Should have completed circuit properly", true, circuit.isFinished());

    }

    @Test
    public void HamiltonPathIsCorrect() throws Exception {
        Graph graph = new Graph();
        Vertex v1 = new Vertex(220, 40);
        Vertex v2 = new Vertex(350, 400);
        Vertex v3 = new Vertex(50, 400);
        Vertex v4 = new Vertex(100, 100);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        v1.connect(v2);
        v2.connect(v3);
        v3.connect(v1);
        v1.connect(v4);
        v2.connect(v4);

        HamiltonPath path = new HamiltonPath(graph);
        ArrayList<Vertex> list = new ArrayList<Vertex>();

        path.tryActivate(v1);
        list.add(v1);
        assertEquals("First vertex should always activate", list, path.getActivated());

        path.tryActivate(v3);
        list.add(v3);
        assertEquals("a connected vertex should activate", list, path.getActivated());

        path.tryActivate(v4);
        assertEquals("an unconnected vertex shouldn't activate", list, path.getActivated());

        path.tryActivate(v1);
        list.remove(1);
        assertEquals("activating previous vertex should de-activate current one", list, path.getActivated());

        path.tryActivate(v3);
        path.tryActivate(v2);
        assertEquals("non-completed path, should not be finished", false, path.isFinished());

        path.tryActivate(v4);
        assertEquals("ending at different vertex than start, should be finished", true, path.isFinished());
    }
}