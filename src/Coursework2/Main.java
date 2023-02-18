package Coursework2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Task test1 = new OneTimeTask("Название 1", Type.PERSONAL, "Тест 1", "14.02.2023 12:00", Repeatability.TASK_ONE_TIME);
        Task test2 = new WeeklyTask("Название 2", Type.WORK, "Тест 2", "31.01.2023 10:00", Repeatability.TASK_DAILY);
        TaskService.addTask(test1);
        TaskService.addTask(test2);

        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                System.out.println("Выберите пункт меню: ");
                System.out.println("1. Добавить задачу\n2. Удалить задачу\n3. Получить задачу на указанный день\n4. Редактировать наименование задачи\n5. Редактировать описание задачи\n6. Вывести список всех задач\n7. Вывести список удалённых задач\n0. Выход");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            try {
                                TaskService.inputTask(scanner);
                            } catch (IncorrectArgumentException e) {
                                System.out.println();
                                System.out.println(e.getMessage());
                                System.out.println();
                            }
                            break;
                        case 2:
                            System.out.print("Укажите id задачи, которую требуется удалить: ");
                            try {
                                TaskService.delDask(scanner);
                            } catch (IncorrectArgumentException e) {
                                System.out.println();
                                System.out.println(e.getMessage());
                                System.out.println();
                            }
                            break;
                        case 3:
                            System.out.println("Введите дату [dd.MM.yyyy]: ");
                            try {
                                TaskService.dateFormat(scanner);
                            } catch (IncorrectArgumentException e) {
                                System.out.println();
                                System.out.println(e.getMessage());
                                System.out.println();
                            }
                            break;
                        case 4:
                            System.out.print("Укажите id задачи для редактирования наименования: ");
                            try {
                                TaskService.editTitle(scanner);
                            } catch (TaskNotFoundException e) {
                                System.out.println();
                                System.out.println(e.getMessage());
                                System.out.println();
                            }
                            break;
                        case 5:
                            System.out.print("Укажите id задачи для редактирования описания: ");
                            try {
                                TaskService.editDescription(scanner);
                            } catch (TaskNotFoundException e) {
                                System.out.println();
                                System.out.println(e.getMessage());
                                System.out.println();
                            }
                            break;
                        case 6:
                            TaskService.taskList();
                            break;
                        case 7:
                            TaskService.listRemovedTasks();
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.nextLine();
                    System.out.println("Выберите пункт меню из списка");
                }
            }
        }
    }
}