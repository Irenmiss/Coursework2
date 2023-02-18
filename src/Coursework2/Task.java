package Coursework2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public abstract class Task {
    private static int idGenerator = 0;
    private String title;
    private Type type;
    private final int id;
    private LocalDateTime dateTime;
    private String description;
    private final Repeatability repeatability;

    public Task(String title, Type type, String description, String dateTime, Repeatability repeatability) {
        idGenerator++;
        this.title = title;
        this.type = type;
        this.id = idGenerator;
        this.description = description;
        this.repeatability = repeatability;
        try {
            setDateTime(dateTime);
        } catch (IncorrectArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public Repeatability getRepeatability() {
        return repeatability;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LocalTime getTime() {
        return getDateTime().toLocalTime();
    }


    public void setDateTime(String dateTime) throws IncorrectArgumentException {
        try {
            this.dateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        } catch (DateTimeParseException e) {
            throw new IncorrectArgumentException("Некорректно указана дата, добавьте задачу повторно");
        }
    }

    public String getDescription() {
        return description;
    }

    public abstract boolean appearsIn(LocalDate date);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return title.equals(task.title) && type == task.type && dateTime.equals(task.dateTime) && description.equals(task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, type, dateTime, description);
    }

    @Override
    public String toString() {
        return "ID: " + id + ". Наименование: " + title + ". Описание: " + description + ". Тип задачи: " + type + ". Время выполнения: " + dateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
