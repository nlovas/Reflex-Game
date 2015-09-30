package com.example.nlovas.nlovas_reflex;

/**
 * Created by nlovas on 9/26/15.
 */
public class TwoPlayerClass  {

    /*
    Storing the scores for p1 and p2 in two-player mode, using the player class
     */

    private int p1;
    private int p2;

    public TwoPlayerClass() {
        this.p1 = 0;
        this.p2 = 0;
    }

    public void setp1Score(){
        p1++;
    }

    public void setp2Score(){
        p2++;
    }

    public int getp1Score(){
        return p1;
    }

    public int getp2Score(){
        return p2;
    }

}
