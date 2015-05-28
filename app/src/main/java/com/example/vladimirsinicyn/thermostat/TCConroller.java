package com.example.vladimirsinicyn.thermostat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class TCConroller {

    private Time time;
    private final int timeFactor = 300;

    public TCConroller() {

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

    private class TCTimerTask extends TimerTask {
        @Override
        public void run() {

//            DaySchedule daySchedule = new DaySchedule();
//            int hours = time.getHours();
//            int mins = time.getMinutes();
//            TemperatureChange change = daySchedule.getChange(hours, mins);
//
//            ThermostateState state = new ThermostateState();
//            if (change != null) {
//                state.changeDayPart(change.targetNight);
//                state.changeTemperature(change.targetTemperature);
//            }

            time.incrementTime();
        }
    }
}

//class TemperatureChange {
//    public boolean targetNight;
//    public Temperature targetTemperature;
//
//} // delete
//class DayProgram {
//    TemperatureChange getChange(int h, int m) {
//        return new TemperatureChange();
//    }
//}


