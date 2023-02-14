package Coursework2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DailyTask extends Task {


    public DailyTask(String title, Type type, String description, String dateTime, Repeatability repeatability) {
        super(title, type, description, dateTime, repeatability);
    }

    public boolean appearsIn(LocalDate date) {
        return date.equals(getDateTime().toLocalDate()) || date.isAfter(getDateTime().toLocalDate());
    }
    @Override
    public String toString() {
        return super.toString() + ". Ежедневная задача.";
    }

}
