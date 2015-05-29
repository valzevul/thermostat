package com.example.vladimirsinicyn.thermostat;

import com.example.vladimirsinicyn.thermostat.model.DaySchedule;
import com.example.vladimirsinicyn.thermostat.model.Temperature;
import com.example.vladimirsinicyn.thermostat.model.ThermostatState;
import com.example.vladimirsinicyn.thermostat.model.Time;
import com.example.vladimirsinicyn.thermostat.model.WeekSchedule;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class TCConroller {

    private Time time;
    private WeekSchedule ws;
    private final int timeFactor = 300;
    private ThermostatState state;
    private int dayOfWeek; // 0 = Monday, 6 = Sunday

    public TCConroller() {

        state = new ThermostatState();
        ws = state.getWeekSchedule();

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

    // ====== WEEK SCHEDULE SAVE/LOAD ======

    public void loadSchedule(String name) throws Exception {
        state = ThermostatState.load(name);
    }

    public void saveSchedule(String name) throws Exception {
        ThermostatState.save(state, name);
    }

    // ====== END WEEK SCHEDULE SAVE/LOAD ======

    public DaySchedule getSchedule(int index) {
        return ws.getDaySchedule(index);
    }


    private class TCTimerTask extends TimerTask {
        @Override
        public void run() {

            // get current time
            int hours = time.getHours();
            int mins = time.getMinutes();
            int realMinsTotal = hours * 60 + mins; // time.toMinutes();
            Time currentTime = new Time(realMinsTotal);

            // check whether it is time to change
            //DaySchedule daySchedule = state.getDayShedule();
            //TemperatureChange change = daySchedule.find(currentTime);

//            if (change != null) {
//                state.change(change.getType());
//            }



            if (time.checkMidnight()) {
                dayOfWeek++;

                if (dayOfWeek >= 7) {
                    dayOfWeek = 0;
                }
            }



            time.incrementTime();
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
