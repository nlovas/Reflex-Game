package com.example.nlovas.nlovas_reflex;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class StatselectActivity extends ActionBarActivity {

    /*
    User can choose to see their reaction stats, gameshow stats, clear their stats, or
    email their stats

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
    private static final String FILENAMEreact = "react.sav";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statselect);
    }

    public void reactionstatsbtn(View view){ //"Building Your First App", Android 2015-09-24
        Intent intent = new Intent(this, ReactionStatsActivity.class);
        startActivity(intent);
    }

    public void gameshowsts(View view){ //"Building Your First App", Android 2015-09-24
        Intent intent = new Intent(this, GameshowstatsActivity.class);
        startActivity(intent);
    }

    public void clearstatsbtn(View view){ //"Building Your First App", Android 2015-09-24

         TwoPlayerClass cleartwoplayerclass = new TwoPlayerClass();
        saveInFile(cleartwoplayerclass,FILENAME2p);
         ThreePlayerClass clearthreeplayerclass = new ThreePlayerClass();
        saveInFile(clearthreeplayerclass,FILENAME3p);
         FourPlayerClass clearfourplayerclass = new FourPlayerClass();
        saveInFile(clearfourplayerclass,FILENAME4p);
        TimesListClass cleartimes = new TimesListClass();
        saveInFile(cleartimes, FILENAMEreact);


        Toast.makeText(getApplicationContext(), "Stats Cleared",
                 Toast.LENGTH_SHORT).show();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_statselect, menu);
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

    private void saveInFile(Object object, String filename) { //code from CMPUT301 lab, University of Alberta, 2015-09-30
        
        try {
            FileOutputStream fos = openFileOutput(filename, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            //https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-30
            gson.toJson(object, out);
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
