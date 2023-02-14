package Coursework2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class OneTimeTask extends Task {

    public OneTimeTask(String title, Type type, String description, String dateTime, Repeatability repeatability) {
        super(title, type, description, dateTime, repeatability);
    }

    public boolean appearsIn(LocalDate date) {

        return Objects.equals(getDateTime().toLocalDate(), date);
    }
    @Override
    public String toString() {
        return super.toString() + ". Однократная задача." ;
    }

}
