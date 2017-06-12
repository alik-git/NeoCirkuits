package com.circuitstudio2016.circuits;

/**
 * Created by aliqk on 6/12/2017.
 */
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GraphParser {

    private int lineNumber;
    private Graph graph;

    private Pattern pGraphHeader = Pattern.compile("Graph");
    private Pattern pVert = Pattern.compile("\\((\\d+),(\\d+)\\)");
    private Pattern pEdgeHeader = Pattern.compile("Edges");
    private Pattern pEdge = Pattern.compile("\\((\\d+)to(\\d+)\\)");
    private Pattern pvEnd = Pattern.compile("vdone");
    private Pattern peEnd = Pattern.compile("edone");

    public Graph parse(String s) {
        this.graph = new Graph();
        int state = 0;
        Matcher m;
        this.lineNumber = 0;

        InputStream is = new ByteArrayInputStream(s.getBytes());
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        try {
            while ((line = br.readLine()) != null) {
                this.lineNumber++;
                line = line.replaceAll("I:", "");
                line = line.replaceAll("\\s", "");
                System.out.println(lineNumber+" "+line+" ["+state);
                switch(state) {
                    case 0:
                        m = pGraphHeader.matcher(line);
                        if (m.find()) {
                            System.out.println("matched 0\n");
                            state = 1;
                            break;
                        }
                    case 1:
                        m = pVert.matcher(line);
                        if (m.find()) {
                            System.out.println("matched vert\n");
                            int x = Integer.parseInt(m.group(1));
                            int y = Integer.parseInt(m.group(2));
                            Vertex v = new Vertex(x,y);
                            System.out.println("vert is " + v);
                            this.graph.addVertex(v);
                            state = 1;
                            break;
                        }
                        m = pvEnd.matcher(line);
                        if (m.find()) {
                            System.out.println("matched vert end\n");
                            state = 2;
                            break;
                        }
                    case 2:
                        m = pEdgeHeader.matcher(line);
                        if (m.find()) {
                            System.out.println("matched e head\n");
                            state = 3;
                            break;
                        }
                    case 3:
                        m = pEdge.matcher(line);
                        if (m.find()) {
                            System.out.println("matched edge\n");
                            int a = Integer.parseInt(m.group(1));
                            int b = Integer.parseInt(m.group(2));
                            System.out.println("edge is " + a + " to " + b + "\n");
                            this.graph.getVertices().get(a).connect(this.graph.getVertices().get(b));
                            System.out.println("added edge\n");
                            state = 3;
                            break;
                        }
                        m = peEnd.matcher(line);
                        if (m.find()) {
                            System.out.println("matched edge end\n");
                            state = 3;
                            break;
                        }
                }
            }
        } catch (Exception e){}
        System.out.println(this.graph);
        return this.graph;
    }



}





































