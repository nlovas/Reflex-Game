package com.example.nlovas.nlovas_reflex;

/**
 * Created by nlovas on 10/2/15.
 */
public class AvgClass {

    /*
    Holds the averages for all, last 10, and last 100
     */

    private double avgall;
    private double avg10;
    private double avg100;

    public AvgClass() { //negative numbers are impossible, so its a good way to check if this stat isnt applicable
        avgall=-1;
        avg10=-1;
        avg100=-1;
    }

    public double getAvgAll() {
        return avgall;
    }

    public double getAvg10() {
        return avg10;
    }

    public double getAvg100() {
        return avg100;
    }

    public void setAvgAll(TimesListClass timeslistclass){

        double sum=0;
        int size = timeslistclass.getSize();

        for(int i=0;i<size;i++){
            sum= sum + timeslistclass.removeTime(i).getTime();
        }

        avgall = (sum/size);

    }

    public void setAvg10(TimesListClass timeslistclass){
        //still gives you a result even if array is smaller than 10

        double sum=0;
        int size = timeslistclass.getSize();

        if(size<10){

            for(int i=0;i<size;i++){
               sum = sum + timeslistclass.removeTime(i).getTime();
            }
            avg10= (sum/size); //size<10

        } else {


            int start = size - 10;
            for (int i =start;i<size; i++) {
                sum = sum + timeslistclass.removeTime(i).getTime();
            }
            avg10= (sum/10);
        }


    }

    public void setAvg100(TimesListClass timeslistclass){

        //still gives you a result even if array is smaller than 100
        double sum=0;
        int size = timeslistclass.getSize();

        if(size<100){

            for(int i=0;i<size;i++){
                sum = sum + timeslistclass.removeTime(i).getTime();
            }
            avg100= (sum/size); //size<100

        } else {


            int start = size - 100;
            for (int i =start;i<size; i++) {
                sum = sum + timeslistclass.removeTime(i).getTime();
            }
            avg100= (sum/100);
        }



    }

}
