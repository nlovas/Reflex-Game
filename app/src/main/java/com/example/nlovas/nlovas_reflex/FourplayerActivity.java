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

    private static final String FILENAME = "file4p.sav";
    private FourPlayerClass fourplayerclass = new FourPlayerClass(); // score are all 0

    public void p1of4click(View view){ //altered code from "Building Your First App" tutorial https://developer.android.com/training/basics/firstapp/starting-activity.html
        Intent intent = new Intent(this, GameshowresultsActivity.class);

        loadFromFile4p(); //pull from saved file

        fourplayerclass.setp1Score(); //player 1 gets a point

        saveInFile4p(); //save the status of these players to file

        intent.putExtra("winnername","Player 1");
        startActivity(intent);
    }

    public void p2of4click(View view){ //altered code from "Building Your First App" tutorial https://developer.android.com/training/basics/firstapp/starting-activity.html
        Intent intent = new Intent(this, GameshowresultsActivity.class);

        loadFromFile4p(); //pull from saved file

        fourplayerclass.setp2Score(); //player 1 gets a point

        saveInFile4p(); //save the status of these players to file

        intent.putExtra("winnername","Player 2");
        startActivity(intent);
    }

    public void p3of4click(View view){ //altered code from "Building Your First App" tutorial https://developer.android.com/training/basics/firstapp/starting-activity.html
        Intent intent = new Intent(this, GameshowresultsActivity.class);

        loadFromFile4p(); //pull from saved file

        fourplayerclass.setp3Score(); //player 1 gets a point

        saveInFile4p(); //save the status of these players to file

        intent.putExtra("winnername","Player 3");
        startActivity(intent);
    }

    public void p4of4click(View view){ //altered code from "Building Your First App" tutorial https://developer.android.com/training/basics/firstapp/starting-activity.html
        Intent intent = new Intent(this, GameshowresultsActivity.class);

        loadFromFile4p(); //pull from saved file

        fourplayerclass.setp4Score(); //player 1 gets a point

        saveInFile4p(); //save the status of these players to file

        intent.putExtra("winnername","Player 4");
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
        //saves the twoplayerclass status (object) into file.sav in the phone
        //saves both players at once
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
        //loads the twoplayerclass status (object) from file.sav in the phone


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
