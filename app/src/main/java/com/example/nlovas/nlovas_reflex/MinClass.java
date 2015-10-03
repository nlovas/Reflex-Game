package com.example.nlovas.nlovas_reflex;

/**
 * Created by nlovas on 10/2/15.
 */
public class MinClass {

    private int minall;
    private int min10;
    private int min100;

    public MinClass() { //negatives are impossible, good for checking if theres a value
        minall=-1;
        min10=-1;
        min100=-1;
    }

    public int getMinAll() {
        return minall;
    }

    public int getMin10() {
        return min10;
    }

    public int getMin100() {
        return min100;
    }

    public void setMinAll(TimesListClass timeslistclass){

        if(timeslistclass.getSize()!=0) { //gaurds against there being nothing inside

            int tempmin = (int) timeslistclass.removeTime(0).getTime();

            for (int i = 0; i < timeslistclass.getSize(); i++) {
                if (timeslistclass.removeTime(i).getTime() < tempmin) { //if the time removed from list has a higher millis than tempmax
                    tempmin = (int) timeslistclass.removeTime(i).getTime();   //replace tempmax's time
                }
            }

            minall = tempmin;
        }
    }

    public void setMin10(TimesListClass timeslistclass){
        //still gives you a result even if array is smaller than 10

        int size = timeslistclass.getSize();
        if(timeslistclass.getSize()!=0) { //gaurds against there being nothing inside
            int tempmin = (int) timeslistclass.removeTime(0).getTime();

            if (size < 10) {

                for (int i = 0; i < size; i++) {
                    if (timeslistclass.removeTime(i).getTime() < tempmin) { //if the time removed from list has a higher millis than tempmax
                        tempmin = (int) timeslistclass.removeTime(i).getTime();   //replace tempmax's time
                    }
                }
                min10 = tempmin;

            } else {


                int start = size - 10;
                for (int i = start; i < size; i++) {
                    if (timeslistclass.removeTime(i).getTime() < tempmin) { //if the time removed from list has a higher millis than tempmax
                        tempmin = (int) timeslistclass.removeTime(i).getTime();   //replace tempmax's time
                    }
                }
                min10 = tempmin;
            }
        }
    }

    public void setMin100(TimesListClass timeslistclass) {

        //still gives you a result even if array is smaller than 100

        int size = timeslistclass.getSize();
        if (timeslistclass.getSize() != 0) { //gaurds against there being nothing inside
            int tempmin = (int) timeslistclass.removeTime(0).getTime();

            if (size < 100) {

                for (int i = 0; i < size; i++) {
                    if (timeslistclass.removeTime(i).getTime() < tempmin) { //if the time removed from list has a higher millis than tempmax
                        tempmin = (int) timeslistclass.removeTime(i).getTime();   //replace tempmax's time
                    }
                }
                min100 = tempmin;

            } else {


                int start = size - 100;
                for (int i = start; i < size; i++) {
                    if (timeslistclass.removeTime(i).getTime() < tempmin) { //if the time removed from list has a higher millis than tempmax
                        tempmin = (int) timeslistclass.removeTime(i).getTime();   //replace tempmax's time
                    }
                }
                min100 = tempmin;
            }


        }
    }
}
