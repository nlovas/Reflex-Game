package com.example.nlovas.nlovas_reflex;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

public class SendEmailActivity extends ActionBarActivity {

    /*
    Prompts the user for their email to send their statistics

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

    private static final String FILENAME2p = "file2p.sav";
    private static final String FILENAME3p = "file3p.sav";
    private static final String FILENAME4p = "file4p.sav";
    private static final String FILENAMEr = "react.sav";


    private TimesListClass receivedtimes = new TimesListClass();
    private TwoPlayerClass receivedtwoplayerclass;
    private ThreePlayerClass receivedthreeplayerclass;
    private FourPlayerClass receivedfourplayerclass;

    private MaxClass maxclass = new MaxClass();
    private MinClass minclass = new MinClass();
    private AvgClass avgclass = new AvgClass();
    private MedianClass medclass = new MedianClass();

    private EditText email_address;




    public void emailstats(View view){ // https://www.youtube.com/watch?v=V1tAL0kjjuU, profgustin, 2015-10-03



        email_address = (EditText) this.findViewById(R.id.enteremaileditText);
        String adrs = email_address.getText().toString();
        String msg = getData();


        sendEmail(adrs, msg);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_send_email, menu);
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

    private String getData(){ //get all of the data and make a string from it

        loadFromFileR(); //put data into receivedtimes

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

        int minall = minclass.getMinAll();
        int min10 = minclass.getMin10();
        int min100 = minclass.getMin100();

        int maxall = maxclass.getMaxAll();
        int max10 = maxclass.getMax10();
        int max100 = maxclass.getMax100();

        double avgall = avgclass.getAvgAll();
        double avg10 = avgclass.getAvg10();
        double avg100 = avgclass.getAvg100();

        int medall = medclass.getMedAll();
        int med10 = medclass.getMed10();
        int med100 = medclass.getMed100();

        if(minall==-1){ //if any are -1 then theres no button presses
             minall = 0;
             min10 = 0;
             min100 = 0;

             maxall = 0;
             max10 = 0;
             max100 = 0;

             avgall = 0;
             avg10 = 0;
             avg100 = 0;

             medall = 0;
             med10 = 0;
             med100 = 0;

        }

        loadFromFile2p();
        loadFromFile3p();
        loadFromFile4p();


        String msg = ("Gameshow Mode: \n" + "2-player: \n" + "Player 1: " + receivedtwoplayerclass.getp1Score() +
                " Player 2: " + receivedtwoplayerclass.getp2Score() + "\n" + "3-Player: \n" +
                "Player 1: " + receivedthreeplayerclass.getp1Score() + " Player 2: " + receivedthreeplayerclass.getp2Score() +
                " Player 3: " + receivedthreeplayerclass.getp3Score() + "\n" + "4-Player: \n" +
                "Player 1: " + receivedfourplayerclass.getp1Score() + " Player 2: " + receivedfourplayerclass.getp2Score() +
                " Player 3: " + receivedfourplayerclass.getp3Score() + " Player 4: " + receivedfourplayerclass.getp4Score() +
                "\n Reaction Statistics:  \n" + "Min of all: " + minall + " min of last 10: " + min10+
        " min of last 100: " + min100 + "\n Max of all: "+ maxall + " max of last 10: " + max10+
                " max of last 100: " + max100 + "\n Average of all: "+ avgall + " avg of last 10: " + avg10+
                " avg of last 100: " + avg100 + "\n Median of all: "+ medall + " median of last 10: " + med10+
                " median of last 100: " + med100 + "\n Thank you for playing!");

        return msg;

    }

    public void sendEmail(String e_address, String message){ //learned + borrowed code from https://www.youtube.com/watch?v=V1tAL0kjjuU 2015-10-03

        String[] recipient = new String[]{e_address};   //who youre sending the data to
        String subject = ("Your Statistics!");
        Intent eintent = new Intent(Intent.ACTION_SEND);
        eintent.putExtra(Intent.EXTRA_EMAIL, recipient);
        eintent.putExtra(Intent.EXTRA_SUBJECT, subject);
        eintent.putExtra(Intent.EXTRA_TEXT, message);
        eintent.setType("message/rfc822");
        startActivity(Intent.createChooser(eintent, "Email"));
    }

    private void loadFromFileR() { //code from CMPUT301 lab, University of Alberta, 2015-09-30



        try {
            FileInputStream fis = openFileInput(FILENAMEr);
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

    private void loadFromFile2p() { //code from CMPUT301 lab, University of Alberta, 2015-09-30


        try {
            FileInputStream fis = openFileInput(FILENAME2p);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            //https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-23
            Type ptype = new TypeToken<TwoPlayerClass>() {}.getType();
            receivedtwoplayerclass=gson.fromJson(in,ptype);


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            receivedtwoplayerclass= new TwoPlayerClass();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    private void loadFromFile3p() { //code from CMPUT301 lab, University of Alberta, 2015-09-30


        try {
            FileInputStream fis = openFileInput(FILENAME3p);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            //https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-23
            Type ptype = new TypeToken<ThreePlayerClass>() {}.getType();
            receivedthreeplayerclass=gson.fromJson(in,ptype);


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            receivedthreeplayerclass= new ThreePlayerClass();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    private void loadFromFile4p() { //code from CMPUT301 lab, University of Alberta, 2015-09-30


        try {
            FileInputStream fis = openFileInput(FILENAME4p);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            //https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-23
            Type ptype = new TypeToken<FourPlayerClass>() {}.getType();
            receivedfourplayerclass=gson.fromJson(in,ptype);


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            receivedfourplayerclass= new FourPlayerClass();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }


}
