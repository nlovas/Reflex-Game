package com.example.nlovas.nlovas_reflex;

import android.widget.Toast;

/**
 * Created by nlovas on 9/28/15.
 */
public class TimeClass {

    /*
    Each individual reaction time
     */

    private double start; //to capture latency, you need to have a start and end time
    private double end;

    private double millitime;

    public TimeClass() {
        start=0;
        end=0;
        millitime=0;
    }

    //to get the start time
    public void startCapture(){
        this.start=System.currentTimeMillis();
    }

    //get end time
    public void endCapture(){
        this.end=System.currentTimeMillis();
    }

    public void setTime(){
        this.millitime = (end - start);
         }

    public double getTime(){
        return this.millitime;
    }

}
