package com.example.nlovas.nlovas_reflex;

/**
 * Created by nlovas on 10/1/15.
 */
public class FourPlayerClass extends ThreePlayerClass {

    /*
    inherits from threeplayerclass which inherits from twoplayerclass
    has all 4 players and its own setter/getter for player 4's score
     */

    private int p4;

    public FourPlayerClass() {
        super();
        this.p4 = 0;
    }

    public void setp4Score(){
        p4++;
    }

    public int getp4Score(){
        return p4;
    }
}
