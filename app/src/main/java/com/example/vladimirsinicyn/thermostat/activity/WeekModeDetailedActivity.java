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
import com.example.vladimirsinicyn.thermostat.model.ChangeType;
import com.example.vladimirsinicyn.thermostat.model.DaySchedule;
import com.example.vladimirsinicyn.thermostat.model.TemperatureChange;
import com.example.vladimirsinicyn.thermostat.model.Time;


public class WeekModeDetailedActivity extends Activity {

    private int index;
    private static TCConroller conroller;

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

    void addChange(View view) {
        DaySchedule schedule = conroller.getSchedule(index);

//        ChangeType type = ChangeType.DAY;
//        Time time = new Time(0);
//        TemperatureChange change = new TemperatureChange(type, time);
//
//        try {
//            schedule.addChange(change);
//        } catch (Exception ex) {
//            // show message
//        }
    }

}
