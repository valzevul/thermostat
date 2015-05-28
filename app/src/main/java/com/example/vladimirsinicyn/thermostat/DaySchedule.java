package sinitsynPackage;

import java.util.ArrayList;

public class DaySchedule {

    private ArrayList<TemperatureChange> changes;

    private int dayChanges = 0;
    private int nightChanges = 0;
    public DaySchedule() {

        changes = new ArrayList<TemperatureChange>();
    }

    public void addChange(TemperatureChange change) throws Exception {

        if (find(change.getTime())!= null) {

            throw new Exception("Similar times mustn't present.");
        }

        if (change.getType() == ChangeType.DAY) {

            dayChanges++;
        } else {
            nightChanges++;
        }

        if (dayChanges == 6) {
            dayChanges--;
            throw new Exception("Too many day changes.");
        }

        if (nightChanges == 6) {
            nightChanges--;
            throw new Exception("Too many night changes.");
        }

        changes.add(change);
    }

    public void deleteTimeChange(TemperatureChange change) throws Exception {

        if (change.getType() == ChangeType.DAY) {
            dayChanges--;
        } else {
            nightChanges--;
        }

        for (int i = 0; i < changes.size(); i++) {
            if (changes.get(i).equals(change)) {

                changes.remove(changes.get(i));
                break;
            }
        }
    }

    public TemperatureChange find(Time time) {

        for (int i = 0; i < changes.size(); i++) {
            if (time.equals(changes.get(i).getTime())) {
                return changes.get(i);
            }
        }

        return null;
    }

    public void print() {

        for (int i = 0; i < changes.size(); i++) {
            System.out.println("type: " + changes.get(i).getType() + " time: " + changes.get(i).getTime().toString());
        }
    }

    public ArrayList<TemperatureChange> getChanges() {

        return changes;
    }
}
