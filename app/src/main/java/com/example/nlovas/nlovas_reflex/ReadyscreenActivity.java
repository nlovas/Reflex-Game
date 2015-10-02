package com.example.nlovas.nlovas_reflex;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ReadyscreenActivity extends ActionBarActivity {

    /*
    Where the player can click the button to get their rection time. If theyre too fast, theyll be
    redirected to the "too early!" screen.
    Times are kept in Time objects and held in an Arraylist to be saved in file with gson.
     */

    private static final String FILENAME = "react.sav";
    private double limit = (double) (Math.random() * 1991) + 10; //from http://stackoverflow.com/questions/7961788/math-random-explained 2015-09-27


    private CountDownTimer countdowntimer;
    private boolean timerstarted;
    private Button readyb;
    public TimeClass time; //for getting individual latencies
    private boolean ispressed=false; //prevents people from pressing again and again without going back
    private TimesListClass times = new TimesListClass(); //holds all times

    public void setTimerstarted() {
        timerstarted = !timerstarted;
    } //toggles timerstarted to true/false


    public void clickedred(View view) { //http://androidbite.blogspot.ca/2012/11/android-count-down-timer-example.html , 2015-09-27
        if ((timerstarted == true)&&(ispressed==false)) { //if the timer is still counting down, then your click is too early
            countdowntimer.cancel();
            timerstarted = false;
            Intent intent = new Intent(this, ToosoonActivity.class);
            startActivity(intent);
        } else if ((timerstarted == false)&&(ispressed==false)) { //if the timer is done, continue to the next screen to play
            time.endCapture();
            time.setTime(); //gets the latency
            times.addTime(time); //add time to list
            saveInFileR();

            readyb.setText("Your time was: " + (int) time.getTime() + "ms"); //problem solved with http://stackoverflow.com/questions/17958887/make-a-button-change-value-of-a-textview 2015-09-28
            ispressed = true;
                }
            else{
                Intent intent = new Intent(this, PrepActivity.class); //if they click again after seeing their time theyre taken back to the prepare screen
                 startActivity(intent);
            }



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.readybutton);

        readyb = (Button) this.findViewById(R.id.readybutton);
        //readyb.setOnClickListener((View.OnClickListener) this);


        countdowntimer = new Cdowntimer((long) limit, 100); //http://developer.android.com/reference/android/os/CountDownTimer.html  2015-09-27
        //idea suggested by Linda Zhang, Second resource suggested by Jillian Lovas
        //also http://androidbite.blogspot.ca/2012/11/android-count-down-timer-example.html 2015-09-27
        countdowntimer.start();
        timerstarted = true;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_prepscreen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
//} everything is currently within the activity

    class Cdowntimer extends CountDownTimer { //http://androidbite.blogspot.ca/2012/11/android-count-down-timer-example.html 2015-09-27

        public Cdowntimer(long startTime, long interval) {
            super(startTime, interval);

        }

        @Override
        public void onFinish() {
            setTimerstarted();
            readyb.setText("Go!"); //Prof Scott Vanselow https://www.youtube.com/watch?v=OWLOMCvtSC8 2015-09-28
            time = new TimeClass(); //makes a new time every time you click
            time.startCapture();
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

    }

    private void saveInFileR() { //code from CMPUT301 lab, University of Alberta, 2015-09-30
        //saves the twoplayerclass status (object) into file.sav in the phone
        //saves both players at once
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            BufferedWriter out= new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            //https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-30
            gson.toJson(times, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }



    private void loadFromFileR() { //code from CMPUT301 lab, University of Alberta, 2015-09-30



        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            //https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-23
            Type ptype = new TypeToken<TimesListClass>() {}.getType();
            times=gson.fromJson(in,ptype);


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            times= new TimesListClass();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

}