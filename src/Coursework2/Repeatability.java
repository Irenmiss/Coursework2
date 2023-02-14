package Coursework2;

public enum Repeatability {
    TASK_ONE_TIME ("Однократная задача"),
    TASK_DAILY ("Ежедневная задача"),
    TASK_WEEKLY ("Еженедельная задача"),
    TASK_MONTH ("Ежемесячная задача"),
    TASK_YEARLY ("Ежегодная задача");
    private final String repeatability;

    Repeatability(String repeatability) {
        this.repeatability = repeatability;
    }
    public String getRepeatability() {
        return repeatability;
    }

    @Override
    public String toString() {
        return getRepeatability();
    }
}
