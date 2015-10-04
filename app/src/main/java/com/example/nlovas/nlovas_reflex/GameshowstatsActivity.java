package com.example.nlovas.nlovas_reflex;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

public class GameshowstatsActivity extends ActionBarActivity {

    /*
    Where the gameshow statistics are shown, pulls the objects from file and prints them.
    Shows the number of times Player x pressed first in the game

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
    private TwoPlayerClass receivedtwoplayerclass;
    private ThreePlayerClass receivedthreeplayerclass;
    private FourPlayerClass receivedfourplayerclass;
    private TimesListClass receivedtimes = new TimesListClass();

    private static final String FILENAME2p = "file2p.sav";
    private static final String FILENAME3p = "file3p.sav";
    private static final String FILENAME4p = "file4p.sav";
    private static final String FILENAME = "react.sav";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameshowstats);
//------2-player stuff----------------------------------------------------
        loadFromFile2p();

        TextView p1of2out = (TextView)findViewById(R.id.p1of2outtextView);
        TextView p2of2out = (TextView)findViewById(R.id.p2of2outtextView);
        p1of2out.setText(receivedtwoplayerclass.getp1Score() + ""); //problem solved with Stack Overflow, User William Morrison, 2015-09-28
        p2of2out.setText(receivedtwoplayerclass.getp2Score() + "");

        saveInFile2p();

        //---3-player stuff ---------------------------------------------------

        loadFromFile3p();

        TextView p1of3out = (TextView)findViewById(R.id.p1of3outtextView);
        TextView p2of3out = (TextView)findViewById(R.id.p2of3outtextView);
        TextView p3of3out = (TextView)findViewById(R.id.p3of3outtextView);
        p1of3out.setText(receivedthreeplayerclass.getp1Score() + "");
        p2of3out.setText(receivedthreeplayerclass.getp2Score() + "");
        p3of3out.setText(receivedthreeplayerclass.getp3Score() + "");

        saveInFile3p();

        //---4-player stuff-----------------------------------------------------

        loadFromFile4p();

        TextView p1of4out = (TextView)findViewById(R.id.p1of4outtextView);
        TextView p2of4out = (TextView)findViewById(R.id.p2of4outtextView);
        TextView p3of4out = (TextView)findViewById(R.id.p3of4outtextView);
        TextView p4of4out = (TextView)findViewById(R.id.p4of4outtextView);
        p1of4out.setText(receivedfourplayerclass.getp1Score() + "");
        p2of4out.setText(receivedfourplayerclass.getp2Score() + "");
        p3of4out.setText(receivedfourplayerclass.getp3Score() + "");
        p4of4out.setText(receivedfourplayerclass.getp4Score() + "");

        saveInFile4p();

    }

    public void estatsbtn(View view){ //"Building Your First App", Android 2015-09-24
        Intent intent = new Intent(this, SendEmailActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gameshowstats, menu);
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

    private void saveInFile2p() { //code from CMPUT301 lab, University of Alberta, 2015-09-30

        try {
            FileOutputStream fos = openFileOutput(FILENAME2p, 0);
            BufferedWriter out= new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            //https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-30
            gson.toJson(receivedtwoplayerclass, out);
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

    //------3-player stuff-----------------------------------------------------------------

    private void loadFromFile3p() { //code from CMPUT301 lab, University of Alberta, 2015-09-30
        //loads the twoplayerclass status (object) from file.sav in the phone

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

    private void saveInFile3p() { //code from CMPUT301 lab, University of Alberta, 2015-09-30

        try {
            FileOutputStream fos = openFileOutput(FILENAME3p, 0);
            BufferedWriter out= new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            //https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-30
            gson.toJson(receivedthreeplayerclass, out);
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

    //---4-player stuff ------------------------------------------------------------------

    private void saveInFile4p() { //code from CMPUT301 lab, University of Alberta, 2015-09-30

        try {
            FileOutputStream fos = openFileOutput(FILENAME4p, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            //https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-30
            gson.toJson(receivedfourplayerclass, out);
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


    private void loadFromFile4p() { //code from CMPUT301 lab, University of Alberta, 2015-09-30


        try {
            FileInputStream fis = openFileInput(FILENAME4p);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            //https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-23
            Type arraylistType = new TypeToken<FourPlayerClass>() {
            }.getType();
            receivedfourplayerclass = gson.fromJson(in, arraylistType);


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            receivedfourplayerclass = new FourPlayerClass(); //happens the first time you play
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }


}
