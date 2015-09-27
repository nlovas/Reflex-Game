package com.example.nlovas.nlovas_reflex;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class TwoplayerActivity extends ActionBarActivity {



    /*
    Shows 2 buttons for player 1 and player 2 to click
     */

    private TwoPlayerClass twopgame = new TwoPlayerClass(); //p1 and p2's sum are both 0


    public void p1click(View view){ //altered code from "Building Your First App" tutorial https://developer.android.com/training/basics/firstapp/starting-activity.html
        Intent intent = new Intent(this, GameshowresultsActivity.class);
        // Toast.makeText(getApplicationContext(), "Wow!!!.",  (testing code)
        //       Toast.LENGTH_SHORT).show();
        twopgame.setP1sum(); //player 1 gets a point

        intent.putExtra("winnername","Player 1");
        startActivity(intent);
    }

    public void p2click(View view){ //altered code from "Building Your First App" tutorial https://developer.android.com/training/basics/firstapp/starting-activity.html
        Intent intent = new Intent(this, GameshowresultsActivity.class);
        // Toast.makeText(getApplicationContext(), "Wow!!!.",  (testing code)
        //       Toast.LENGTH_SHORT).show();
        twopgame.setP2sum(); //player 2 gets a point

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
}
