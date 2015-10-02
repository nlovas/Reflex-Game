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

    public TimesListClass() {
        this.timeslist = new ArrayList<TimeClass>();
    }

    public void addTime(TimeClass time){
        timeslist.add(time);
    }

    public Collection getTimes(){
        return timeslist;
    }

    public void clearTimes(){
        timeslist.clear();
    }

    //remove this, for testing purposes:
    public TimeClass removeTime(){
        return timeslist.get(0);
    }

}
