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
import com.example.vladimirsinicyn.thermostat.ThermostatApp;


public class WeekModeFullActivity extends Activity {

    private static TCConroller conroller;

    private TextView nightTemp;
    private TextView dayTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.week_mode_full);

        setTitle("Thermostat");

        ThermostatApp app = ((ThermostatApp)getApplication());
        app.initContorller();
        conroller = app.getConroller();

        nightTemp = (TextView) findViewById(R.id.night_degree_textView);
        dayTemp = (TextView) findViewById(R.id.day_degree_textView);

        nightTemp.setText(conroller.getNightTemperature().toString() + "°C");
        dayTemp.setText(conroller.getDayTemperature().toString() + "°C");
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

    // ================ inc/decrement of day/night temp of WEEK SCHEDULE ================
    public void incNightTemp(View view) {
        conroller.incrementNightTemperature();
        nightTemp.setText(conroller.getNightTemperature().toString() + "°C");
    }

    public void decNightTemp(View view) {
        conroller.decrementNightTemperature();
        nightTemp.setText(conroller.getNightTemperature().toString() + "°C");
    }

    public void incDayTemp(View view) {
        conroller.incrementDayTemperature();
        dayTemp.setText(conroller.getDayTemperature().toString() + "°C");
    }

    public void decDayTemp(View view) {
        conroller.decrementDayTemperature();
        dayTemp.setText(conroller.getDayTemperature().toString() + "°C");
    }
    // ================ END inc/decrement of day/night temp of WEEK SCHEDULE ================

    // ================ go to day schedules ================

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

    // ================ END go to day schedules ================
}
