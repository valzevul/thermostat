package com.example.vladimirsinicyn.thermostat;

import android.util.Log;
import android.widget.CheckBox;

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

    // *.ModTurnedOn variables needed to indicate whether
    // room temperature is changed to the mod temperature
    // when user changed the state to the mod.
    //
    // Thus we don't need to turn the mod on every minute
    private boolean customModTurnedOn = false;
    private boolean vacationModTurnedOn = false;

    private CheckBox customCheckBox;
    private CheckBox vacationCheckBox;

    public TCConroller() {
        //Log.i("TCController", "TEST!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        state = new ThermostatState();
        ws = state.getWeekSchedule();

        // init time from system time
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        String[] arr = timeStamp.split("_");

        int realHours = Integer.parseInt(arr[1].substring(0, 2));
        int realMins = Integer.parseInt(arr[1].substring(2, 4));
        int realMinsTotal = realHours * 60 + realMins;
        time = new Time(realMinsTotal);

        // init day of week from system day of week
        int realYear = Integer.parseInt(arr[0].substring(0, 4));
        int realMonth = Integer.parseInt(arr[0].substring(4, 6));
        int realDay = Integer.parseInt(arr[0].substring(6, 8));
        int realDayOfWeek = getRealDayOfWeek(realYear, realMonth, realDay);
        state.setDayIndex(realDayOfWeek);

        // init last change
        // TODO: CLONE default changes!
        // TODO: maybe add method clone to class TemperatureChange
        TemperatureChange first =  WeekSchedule.firstDefaultTemperatureChange;
        TemperatureChange last =  WeekSchedule.lastDefaultTemperatureChange;
        TemperatureChange midnight = WeekSchedule.midnightTemperatureChange;
        if (time.isGreater(last.getTime())) {
            state.setLastChange(last);
        } else if (time.isGreater(first.getTime())) {
            state.setLastChange(first);
        } else {
            state.setLastChange(midnight);
        }

        int msInMin = 60 * 1000; // milliseconds in minute

        // our time is 300 times faster than real time
        // which means that:
        // one minute of our time
        // is
        // 200 ms of real time
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TCTimerTask(), 0, msInMin / timeFactor);
    }

    // TODO: fix wrong day of week
    // now it returns TUESDAY instead of real SATURDAY
    private static int getRealDayOfWeek(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        int dow = c.get(Calendar.DAY_OF_WEEK);

        // we have sunday = 0, saturday = 6
        // whereas ANDROID (Calendar class) has sunday = 1, saturday = 7
        return dow - 1;
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
    public boolean getVacation() {
        return state.isVacation();
    }

    public void setVacation(boolean on) {
        state.setVacation(on);
    }

    public boolean getCustom() {
        return state.isCustom();
    }

    public void setCustom(boolean on) {
        state.setCustom(on);
    }
// ====== END VACATION/CUSTOM MODS GETTERS/SETTERS  ======

// ====== VACATION/CUSTOM MODS CHECKBOXES GETTERS/SETTERS  ======

    public CheckBox getCustomCheckox() {
        return customCheckBox;
    }

    public void setCustomCheckBox(CheckBox checkBox) {
        customCheckBox = checkBox;
    }

    public CheckBox getVacationCheckox() {
        return vacationCheckBox;
    }

    public void setVacationCheckBox(CheckBox checkBox) {
        vacationCheckBox = checkBox;
    }

// ====== END VACATION/CUSTOM MODS CHECKBOXES GETTERS/SETTERS  ======

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
         * 2.4) unchecks and disables checkboxes of mods
         */
        @Override
        public void run() {

            // turn ON vacation mod if state says so (the checkbox was checked by user)
            if (state.isVacation()) {
                // check whether we turned it already
                if (!vacationModTurnedOn) {

                    // turn the mod on
                    vacationModTurnedOn = true;

                    // uncheck custom mod and disable the custom checkbox
                    // NOTE:
                    // (custom mod was already turned off in state
                    // by setter of vacation mod)
                    customCheckBox.setChecked(false);
                    customCheckBox.setEnabled(false);

                    // turn on vacation temperature in state
                    state.setTemperatureRoom(state.getVacationTemperature());

                    // TODO: show vacation temperature on the main screen
                }
            } else { // turn OFF vacation mod if state says so (the checkbox was unchecked by user)
                // check whether we turned it OFF already
                if (vacationModTurnedOn) {

                    // turn the mod OFF
                    vacationModTurnedOn = false;

                    // turn on SCHEDULE temperature in state
                    // set state like
                    // the neareast previous change (by time) by schedule
                    // happened
                    LightCondition nearestPreviousLightConditionBySchedule =
                            state.getLastChange().getTargetCondition();

                    Temperature nearestPreviousTempBySchedule =
                            toTemp(nearestPreviousLightConditionBySchedule);

                    state.setTemperatureRoom(nearestPreviousTempBySchedule);

                    // enable custom mod checkbox
                    customCheckBox.setEnabled(true);

                    // TODO: show SCHEDULE temperature on the main screen
                    // show state.getTemperatureRoom
                }
            }

            // turn ON custom mod if state says so (the checkbox was checked by user)
            if (state.isCustom()) {
                // check whether we turned it ON already
                if (!customModTurnedOn) {

                    // turn the mod on
                    customModTurnedOn = true;

                    // turn on custom temperature in state
                    state.setTemperatureRoom(state.getCustomTemperature());

                    // TODO: show custom temperature on the main screen
                }
            } else { // turn OFF custom mod if state says so (the checkbox was unchecked by user)
                // check whether we turned it OFF already
                if (customModTurnedOn) {

                    // turn the mod OFF
                    customModTurnedOn = false;

                    // turn on SCHEDULE temperature in state
                    // set state like
                    // the last change before custom mod was turned on
                    // happened
                    LightCondition lastConditionBeforeCustomModOn =
                            state.getLastChange().getTargetCondition();
                    Temperature lastTempBeforeCustomModOn = toTemp(lastConditionBeforeCustomModOn);
                    state.setTemperatureRoom(lastTempBeforeCustomModOn);

                    // TODO: show SCHEDULE temperature on the main screen
                    // show state.getTemperatureRoom
                }
            }

            // get current thermostat time
            int realMinsTotal = time.toMinutes();
            Time currentTime = new Time(realMinsTotal); // time as time stamp

            // check whether it is time to change (by schedule)
            DaySchedule daySchedule = ws.getDaySchedule(state.getDayIndex());
            TemperatureChange change = daySchedule.find(currentTime);

            if (change != null) {

                state.setLastChange(change);

                if (!state.isVacation()) {
                    if (state.isCustom()) {
                        // off custom mode
                        state.setCustom(false);
                        customModTurnedOn = false;

                        // uncheck checkbox 'custom' on main screen
                        customCheckBox.setChecked(false);
                    }

                    // change temperature in room (in state)
                    // change light condition (in state)
                    LightCondition targetLightCondition = change.getTargetCondition();
                    state.setTemperatureRoom(toTemp(targetLightCondition));
                    state.changeCurrentLightCondition();

                    // TODO: change temperature in room (on the main screen)
                    // TODO: change light condition (on the main screen)
                }
            }

            time.incrementTime();

            if (time.checkMidnight()) {
                state.incrementDayIndex();
            }
        }
    }
}
