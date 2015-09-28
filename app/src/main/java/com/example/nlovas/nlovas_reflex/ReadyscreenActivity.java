package com.example.nlovas.nlovas_reflex;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ReadyscreenActivity extends ActionBarActivity {

    private double limit = (double) (Math.random() * 1991) + 10; //from http://stackoverflow.com/questions/7961788/math-random-explained 2015-09-27
    //private double starttime = (double)System.currentTimeMillis(); //grabs current time
    //learned from http://www.tutorialspoint.com/java/lang/system_currenttimemillis.htm , 2015-09-27

    private CountDownTimer countdowntimer;
    private boolean timerstarted;
    private Button readyb;

    public void setTimerstarted() {
        timerstarted = !timerstarted;
    }

    //currently goes back to itself when clicked
    public void clickedred(View view) { //http://androidbite.blogspot.ca/2012/11/android-count-down-timer-example.html , 2015-09-27
        if (timerstarted == true) {
            countdowntimer.cancel();
            timerstarted = false;
            Intent intent = new Intent(this, ToosoonActivity.class);
            startActivity(intent);
        } else {

        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.readybutton);

        readyb = (Button) this.findViewById(R.id.readybutton);
        readyb.setOnClickListener((View.OnClickListener) this);
        //timerstarted = false;

        countdowntimer = new Cdowntimer((long) limit, 100); //http://developer.android.com/reference/android/os/CountDownTimer.html  2015-09-27
        //idea suggested by Linda Zhang, Second resource shared by Jillian Lovas
        //also http://androidbite.blogspot.ca/2012/11/android-count-down-timer-example.html 2015-09-27
        countdowntimer.start();
        timerstarted = true;



/*
        Button readybutton = (Button) findViewById(R.id.readybutton); //from in-class lab University of Alberta, 2015-09-27

        while((System.currentTimeMillis() - starttime)<limit){

            readybutton.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {   //from in-class lab University of Alberta, 2015-09-27
                    // setResult(RESULT_OK);
                    Intent intent = new Intent(this, ClickthebuttonActivity.class);
                    startActivity(intent);
                }
            });

        }
        Toast.makeText(getApplicationContext(), "Wow!!!.",
                Toast.LENGTH_SHORT).show();
*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_prepscreen, menu);
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
//} everything is currently within the activity

    class Cdowntimer extends CountDownTimer { //http://androidbite.blogspot.ca/2012/11/android-count-down-timer-example.html 2015-09-27

        public Cdowntimer(long startTime, long interval) {
            super(startTime, interval);

        }

        @Override
        public void onFinish() {
            setTimerstarted();
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

    }
}