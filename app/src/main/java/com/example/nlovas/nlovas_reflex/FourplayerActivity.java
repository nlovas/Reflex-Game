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

public class FourplayerActivity extends ActionBarActivity {

    /*Gson:
    Copyright 2008 Google Inc.,Nicole Lovas 2015

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
-----------------------------------------------------------------
Portions of this page are modifications based on work created
and shared by the Android Open Source Project and used according to terms described in the Creative Commons 2.5 Attribution License.
"Building Your First App" https://developer.android.com/training/basics/firstapp/starting-activity.html, 2015-09-24


     */




    private static final String FILENAME = "file4p.sav";
    private FourPlayerClass fourplayerclass = new FourPlayerClass(); // score are all 0

    public void p1of4click(View view){ //"Building Your First App", Android 2015-09-24
        Intent intent = new Intent(this, GameshowresultsActivity.class);

        loadFromFile4p(); //pull from saved file

        fourplayerclass.setp1Score(); //player 1 gets a point

        saveInFile4p(); //save the status of these players to file

        intent.putExtra("winnername","Player 1"); // https://youtu.be/ViwazAAR-vE, TZCoder, 2015-09-27
        startActivity(intent);
    }

    public void p2of4click(View view){ //"Building Your First App",Android 2015-09-24
        Intent intent = new Intent(this, GameshowresultsActivity.class);

        loadFromFile4p(); //pull from saved file

        fourplayerclass.setp2Score(); //player 2 gets a point

        saveInFile4p(); //save the status of these players to file

        intent.putExtra("winnername","Player 2");// https://youtu.be/ViwazAAR-vE, TZCoder, 2015-09-27
        startActivity(intent);
    }

    public void p3of4click(View view){ //"Building Your First App",Android 2015-09-24
        Intent intent = new Intent(this, GameshowresultsActivity.class);

        loadFromFile4p(); //pull from saved file

        fourplayerclass.setp3Score(); //player 3 gets a point

        saveInFile4p(); //save the status of these players to file

        intent.putExtra("winnername","Player 3"); // https://youtu.be/ViwazAAR-vE, TZCoder, 2015-09-27
        startActivity(intent);
    }

    public void p4of4click(View view){ //"Building Your First App",Android 2015-09-24
        Intent intent = new Intent(this, GameshowresultsActivity.class);

        loadFromFile4p(); //pull from saved file

        fourplayerclass.setp4Score(); //player 4 gets a point

        saveInFile4p(); //save the status of these players to file

        intent.putExtra("winnername","Player 4"); // https://youtu.be/ViwazAAR-vE, TZCoder, 2015-09-27
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourplayer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fourplayer, menu);
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


    private void saveInFile4p() { //code from CMPUT301 lab, University of Alberta, 2015-09-30

        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            //https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-30
            gson.toJson(fourplayerclass, out);
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
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            //https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-23
            Type arraylistType = new TypeToken<FourPlayerClass>() {
            }.getType();
            fourplayerclass = gson.fromJson(in, arraylistType);


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            fourplayerclass = new FourPlayerClass(); //happens the first time you play
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

}
