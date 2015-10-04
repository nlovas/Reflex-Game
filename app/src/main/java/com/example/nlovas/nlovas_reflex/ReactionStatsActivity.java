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

Gson:
    Copyright 2008 Google Inc.Nicole Lovas 2015

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.


 */

    private static final String FILENAME = "react.sav";
    private TimesListClass receivedtimes = new TimesListClass();
    private MaxClass maxclass = new MaxClass();
    private MinClass minclass = new MinClass();
    private AvgClass avgclass = new AvgClass();
    private MedianClass medclass = new MedianClass();



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


    }

    public void emailstatsbtn(View view){ //"Building Your First App",Android 2015-09-24
        Intent intent = new Intent(this, SendEmailActivity.class);
        startActivity(intent);
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
