package com.example.vladimirsinicyn.thermostat.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.vladimirsinicyn.thermostat.R;
import com.example.vladimirsinicyn.thermostat.TCConroller;
import com.example.vladimirsinicyn.thermostat.ThermostatApp;


public class VacationWeatherActivity extends Activity {

    private static TCConroller conroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_vacation);

        setTitle("Thermostat");

        ThermostatApp state = ((ThermostatApp)getApplication());
        state.initContorller();
        conroller = state.getConroller();
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

    public void incNightTemp(View view) {
        System.out.println("Increase night time!");
    }
}
