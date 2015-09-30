package com.example.nlovas.nlovas_reflex;

/**
 * Created by nlovas on 9/29/15.
 */
public class Player {
    //scrap this

    private int score;

    public Player() {
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore() {
        this.score = score++;
    }
}
