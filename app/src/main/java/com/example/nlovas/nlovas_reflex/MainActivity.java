package com.example.nlovas.nlovas_reflex;

/*
<Nlovas-reflex is a reflex testing app for single and multiple players>
        Copyright (C) <2015>  <Nicole Lovas, nlovas@ualberta.ca>

        This program is free software: you can redistribute it and/or modify
        it under the terms of the GNU General Public License as published by
        the Free Software Foundation, either version 3 of the License, or
        (at your option) any later version.

        This program is distributed in the hope that it will be useful,
        but WITHOUT ANY WARRANTY; without even the implied warranty of
        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
        GNU General Public License for more details.

        You should have received a copy of the GNU General Public License
        along with this program.  If not, see <http://www.gnu.org/licenses/>.
---------------------------------------------------------------------------------

Portions of this page are modifications based on work created
and shared by the Android Open Source Project and used according to terms described in the Creative Commons 2.5 Attribution License.
"Building Your First App" https://developer.android.com/training/basics/firstapp/starting-activity.html, 2015-09-24



*/


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
/*
The first activity, shows the different play options and
the statistics option.
  */



    public void showprep(View view){
        Intent intent = new Intent(this, PrepActivity.class);
        startActivity(intent);
    }

    public void statselect(View view){
        Intent intent = new Intent(this, StatselectActivity.class);
        startActivity(intent);
    }

    public void gsmode(View view){
        Intent intent = new Intent(this, GameshowActivity.class);
        // Toast.makeText(getApplicationContext(), "Wow!!!.",
        //       Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
