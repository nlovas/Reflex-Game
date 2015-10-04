package com.example.nlovas.nlovas_reflex;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class GameshowActivity extends ActionBarActivity {

    /*
    Gives the options for the number of players

    Portions of this page are modifications based on work created
and shared by the Android Open Source Project and used according to terms described in the Creative Commons 2.5 Attribution License.
"Building Your First App" https://developer.android.com/training/basics/firstapp/starting-activity.html, 2015-09-24
     */

    public void twoplayersselected(View view){ //"Building Your First App", Android 2015-09-24
        Intent intent = new Intent(this, TwoplayerActivity.class);
        startActivity(intent);
    }

    public void threeplayersselected(View view){ //"Building Your First App", Android 2015-09-24
        Intent intent = new Intent(this, ThreeplayerActivity.class);
        startActivity(intent);
    }

    public void fourplayersselected(View view){ //"Building Your First App", Android 2015-09-24
        Intent intent = new Intent(this, FourplayerActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectnumplayers);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gameshow, menu);
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
}
