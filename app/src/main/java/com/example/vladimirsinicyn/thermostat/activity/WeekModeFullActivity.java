package com.example.vladimirsinicyn.thermostat.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.vladimirsinicyn.thermostat.R;
import com.example.vladimirsinicyn.thermostat.TCConroller;


public class WeekModeFullActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.week_mode_full);

        setTitle("Thermostat");
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
        if (id == R.id.action_load) {
            return true;
        }

        if (id == R.id.action_save) {
            return true;
        }

        if (id == R.id.day_night) {

            Intent intent = new Intent(WeekModeFullActivity.this, CurrentWeatherActivity.class);
            startActivity(intent);

            return true;
        }

        if (id == R.id.calendar) {

            // stay on this screen

            return true;
        }

        if (id == R.id.hot) {

            Intent intent = new Intent(WeekModeFullActivity.this, VacationWeatherActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void incNightTemp(View view) {
        final TextView nightTemp = (TextView) findViewById(R.id.night_degree_textView);
        TCConroller conroller = CurrentWeatherActivity.conroller;
        conroller.incrementNightTemperature();
        nightTemp.setText(conroller.getNightTemperature().toString() + "&#x2103;");
    }

    public void setSundaySchedule(View view) {
        Intent intent = new Intent(WeekModeFullActivity.this, WeekModeDetailedActivity.class);
        intent.putExtra("dayOfweek", "Sunday");
        startActivity(intent);
    }

    public void setMondaySchedule(View view) {
        Intent intent = new Intent(WeekModeFullActivity.this, WeekModeDetailedActivity.class);
        intent.putExtra("dayOfweek", "Monday");
        startActivity(intent);
    }

    public void setTuesdaySchedule(View view) {
        Intent intent = new Intent(WeekModeFullActivity.this, WeekModeDetailedActivity.class);
        intent.putExtra("dayOfweek", "Tuesday");
        startActivity(intent);
    }

    public void setWednesdaySchedule(View view) {
        Intent intent = new Intent(WeekModeFullActivity.this, WeekModeDetailedActivity.class);
        intent.putExtra("dayOfweek", "Wednesday");
        startActivity(intent);
    }

    public void setThursdaySchedule(View view) {
        Intent intent = new Intent(WeekModeFullActivity.this, WeekModeDetailedActivity.class);
        intent.putExtra("dayOfweek", "Thursday");
        startActivity(intent);
    }

    public void setFridaySchedule(View view) {
        Intent intent = new Intent(WeekModeFullActivity.this, WeekModeDetailedActivity.class);
        intent.putExtra("dayOfweek", "Friday");
        startActivity(intent);
    }

    public void setSaturdaySchedule(View view) {
        Intent intent = new Intent(WeekModeFullActivity.this, WeekModeDetailedActivity.class);
        intent.putExtra("dayOfweek", "Saturday");
        startActivity(intent);
    }
}
