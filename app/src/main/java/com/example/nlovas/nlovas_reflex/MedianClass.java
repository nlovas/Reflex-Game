package com.example.nlovas.nlovas_reflex;

import static java.util.Arrays.sort;

/**
 * Created by nlovas on 10/3/15.
 */
public class MedianClass {

    private int medall;
    private int med10;
    private int med100;

    public MedianClass() { //negative numbers are impossible, so its a good way to check if this stat isnt applicable
        medall=-1;
        med10=-1;
        med100=-1;
    }

    public int getMedAll() {
        return medall;
    }

    public int getMed10() {
        return med10;
    }

    public int getMed100() {
        return med100;
    }

    public void setMedianAll(TimesListClass timeslistclass){

        int size = timeslistclass.getSize();
        double[] sortarray = new double[size];

        if(size!=0) { //protect against having nothing in your array

                if(size==1){//protect against having only 1 item in array
                    medall = (int)timeslistclass.removeTime(0).getTime();
                }

            for (int i = 0; i < size; i++) {
                sortarray[i] = timeslistclass.removeTime(i).getTime(); //fills sortarray with onle the ms from time
            }

            sort(sortarray); //sorts into ascending order http://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html#sort%28double[]%29, 2015-10-03

            if ((size % 2) == 0) { //if size is even

                int middle1 = (size / 2);
                int middle2 = (middle1 - 1); //array must have at least 2 times in it

                medall = (int) (sortarray[middle1] + sortarray[middle2]) / 2; //gets avg of the 2 values

            } else { //if size is odd

                int middle = (size / 2); //rounds down to get the middle
                medall = (int) sortarray[middle];

            }
        }
    }

    public void setMedian10(TimesListClass timeslistclass){
        //still gives you a result even if array is smaller than 10

        int size = timeslistclass.getSize();


        if(size!=0) { //protect against having nothing in your array

            if (size == 1) {//protect against having only 1 item in array
                med10 = (int) timeslistclass.removeTime(0).getTime();
            }

            if (size < 10) {
                double[] sortarray = new double[size];

                for (int i = 0; i < size; i++) {
                    sortarray[i] = timeslistclass.removeTime(i).getTime(); //fills sortarray with onle the ms from time
                }
                sort(sortarray);

                if ((size % 2) == 0) { //if size is even

                    int middle1 = (size / 2);
                    int middle2 = (middle1 - 1);

                    med10 = (int) (sortarray[middle1] + sortarray[middle2]) / 2; //gets avg of the 2 values

                } else { //if size is odd

                    int middle = (size / 2); //rounds down to get the middle
                    med10 = (int) sortarray[middle];

                }

            } else {

                double[] sortarray = new double[10];
                int start = size - 10;
                for (int i = 0; i < 10; i++) {
                    sortarray[i] = timeslistclass.removeTime((start+i)).getTime(); //fills sortarray with onle the ms from time
                }
                sort(sortarray);

                    int middle1 = 4;
                    int middle2 = 5;

                    med10 = (int) (sortarray[middle1] + sortarray[middle2]) / 2; //gets avg of the 2 values

                 /*else { //if size is odd

                    int middle = (size / 2); //rounds down to get the middle
                    med10 = (int) sortarray[middle];

                }*/
            }
        }

    }

    public void setMedian100(TimesListClass timeslistclass){
        //still gives you a result even if array is smaller than 100


        int size = timeslistclass.getSize();


        if(size!=0) { //protect against having nothing in your array

            if (size == 1) {//protect against having only 1 item in array
                med100 = (int) timeslistclass.removeTime(0).getTime();
            }

            if (size < 100) {

                double[] sortarray = new double[size];
                for (int i = 0; i < size; i++) {
                    sortarray[i] = timeslistclass.removeTime(i).getTime(); //fills sortarray with onle the ms from time
                }
                sort(sortarray);
                if ((size % 2) == 0) { //if size is even

                    int middle1 = (size / 2);
                    int middle2 = (middle1 - 1);

                    med100 = (int) (sortarray[middle1] + sortarray[middle2]) / 2; //gets avg of the 2 values

                } else { //if size is odd

                    int middle = (size / 2); //rounds down to get the middle
                    med100 = (int) sortarray[middle];

                }

            } else {

                double[] sortarray = new double[100];
                int start = size - 100;
                for (int i = 0; i < 100; i++) {
                    sortarray[i] = timeslistclass.removeTime((start+i)).getTime(); //fills sortarray with onle the ms from time
                }
                sort(sortarray);

                if ((size % 2) == 0) { //if size is even

                    int middle1 = 49;
                    int middle2 = 50;

                    med100 = (int) (sortarray[middle1] + sortarray[middle2]) / 2; //gets avg of the 2 values

                } /*else { //if size is odd

                    int middle = (size / 2); //rounds down to get the middle
                    med100 = (int) sortarray[middle];

                }*/
            }
        }

    }

}
