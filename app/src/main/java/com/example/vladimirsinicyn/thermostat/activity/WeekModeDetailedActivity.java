package com.example.vladimirsinicyn.thermostat.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.vladimirsinicyn.thermostat.R;
import com.example.vladimirsinicyn.thermostat.TCConroller;
import com.example.vladimirsinicyn.thermostat.ThermostatApp;
import com.example.vladimirsinicyn.thermostat.model.DaySchedule;
import com.example.vladimirsinicyn.thermostat.model.LightCondition;
import com.example.vladimirsinicyn.thermostat.model.Temperature;
import com.example.vladimirsinicyn.thermostat.model.TemperatureChange;
import com.example.vladimirsinicyn.thermostat.model.Time;

import java.util.ArrayList;


public class WeekModeDetailedActivity extends Activity {

    private int index;
    private static TCConroller conroller;
    private DaySchedule schedule;
    private int numOfChanges = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.week_mode_detailed);

        setTitle("Thermostat");

        ThermostatApp app = ((ThermostatApp)getApplication());
        app.initContorller();
        conroller = app.getConroller();

        String dayOfWeek = getIntent().getExtras().getString("dayOfweek");
        switch (dayOfWeek) {
            case "Sunday":
                index = 0;
                break;
            case "Monday":
                index = 1;
                break;
            case "Tuesday":
                index = 2;
                break;
            case "Wednesday":
                index = 3;
                break;
            case "Thursday":
                index = 4;
                break;
            case "Friday":
                index = 5;
                break;
            case "Saturday":
                index = 6;
                break;
        }

        final TextView weekday = (TextView) findViewById(R.id.weekday);
        weekday.setText(dayOfWeek);

        schedule = conroller.getSchedule(index);
        ArrayList<TemperatureChange> changes = schedule.getChanges();

//        for (int i = 0; i < DaySchedule.MAX_TODAY + DaySchedule.MAX_TONIGHT; i++) {
//            if (changes.get(i) == null) {
//
//            }
//        }

        int i = 0;

        if (changes.size() < i + 1) {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.first_layout);
            layout.setVisibility(View.GONE);
        } else {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.first_layout);
            TextView time = (TextView) findViewById(R.id.time_1);
            TemperatureChange change = changes.get(i);
            time.setText(change.getTime().toString());
            numOfChanges++;
            layout.setVisibility(View.VISIBLE);
        }
        i++;

        if (changes.size() < i + 1) {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.second_layout);
            layout.setVisibility(View.GONE);
        } else {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.second_layout);
            TextView time = (TextView) findViewById(R.id.time_2);
            TemperatureChange change = changes.get(i);
            time.setText(change.getTime().toString());
            numOfChanges++;
            layout.setVisibility(View.VISIBLE);
        }
        i++;

        if (changes.size() < i + 1) {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.third_layout);
            layout.setVisibility(View.GONE);
        } else {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.third_layout);
            TextView time = (TextView) findViewById(R.id.time_3);
            TemperatureChange change = changes.get(i);
            time.setText(change.getTime().toString());
            numOfChanges++;
            layout.setVisibility(View.VISIBLE);
        }
        i++;

        if (changes.size() < i + 1) {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.fourth_layout);
            layout.setVisibility(View.GONE);
        } else {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.fourth_layout);
            TextView time = (TextView) findViewById(R.id.time_4);
            TemperatureChange change = changes.get(i);
            time.setText(change.getTime().toString());
            numOfChanges++;
            layout.setVisibility(View.VISIBLE);
        }
        i++;

        if (changes.size() < i + 1) {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.fifth_layout);
            layout.setVisibility(View.GONE);
        } else {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.fifth_layout);
            TextView time = (TextView) findViewById(R.id.time_5);
            TemperatureChange change = changes.get(i);
            time.setText(change.getTime().toString());
            numOfChanges++;
            layout.setVisibility(View.VISIBLE);
        }
        i++;

        if (changes.size() < i + 1) {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.sixth_layout);
            layout.setVisibility(View.GONE);
        } else {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.sixth_layout);
            TextView time = (TextView) findViewById(R.id.time_6);
            TemperatureChange change = changes.get(i);
            time.setText(change.getTime().toString());
            numOfChanges++;
            layout.setVisibility(View.VISIBLE);
        }
        i++;

        if (changes.size() < i + 1) {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.seventh_layout);
            layout.setVisibility(View.GONE);
        } else {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.seventh_layout);
            TextView time = (TextView) findViewById(R.id.time_7);
            TemperatureChange change = changes.get(i);
            time.setText(change.getTime().toString());
            numOfChanges++;
            layout.setVisibility(View.VISIBLE);
        }
        i++;

        if (changes.size() < i + 1) {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.eighth_layout);
            layout.setVisibility(View.GONE);
        } else {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.eighth_layout);
            TextView time = (TextView) findViewById(R.id.time_8);
            TemperatureChange change = changes.get(i);
            time.setText(change.getTime().toString());
            numOfChanges++;
            layout.setVisibility(View.VISIBLE);
        }
        i++;

        if (changes.size() < i + 1) {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.nineth_layout);
            layout.setVisibility(View.GONE);
        } else {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.nineth_layout);
            TextView time = (TextView) findViewById(R.id.time_9);
            TemperatureChange change = changes.get(i);
            time.setText(change.getTime().toString());
            numOfChanges++;
            layout.setVisibility(View.VISIBLE);
        }
        i++;

        if (changes.size() < i + 1) {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.tenth_layout);
            layout.setVisibility(View.GONE);
        } else {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.tenth_layout);
            TextView time = (TextView) findViewById(R.id.time_10);
            TemperatureChange change = changes.get(i);
            time.setText(change.getTime().toString());
            numOfChanges++;
            layout.setVisibility(View.VISIBLE);
        }
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

            Intent intent = new Intent(WeekModeDetailedActivity.this, CurrentWeatherActivity.class);
            startActivity(intent);

            return true;
        }

        if (id == R.id.calendar) {

            Intent intent = new Intent(WeekModeDetailedActivity.this, WeekModeFullActivity.class);
            startActivity(intent);

            return true;
        }

        if (id == R.id.hot) {

            Intent intent = new Intent(WeekModeDetailedActivity.this, VacationWeatherActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addChange(View view) {

        TemperatureChange latestChange = schedule.getLatestChange();

        Time newTime = new Time(latestChange.getTime().toMinutes());
        newTime.incrementTime();

        LightCondition newLightCondition;

        if (latestChange.getTargetCondition() == LightCondition.DAY) {
            newLightCondition = LightCondition.NIGHT;
        } else {
            newLightCondition = LightCondition.DAY;
        }

        TemperatureChange newChange = new TemperatureChange(newLightCondition, newTime);

        try {
            schedule.addChange(newChange);
        } catch (Exception ex) {
            // TODO: handle exception (some message for user like 'no more changes allowed')
            return;
        }


        // TODO: show new change on screen
        RelativeLayout newLayout = getNewLayout();
        newLayout.setVisibility(View.VISIBLE);

        // old code
//        LightCondition targetLightCondition = LightCondition.DAY;
//        Time time = new Time(0);
//        TemperatureChange change = new TemperatureChange(targetLightCondition, time);
//
//        try {
//            schedule.addChange(change);
//        } catch (Exception ex) {
//            // show message
//        }
    }

    /**
     * pre: do not call it when all changes were initialized
     *  for example when number of changes == 10
     *
     * @return
     */
    private RelativeLayout getNewLayout() {

        RelativeLayout newLayout = null;

        switch (numOfChanges) {
            case 0:
                newLayout = (RelativeLayout) findViewById(R.id.first_layout);
                break;
            case 1:
                newLayout = (RelativeLayout) findViewById(R.id.second_layout);
                break;
            case 2:
                newLayout = (RelativeLayout) findViewById(R.id.third_layout);
                break;
            case 3:
                newLayout = (RelativeLayout) findViewById(R.id.fourth_layout);
                break;
            case 4:
                newLayout = (RelativeLayout) findViewById(R.id.fifth_layout);
                break;
            case 5:
                newLayout = (RelativeLayout) findViewById(R.id.sixth_layout);
                break;
            case 6:
                newLayout = (RelativeLayout) findViewById(R.id.seventh_layout);
                break;
            case 7:
                newLayout = (RelativeLayout) findViewById(R.id.eighth_layout);
                break;
            case 8:
                newLayout = (RelativeLayout) findViewById(R.id.nineth_layout);
                break;
            case 9:
                newLayout = (RelativeLayout) findViewById(R.id.tenth_layout);
                break;
            default:
                return null;
        }

        return newLayout;
    }
}
