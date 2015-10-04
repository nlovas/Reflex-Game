package com.example.nlovas.nlovas_reflex;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

public class ReactionStatsActivity extends ActionBarActivity {

/*
Shows the user various statistics of their reaction times
call on the statistics class to get the correct information
 */

    private static final String FILENAME = "react.sav";
   // private static final String FILENAMEstats = "stats.sav";
    private TimesListClass receivedtimes = new TimesListClass();
    private MaxClass maxclass = new MaxClass();
    private MinClass minclass = new MinClass();
    private AvgClass avgclass = new AvgClass();
    private MedianClass medclass = new MedianClass();
  //  private StatisticsClass statsclass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_stats);

        loadFromFileR(); //put data into receivedtimes

        TextView maxalloutput = (TextView) this.findViewById(R.id.maxallouttextView);
        TextView max10output = (TextView) this.findViewById(R.id.maxtenouttextView);
        TextView max100output = (TextView) this.findViewById(R.id.maxhundredouttextView);

        TextView minalloutput = (TextView) this.findViewById(R.id.minallouttextView);
        TextView min10output = (TextView) this.findViewById(R.id.mintenouttextView);
        TextView min100output = (TextView) this.findViewById(R.id.minhundredouttextView);

        TextView avgalloutput = (TextView) this.findViewById(R.id.avgallouttextView);
        TextView avg10output = (TextView) this.findViewById(R.id.avgtenouttextView);
        TextView avg100output = (TextView) this.findViewById(R.id.avghundredouttextView);

        TextView medalloutput = (TextView) this.findViewById(R.id.medallouttextView);
        TextView med10output = (TextView) this.findViewById(R.id.medtenouttextView);
        TextView med100output = (TextView) this.findViewById(R.id.medhundredouttextView);

        maxclass.setMaxAll(receivedtimes);
        maxclass.setMax10(receivedtimes);
        maxclass.setMax100(receivedtimes);

        minclass.setMinAll(receivedtimes);
        minclass.setMin10(receivedtimes);
        minclass.setMin100(receivedtimes);

        avgclass.setAvgAll(receivedtimes);
        avgclass.setAvg10(receivedtimes);
        avgclass.setAvg100(receivedtimes);

        medclass.setMedianAll(receivedtimes);
        medclass.setMedian10(receivedtimes);
        medclass.setMedian100(receivedtimes);

       if(maxclass.getMaxAll()>(-1)) { //if times are unset, it will still read as "none"

           maxalloutput.setText(maxclass.getMaxAll() + "ms");
           max10output.setText(maxclass.getMax10() + "ms");
           max100output.setText(maxclass.getMax100() + "ms");

       }
        if(minclass.getMinAll()>(-1)) { //if times are unset, it will still read as "none"

            minalloutput.setText(minclass.getMinAll() + "ms");
            min10output.setText(minclass.getMin10() + "ms");
            min100output.setText(minclass.getMin100() + "ms");

        }
        if(avgclass.getAvgAll()>(-1)) { //if times are unset, it will still read as "none"

            avgalloutput.setText((int)avgclass.getAvgAll() + "ms"); //cast as ints because doubles too big to print
            avg10output.setText((int)avgclass.getAvg10() + "ms");
            avg100output.setText((int)avgclass.getAvg100() + "ms");

        }
        if(medclass.getMedAll()>(-1)) { //if times are unset, it will still read as "none"

            medalloutput.setText((int)medclass.getMedAll() + "ms"); //cast as ints because doubles too big to print
            med10output.setText((int)medclass.getMed10() + "ms");
            med100output.setText((int)medclass.getMed100() + "ms");

        }

       // statsclass = new StatisticsClass(maxclass,minclass,medclass,avgclass); //packed up for email delivery
      //  saveInFilestats();
    }

    public void emailstatsbtn(View view){ //altered code from "Building Your First App" tutorial https://developer.android.com/training/basics/firstapp/starting-activity.html
        Intent intent = new Intent(this, SendEmailActivity.class);
        startActivity(intent);
    }

    /*public void goback(View view){ //altered code from "Building Your First App" tutorial https://developer.android.com/training/basics/firstapp/starting-activity.html
        Intent intent = new Intent(this, StatselectActivity.class);
        startActivity(intent);
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reaction_stats, menu);
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

    private void loadFromFileR() { //code from CMPUT301 lab, University of Alberta, 2015-09-30



        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            //https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-23
            Type ptype = new TypeToken<TimesListClass>() {}.getType();
            receivedtimes=gson.fromJson(in,ptype);


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            receivedtimes= new TimesListClass();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }
/*
    private void saveInFilestats() { //code from CMPUT301 lab, University of Alberta, 2015-09-30

        try {
            FileOutputStream fos = openFileOutput(FILENAMEstats, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            //https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-30
            gson.toJson(statsclass, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }*/
}
