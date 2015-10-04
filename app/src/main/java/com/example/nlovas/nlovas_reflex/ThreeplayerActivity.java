package com.example.nlovas.nlovas_reflex;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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

public class ThreeplayerActivity extends ActionBarActivity {

     /*
    Shows 3 buttons for player 1 and player 2 and 3 to click
    The plan: loadfromfile3p creates new object, which is pulled out, updated, then put back into file
    every button click

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

    private static final String FILENAME = "file3p.sav";
    private ThreePlayerClass threeplayerclass = new ThreePlayerClass(); // scores are all 0


    public void p1of3click(View view) { //"Building Your First App", Android 2015-09-24
        Intent intent = new Intent(this, GameshowresultsActivity.class);
        loadFromFile3p(); //pull from saved file

        threeplayerclass.setp1Score(); //player 1 gets a point

        saveInFile3p(); //save the status of these players to file

        intent.putExtra("winnername", "Player 1"); // https://youtu.be/ViwazAAR-vE, TZCoder, 2015-09-27
        startActivity(intent);
    }

    public void p2of3click(View view) { //"Building Your First App", Android 2015-09-24
        Intent intent = new Intent(this, GameshowresultsActivity.class);

        loadFromFile3p(); //pull from saved file

        threeplayerclass.setp2Score(); //player 1 gets a point

        saveInFile3p(); //save the status of these players to file

        intent.putExtra("winnername", "Player 2"); // https://youtu.be/ViwazAAR-vE, TZCoder, 2015-09-27
        startActivity(intent);
    }

    public void p3of3click(View view) { //"Building Your First App", Android 2015-09-24
        Intent intent = new Intent(this, GameshowresultsActivity.class);

        loadFromFile3p(); //pull from saved file

        threeplayerclass.setp3Score(); //player 3 gets a point

        saveInFile3p(); //save the status of these players to file

        intent.putExtra("winnername", "Player 3"); // https://youtu.be/ViwazAAR-vE, TZCoder, 2015-09-27
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threeplayer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_threeplayer, menu);
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

    private void saveInFile3p() { //code from CMPUT301 lab, University of Alberta, 2015-09-30

        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            //https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-30
            gson.toJson(threeplayerclass, out);
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


    private void loadFromFile3p() { //code from CMPUT301 lab, University of Alberta, 2015-09-30



        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            //https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-23
            Type arraylistType = new TypeToken<ThreePlayerClass>() {
            }.getType();
            threeplayerclass = gson.fromJson(in, arraylistType);


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            threeplayerclass = new ThreePlayerClass(); //happens the first time you play
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }
}
