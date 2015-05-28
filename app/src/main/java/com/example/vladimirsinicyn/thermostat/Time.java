package sinitsynPackage;

import java.io.Serializable;

public class Time implements Serializable,  TimeInterface{

    private final int minutesInDay = 60 * 24;
    private int minutes;
    private int hours;

    public Time(int minutes) {

        parseTime(minutes);
    }

    @Override
    public boolean checkMidnight() {

        return minutes == 0 && hours == 0;
    }

    @Override
    public void incrementTime() {

        int temp = toMinutes();
        temp++;

        parseTime(temp);
    }

    private int toMinutes() {

        return minutes + hours * 60;
    }

    private void parseTime(int minutes) {

        minutes = minutes % minutesInDay;
        this.hours = minutes / 60;
        this.minutes = minutes % 60;
    }

    @Override
    public String toString() {

        return hours + ":" + minutes;
    }
}
