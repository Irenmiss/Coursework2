package Coursework2;

public enum Type {

    WORK("Рабочая задача"),
    PERSONAL("Личная задача");

    private final String value;

    Type(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
