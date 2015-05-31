package com.example.vladimirsinicyn.thermostat.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.vladimirsinicyn.thermostat.R;
import com.example.vladimirsinicyn.thermostat.TCConroller;
import com.example.vladimirsinicyn.thermostat.ThermostatApp;
import com.example.vladimirsinicyn.thermostat.model.LightCondition;


public class CurrentWeatherActivity extends Activity implements SeekBar.OnSeekBarChangeListener{

    private static TCConroller conroller;
    private SeekBar bar;

    private Handler temperatureRoomHandler;
    private Handler customCheckboxCheckedHandler;
    private Handler customCheckboxEnabledHandler;
    private Handler lightConditionImageHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_current);

        setTitle("Thermostat");

        ThermostatApp app = ((ThermostatApp)getApplication());
        app.initContorller();
        conroller = app.getConroller();

        conroller.setSunImage(getResources().getDrawable(R.drawable.sun));
        conroller.setMoonImage(getResources().getDrawable(R.drawable.moon));

        // seek bar handle
        bar = (SeekBar) findViewById(R.id.seekBar1);
        bar.setOnSeekBarChangeListener(this);
        bar.setMax(ThermostatApp.MAX_TEMP - ThermostatApp.MIN_TEMP);

        // set checkbox of custom mod to the right state
        final CheckBox customCheckBox = (CheckBox) findViewById(R.id.chkCustom);
        customCheckBox.setChecked(conroller.getCustom());
        customCheckBox.setEnabled(!conroller.getVacation());
        // save checkbox 'custom mod' in controller
        // old way: conroller.setCustomCheckBox(customCheckBox);
        //new way!
        customCheckboxCheckedHandler = new Handler() {
            public void handleMessage(Message msg) {
                boolean custom = (boolean) msg.obj;
                customCheckBox.setChecked(custom);
            }
        };
        conroller.setCustomCheckboxCheckedHandler(customCheckboxCheckedHandler);
        customCheckboxEnabledHandler = new Handler() {
            public void handleMessage(Message msg) {
                boolean enabled = (boolean) msg.obj;
                customCheckBox.setEnabled(enabled);
            }
        };
        conroller.setCustomCheckboxEnabledHandler(customCheckboxEnabledHandler);


        // set textview of room temperature to the right state
        final TextView mainScreenRoomTemperature = (TextView) findViewById(R.id.day_degree_textView);
        mainScreenRoomTemperature.setText(conroller.getTemperatureRoom().toString() + "°C");
        // save textview of room temperature (current) in contoller
        // old way: conroller.setTemperatureRoomTextView(mainScreenRoomTemperature);
        //new way!
        temperatureRoomHandler = new Handler() {
            public void handleMessage(Message msg) {
                String text = (String) msg.obj;
                mainScreenRoomTemperature.setText(text + "°C");
            }
        };
        conroller.setTemperatureRoomHandler(temperatureRoomHandler);

        // set imageview of light condition to the right state
        final ImageView lightConditionImageView = (ImageView) findViewById(R.id.light_condition_image);
        LightCondition lightCondition = conroller.getLightCondition();
        lightConditionImageView.setBackground(toDrawable(lightCondition));
        // save imageview of light condition (current) in contoller
        // old way: conroller.setLightConditionImageView(lightConditionImageView);
        //new way!
        lightConditionImageHandler = new Handler() {
            public void handleMessage(Message msg) {
                Drawable d = (Drawable) msg.obj;
                lightConditionImageView.setBackground(d);
            }
        };
        conroller.setLightConditionImageHandler(lightConditionImageHandler);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // finish(); // needed here?

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_load) {

            // load schedule
            // TODO: get name of the file

            return true;
        }

        if (id == R.id.action_save) {

            // save schedule
            // get name of the file

            return true;
        }

        if (id == R.id.day_night) {

            // stay on this screen

            return true;
        }

        if (id == R.id.calendar) {

            Intent intent = new Intent(CurrentWeatherActivity.this, WeekModeFullActivity.class);
            startActivity(intent);

            return true;
        }

        if (id == R.id.hot) {

            Intent intent = new Intent(CurrentWeatherActivity.this, VacationWeatherActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

// =============== SEEK BAR HANDLERS ===============
    /**
     * Notification that the progress level has changed. Clients can use the fromUser parameter
     * to distinguish user-initiated changes from those that occurred programmatically.
     *
     * @param seekBar  The SeekBar whose progress has changed
     * @param progress The current progress level. This will be in the range 0..max where max
     *                 was set by {@link ProgressBar#setMax(int)}. (The default value for max is 100.)
     * @param fromUser True if the progress change was initiated by the user.
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    /**
     * Notification that the user has started a touch gesture. Clients may want to use this
     * to disable advancing the seekbar.
     *
     * @param seekBar The SeekBar in which the touch gesture began
     */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    /**
     * Notification that the user has finished a touch gesture. Clients may want to use this
     * to re-enable advancing the seekbar.
     *
     * @param seekBar The SeekBar in which the touch gesture began
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}

// =============== END SEEK BAR HANDLERS ===============

    public void changeCustomMod(View view) {

        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        if (view.getId() == R.id.chkCustom) {
            conroller.setCustom(checked);
        }
    }

    // auxiliary method LIGHTCONDITION --> DRAWABLE (PICTURE OF CONDITION)
    public Drawable toDrawable(LightCondition lightCondition) {
        if (lightCondition == LightCondition.DAY) {
            return getResources().getDrawable(R.drawable.sun);
        } else {
            return getResources().getDrawable(R.drawable.moon);
        }
    }
}
