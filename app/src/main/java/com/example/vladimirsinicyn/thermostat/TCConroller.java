package com.example.vladimirsinicyn.thermostat;

import com.example.vladimirsinicyn.thermostat.model.LightCondition;
import com.example.vladimirsinicyn.thermostat.model.DaySchedule;
import com.example.vladimirsinicyn.thermostat.model.Temperature;
import com.example.vladimirsinicyn.thermostat.model.TemperatureChange;
import com.example.vladimirsinicyn.thermostat.model.ThermostatState;
import com.example.vladimirsinicyn.thermostat.model.Time;
import com.example.vladimirsinicyn.thermostat.model.WeekSchedule;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class TCConroller {

    private Time time; // the time of all thermostat

    private ThermostatState state;
    private WeekSchedule ws; // current working week schedule

    private final int timeFactor = 300; // times faster than real time

//    private CurrentWeatherActivity currentWeatherActivity;
//    private VacationWeatherActivity vacationWeatherActivity;
//    private WeekModeDetailedActivity weekModeDetailedActivity;
//    private WeekModeFullActivity weekModeFullActivity;

    private boolean customModTurnedOn = false;

    public TCConroller() {

        state = new ThermostatState();
        ws = state.getWeekSchedule();

        // init time from system time
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        String[] arr = timeStamp.split("_");

        int realHours = Integer.parseInt(arr[1].substring(0, 2));
        int realMins = Integer.parseInt(arr[1].substring(2, 4));
        int realMinsTotal = realHours * 60 + realMins;

        time = new Time(realMinsTotal);

        int msInMin = 60 * 1000;

        // our time is 300 times faster than real time
        // which means that:
        // one minute of our time
        // is
        // 200 ms of real time
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TCTimerTask(), 0, msInMin / timeFactor);
    }

// ====== WEEK TEMPERATURE GETTERS and INCR/DECREMENTS ======
    public Temperature getNightTemperature() {
        return ws.getNightTemperature();
    }

    public Temperature getDayTemperature() {
        return ws.getDayTemperature();
    }

    public void incrementNightTemperature() {
        ws.getNightTemperature().incrementTemperature();
    }

    public void incrementDayTemperature() {
        ws.getDayTemperature().incrementTemperature();
    }

    public void decrementNightTemperature() {
        ws.getNightTemperature().decrementTemperature();
    }

    public void decrementDayTemperature() {
        ws.getDayTemperature().decrementTemperature();
    }
// ====== END WEEK TEMPERATURE GETTERS and INCR/DECREMENTS ======

// ====== CUSTOM TEMPERATURE GETTERS and INCR/DECREMENTS ======

    // TODO: write code here

// ====== END CUSTOM TEMPERATURE GETTERS and INCR/DECREMENTS ======

// ====== WEEK SCHEDULE SAVE/LOAD + getter of day schedule ======
    public void loadSchedule(String name) throws Exception {
        state = ThermostatState.load(name);
    }

    public void saveSchedule(String name) throws Exception {
        ThermostatState.save(state, name);
    }

    public DaySchedule getSchedule(int index) {
        return ws.getDaySchedule(index);
    }
// ====== END WEEK SCHEDULE SAVE/LOAD ======

    TemperatureChange getNextChange() {

        return new TemperatureChange(LightCondition.DAY, time); // TODO: do it right
    }

// ====== VACATION/CUSTOM MODS GETTERS/SETTERS  ======
    public void setVacation(boolean on) {
        state.setVacation(on);
    }

    public boolean getVacation() {
        return state.isVacation();
    }

    public void setCustom(boolean on) {
        state.setCustom(on);
    }

    public boolean getCustom() {
        return state.isCustom();
    }
// ====== END VACATION/CUSTOM MODS GETTERS/SETTERS  ======

    private Temperature toTemp(LightCondition lightCondition) {
        if (lightCondition == LightCondition.DAY) {
            return ws.getDayTemperature();
        } else {
            return ws.getNightTemperature();
        }
    }

    private class TCTimerTask extends TimerTask {

        /** happens every minute
         * handles changes of room temperature and light condition:
         * 1) changes state of thermostat when
         * 1.1) it is time (checks week and day schedules)
         * 1.2) it is custom mode
         * 1.3) it is vacation mode
         * 2) shows changes on screens (when changes made)
         * 2.1) room temperature on main screen
         * 2.2) light condition on main screen
         * 2.3) time of next change on main screen
         */
        @Override
        public void run() {
            // turn on custom mod
            if (state.isCustom()) {
                if (!customModTurnedOn) {
                    // turn on custom temperature
                    // show castom temperature

                    customModTurnedOn = true;
                }
            }

            // get current thermostat time
            int realMinsTotal = time.toMinutes();
            Time currentTime = new Time(realMinsTotal); // time as time stamp

            // check whether it is time to change (by schedule)
            DaySchedule daySchedule = ws.getDaySchedule(state.getDayIndex());
            TemperatureChange change = daySchedule.find(currentTime);

            if (change != null) {
                if (state.isCustom()) {
                    // off custom mode (+uncheck checkbox 'custom' on main screen)
                    state.setCustom(false);
                    customModTurnedOn = false;
                }

                // change temperature in room (on main screen and in state)
                // change light condition (on main screen and in state)

                LightCondition targetLightCondition = change.getTargetCondition();
                state.setTemperatureRoom(toTemp(targetLightCondition));
                state.changeCurrentLightCondition();

            }

            time.incrementTime();

            if (time.checkMidnight()) {
                state.incrementDayIndex();
            }
        }
    }
}
