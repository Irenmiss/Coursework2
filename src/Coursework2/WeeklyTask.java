package Coursework2;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class WeeklyTask extends Task {
    public WeeklyTask(String title, Type type, String description, String dateTime, Repeatability repeatability) {
        super(title, type, description, dateTime, repeatability);
    }

    public boolean appearsIn(LocalDate date) {
        long daysBetween = ChronoUnit.DAYS.between(getDateTime().toLocalDate(), date);
        return (daysBetween >= 0 && daysBetween % 7 == 0);
    }

    @Override
    public String toString() {
        return super.toString() + ". Еженедельная задача.";
    }

}
