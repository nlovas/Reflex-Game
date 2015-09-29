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
    public TimeClass time = new TimeClass(); //for getting latency
    private boolean ispressed=false; //prevents people from pressing again and again without going back

    public void setTimerstarted() {
        timerstarted = !timerstarted;
    } //toggles timerstarted to true/false


    public void clickedred(View view) { //http://androidbite.blogspot.ca/2012/11/android-count-down-timer-example.html , 2015-09-27
        if ((timerstarted == true)&&(ispressed==false)) { //if the timer is still counting down, then your click is too early
            countdowntimer.cancel();
            timerstarted = false;
            Intent intent = new Intent(this, ToosoonActivity.class);
            startActivity(intent);
        } else if ((timerstarted == false)&&(ispressed==false)) { //if the timer is done, continue to the next screen to play
            time.endCapture();
            time.setTime();

            readyb.setText("Your time was: " + (int) time.getTime() + "ms"); //problem solved with http://stackoverflow.com/questions/17958887/make-a-button-change-value-of-a-textview 2015-09-28
            ispressed = true;
                }
            else{
                Intent intent = new Intent(this, PrepActivity.class); //if they click again after seeing their time theyre taken back to the prepare screen
                 startActivity(intent);
            }



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.readybutton);

        readyb = (Button) this.findViewById(R.id.readybutton);
        //readyb.setOnClickListener((View.OnClickListener) this);


        countdowntimer = new Cdowntimer((long) limit, 100); //http://developer.android.com/reference/android/os/CountDownTimer.html  2015-09-27
        //idea suggested by Linda Zhang, Second resource shared by Jillian Lovas
        //also http://androidbite.blogspot.ca/2012/11/android-count-down-timer-example.html 2015-09-27
        countdowntimer.start();
        timerstarted = true;

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
            readyb.setText("Go!"); //Prof Scott Vanselow https://www.youtube.com/watch?v=OWLOMCvtSC8 2015-09-28
            time.startCapture();
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

    }
}