package com.example.nlovas.nlovas_reflex;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class TwoplayerActivity extends ActionBarActivity {

    private static final String FILENAME = "file.sav";

    /*
    Shows 2 buttons for player 1 and player 2 to click
     */

    private TwoPlayerClass twoplayerclass = new TwoPlayerClass(); //p1 and p2's score are both 0
    //want to have only one of these for the entire duration


    public void p1click(View view){ //altered code from "Building Your First App" tutorial https://developer.android.com/training/basics/firstapp/starting-activity.html
        Intent intent = new Intent(this, GameshowresultsActivity.class);
        twoplayerclass.setp1Score(); //player 1 gets a point

       // saveInFile();

        intent.putExtra("winnername","Player 1"); //learned how to pass strings using intents with https://youtu.be/ViwazAAR-vE, 2015-09-27
        startActivity(intent);
    }

    public void p2click(View view){ //altered code from "Building Your First App" tutorial https://developer.android.com/training/basics/firstapp/starting-activity.html
        Intent intent = new Intent(this, GameshowresultsActivity.class);
        twoplayerclass.setp2Score(); //player 2 gets a point

        intent.putExtra("winnername","Player 2");
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

    private void saveInFile() { //code from CMPUT301 lab, University of Alberta, 2015-09-30
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


}
