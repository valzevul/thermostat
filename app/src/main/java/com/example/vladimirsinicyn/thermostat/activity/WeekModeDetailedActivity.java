package com.example.vladimirsinicyn.thermostat.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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

//        ArrayList<TemperatureChange> changes = schedule.getChanges();
//        for (int i = 0; i < DaySchedule.MAX_TODAY + DaySchedule.MAX_TONIGHT; i++) {
//            if (schedule.getChange(i) == null) {
//
//            }
//        }

        int i = 0;

        if (schedule.getNumberOfChanges() < i + 1) {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.first_layout);
            layout.setVisibility(View.INVISIBLE);
        } else {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.first_layout);
            TextView time = (TextView) findViewById(R.id.time_1);
            TemperatureChange change = schedule.getChange(i);
            time.setText(change.getTime().toString());
            layout.setVisibility(View.VISIBLE);
        }
        i++;

        if (schedule.getNumberOfChanges() < i + 1) {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.second_layout);
            layout.setVisibility(View.INVISIBLE);
        } else {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.second_layout);
            TextView time = (TextView) findViewById(R.id.time_2);
            TemperatureChange change = schedule.getChange(i);
            time.setText(change.getTime().toString());
            layout.setVisibility(View.VISIBLE);
        }
        i++;

        if (schedule.getNumberOfChanges() < i + 1) {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.third_layout);
            layout.setVisibility(View.INVISIBLE);
        } else {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.third_layout);
            TextView time = (TextView) findViewById(R.id.time_3);
            TemperatureChange change = schedule.getChange(i);
            time.setText(change.getTime().toString());
            layout.setVisibility(View.VISIBLE);
        }
        i++;

        if (schedule.getNumberOfChanges() < i + 1) {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.fourth_layout);
            layout.setVisibility(View.INVISIBLE);
        } else {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.fourth_layout);
            TextView time = (TextView) findViewById(R.id.time_4);
            TemperatureChange change = schedule.getChange(i);
            time.setText(change.getTime().toString());
            layout.setVisibility(View.VISIBLE);
        }
        i++;

        if (schedule.getNumberOfChanges() < i + 1) {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.fifth_layout);
            layout.setVisibility(View.INVISIBLE);
        } else {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.fifth_layout);
            TextView time = (TextView) findViewById(R.id.time_5);
            TemperatureChange change = schedule.getChange(i);
            time.setText(change.getTime().toString());
            layout.setVisibility(View.VISIBLE);
        }
        i++;

        if (schedule.getNumberOfChanges() < i + 1) {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.sixth_layout);
            layout.setVisibility(View.INVISIBLE);
        } else {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.sixth_layout);
            TextView time = (TextView) findViewById(R.id.time_6);
            TemperatureChange change = schedule.getChange(i);
            time.setText(change.getTime().toString());
            layout.setVisibility(View.VISIBLE);
        }
        i++;

        if (schedule.getNumberOfChanges() < i + 1) {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.seventh_layout);
            layout.setVisibility(View.INVISIBLE);
        } else {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.seventh_layout);
            TextView time = (TextView) findViewById(R.id.time_7);
            TemperatureChange change = schedule.getChange(i);
            time.setText(change.getTime().toString());
            layout.setVisibility(View.VISIBLE);
        }
        i++;

        if (schedule.getNumberOfChanges() < i + 1) {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.eighth_layout);
            layout.setVisibility(View.INVISIBLE);
        } else {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.eighth_layout);
            TextView time = (TextView) findViewById(R.id.time_8);
            TemperatureChange change = schedule.getChange(i);
            time.setText(change.getTime().toString());
            layout.setVisibility(View.VISIBLE);
        }
        i++;

        if (schedule.getNumberOfChanges() < i + 1) {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.nineth_layout);
            layout.setVisibility(View.INVISIBLE);
        } else {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.nineth_layout);
            TextView time = (TextView) findViewById(R.id.time_9);
            TemperatureChange change = schedule.getChange(i);
            time.setText(change.getTime().toString());
            layout.setVisibility(View.VISIBLE);
        }
        i++;

        if (schedule.getNumberOfChanges() < i + 1) {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.tenth_layout);
            layout.setVisibility(View.INVISIBLE);
        } else {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.tenth_layout);
            TextView time = (TextView) findViewById(R.id.time_10);
            TemperatureChange change = schedule.getChange(i);
            time.setText(change.getTime().toString());
            layout.setVisibility(View.VISIBLE);
        }

        int numOfChanges = schedule.getNumberOfChanges();
        ImageView view;
        switch (numOfChanges) {
            case 1:
                view = (ImageView) findViewById(R.id.reverse_imageView);
                view.setVisibility(View.VISIBLE);
                break;
            case 2:
                view = (ImageView) findViewById(R.id.reverse2_imageView);
                view.setVisibility(View.VISIBLE);
                break;
            case 3:
                view = (ImageView) findViewById(R.id.reverse3_imageView);
                view.setVisibility(View.VISIBLE);
                break;
            case 4:
                view = (ImageView) findViewById(R.id.reverse4_imageView);
                view.setVisibility(View.VISIBLE);
                break;
            case 5:
                view = (ImageView) findViewById(R.id.reverse5_imageView);
                view.setVisibility(View.VISIBLE);
                break;
            case 6:
                view = (ImageView) findViewById(R.id.reverse6_imageView);
                view.setVisibility(View.VISIBLE);
                break;
            case 7:
                view = (ImageView) findViewById(R.id.reverse7_imageView);
                view.setVisibility(View.VISIBLE);
                break;
            case 8:
                view = (ImageView) findViewById(R.id.reverse8_imageView);
                view.setVisibility(View.VISIBLE);
                break;
            case 9:
                view = (ImageView) findViewById(R.id.reverse9_imageView);
                view.setVisibility(View.VISIBLE);
                break;
            case 10:
                view = (ImageView) findViewById(R.id.reverse10_imageView);
                view.setVisibility(View.VISIBLE);
                break;
        }
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
        Log.i("addition of temp change", newTime.toString()); // TODO: delete this debug output string
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
        RelativeLayout newLayout = getNewLayout(newTime);
        newLayout.setVisibility(View.VISIBLE);

        int numOfChanges = schedule.getNumberOfChanges(); // after adding
        ImageView deleteView;
        ImageView prevDeleteView;
        switch (numOfChanges) {
            case 1:
                deleteView = (ImageView) findViewById(R.id.reverse_imageView);
                deleteView.setVisibility(View.VISIBLE);
                break;
            case 2:
                prevDeleteView = (ImageView) findViewById(R.id.reverse_imageView);
                prevDeleteView.setVisibility(View.INVISIBLE);
                deleteView = (ImageView) findViewById(R.id.reverse2_imageView);
                deleteView.setVisibility(View.VISIBLE);
                break;
            case 3:
                prevDeleteView = (ImageView) findViewById(R.id.reverse2_imageView);
                prevDeleteView.setVisibility(View.INVISIBLE);
                deleteView = (ImageView) findViewById(R.id.reverse3_imageView);
                deleteView.setVisibility(View.VISIBLE);
                break;
            case 4:
                prevDeleteView = (ImageView) findViewById(R.id.reverse3_imageView);
                prevDeleteView.setVisibility(View.INVISIBLE);
                deleteView = (ImageView) findViewById(R.id.reverse4_imageView);
                deleteView.setVisibility(View.VISIBLE);
                break;
            case 5:
                prevDeleteView = (ImageView) findViewById(R.id.reverse4_imageView);
                prevDeleteView.setVisibility(View.INVISIBLE);
                deleteView = (ImageView) findViewById(R.id.reverse5_imageView);
                deleteView.setVisibility(View.VISIBLE);
                break;
            case 6:
                prevDeleteView = (ImageView) findViewById(R.id.reverse5_imageView);
                prevDeleteView.setVisibility(View.INVISIBLE);
                deleteView = (ImageView) findViewById(R.id.reverse6_imageView);
                deleteView.setVisibility(View.VISIBLE);
                break;
            case 7:
                prevDeleteView = (ImageView) findViewById(R.id.reverse6_imageView);
                prevDeleteView.setVisibility(View.INVISIBLE);
                deleteView = (ImageView) findViewById(R.id.reverse7_imageView);
                deleteView.setVisibility(View.VISIBLE);
                break;
            case 8:
                prevDeleteView = (ImageView) findViewById(R.id.reverse7_imageView);
                prevDeleteView.setVisibility(View.INVISIBLE);
                deleteView = (ImageView) findViewById(R.id.reverse8_imageView);
                deleteView.setVisibility(View.VISIBLE);
                break;
            case 9:
                prevDeleteView = (ImageView) findViewById(R.id.reverse8_imageView);
                prevDeleteView.setVisibility(View.INVISIBLE);
                deleteView = (ImageView) findViewById(R.id.reverse9_imageView);
                deleteView.setVisibility(View.VISIBLE);
                break;
            case 10:
                prevDeleteView = (ImageView) findViewById(R.id.reverse9_imageView);
                prevDeleteView.setVisibility(View.INVISIBLE);
                deleteView = (ImageView) findViewById(R.id.reverse10_imageView);
                deleteView.setVisibility(View.VISIBLE);
                break;
        }
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
    private RelativeLayout getNewLayout(Time newTime) {

        RelativeLayout newLayout = null;
        TextView time;
        switch (schedule.getNumberOfChanges() - 1) {
            case 0:
                newLayout = (RelativeLayout) findViewById(R.id.first_layout);
                time = (TextView) findViewById(R.id.time_1);
                time.setText(newTime.toString());
                break;
            case 1:
                newLayout = (RelativeLayout) findViewById(R.id.second_layout);
                time = (TextView) findViewById(R.id.time_2);
                time.setText(newTime.toString());
                break;
            case 2:
                newLayout = (RelativeLayout) findViewById(R.id.third_layout);
                time = (TextView) findViewById(R.id.time_3);
                time.setText(newTime.toString());
                break;
            case 3:
                newLayout = (RelativeLayout) findViewById(R.id.fourth_layout);
                time = (TextView) findViewById(R.id.time_4);
                time.setText(newTime.toString());
                break;
            case 4:
                newLayout = (RelativeLayout) findViewById(R.id.fifth_layout);
                time = (TextView) findViewById(R.id.time_5);
                time.setText(newTime.toString());
                break;
            case 5:
                newLayout = (RelativeLayout) findViewById(R.id.sixth_layout);
                time = (TextView) findViewById(R.id.time_6);
                time.setText(newTime.toString());
                break;
            case 6:
                newLayout = (RelativeLayout) findViewById(R.id.seventh_layout);
                time = (TextView) findViewById(R.id.time_7);
                time.setText(newTime.toString());
                break;
            case 7:
                newLayout = (RelativeLayout) findViewById(R.id.eighth_layout);
                time = (TextView) findViewById(R.id.time_8);
                time.setText(newTime.toString());
                break;
            case 8:
                newLayout = (RelativeLayout) findViewById(R.id.nineth_layout);
                time = (TextView) findViewById(R.id.time_9);
                time.setText(newTime.toString());
                break;
            case 9:
                newLayout = (RelativeLayout) findViewById(R.id.tenth_layout);
                time = (TextView) findViewById(R.id.time_10);
                time.setText(newTime.toString());
                break;
            default:
                return null;
        }

        return newLayout;
    }

// ========= Handlers of DOWN ARROWS =========
    /**
     * Handle decrease of time of the first change
     *
     * if this method was called it means that
     * the first change is initialised and is visible
     *
     * @param view
     */
    public void handleDownArrow1(View view) {
        // check the allowed interval of time in which we can change it
        // check lower bound - find previous change
        // check upper bound - find next change
//        try{
        int changeIndex = 1 - 1;
        TemperatureChange change = schedule.getChange(changeIndex);
        Time changeTime = change.getTime();

        TemperatureChange prevChange = schedule.findClosestLess(change.getTime());
        Time lowerBound = prevChange.getTime();

        if (changeTime.toMinutes() <= lowerBound.toMinutes() + 1) {
            // TODO: show some message for user like 'no more can't go lower than previous change'
            return;
        }

        // change state (time) of the chosen TempChange
        try {
            change.getTime().decrementTime();
        } catch (Exception ex) {
            // TODO: handle exception (some message for user like ' can't go lower than 00:00')
            // actually we can not ever go here
            return;
        }

        // show the new time of the change on the screen
        TextView time = (TextView) findViewById(R.id.time_1);
        time.setText(change.getTime().toString());
//        } catch (Exception ex) {
//
//        }
    }

    /**
     * Handle decrease of time of the second change
     *
     * if this method was called it means that
     * the second change is initialised and is visible
     *
     * @param view
     */
    public void handleDownArrow2(View view) {
        // check the allowed interval of time in which we can change it
        // check lower bound - find previous change
        // check upper bound - find next change
//        try{
        int changeIndex = 2 - 1;
        TemperatureChange change = schedule.getChange(changeIndex);
        Time changeTime = change.getTime();

        TemperatureChange prevChange = schedule.findClosestLess(change.getTime());
        Time lowerBound = prevChange.getTime();

        if (changeTime.toMinutes() <= lowerBound.toMinutes() + 1) {
            // TODO: show some message for user like 'no more can't go lower than previous change'
            return;
        }

        // change state (time) of the chosen TempChange
        try {
            change.getTime().decrementTime();
        } catch (Exception ex) {
            // TODO: handle exception (some message for user like ' can't go lower than 00:00')
            // actually we can not ever go here
            return;
        }

        // show the new time of the change on the screen
        TextView time = (TextView) findViewById(R.id.time_2);
        time.setText(change.getTime().toString());
//        } catch (Exception ex) {
//
//        }
    }

    /**
     * Handle decrease of time of the third change
     *
     * if this method was called it means that
     * the third change is initialised and is visible
     *
     * @param view
     */
    public void handleDownArrow3(View view) {
        // check the allowed interval of time in which we can change it
        // check lower bound - find previous change
        // check upper bound - find next change
//        try{
        int changeIndex = 3 - 1;
        TemperatureChange change = schedule.getChange(changeIndex);
        Time changeTime = change.getTime();

        TemperatureChange prevChange = schedule.findClosestLess(change.getTime());
        Time lowerBound = prevChange.getTime();

        if (changeTime.toMinutes() <= lowerBound.toMinutes() + 1) {
            // TODO: show some message for user like 'no more can't go lower than previous change'
            return;
        }

        // change state (time) of the chosen TempChange
        try {
            change.getTime().decrementTime();
        } catch (Exception ex) {
            // TODO: handle exception (some message for user like ' can't go lower than 00:00')
            // actually we can not ever go here
            return;
        }

        // show the new time of the change on the screen
        TextView time = (TextView) findViewById(R.id.time_3);
        time.setText(change.getTime().toString());
//        } catch (Exception ex) {
//
//        }
    }

    /**
     * Handle decrease of time of the forth change
     *
     * if this method was called it means that
     * the forth change is initialised and is visible
     *
     * @param view
     */
    public void handleDownArrow4(View view) {
        // check the allowed interval of time in which we can change it
        // check lower bound - find previous change
        // check upper bound - find next change
//        try{
        int changeIndex = 4 - 1;
        TemperatureChange change = schedule.getChange(changeIndex);
        Time changeTime = change.getTime();

        TemperatureChange prevChange = schedule.findClosestLess(change.getTime());
        Time lowerBound = prevChange.getTime();

        if (changeTime.toMinutes() <= lowerBound.toMinutes() + 1) {
            // TODO: show some message for user like 'no more can't go lower than previous change'
            return;
        }

        // change state (time) of the chosen TempChange
        try {
            change.getTime().decrementTime();
        } catch (Exception ex) {
            // TODO: handle exception (some message for user like ' can't go lower than 00:00')
            // actually we can not ever go here
            return;
        }

        // show the new time of the change on the screen
        TextView time = (TextView) findViewById(R.id.time_4);
        time.setText(change.getTime().toString());
//        } catch (Exception ex) {
//
//        }
    }

    /**
     * Handle decrease of time of the fifth change
     *
     * if this method was called it means that
     * the fifth change is initialised and is visible
     *
     * @param view
     */
    public void handleDownArrow5(View view) {
        // check the allowed interval of time in which we can change it
        // check lower bound - find previous change
        // check upper bound - find next change
//        try{
        int changeIndex = 5 - 1;
        TemperatureChange change = schedule.getChange(changeIndex);
        Time changeTime = change.getTime();

        TemperatureChange prevChange = schedule.findClosestLess(change.getTime());
        Time lowerBound = prevChange.getTime();

        if (changeTime.toMinutes() <= lowerBound.toMinutes() + 1) {
            // TODO: show some message for user like 'no more can't go lower than previous change'
            return;
        }

        // change state (time) of the chosen TempChange
        try {
            change.getTime().decrementTime();
        } catch (Exception ex) {
            // TODO: handle exception (some message for user like ' can't go lower than 00:00')
            // actually we can not ever go here
            return;
        }

        // show the new time of the change on the screen
        TextView time = (TextView) findViewById(R.id.time_5);
        time.setText(change.getTime().toString());
//        } catch (Exception ex) {
//
//        }
    }

    /**
     * Handle decrease of time of the sixth change
     *
     * if this method was called it means that
     * the sixth change is initialised and is visible
     *
     * @param view
     */
    public void handleDownArrow6(View view) {
        // check the allowed interval of time in which we can change it
        // check lower bound - find previous change
        // check upper bound - find next change
//        try{
        int changeIndex = 6 - 1;
        TemperatureChange change = schedule.getChange(changeIndex);
        Time changeTime = change.getTime();

        TemperatureChange prevChange = schedule.findClosestLess(change.getTime());
        Time lowerBound = prevChange.getTime();

        if (changeTime.toMinutes() <= lowerBound.toMinutes() + 1) {
            // TODO: show some message for user like 'no more can't go lower than previous change'
            return;
        }

        // change state (time) of the chosen TempChange
        try {
            change.getTime().decrementTime();
        } catch (Exception ex) {
            // TODO: handle exception (some message for user like ' can't go lower than 00:00')
            // actually we can not ever go here
            return;
        }

        // show the new time of the change on the screen
        TextView time = (TextView) findViewById(R.id.time_6);
        time.setText(change.getTime().toString());
//        } catch (Exception ex) {
//
//        }
    }

    /**
     * Handle decrease of time of the seventh change
     *
     * if this method was called it means that
     * the seventh change is initialised and is visible
     *
     * @param view
     */
    public void handleDownArrow7(View view) {
        // check the allowed interval of time in which we can change it
        // check lower bound - find previous change
        // check upper bound - find next change
//        try{
        int changeIndex = 7 - 1;
        TemperatureChange change = schedule.getChange(changeIndex);
        Time changeTime = change.getTime();

        TemperatureChange prevChange = schedule.findClosestLess(change.getTime());
        Time lowerBound = prevChange.getTime();

        if (changeTime.toMinutes() <= lowerBound.toMinutes() + 1) {
            // TODO: show some message for user like 'no more can't go lower than previous change'
            return;
        }

        // change state (time) of the chosen TempChange
        try {
            change.getTime().decrementTime();
        } catch (Exception ex) {
            // TODO: handle exception (some message for user like ' can't go lower than 00:00')
            // actually we can not ever go here
            return;
        }

        // show the new time of the change on the screen
        TextView time = (TextView) findViewById(R.id.time_7);
        time.setText(change.getTime().toString());
//        } catch (Exception ex) {
//
//        }
    }

    /**
     * Handle decrease of time of the eighth change
     *
     * if this method was called it means that
     * the eighth change is initialised and is visible
     *
     * @param view
     */
    public void handleDownArrow8(View view) {
        // check the allowed interval of time in which we can change it
        // check lower bound - find previous change
        // check upper bound - find next change
//        try{
        int changeIndex = 8 - 1;
        TemperatureChange change = schedule.getChange(changeIndex);
        Time changeTime = change.getTime();

        TemperatureChange prevChange = schedule.findClosestLess(change.getTime());
        Time lowerBound = prevChange.getTime();

        if (changeTime.toMinutes() <= lowerBound.toMinutes() + 1) {
            // TODO: show some message for user like 'no more can't go lower than previous change'
            return;
        }

        // change state (time) of the chosen TempChange
        try {
            change.getTime().decrementTime();
        } catch (Exception ex) {
            // TODO: handle exception (some message for user like ' can't go lower than 00:00')
            // actually we can not ever go here
            return;
        }

        // show the new time of the change on the screen
        TextView time = (TextView) findViewById(R.id.time_8);
        time.setText(change.getTime().toString());
//        } catch (Exception ex) {
//
//        }
    }

    /**
     * Handle decrease of time of the ninth change
     *
     * if this method was called it means that
     * the ninth change is initialised and is visible
     *
     * @param view
     */
    public void handleDownArrow9(View view) {
        // check the allowed interval of time in which we can change it
        // check lower bound - find previous change
        // check upper bound - find next change
//        try{
        int changeIndex = 9 - 1;
        TemperatureChange change = schedule.getChange(changeIndex);
        Time changeTime = change.getTime();

        TemperatureChange prevChange = schedule.findClosestLess(change.getTime());
        Time lowerBound = prevChange.getTime();

        if (changeTime.toMinutes() <= lowerBound.toMinutes() + 1) {
            // TODO: show some message for user like 'no more can't go lower than previous change'
            return;
        }

        // change state (time) of the chosen TempChange
        try {
            change.getTime().decrementTime();
        } catch (Exception ex) {
            // TODO: handle exception (some message for user like ' can't go lower than 00:00')
            // actually we can not ever go here
            return;
        }

        // show the new time of the change on the screen
        TextView time = (TextView) findViewById(R.id.time_9);
        time.setText(change.getTime().toString());
//        } catch (Exception ex) {
//
//        }
    }

    /**
     * Handle decrease of time of the tenth change
     *
     * if this method was called it means that
     * the tenth change is initialised and is visible
     *
     * @param view
     */
    public void handleDownArrow10(View view) {
        // check the allowed interval of time in which we can change it
        // check lower bound - find previous change
        // check upper bound - find next change
//        try{
        int changeIndex = 10 - 1;
        TemperatureChange change = schedule.getChange(changeIndex);
        Time changeTime = change.getTime();

        TemperatureChange prevChange = schedule.findClosestLess(change.getTime());
        Time lowerBound = prevChange.getTime();

        if (changeTime.toMinutes() <= lowerBound.toMinutes() + 1) {
            // TODO: show some message for user like 'no more can't go lower than previous change'
            return;
        }

        // change state (time) of the chosen TempChange
        try {
            change.getTime().decrementTime();
        } catch (Exception ex) {
            // TODO: handle exception (some message for user like ' can't go lower than 00:00')
            // actually we can not ever go here
            return;
        }

        // show the new time of the change on the screen
        TextView time = (TextView) findViewById(R.id.time_10);
        time.setText(change.getTime().toString());
//        } catch (Exception ex) {
//
//        }
    }
// ========= END Handlers of DOWN ARROWS =========

// ========= Handlers of UP ARROWS =========
    /**
     * Handle increase of time of the first change
     *
     * if this method was called it means that
     * the first change is initialised and is visible
     *
     * @param view
     */
    public void handleUpArrow1(View view) {
        // check the allowed interval of time in which we can change it
        // check lower bound - find previous change
        // check upper bound - find next change
//        try{
        int changeIndex = 1 - 1;
        TemperatureChange change = schedule.getChange(changeIndex);
        Time changeTime = change.getTime();

        TemperatureChange nextChange = schedule.findClosest(change.getTime());
        Time upperBound;
        if (nextChange == null) {
            upperBound = new Time(1439); // 23:59
        } else {
            upperBound = nextChange.getTime();
        }

        if (changeTime.toMinutes() >= upperBound.toMinutes() - 1) {
            // TODO: show some message for user like 'no more can't go higher than next change'
            return;
        }

        // change state (time) of the chosen TempChange
        try {
            change.getTime().incrementTime();
        } catch (Exception ex) {
            // TODO: handle exception (some message for user like ' can't go lower than 00:00')
            // actually we can not ever go here
            return;
        }

        // show the new time of the change on the screen
        TextView time = (TextView) findViewById(R.id.time_1);
        time.setText(change.getTime().toString());
//        } catch (Exception ex) {
//
//        }
    }

    /**
     * Handle increase of time of the second change
     *
     * if this method was called it means that
     * the second change is initialised and is visible
     *
     * @param view
     */
    public void handleUpArrow2(View view) {
        // check the allowed interval of time in which we can change it
        // check lower bound - find previous change
        // check upper bound - find next change
//        try{
        int changeIndex = 2 - 1;
        TemperatureChange change = schedule.getChange(changeIndex);
        Time changeTime = change.getTime();

        TemperatureChange nextChange = schedule.findClosest(change.getTime());
        Time upperBound;
        if (nextChange == null) {
            upperBound = new Time(1439); // 23:59
        } else {
            upperBound = nextChange.getTime();
        }

        if (changeTime.toMinutes() >= upperBound.toMinutes() - 1) {
            // TODO: show some message for user like 'no more can't go higher than next change'
            return;
        }

        // change state (time) of the chosen TempChange
        try {
            change.getTime().incrementTime();
        } catch (Exception ex) {
            // TODO: handle exception (some message for user like ' can't go lower than 00:00')
            // actually we can not ever go here
            return;
        }

        // show the new time of the change on the screen
        TextView time = (TextView) findViewById(R.id.time_2);
        time.setText(change.getTime().toString());
//        } catch (Exception ex) {
//
//        }
    }

    /**
     * Handle increase of time of the third change
     *
     * if this method was called it means that
     * the third change is initialised and is visible
     *
     * @param view
     */
    public void handleUpArrow3(View view) {
        // check the allowed interval of time in which we can change it
        // check lower bound - find previous change
        // check upper bound - find next change
//        try{
        int changeIndex = 3 - 1;
        TemperatureChange change = schedule.getChange(changeIndex);
        Time changeTime = change.getTime();

        TemperatureChange nextChange = schedule.findClosest(change.getTime());
        Time upperBound;
        if (nextChange == null) {
            upperBound = new Time(1439); // 23:59
        } else {
            upperBound = nextChange.getTime();
        }

        if (changeTime.toMinutes() >= upperBound.toMinutes() - 1) {
            // TODO: show some message for user like 'no more can't go higher than next change'
            return;
        }

        // change state (time) of the chosen TempChange
        try {
            change.getTime().incrementTime();
        } catch (Exception ex) {
            // TODO: handle exception (some message for user like ' can't go lower than 00:00')
            // actually we can not ever go here
            return;
        }

        // show the new time of the change on the screen
        TextView time = (TextView) findViewById(R.id.time_3);
        time.setText(change.getTime().toString());
//        } catch (Exception ex) {
//
//        }
    }

    /**
     * Handle increase of time of the forth change
     *
     * if this method was called it means that
     * the forth change is initialised and is visible
     *
     * @param view
     */
    public void handleUpArrow4(View view) {
        // check the allowed interval of time in which we can change it
        // check lower bound - find previous change
        // check upper bound - find next change
//        try{
        int changeIndex = 4 - 1;
        TemperatureChange change = schedule.getChange(changeIndex);
        Time changeTime = change.getTime();

        TemperatureChange nextChange = schedule.findClosest(change.getTime());
        Time upperBound;
        if (nextChange == null) {
            upperBound = new Time(1439); // 23:59
        } else {
            upperBound = nextChange.getTime();
        }

        if (changeTime.toMinutes() >= upperBound.toMinutes() - 1) {
            // TODO: show some message for user like 'no more can't go higher than next change'
            return;
        }

        // change state (time) of the chosen TempChange
        try {
            change.getTime().incrementTime();
        } catch (Exception ex) {
            // TODO: handle exception (some message for user like ' can't go lower than 00:00')
            // actually we can not ever go here
            return;
        }

        // show the new time of the change on the screen
        TextView time = (TextView) findViewById(R.id.time_4);
        time.setText(change.getTime().toString());
//        } catch (Exception ex) {
//
//        }
    }

    /**
     * Handle increase of time of the fifth change
     *
     * if this method was called it means that
     * the fifth change is initialised and is visible
     *
     * @param view
     */
    public void handleUpArrow5(View view) {
        // check the allowed interval of time in which we can change it
        // check lower bound - find previous change
        // check upper bound - find next change
//        try{
        int changeIndex = 5 - 1;
        TemperatureChange change = schedule.getChange(changeIndex);
        Time changeTime = change.getTime();

        TemperatureChange nextChange = schedule.findClosest(change.getTime());
        Time upperBound;
        if (nextChange == null) {
            upperBound = new Time(1439); // 23:59
        } else {
            upperBound = nextChange.getTime();
        }

        if (changeTime.toMinutes() >= upperBound.toMinutes() - 1) {
            // TODO: show some message for user like 'no more can't go higher than next change'
            return;
        }

        // change state (time) of the chosen TempChange
        try {
            change.getTime().incrementTime();
        } catch (Exception ex) {
            // TODO: handle exception (some message for user like ' can't go lower than 00:00')
            // actually we can not ever go here
            return;
        }

        // show the new time of the change on the screen
        TextView time = (TextView) findViewById(R.id.time_5);
        time.setText(change.getTime().toString());
//        } catch (Exception ex) {
//
//        }
    }

    /**
     * Handle increase of time of the sixth change
     *
     * if this method was called it means that
     * the sixth change is initialised and is visible
     *
     * @param view
     */
    public void handleUpArrow6(View view) {
        // check the allowed interval of time in which we can change it
        // check lower bound - find previous change
        // check upper bound - find next change
//        try{
        int changeIndex = 6 - 1;
        TemperatureChange change = schedule.getChange(changeIndex);
        Time changeTime = change.getTime();

        TemperatureChange nextChange = schedule.findClosest(change.getTime());
        Time upperBound;
        if (nextChange == null) {
            upperBound = new Time(1439); // 23:59
        } else {
            upperBound = nextChange.getTime();
        }

        if (changeTime.toMinutes() >= upperBound.toMinutes() - 1) {
            // TODO: show some message for user like 'no more can't go higher than next change'
            return;
        }

        // change state (time) of the chosen TempChange
        try {
            change.getTime().incrementTime();
        } catch (Exception ex) {
            // TODO: handle exception (some message for user like ' can't go lower than 00:00')
            // actually we can not ever go here
            return;
        }

        // show the new time of the change on the screen
        TextView time = (TextView) findViewById(R.id.time_6);
        time.setText(change.getTime().toString());
//        } catch (Exception ex) {
//
//        }
    }

    /**
     * Handle increase of time of the seventh change
     *
     * if this method was called it means that
     * the seventh change is initialised and is visible
     *
     * @param view
     */
    public void handleUpArrow7(View view) {
        // check the allowed interval of time in which we can change it
        // check lower bound - find previous change
        // check upper bound - find next change
//        try{
        int changeIndex = 7 - 1;
        TemperatureChange change = schedule.getChange(changeIndex);
        Time changeTime = change.getTime();

        TemperatureChange nextChange = schedule.findClosest(change.getTime());
        Time upperBound;
        if (nextChange == null) {
            upperBound = new Time(1439); // 23:59
        } else {
            upperBound = nextChange.getTime();
        }

        if (changeTime.toMinutes() >= upperBound.toMinutes() - 1) {
            // TODO: show some message for user like 'no more can't go higher than next change'
            return;
        }

        // change state (time) of the chosen TempChange
        try {
            change.getTime().incrementTime();
        } catch (Exception ex) {
            // TODO: handle exception (some message for user like ' can't go lower than 00:00')
            // actually we can not ever go here
            return;
        }

        // show the new time of the change on the screen
        TextView time = (TextView) findViewById(R.id.time_7);
        time.setText(change.getTime().toString());
//        } catch (Exception ex) {
//
//        }
    }

    /**
     * Handle increase of time of the eighth change
     *
     * if this method was called it means that
     * the eighth change is initialised and is visible
     *
     * @param view
     */
    public void handleUpArrow8(View view) {
        // check the allowed interval of time in which we can change it
        // check lower bound - find previous change
        // check upper bound - find next change
//        try{
        int changeIndex = 8 - 1;
        TemperatureChange change = schedule.getChange(changeIndex);
        Time changeTime = change.getTime();

        TemperatureChange nextChange = schedule.findClosest(change.getTime());
        Time upperBound;
        if (nextChange == null) {
            upperBound = new Time(1439); // 23:59
        } else {
            upperBound = nextChange.getTime();
        }

        if (changeTime.toMinutes() >= upperBound.toMinutes() - 1) {
            // TODO: show some message for user like 'no more can't go higher than next change'
            return;
        }

        // change state (time) of the chosen TempChange
        try {
            change.getTime().incrementTime();
        } catch (Exception ex) {
            // TODO: handle exception (some message for user like ' can't go lower than 00:00')
            // actually we can not ever go here
            return;
        }

        // show the new time of the change on the screen
        TextView time = (TextView) findViewById(R.id.time_8);
        time.setText(change.getTime().toString());
//        } catch (Exception ex) {
//
//        }
    }

    /**
     * Handle increase of time of the ninth change
     *
     * if this method was called it means that
     * the ninth change is initialised and is visible
     *
     * @param view
     */
    public void handleUpArrow9(View view) {
        // check the allowed interval of time in which we can change it
        // check lower bound - find previous change
        // check upper bound - find next change
//        try{
        int changeIndex = 9 - 1;
        TemperatureChange change = schedule.getChange(changeIndex);
        Time changeTime = change.getTime();

        TemperatureChange nextChange = schedule.findClosest(change.getTime());
        Time upperBound;
        if (nextChange == null) {
            upperBound = new Time(1439); // 23:59
        } else {
            upperBound = nextChange.getTime();
        }

        if (changeTime.toMinutes() >= upperBound.toMinutes() - 1) {
            // TODO: show some message for user like 'no more can't go higher than next change'
            return;
        }

        // change state (time) of the chosen TempChange
        try {
            change.getTime().incrementTime();
        } catch (Exception ex) {
            // TODO: handle exception (some message for user like ' can't go lower than 00:00')
            // actually we can not ever go here
            return;
        }

        // show the new time of the change on the screen
        TextView time = (TextView) findViewById(R.id.time_9);
        time.setText(change.getTime().toString());
//        } catch (Exception ex) {
//
//        }
    }

    /**
     * Handle increase of time of the tenth change
     *
     * if this method was called it means that
     * the tenth change is initialised and is visible
     *
     * @param view
     */
    public void handleUpArrow10(View view) {
        // check the allowed interval of time in which we can change it
        // check lower bound - find previous change
        // check upper bound - find next change
//        try{
        int changeIndex = 10 - 1;
        TemperatureChange change = schedule.getChange(changeIndex);
        Time changeTime = change.getTime();

        TemperatureChange nextChange = schedule.findClosest(change.getTime());
        Time upperBound;
        if (nextChange == null) {
            upperBound = new Time(1439); // 23:59
        } else {
            upperBound = nextChange.getTime();
        }

        if (changeTime.toMinutes() >= upperBound.toMinutes() - 1) {
            // TODO: show some message for user like 'no more can't go higher than next change'
            return;
        }

        // change state (time) of the chosen TempChange
        try {
            change.getTime().incrementTime();
        } catch (Exception ex) {
            // TODO: handle exception (some message for user like ' can't go lower than 00:00')
            // actually we can not ever go here
            return;
        }

        // show the new time of the change on the screen
        TextView time = (TextView) findViewById(R.id.time_10);
        time.setText(change.getTime().toString());
//        } catch (Exception ex) {
//
//        }
    }

    public void deleteTime(View view) {

        ArrayList<Integer> idS = new ArrayList<>();
        idS.add(R.id.first_layout);
        idS.add(R.id.second_layout);
        idS.add(R.id.third_layout);
        idS.add(R.id.fourth_layout);
        idS.add(R.id.fifth_layout);
        idS.add(R.id.sixth_layout);
        idS.add(R.id.seventh_layout);
        idS.add(R.id.eighth_layout);
        idS.add(R.id.nineth_layout);
        idS.add(R.id.tenth_layout);

        for (int i = 0; i < idS.size(); i++) {
            (findViewById(idS.get(i))).setVisibility(View.INVISIBLE);
        }
        schedule = conroller.getSchedule(index);
        schedule.delete();
        //findViewById(idS.get(0)).setVisibility(View.INVISIBLE);

    }
// ========= END Handlers of UP ARROWS =========
}
