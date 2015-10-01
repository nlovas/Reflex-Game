package com.example.nlovas.nlovas_reflex;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
     */
    private TwoPlayerClass receivedtwoplayerclass; // = new TwoPlayerClass(); //does this need to change?

    private static final String FILENAME = "file.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameshowstats);

                loadFromFile2p();

        TextView p1of2out = (TextView)findViewById(R.id.p1of2outtextView);
        TextView p2of2out = (TextView)findViewById(R.id.p2of2outtextView);
        p1of2out.setText(receivedtwoplayerclass.getp1Score() + "");
        p2of2out.setText(receivedtwoplayerclass.getp2Score() + "");

        saveInFile();

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
        //loads the twoplayerclass status (object) from file.sav in the phone
        /*
        if theres time: 1-make this more generic so all gameshow classes can use it
                        2-make it a class of its own
         */

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            //https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-23
            Type arraylistType = new TypeToken<TwoPlayerClass>() {}.getType();
            receivedtwoplayerclass=gson.fromJson(in,arraylistType);


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            receivedtwoplayerclass= new TwoPlayerClass();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    private void saveInFile() { //code from CMPUT301 lab, University of Alberta, 2015-09-30
        //saves the twoplayerclass status (object) into file.sav in the phone
        //saves both players at once
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);
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
}
