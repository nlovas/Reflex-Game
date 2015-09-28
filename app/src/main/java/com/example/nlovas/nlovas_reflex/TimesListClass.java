package com.example.nlovas.nlovas_reflex;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by nlovas on 9/28/15.
 */
public class TimesListClass {

    /*
    Holds all of the reaction times from the reaction timer
     */

    private ArrayList<TimeClass> timeslist;

    public TimesListClass(ArrayList<TimeClass> timeslist) {
        this.timeslist = timeslist;
    }

    public void addTime(TimeClass time){

    }

    public Collection getTimes(){
        return timeslist;
    }

}
