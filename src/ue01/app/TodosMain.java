package ue01.app;

import inout.In;
import inout.Out;
import ue01.list.TodoManager;

import java.time.LocalDate;

public class TodosMain {

    public static void main(String[] args) {

        TodoManager todoManager = new TodoManager();

        // addTestData();

        // TODO: Test TodoManager

        char menuAction;
        do {
            printMenu();
            menuAction = In.readChar();

            switch (menuAction) {

                case 'a':
                    addTestData(todoManager);
                    break;

                case 'b':
                    todoManager.printAllTodos();
                    break;

                case 'c':
                    LocalDate cDate = inputDate();
                    todoManager.printAllDoneTodosUntilDate(cDate);
                    break;

                case 'd':
                    todoManager.setTodosToDone();
                    break;

                case 'e':
                    todoManager.printAllDoneTodos();
                    break;

                case 'f':
                    todoManager.printAllOpenTodos();
                    break;

                case 'g':
                    LocalDate gDate = inputDate();
                    todoManager.printAllOpenTodosPerDate(gDate);
                    break;

                case 'h':

                    break;

                case 'i':
                    String description = inputDescription();
                    LocalDate iDate = inputDate();
                    todoManager.addTodo(description, iDate);
                    break;

                case 'x':
                    System.out.println("Bye!");
                    break;

                default:
                    System.out.println("Sorry, undefined input!");
                    break;
            }
        } while (menuAction != 'x');
    }

    private static void addTestData(TodoManager todoManager) {
        In.open("todos.txt");
        if (!In.done()) {
            Out.println("Cannot open file todos.txt");
            return;
        }
        int year = In.readInt();
        while (In.done()) {
            int month = In.readInt();
            int day = In.readInt();
            String description = In.readString();
            // TODO: Add todo with year, month, and day to manager
            year = In.readInt();
            todoManager.addTodo(description, LocalDate.of(year, month, day));
        }
        In.close();
    }

    private static void printMenu() {
        System.out.println("===============================================");
        System.out.println("(a) Add test data");
        System.out.println("(b) Print all todos");
        System.out.println("(c) Print all todos until a specific date");
        System.out.println("(d) Set specific todos to done");
        System.out.println("(e) Print all done todos");
        System.out.println("(f) Print all open todos");
        System.out.println("(g) Print all open todos for a specific date");
        System.out.println("(h) Delete all done todos until a specific date");
        System.out.println("(i) Add Todo per description and date");
        System.out.println("(x) End");
        System.out.println("===============================================");
        System.out.print("What do you want to do? : ");
    }

    private static LocalDate inputDate() {
        System.out.print("Please input year: ");
        int year = In.readInt();
        System.out.println();
        System.out.print("Please input month: ");
        int month = In.readInt();
        System.out.println();
        System.out.print("Please input day: ");
        int day = In.readInt();
        System.out.println();
        LocalDate date = LocalDate.of(year, month, day);
        return date;
    }

    private static String inputDescription() {
        System.out.println();
        String description = In.readString();
        return description;
    }

    private static int inputId() {
        System.out.println();
        int id = In.readInt();
        return id;
    }
}