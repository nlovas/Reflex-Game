package com.example.nlovas.nlovas_reflex;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
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
import java.util.ArrayList;

public class TwoplayerActivity extends ActionBarActivity {



    /*
    Shows 2 buttons for player 1 and player 2 to click
    The plan: loadfromfile2p creates new object, which is pulled out, updated, then put back into file
    every button click

    Gson:
    Copyright 2008 Google Inc., Nicole Lovas 2015

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
    private static final String FILENAME = "file2p.sav";
    private TwoPlayerClass twoplayerclass = new TwoPlayerClass(); //p1 and p2's score are both 0
    //want to have only one of these for the entire duration


    public void p1click(View view){ //altered code from "Building Your First App" tutorial https://developer.android.com/training/basics/firstapp/starting-activity.html
        Intent intent = new Intent(this, GameshowresultsActivity.class);

        loadFromFile2p(); //pull from saved file

        twoplayerclass.setp1Score(); //player 1 gets a point

        saveInFile2p(); //save the status of these players to file

        intent.putExtra("winnername","Player 1"); // https://youtu.be/ViwazAAR-vE, TZCoder, 2015-09-27
        startActivity(intent);
    }

    public void p2click(View view){ //altered code from "Building Your First App" tutorial https://developer.android.com/training/basics/firstapp/starting-activity.html
        Intent intent = new Intent(this, GameshowresultsActivity.class);

        loadFromFile2p();

        twoplayerclass.setp2Score(); //player 2 gets a point

        saveInFile2p(); //save the status of these players to file

        intent.putExtra("winnername","Player 2"); // https://youtu.be/ViwazAAR-vE, TZCoder, 2015-09-27
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twoplayerbutton);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_twoplayer, menu);
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

    //put into a class of its own if time permits
    private void saveInFile2p() { //code from CMPUT301 lab, University of Alberta, 2015-09-30
        //saves the twoplayerclass status (object) into file.sav in the phone
        //saves both players at once
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            BufferedWriter out= new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            //https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-30
            gson.toJson(twoplayerclass, out);
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



    private void loadFromFile2p() { //code from CMPUT301 lab, University of Alberta, 2015-09-30
        //loads the twoplayerclass status (object) from file.sav in the phone


        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            //https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-23
            Type ptype = new TypeToken<TwoPlayerClass>() {}.getType();
            twoplayerclass=gson.fromJson(in,ptype);


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            twoplayerclass= new TwoPlayerClass(); //happens the first time you play
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }



}
