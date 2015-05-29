package com.example.vladimirsinicyn.thermostat;

import com.example.vladimirsinicyn.thermostat.model.ChangeType;
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

        return new TemperatureChange(ChangeType.DAY, time); // TODO: do it right
    }


    private class TCTimerTask extends TimerTask {
        @Override
        public void run() {

            // get current thermostat time
            int realMinsTotal = time.toMinutes();
            Time currentTime = new Time(realMinsTotal); // time as time stamp

            // check whether it is time to change
            DaySchedule daySchedule = ws.getDaySchedule(state.getDayIndex());
            TemperatureChange change = daySchedule.find(currentTime);

            if (change != null) {
                // change temperature in room (on main screen and in state)
                // change day type (on main screen and in state)

            }

            time.incrementTime();

            if (time.checkMidnight()) {
                state.incrementDayIndex();
            }
        }
    }
}

//class ThermostateState{
//
//    private Temperature targetTemperature;
//    private boolean night;
//
//    public Temperature getTemperature() {
//        return targetTemperature;
//    }
//
//    public void change(ChangeType type) {
//
//    }
//
//    public boolean getDayPart() {
//        return night;
//    }
//
//    public DaySchedule getDayShedule() {return new DaySchedule();}
//
//    public void incrementDay() {
//
//    }
//}
