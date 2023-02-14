package Coursework2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.Scanner;

public class TaskService {
    private static final Map<Integer, Task> taskMap = new HashMap<>();
    private static Set<Task> removedTasks = new HashSet<>();

    public static void addTask(Task task) {
        if (Objects.nonNull(task.getDateTime()) && Objects.nonNull(task.getType()) && !task.getTitle().isEmpty() && !task.getDescription().isEmpty()) {
            taskMap.put(task.getId(), task);
            System.out.println("Добавлена задача:\n" + task);
        } else {
            System.out.println();
            System.out.println("Заполнены не все данные, задача не добавлена");
            System.out.println();
        }
    }

    public static void removeTask(int idNum) throws TaskNotFoundException {
        Task task = taskMap.get(idNum);
        removedTasks.add(task);
        if (taskMap.values().removeIf(i -> i.getId() == idNum)) {
            System.out.println("Задача под номером " + idNum + " перемещена в архив");
        } else {
            throw new TaskNotFoundException("Задача с указанным id не существует");
        }
    }

    public static void listRemovedTasks() {
        System.out.println("Список помещённых в архив задач: ");
        removedTasks.forEach(System.out::println);
        if (removedTasks.size() == 0) {
            System.out.println("В архиве отсутствуют удалённые задачи");
        }
    }

    public static void taskList() {
        System.out.println("Список текущих задач: ");
        for (Map.Entry<Integer, Task> taskMap : taskMap.entrySet()) {
            System.out.println(taskMap.getKey() + " " + taskMap.getValue());
        }
    }

    public static void getAllByDate(LocalDate date) throws TaskNotFoundException {
        for (Map.Entry<Integer, Task> taskMap : taskMap.entrySet()) {
            LocalDate taskDate = taskMap.getValue().getDateTime().toLocalDate();
            if (taskDate.equals(date)) {
                System.out.println(taskMap.getKey() + " " + taskMap.getValue());
            } else if (date.isAfter(taskDate) && taskMap.getValue().appearsIn(date)) {
                System.out.println(taskMap.getKey() + " " + taskMap.getValue());
            } else throw new TaskNotFoundException("На указанную дату задачи не запланированы");
        }
    }

    public static int checkId(Scanner scanner) throws IncorrectArgumentException {
        int intId;
        try {
            intId = scanner.nextInt();
        } catch (InputMismatchException e) {
            throw new IncorrectArgumentException("Некорректный формат данных. Введите число");
        }
        return intId;
    }

    public static Type typeTask(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Выберите тип задачи: \n1. Личная \n2. Рабочая");
                Type type = null;
                int taskType = scanner.nextInt();
                if (taskType == 1) {
                    type = Type.PERSONAL;
                } else if (taskType == 2) {
                    type = Type.WORK;
                }
                return type;
            } catch (NumberFormatException e) {
                System.out.println("Некорректно указан тип задачи, укажите номер");
            }
        }
    }

    public static void inputTask(Scanner scanner) throws IncorrectArgumentException {
        System.out.println("Введите название задачи: ");
        scanner.nextLine();
        String taskTitle = scanner.nextLine();
        System.out.println("Введите описание задачи: ");
        String taskDescription = scanner.nextLine();
        System.out.println("Введите дату выполнения задачи в формате [dd.MM.yyyy HH:mm]: ");
        String taskDate = scanner.nextLine();
        Type typeTask;
        label:
        while (true) {
            System.out.println("Выберите повторяемость задачи: \n1. Однократная \n2. Ежедневная \n3. Еженедельная \n4. Ежемесячная \n5. Ежегодная");
            if (scanner.hasNextInt()) {
                int replayTask = scanner.nextInt();
                switch (replayTask) {
                    case 1:
                        typeTask = typeTask(scanner);
                        addTask(new OneTimeTask(taskTitle, typeTask, taskDescription, taskDate, Repeatability.TASK_ONE_TIME));
                        break label;
                    case 2:
                        typeTask = typeTask(scanner);
                        addTask(new DailyTask(taskTitle, typeTask, taskDescription, taskDate, Repeatability.TASK_DAILY));
                        break label;
                    case 3:
                        typeTask = typeTask(scanner);
                        addTask(new WeeklyTask(taskTitle, typeTask, taskDescription, taskDate, Repeatability.TASK_WEEKLY));
                        break label;
                    case 4:
                        typeTask = typeTask(scanner);
                        addTask(new MonthlyTask(taskTitle, typeTask, taskDescription, taskDate, Repeatability.TASK_MONTH));
                        break label;
                    case 5:
                        typeTask = typeTask(scanner);
                        addTask(new YearlyTask(taskTitle, typeTask, taskDescription, taskDate, Repeatability.TASK_YEARLY));
                        break label;
                    default:
                        System.out.println("Выберите число от 1 до 5");
                        break;
                }
            } else {
                scanner.next();
            }
        }
    }

    public static void delDask(Scanner scanner) throws IncorrectArgumentException {
        int inputId = checkId(scanner);
        try {
            TaskService.removeTask(inputId);
        } catch (TaskNotFoundException e) {
            System.out.println();
            System.out.println(e.getMessage());
            System.out.println();
        }
    }

    public static void dateFormat(Scanner scanner) throws IncorrectArgumentException {
        LocalDate date;
        try {
            scanner.nextLine();
            String str = scanner.nextLine();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            date = LocalDate.parse(str, dtf);
        } catch (DateTimeParseException e) {
            throw new IncorrectArgumentException("Некорректный формат даты");
        }
        try {
            TaskService.getAllByDate(date);
        } catch (TaskNotFoundException e) {
            System.out.println();
            System.out.println(e.getMessage());
            System.out.println();
        }
    }

    public static void editTitle(Scanner scanner) throws TaskNotFoundException {
        int inputId = scanner.nextInt();
        if (taskMap.containsKey(inputId)) {
            System.out.println("Введите новое название задачи:");
            scanner.nextLine();
            String newTitle = scanner.nextLine();
            taskMap.get(inputId).setTitle(newTitle);
            System.out.println("Название  задачи под номером " + inputId + " изменено на " + newTitle);
        } else {
            throw new TaskNotFoundException("Задача с указанным id не существует");
        }
    }

    public static void editDescription(Scanner scanner) throws TaskNotFoundException {
        int inputId = scanner.nextInt();
        if (taskMap.containsKey(inputId)) {
            System.out.println("Введите новое описание:");
            scanner.nextLine();
            String newDescription = scanner.nextLine();
            taskMap.get(inputId).setDescription(newDescription);
            System.out.println("Описание задачи под номером " + inputId + " изменено на " + newDescription);
        } else {
            throw new TaskNotFoundException("Задача с указанным id не существует");
        }
    }
}




