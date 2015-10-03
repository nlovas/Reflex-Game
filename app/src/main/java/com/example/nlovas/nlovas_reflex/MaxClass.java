package com.example.nlovas.nlovas_reflex;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by nlovas on 10/2/15.
 */
public class MaxClass {

    private int maxall;
    private int max10;
    private int max100;

    public MaxClass() { //negative numbers are impossible, so its a good way to check if this stat isnt applicable
        maxall=-1;
        max10=-1;
        max100=-1;
    }

    public int getMaxAll() {
        return maxall;
    }

    public int getMax10() {
        return max10;
    }

    public int getMax100() {
        return max100;
    }

    public void setMaxAll(TimesListClass timeslistclass){

        //double[] ms_holder = new double[timeslistclass.getSize()]; //a regular array with the same size as timeslistclass

        int tempmax = -1;

        for(int i=0;i<timeslistclass.getSize();i++){
            if(timeslistclass.removeTime(i).getTime()>tempmax){ //if the time removed from list has a higher millis than tempmax
                tempmax = (int)timeslistclass.removeTime(i).getTime();   //replace tempmax's time
            }
        }

        maxall=tempmax;

    }

    public void setMax10(TimesListClass timeslistclass){
        //still gives you a result even if array is smaller than 10

        int size = timeslistclass.getSize();
        int tempmax = -1;

        if(size<10){

            for(int i=0;i<size;i++){
                if(timeslistclass.removeTime(i).getTime()>tempmax){ //if the time removed from list has a higher millis than tempmax
                    tempmax = (int)timeslistclass.removeTime(i).getTime();   //replace tempmax's time
                }
            }
            max10=tempmax;

        } else {


            int start = size - 10;
            for (int i =start;i<size; i++) {
                if (timeslistclass.removeTime(i).getTime() > tempmax) { //if the time removed from list has a higher millis than tempmax
                    tempmax = (int)timeslistclass.removeTime(i).getTime();   //replace tempmax's time
                }
            }
            max10=tempmax;
        }
    }

    public void setMax100(TimesListClass timeslistclass){

        //still gives you a result even if array is smaller than 100

        int size = timeslistclass.getSize();
        int tempmax = -1;

        if(size<100){

            for(int i=0;i<size;i++){
                if(timeslistclass.removeTime(i).getTime()>tempmax){ //if the time removed from list has a higher millis than tempmax
                    tempmax = (int)timeslistclass.removeTime(i).getTime();   //replace tempmax's time
                }
            }
            max100=tempmax;

        } else {


            int start = size - 100;
            for (int i =start;i<size; i++) {
                if (timeslistclass.removeTime(i).getTime() > tempmax) { //if the time removed from list has a higher millis than tempmax
                    tempmax = (int)timeslistclass.removeTime(i).getTime();   //replace tempmax's time
                }
            }
            max100=tempmax;
        }


    }
}
