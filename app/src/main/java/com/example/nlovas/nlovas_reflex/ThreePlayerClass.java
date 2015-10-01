package com.example.nlovas.nlovas_reflex;

/**
 * Created by nlovas on 10/1/15.
 */
public class ThreePlayerClass extends TwoPlayerClass {

    /*
    keeps the data for 3-player mode
    inherits 1p and 2p and their setters/getters from twoplayerclass
     */

    protected int p3; //for fourplayerclass to access

    public ThreePlayerClass() {
        super(); //calls constructor from twoplayerclass
        this.p3 = 0;
    }

    public void setp3Score(){
        p3++;
    }

    public int getp3Score(){
        return p3;
    }
}
