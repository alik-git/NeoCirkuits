package com.circuitstudio2016.circuits;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

import android.graphics.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Vertex implements Parcelable, Serializable{
    private int x;



    private int y;
    int newx =0;
    int newy =0;
    int stepNum = 0;
    int eNum = 0;



    private int xx;
    private float xProp;
    private int yy;
    private float yProp;
    private int radius = (Resources.getSystem().getDisplayMetrics().widthPixels)/24;
    int screenX = Resources.getSystem().getDisplayMetrics().widthPixels/12;
    private int color;
    private boolean isActivated;
    private ArrayList<Vertex> connections;
    private ArrayList<Vertex> econnections = new ArrayList<Vertex>();



    public Vertex(int x, int y, int radius){
        //this snaps the vertices to the grid-lines
        if (x%screenX == 0) {
            this.x = x;
            this.xx = this.x;
        } else {
            this.x = (screenX*(Math.round(x/screenX))+screenX);
            this.xx = this.x;
        }
        if ((y - screenX/2)%screenX == 0) {
            this.y = y;
            this.yy = this.y;
        } else {
            this.y = (screenX*(Math.round(y/screenX))+screenX/2);
            this.yy = this.y;
        }
        this.xProp = (float)this.x/1080;
        this.yProp = (float)this.y/1920;
        this.radius = radius;
        this.color = Color.parseColor("#40ff70");
        connections = new ArrayList<Vertex>();
    }

    public Vertex(int x, int y){
        //this snaps the vertices to the grid-lines
        if (x%screenX == 0) {
            this.x = x;
            this.xx = this.x;
        } else {
            this.x = (screenX*(Math.round(x/screenX))+screenX);
            this.xx = this.x;
        }
        if ((y - screenX/2)%screenX == 0) {
            this.y = y;
            this.yy = this.y;
        } else {
            this.y = (screenX*(Math.round(y/screenX))+screenX/2);
            this.yy = this.y;
        }
        this.xProp = (float)this.x/1080;
        this.yProp = (float)this.y/1920;
        this.color = Color.parseColor("#40ff70");
        connections = new ArrayList<Vertex>();
    }

    public Vertex(Vertex v) {
        this.x = v.getX();
        this.xx = this.x;
        this.y = v.getY();
        this.yy = this.y;
        this.xProp = (float)this.x/1080;
        this.yProp = (float)this.y/1920;
        this.radius = v.getRadius();
        this.color = v.getColor();
        connections = new ArrayList<Vertex>();
    }

    protected Vertex(Parcel in) {
        x = in.readInt();
        xx = in.readInt();
        xProp = in.readFloat();
        y = in.readInt();
        yy = in.readInt();
        yProp = in.readFloat();
        radius = in.readInt();
        color = in.readInt();
        isActivated = in.readByte() != 0;
        connections = new ArrayList<Vertex>();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(x);
        dest.writeInt(xx);
        dest.writeFloat(xProp);
        dest.writeInt(y);
        dest.writeInt(yy);
        dest.writeFloat(yProp);
        dest.writeInt(radius);
        dest.writeInt(color);
        dest.writeByte((byte) (isActivated ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Vertex> CREATOR = new Creator<Vertex>() {
        @Override
        public Vertex createFromParcel(Parcel in) {
            return new Vertex(in);
        }

        @Override
        public Vertex[] newArray(int size) {
            return new Vertex[size];
        }
    };

    public int getXx() {
//        System.out.println(Resources.getSystem().getDisplayMetrics().widthPixels);
//        System.out.println(Resources.getSystem().getDisplayMetrics().heightPixels);
        return xx;
    }

    public void setXx(int xx) {
        this.xx = xx;
    }

    public int getYy() {
        return yy;
    }

    public void setYy(int yy) {
        this.yy = yy;
    }
    public int getX(){ return x; }

    public int getY(){ return y; }

    public int getRadius(){ return radius; }

    public int getColor() { return color; }

    public boolean isActivated(){
        return isActivated;
    }

    public void setColor(int c) { this.color = c; }

    public void setActivated(Boolean b){
        if (b) {
            eNum++;
        } else { eNum--; }

        if (eNum > 0) {
            this.isActivated = true;
        } else {
            this.isActivated = false;
        }
        if (b) {this.color = Color.GREEN;}
        else   {this.color = Color.RED;}
    }

    public void setUActivated(Boolean b){

            this.isActivated = b;
            this.eNum = 0;
            for (Vertex v: this.connections) {
                if (!this.isEConnected(v)) {
                    this.Econnect(v);
                }

            }
    }

    public void setEActivated(Boolean b){
        if (b) {
            eNum++;
        } else { eNum--; }

        if (eNum > 0) {
            this.isActivated = true;
        } else {
            this.isActivated = false;
        }

        //this.isActivated = b;
        if (b) {this.color = Color.GREEN;}
        else   {this.color = Color.RED;}
    }


    private void addConnection(Vertex other){
        connections.add(other);
        econnections.add(other);
    }

    private void removeConnection(Vertex other) {
        connections.remove(other);
        econnections.remove(other);
    }

    private void addEConnection(Vertex other){
        econnections.add(other);
    }

    private void removeEConnection(Vertex other) {
        econnections.remove(other);
    }

    public void Econnect(Vertex other){
        addEConnection(other);
        other.addEConnection(this);
    }

    public void disEconnect(Vertex other){
        if(isConnected(other)){
            removeEConnection(other);
            other.removeEConnection(this);
        }
    }

    public void connect(Vertex other){
        addConnection(other);
        other.addConnection(this);
    }

    public void disconnect(Vertex other){
        if(isConnected(other)){
            removeConnection(other);
            other.removeConnection(this);
        }
    }

    public void step() {
//        Random rn = new Random();
//        int step = rn.nextInt(10);
//        this.newx = rn.nextInt(3) - 1;
//        this.newy = rn.nextInt(3) - 1;
//        System.out.println(newx);
//        System.out.println(newy);
//        if (step == 0) {
//            this.xx += newx;
//        } else if (step == 1) { this.yy += newy; }
//
//        double distancefromBoundary = Math.sqrt((x-xx)*(x-xx) + (y-yy)*(y-yy));
//        while (distancefromBoundary > this.radius*3) {
//            //System.out.println("woooo step!");
//            if (step == 0) {
//                this.xx += 2*((-1)*newx);
//            } else if (step == 1) { this.yy += 2*((-1)*newy); }
//            distancefromBoundary = Math.sqrt((x-xx)*(x-xx) + (y-yy)*(y-yy));
//        }

        Random rn = new Random();
        stepNum = rn.nextInt(4);
        if (this.newx ==0 && this.newy== 0) {
            this.newx = rn.nextInt(3) - 1;
            this.newy = rn.nextInt(3) - 1;
        }
        if (stepNum == 0) {
            this.xx += this.newx;
            this.yy += this.newy;
        }
        double distancefromBoundary = Math.sqrt((x-xx)*(x-xx) + (y-yy)*(y-yy));

        if (distancefromBoundary >= this.radius) {
            this.xx += 2*((-1)*newx);
            this.yy += 2*((-1)*newy);

            this.newx = rn.nextInt(3) - 1;
            this.newy = rn.nextInt(3) - 1;

        }


    }

    public ArrayList<Vertex> getConnections(){
        return connections;
    }

    public boolean isConnected(Vertex other){
        for(Vertex v: connections){
            if(v == other){
                return true;
            }
        }
        return false;
    }

    public boolean isEConnected(Vertex other){
        for(Vertex v: econnections){
            if(v == other){
                return true;
            }
        }
        return false;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String toString(){
        String s = "";
        s+= "(" + x + ", ";
        s+= y + ")";
        return s;
    }

    public float getxProp() {
        //this.xProp = (float)this.xx/1080;
        System.out.println("woooo stepx!" + this.xProp);
        System.out.println("woooo x!" + this.x);
        return xProp;
    }

    public void setxProp(float xProp) {
        this.xProp = xProp;
    }

    public float getyProp() {
        //this.yProp = (float)this.yy/1920;
        System.out.println("woooo step!y" + this.yProp);
        System.out.println("woooo y!" + this.y);
        return yProp;
    }

    public void setyProp(float yProp) {
        this.yProp = yProp;
    }

    public void proportion(int sX, int sY) {
        System.out.println("woooo step666x!" + this.xProp);
        System.out.println("woooo step666!y" + this.yProp);
        this.x = Math.round(sX*this.xProp);
        this.y = Math.round(sY*this.yProp);
        this.xx = this.x;
        this.yy = this.y;
    }
}
