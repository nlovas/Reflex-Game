package com.example.nlovas.nlovas_reflex;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

public class ReactionStatsActivity extends ActionBarActivity {

/*
Shows the user various statistics of their reaction times
call on the statistics class to get the correct information
 */

    private static final String FILENAME = "react.sav";
    private TimesListClass receivedtimes = new TimesListClass();
    private MaxClass maxclass = new MaxClass();

    //remove this, for testing purposes:
    private TimeClass time = new TimeClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_stats);

        loadFromFileR(); //put data into receivedtimes

        //for testing purposes:
        //time=receivedtimes.removeTime(0);
        TextView maxalloutput = (TextView) this.findViewById(R.id.maxallouttextView);
        TextView max10output = (TextView) this.findViewById(R.id.maxtenouttextView);
        TextView max100output = (TextView) this.findViewById(R.id.maxhundredouttextView);

        //output.setText(time.getTime() + "");

        //note to self: make sure -1's wont show and convert to seconds (method)
        maxclass.setMaxAll(receivedtimes);
        maxclass.setMax10(receivedtimes);
        maxclass.setMax100(receivedtimes);
        maxalloutput.setText(maxclass.getMaxAll() + "");
        max10output.setText(maxclass.getMax10() + "");
        max100output.setText(maxclass.getMax100() + "");

        TextView test = (TextView) this.findViewById(R.id.minallouttextView);
        test.setText(receivedtimes.getSize()+ "");

    }

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
}
