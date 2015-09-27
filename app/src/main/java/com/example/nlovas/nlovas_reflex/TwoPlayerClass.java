package com.example.nlovas.nlovas_reflex;

/**
 * Created by nlovas on 9/26/15.
 */
public class TwoPlayerClass {

    /*
    Storing the scores for p1 and p2 in two-player mode
     */

    private int p1sum;
    private int p2sum;

    public TwoPlayerClass() {
        this.p1sum = 0;
        this.p2sum = 0;
    }

    public void setP1sum(){
        p1sum++;
    }

    public void setP2sum(){
        p2sum++;
    }

    public int getP1sum(){
        return p1sum;
    }

    public int getP2sum(){
        return p2sum;
    }
}
