package com.example.vladimirsinicyn.thermostat.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vladimirsinicyn.thermostat.R;
import com.example.vladimirsinicyn.thermostat.TCConroller;
import com.example.vladimirsinicyn.thermostat.ThermostatApp;

import java.io.File;


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

    private String m_Text = "";

    public void getName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title");

// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_load) {

//            // load schedule
//            // TODO: get name of the file
//            getName();
//            //System.out.println(m_Text);
//
//            try {
//                conroller.loadSchedule(m_Text);
//            } catch (Exception ex) {
//                // TODO: handle
//            }

            String name = "save.out";
            File directory = Environment.getExternalStorageDirectory();
            try {
                conroller.loadSchedule(directory + File.separator + name);
            } catch (Exception ex) {
                // TODO: handle
                Log.i("CurrentWeatherActivity - load", ex.getMessage());
            }


            return true;
        }

        if (id == R.id.action_save) {

//            // save schedule
//            // TODO: get name of the file
//            getName();
//            //System.out.println(m_Text);
//
//            try {
//                conroller.saveSchedule(m_Text);
//            } catch (Exception ex) {
//                // TODO: handle
//            }

            String name = "save";
            File directory = Environment.getExternalStorageDirectory();

            try {
                conroller.saveSchedule(directory + File.separator + name);
            } catch (Exception ex) {
                // TODO: handle
                Log.i("CurrentWeatherActivity - save", ex.getMessage());
            }

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

    // ================ go to each day schedules ================

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
