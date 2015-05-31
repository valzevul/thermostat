package com.example.vladimirsinicyn.thermostat;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

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

    private boolean thermostatWORKS = false;
    private boolean thermostatTURNED_ON = false;

    private Time time; //= new Time(0); // the time of all thermostat

    private ThermostatState state; // the state of all thermostat
    private WeekSchedule ws; // current working week schedule

    private final int timeFactor = 600;//300; // times faster than real time

    // *.ModTurnedOn variables needed to indicate whether
    // room temperature is changed to the mod temperature
    // when user changed the state to the mod.
    //
    // Thus we don't need to turn the mod on every minute
    private boolean customModTurnedOn = false;
    private boolean vacationModTurnedOn = false;

    private Drawable sun;
    private Drawable moon;

    // these fields are references to different controls
    // to change view when it is needed (according to changes of state [model])
    private Handler temperatureRoomCustomHandler;
    private Handler temperatureRoomVacationHandler;
    private Handler customCheckboxCheckedHandler;
    private Handler customCheckboxEnabledHandler;
    private Handler lightConditionImageHandler;

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

        thermostatWORKS = true;
    }

    // TODO: fix wrong day of week
    // now it returns TUESDAY instead of real SATURDAY
    private static int getRealDayOfWeek(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        int dow = c.get(Calendar.DAY_OF_WEEK);

        // we have sunday = 0, saturday = 6
        // whereas ANDROID (Calendar class) has sunday = 1, saturday = 7
        // TODO: check ANDROID MAGIC - gives three days less
        return ((dow - 1) + 3) % 7;
    }

// ====== HANDLERS OF ACTIVITIES SETTERS ======
    public void setTemperatureRoomCustomHandler(Handler temperatureRoomCustomHandler) {
        this.temperatureRoomCustomHandler = temperatureRoomCustomHandler;
    }

    public void setTemperatureRoomVacationHandler(Handler temperatureRoomVacationHandler) {
        this.temperatureRoomVacationHandler = temperatureRoomVacationHandler;
    }

    public void setCustomCheckboxCheckedHandler(Handler customCheckboxCheckedHandler) {
        this.customCheckboxCheckedHandler = customCheckboxCheckedHandler;
    }

    public void setCustomCheckboxEnabledHandler(Handler customCheckboxEnabledHandler) {
        this.customCheckboxEnabledHandler = customCheckboxEnabledHandler;
    }

    public void setLightConditionImageHandler(Handler lightConditionImageHandler) {
        this.lightConditionImageHandler = lightConditionImageHandler;
    }
// ====== END HANDLERS OF ACTIVITIES SETTERS ======

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

// ====== CURRENT ROOM TEMPERATURE AND LIGHT CONDITION GETTERS and INCR/DECREMENTS ======
    public Temperature getTemperatureRoom() {
        return state.getTemperatureRoom();
    }

    public LightCondition getLightCondition() {
        return state.getCurrentLightCondition();
    }
// ====== END CURRENT ROOM TEMPERATURE AND LIGHT CONDITION GETTERS and INCR/DECREMENTS ======

// ====== WEEK SCHEDULE SAVE/LOAD + getter of day schedule ======
    public void loadSchedule(String name) throws Exception {
        Time timeStamp = new Time(time.toMinutes());
        state = ThermostatState.load(name, timeStamp, state.getDayIndex());
    }

    public void saveSchedule(String name) throws Exception {
        ThermostatState.save(state, name);
    }

    public DaySchedule getSchedule(int index) {
        return ws.getDaySchedule(index);
    }
// ====== END WEEK SCHEDULE SAVE/LOAD ======

// ====== NEXT CHANGE GETTER ======
    // TODO: use it in week_detailed
    TemperatureChange getNextChange(int dayIndex) {
        DaySchedule schedule = ws.getDaySchedule(dayIndex);
        TemperatureChange nextChange = schedule.findClosest(time);
        return nextChange;
    }
// ====== END NEXT CHANGE GETTER ======

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

// ====== VACATION/CUSTOM TEMPERATURE GETTERS/SETTERS  ======
    public Temperature getCustomTemperature() {
        return state.getCustomTemperature();
    }

    public void setCustomTemperature(Temperature newCustomTemperature) {
        state.setCustomTemperature(newCustomTemperature);
    }

    public Temperature getVacationTemperature() {
        return state.getVacationTemperature();
    }

    public void setVacationTemperature(Temperature newVacationTemperature) {
        state.setVacationTemperature(newVacationTemperature);
    }

// ====== END VACATION/CUSTOM TEMPERATURE GETTERS/SETTERS  ======

    public void setMoonImage(Drawable moon) {
        this.moon = moon;
    }

    public void setSunImage(Drawable sun) {
        this.sun = sun;
    }

    // auxiliary method LIGHTCONDITION --> TEMPERATURE
    private Temperature toTemp(LightCondition lightCondition) {
        if (lightCondition == LightCondition.DAY) {
            return ws.getDayTemperature();
        } else {
            return ws.getNightTemperature();
        }
    }

    // auxiliary method LIGHTCONDITION --> DRAWABLE (PICTURE OF CONDITION)
    public Drawable toDrawable(LightCondition lightCondition) {
        if (lightCondition == LightCondition.DAY) {
            return sun;
        } else {
            return moon;
        }
    }

// ====== The TimerTack class  ======

    /**
     * specifies what should happen every minute of
     * out (not real) time
     *
     * and changes state [model] and view [screens/activities]
     */
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

            if (temperatureRoomCustomHandler == null) {
                Log.i("TIMER", "SKIP temperatureRoomCustomHandler");
                return;
            }

            if (temperatureRoomVacationHandler == null) {
                Log.i("TIMER", "SKIP temperatureRoomVacationHandler");
                return;
            }

            // initial change (done once)
            if (thermostatWORKS) {
                if (!thermostatTURNED_ON) {
                    // set default settings
                    // turn on SCHEDULE temperature in state
                    // set state like
                    // the neareast previous change (by time) by schedule
                    // happened
                    LightCondition nearestPreviousLightConditionBySchedule =
                            state.getLastChange().getTargetCondition();

                    Temperature nearestPreviousTempBySchedule =
                            toTemp(nearestPreviousLightConditionBySchedule);

                    state.setTemperatureRoom(nearestPreviousTempBySchedule);
                    state.setCurrentLightCondition(nearestPreviousLightConditionBySchedule);

                    // show SCHEDULE temperature on the main screen
                    // old:
                    // temperatureRoomTextView.setText(state.getTemperatureRoom().toString() + "°C");
                    // new way:
                    Message msgTemperature = new Message();
                    msgTemperature.obj = state.getTemperatureRoom().toString();
                    temperatureRoomCustomHandler.sendMessage(msgTemperature);
                    // show SCHEDULE temperature on the vacation screen
                    Message msgTemperature2 = new Message();
                    msgTemperature2.obj = state.getTemperatureRoom().toString();
                    temperatureRoomVacationHandler.sendMessage(msgTemperature2);

                    // show light condition (on the main screen)
                    // new way:
                    Message msgLight = new Message();
                    msgLight.obj = toDrawable(nearestPreviousLightConditionBySchedule);
                    lightConditionImageHandler.sendMessage(msgLight);

                    // turn on the thermostat
                    thermostatTURNED_ON = true;
                }
            }

            // turn ON vacation mod if state says so (the checkbox was checked by user)
            if (state.isVacation()) {
                // check whether we turned it already
                if (!vacationModTurnedOn) {

                    // turn the mod on
                    vacationModTurnedOn = true;

                    // turn custom mod off
                    customModTurnedOn = false;

                    // uncheck(maybe non needed) custom mod checkbox (main screen)
                    // and disable it
                    //
                    // NOTE:
                    // (custom mod was already turned off in state
                    // by setter of vacation mod)
                    // so the checkbox automatically unchecked
                    // customCheckBox.setChecked(false);
                    // old: customCheckBox.setEnabled(false);
                    // new way:
                    Message msgCustom = new Message();
                    msgCustom.obj = false;
                    customCheckboxEnabledHandler.sendMessage(msgCustom);

                    // turn on vacation temperature in state
                    state.setTemperatureRoom(state.getVacationTemperature());

                    // show vacation temperature on the main screen
                    // old:
                    // temperatureRoomTextView.setText(state.getTemperatureRoom().toString() + "°C");
                    // new way:
                    Message msgTemperature = new Message();
                    msgTemperature.obj = state.getTemperatureRoom().toString();
                    temperatureRoomCustomHandler.sendMessage(msgTemperature);
                    // show vacation temperature on the vacation screen
                    Message msgTemperature2 = new Message();
                    msgTemperature2.obj = state.getTemperatureRoom().toString();
                    temperatureRoomVacationHandler.sendMessage(msgTemperature2);
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
                    state.setCurrentLightCondition(nearestPreviousLightConditionBySchedule);

                    // enable custom mod checkbox
                    // old: customCheckBox.setEnabled(true);
                    // new way:
                    Message msgCustom = new Message();
                    msgCustom.obj = true;
                    customCheckboxEnabledHandler.sendMessage(msgCustom);

                    // show SCHEDULE temperature on the main screen
                    // old:
                    // temperatureRoomTextView.setText(state.getTemperatureRoom().toString() + "°C");
                    // new way:
                    Message msgTemperature = new Message();
                    msgTemperature.obj = state.getTemperatureRoom().toString();
                    temperatureRoomCustomHandler.sendMessage(msgTemperature);
                    // show SCHEDULE temperature on the vacation screen
                    Message msgTemperature2 = new Message();
                    msgTemperature2.obj = state.getTemperatureRoom().toString();
                    temperatureRoomVacationHandler.sendMessage(msgTemperature2);
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

                    // show custom temperature on the main screen
                    // old:
                    // temperatureRoomTextView.setText(state.getTemperatureRoom().toString() + "°C");
                    // new way:
                    Message msgTemperature = new Message();
                    msgTemperature.obj = state.getTemperatureRoom().toString();
                    temperatureRoomCustomHandler.sendMessage(msgTemperature);
                    // show custom temperature on the vacation screen
                    Message msgTemperature2 = new Message();
                    msgTemperature2.obj = state.getTemperatureRoom().toString();
                    temperatureRoomVacationHandler.sendMessage(msgTemperature2);
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
                    state.setCurrentLightCondition(lastConditionBeforeCustomModOn);

                    // show SCHEDULE temperature on the main screen
                    // old:
                    // temperatureRoomTextView.setText(state.getTemperatureRoom().toString() + "°C");
                    // new way:
                    Message msgTemperature = new Message();
                    msgTemperature.obj = state.getTemperatureRoom().toString();
                    temperatureRoomCustomHandler.sendMessage(msgTemperature);
                    // show SCHEDULE temperature on the vacation screen
                    Message msgTemperature2 = new Message();
                    msgTemperature2.obj = state.getTemperatureRoom().toString();
                    temperatureRoomVacationHandler.sendMessage(msgTemperature2);
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
                        // old: customCheckBox.setChecked(false);
                        // new way:
                        Message msg = new Message();
                        msg.obj = false;
                        customCheckboxCheckedHandler.sendMessage(msg);
                    }

                    // change temperature in room (in state)
                    // change light condition (in state)
                    LightCondition targetLightCondition = change.getTargetCondition();
                    state.setTemperatureRoom(toTemp(targetLightCondition));
                    state.changeCurrentLightCondition();

                    // show temperature in room (on the main screen)
                    // old:
                    // temperatureRoomTextView.setText(state.getTemperatureRoom().toString() + "°C");
                    // new way:
                    Message msgTemperature = new Message();
                    msgTemperature.obj = state.getTemperatureRoom().toString();
                    temperatureRoomCustomHandler.sendMessage(msgTemperature);
                    // show temperature in room (on the vacation screen)
                    Message msgTemperature2 = new Message();
                    msgTemperature2.obj = state.getTemperatureRoom().toString();
                    temperatureRoomVacationHandler.sendMessage(msgTemperature2);

                    // show light condition (on the main screen)
                    // old: lightConditionImageView.setBackground(toDrawable(targetLightCondition));
                    // new way:
                    Message msgLight = new Message();
                    msgLight.obj = toDrawable(targetLightCondition);
                    lightConditionImageHandler.sendMessage(msgLight);
                }
            }

            time.incrementTime();

            if (time.checkMidnight()) {
                state.incrementDayIndex();

                // TODO: PERFORM A MIDNIGHT CHANGE
                // note: think of situation when user sets
                // TemperatureChange on midnight
                // note: think of situations when
                // it is needed at all
            }
        }
    }
// ====== END The TimerTack class  ======
}
