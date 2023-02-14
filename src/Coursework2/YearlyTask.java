package Coursework2;

import java.time.LocalDate;
import java.time.Period;

public class YearlyTask extends Task {

    public YearlyTask(String title, Type type, String description, String dateTime, Repeatability repeatability) {
        super(title, type, description, dateTime, repeatability);
    }

    public boolean appearsIn(LocalDate date) {
        Period period = Period.between(getDateTime().toLocalDate(), date);
        return period.getYears() >= 0 && period.getMonths() == 0 && period.getDays() == 0;
    }

    @Override
    public String toString() {
        return super.toString() + ". Ежегодная задача.";
    }
}
