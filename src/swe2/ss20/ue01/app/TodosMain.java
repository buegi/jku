package swe2.ss20.ue01.app;

import swe2.inout.In;
import swe2.inout.Out;
import swe2.ss20.ue01.list.Todo;
import swe2.ss20.ue01.list.TodoManager;

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
                    System.out.println("Op (a), Add test data: ");
                    addTestData(todoManager);
                    break;

                case 'b':
                    System.out.println("Op (b), Print all todos: ");
                    printTodos(todoManager.getAllTodos());
                    break;

                case 'c':
                    System.out.println("Op (c), Print all todos until a specific date: ");
                    printTodos(todoManager.getAllTodosUntil(inputDate()));
                    break;

                case 'd':
                    System.out.println("Op (d), Set todo to done: ");
                    int toFinish = inputTodos();
                    Todo[] temp = todoManager.setDone(toFinish);
                    printTodos(temp);
                    break;

                case 'e':
                    System.out.println("Op (e), Print all done todos: ");
                    printTodos(todoManager.getAllDoneTodos());
                    break;

                case 'f':
                    System.out.println("Op (f) Print all open todos: ");
                    printTodos(todoManager.getAllOpenTodos());
                    break;

                case 'g':
                    System.out.println("Op (g), Print all open todos for a specific date: ");
                    printTodos(todoManager.getAllOpenTodosPerDate(inputDate()));
                    break;

                case 'h':
                    System.out.println("Op (h) Delete all done todos until a specific date: ");
                    printTodos(todoManager.removeDoneTodosUntil(inputDate()));
                    break;

                case 'x':
                    System.out.println("Op (x), Bye!");
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
            printTodos(todoManager.addTodo(description, LocalDate.of(year, month, day)));
        }
        In.close();
    }

    private static void printMenu() {
        System.out.println("====================   Menu   =========================");
        System.out.println("(a) Add test data");
        System.out.println("(b) Print all todos");
        System.out.println("(c) Print all todos until a specific date");
        System.out.println("(d) Set todo to done");
        System.out.println("(e) Print all done todos");
        System.out.println("(f) Print all open todos");
        System.out.println("(g) Print all open todos for a specific date");
        System.out.println("(h) Delete all done todos until a specific date");
        System.out.println("(x) End");
        System.out.println("=======================================================");
        System.out.print("What do you want to do? : ");
    }

    private static LocalDate inputDate() {
        System.out.print("Please input year: ");
        int year = In.readInt();
        System.out.print("Please input month: ");
        int month = In.readInt();
        System.out.print("Please input day: ");
        int day = In.readInt();
        if (month < 1 || month > 12 || day < 1 || day > 31) {
            System.out.println("Invalid input");
            return null;
        }
        LocalDate date = LocalDate.of(year, month, day);
        return date;
    }

    private static int inputTodos() {
        System.out.println("Please enter the Todo you want to set done:");
        int setDone = In.readInt();
        return setDone;
    }

    private static void printTodos(Todo[] todoList) {
        if (todoList.length <= 0) {
            System.out.println("No Todos for your input found!");
            return;
        }
        for (Todo todo : todoList) {
            System.out.println(todo.toString());
        }
    }
}