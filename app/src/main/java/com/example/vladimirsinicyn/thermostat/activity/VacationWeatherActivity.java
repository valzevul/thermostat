package com.example.vladimirsinicyn.thermostat.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.vladimirsinicyn.thermostat.R;
import com.example.vladimirsinicyn.thermostat.TCConroller;
import com.example.vladimirsinicyn.thermostat.ThermostatApp;
import com.example.vladimirsinicyn.thermostat.model.Temperature;


public class VacationWeatherActivity extends Activity implements SeekBar.OnSeekBarChangeListener {

    private static TCConroller conroller;
    private SeekBar bar;

    private Handler temperatureRoomVacationHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_vacation);

        setTitle("Thermostat");

        ThermostatApp app = ((ThermostatApp)getApplication());
        app.initContorller();
        conroller = app.getConroller();

        // set checkbox of vacation mod to the right state
        CheckBox vacationCheckBox = (CheckBox) findViewById(R.id.chkVacation);
        vacationCheckBox.setChecked(conroller.getVacation());

        // set textview of room temperature to the right state
        final TextView vacationScreenRoomTemperature = (TextView) findViewById(R.id.day_degree_textView_Vacation);
        vacationScreenRoomTemperature.setText(conroller.getTemperatureRoom().toString() + "°C");
        // save textview of room temperature (current) in contoller
        // new way!
        temperatureRoomVacationHandler = new Handler() {
            public void handleMessage(Message msg) {
                String text = (String) msg.obj;
                vacationScreenRoomTemperature.setText(text + "°C");
            }
        };
        conroller.setTemperatureRoomVacationHandler(temperatureRoomVacationHandler);

        // seek bar handle
        bar = (SeekBar) findViewById(R.id.seekBarVacation);
        bar.setOnSeekBarChangeListener(this);
        bar.setMax((ThermostatApp.MAX_TEMP - ThermostatApp.MIN_TEMP) * 10);

        // set textview of vacation temperature to the right state
        final TextView textViewProgressVacation = (TextView) findViewById(R.id.textViewProgressVacation);
        textViewProgressVacation.setText("↓ " + conroller.getVacationTemperature() + "°C");

        // TODO: set slider of vacation temperature to the right state
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

            Intent intent = new Intent(VacationWeatherActivity.this, CurrentWeatherActivity.class);
            startActivity(intent);

            return true;
        }

        if (id == R.id.calendar) {

            Intent intent = new Intent(VacationWeatherActivity.this, WeekModeFullActivity.class);
            startActivity(intent);

            return true;
        }

        if (id == R.id.hot) {

            // stay on this screen

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
     *                 was set by {@link android.widget.ProgressBar#setMax(int)}. (The default value for max is 100.)
     * @param fromUser True if the progress change was initiated by the user.
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if (!fromUser) {
            return;
        }

        // count new custom temperature
        int tempWhole = (progress / 10) + 5;
        int tempFrac = progress % 10;
        Temperature newVacationTemp = new Temperature(tempWhole, tempFrac);

        // change custom temperature in state
        conroller.setVacationTemperature(newVacationTemp);

        // show it on this screen (main screen)
        TextView textViewProgressVacation = (TextView) findViewById(R.id.textViewProgressVacation);
        textViewProgressVacation.setText("↓ " + newVacationTemp + "°C");
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
    /**
     * Handles check of checkbox in VACATION MODE
     *
     * @param view
     */
    public void changeVacationMod(View view) {

        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        if (view.getId() == R.id.chkVacation) {
            conroller.setVacation(checked);
        }
    }
}
