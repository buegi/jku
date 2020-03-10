package ue01.app;

import inout.In;
import inout.Out;
import ue01.data.Todo;
import ue01.list.TodoList;
import ue01.list.TodoManager;

import java.time.LocalDate;

public class TodosMain {

    public static void main(String[] args) {

        TodoManager todoManager = new TodoManager();

        char menuAction;
        do {
            printMenu();
            menuAction = In.readChar();

            switch (menuAction) {

                case 'a':
                    // aus den Daten ihren TodoManager befüllen
                    addTestData(todoManager);
                    break;

                case 'b':
                    // alle Todos ausgeben
                    printTodos(todoManager.getAllTodos());
                    break;

                case 'c':
                    // die Todos bis zu einem bestimmten Datum ausgeben
                    printTodos(todoManager.getAllTodosUntil(inputDate()));
                    break;

                case 'd':
                    // einige Todos abschließen
                    // TODO
                    break;

                case 'e':
                    // die abgeschlossenen Todos ausgeben
                    printTodos(todoManager.getAllDoneTodos());
                    break;

                case 'f':
                    // die noch offenen Todos ausgeben
                    printTodos(todoManager.getAllOpenTodos());
                    break;

                case 'g':
                    // die offenen zu einem bestimmten Datum ausgeben
                    printTodos(todoManager.getAllOpenTodosUntil(inputDate()));
                    break;

                case 'h':
                    // die abgeschlossenen bis einem Datum löschen
                    todoManager.removeDoneTodosUntil(inputDate());
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
        In.open("src/ue01/app/todos.txt");
        if (!In.done()) {
            Out.println("Cannot open file todos.txt");
            return;
        }
        int year = In.readInt();
        while (In.done()) {
            int month = In.readInt();
            int day = In.readInt();
            String description = In.readString();
            year = In.readInt();
            // TODO: Add todo with year, month, and day to manager
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

    private static void printTodos(Todo[] todoList) {
        for (int i = 0; i < todoList.length; i++) {
            System.out.println(todoList[i].toString());
        }
    }
}