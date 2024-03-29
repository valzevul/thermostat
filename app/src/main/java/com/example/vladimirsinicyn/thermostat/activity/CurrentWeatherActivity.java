package com.example.vladimirsinicyn.thermostat.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.os.Message;
import android.os.Handler;
import android.provider.ContactsContract;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vladimirsinicyn.thermostat.R;
import com.example.vladimirsinicyn.thermostat.TCConroller;
import com.example.vladimirsinicyn.thermostat.ThermostatApp;
import com.example.vladimirsinicyn.thermostat.model.LightCondition;
import com.example.vladimirsinicyn.thermostat.model.Temperature;
import com.example.vladimirsinicyn.thermostat.model.TemperatureChange;
import com.example.vladimirsinicyn.thermostat.model.Time;

import java.io.File;


public class CurrentWeatherActivity extends Activity implements SeekBar.OnSeekBarChangeListener{

    private static TCConroller conroller;
    private SeekBar bar;

    private Handler temperatureRoomCustomHandler;
    private Handler customCheckboxCheckedHandler;
    private Handler customCheckboxEnabledHandler;
    private Handler lightConditionImageHandler;
    private Handler currentTimeHandler;
    private Handler nextChangeHandler;

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
        bar = (SeekBar) findViewById(R.id.seekBarCustom);
        bar.setOnSeekBarChangeListener(this);
        bar.setMax((ThermostatApp.MAX_TEMP - ThermostatApp.MIN_TEMP) * 10);

        // set textview of custom temperature to the right state
        final TextView textViewProgressCustom = (TextView) findViewById(R.id.textViewProgressCustom);
        textViewProgressCustom.setText("↓ " + conroller.getCustomTemperature() + "°C");

        // TODO: set slider of custom temperature to the right state

        // set checkbox of custom mod to the right state (enabled/disabled and checked/unchecked)
        final CheckBox customCheckBox = (CheckBox) findViewById(R.id.chkCustom);
        customCheckBox.setChecked(conroller.getCustom());
        customCheckBox.setEnabled(!conroller.getVacation());
        // save checkbox 'custom mod' in controller
        // old way: conroller.setCustomCheckBox(customCheckBox);
        // new way!
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
        final TextView mainScreenRoomTemperature = (TextView) findViewById(R.id.day_degree_textView_Custom);
        mainScreenRoomTemperature.setText(conroller.getTemperatureRoom().toString() + "°C");
        // save textview of room temperature (current) in contoller
        // old way: conroller.setTemperatureRoomTextView(mainScreenRoomTemperature);
        // new way!
        temperatureRoomCustomHandler = new Handler() {
            public void handleMessage(Message msg) {
                String text = (String) msg.obj;
                mainScreenRoomTemperature.setText(text + "°C");
            }
        };
        conroller.setTemperatureRoomCustomHandler(temperatureRoomCustomHandler);

        // set imageview of light condition to the right state
        final ImageView lightConditionImageView = (ImageView) findViewById(R.id.light_condition_image);
        LightCondition lightCondition = conroller.getLightCondition();
        lightConditionImageView.setBackground(toDrawable(lightCondition));
        // save imageview of light condition (current) in contoller
        // old way: conroller.setLightConditionImageView(lightConditionImageView);
        // new way!
        lightConditionImageHandler = new Handler() {
            public void handleMessage(Message msg) {
                Drawable d = (Drawable) msg.obj;
                lightConditionImageView.setBackground(d);
            }
        };
        conroller.setLightConditionImageHandler(lightConditionImageHandler);

        // set textview of current time to the right state
        final TextView mainScreenCurrentTime = (TextView) findViewById(R.id.current_time);
        mainScreenCurrentTime.setText(conroller.getTime().toString());
        // save textview of room temperature (current) in contoller
        // new way!
        currentTimeHandler = new Handler() {
            public void handleMessage(Message msg) {
                String text = (String) msg.obj;
                mainScreenCurrentTime.setText(text);
            }
        };
        conroller.setCurrentTimeHandler(currentTimeHandler);

        Looper mainLooper = getMainLooper();
        conroller.setMainLooper(mainLooper);

        // set textview of current time to the right state
        final TextView mainNextChange = (TextView) findViewById(R.id.next_change);
        TemperatureChange nextChange = conroller.getNextChange();
        if (nextChange == null) {
            mainNextChange.setText("No next.");
        } else {
            mainNextChange.setText(nextChange.getTime().toString());
        }
        // save textview of room temperature (current) in contoller
        // new way!
        nextChangeHandler = new Handler() {
            public void handleMessage(Message msg) {
                String text = (String) msg.obj;
                mainNextChange.setText(text);
            }
        };
        conroller.setNextChangeHandler(nextChangeHandler);
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

        // finish(); // needed here?

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_load) {

//            // load schedule
//            // get name of the file
//            getName();
//            //System.out.println(m_Text);

            String name = "save.out";
            File directory = Environment.getExternalStorageDirectory();
            String filename = directory + File.separator + name;
            File file = new File(filename);
            try {
                if (file.exists()) {
                    conroller.loadSchedule(filename);
                } else {
                    // show message for user (nothing to load)
                    String msg = "nothing to load";
                    Toast.makeText(CurrentWeatherActivity.this, msg, Toast.LENGTH_SHORT).show();
                    return true;
                }
            } catch (Exception ex) {
                // TODO: handle
                Log.i("load", ex.getMessage());
            }

            return true;
        }

        if (id == R.id.action_save) {

//            // save schedule
//            // get name of the file
//            getName();
//            //System.out.println(m_Text);

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

        if (!fromUser) {
            return;
        }

        // count new custom temperature
        int tempWhole = (progress / 10) + 5;
        int tempFrac = progress % 10;
        Temperature newCustomTemp = new Temperature(tempWhole, tempFrac);

        // change custom temperature in state
        conroller.setCustomTemperature(newCustomTemp);

        // show it on this screen (main screen)
        TextView textViewProgressCustom = (TextView) findViewById(R.id.textViewProgressCustom);
        textViewProgressCustom.setText("↓ " + newCustomTemp + "°C");
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
