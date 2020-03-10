package ue01.list;

import ue01.data.Todo;

import java.time.LocalDate;

public class TodoManager {

    private TodoList todoList;

    public TodoManager() {
        this.todoList = new TodoList();
    }

    public void printAllTodos() {
        //TODO
        System.out.println("All Todos: ");
        System.out.println("===========");
        System.out.println("TODO 1");
        System.out.println("TODO 2");
        System.out.println("TODO 3");
        System.out.println("TODO 4");
        System.out.println("TODO 5");
        System.out.println("===========");
    }

    public void addTodo(String description, LocalDate date) {
        boolean success = this.todoList.insert(description, date);
        if (success) {
            System.out.print("Todo successfully added!");
        } else {
            System.out.print("Todo could not be added!");
        }
    }

    public Todo getTodo(int id) {
        return todoList.lookup(id);
    }



    public void printAllOpenTodosPerDate(LocalDate date) {
        // TODO
    }

    public void printAllOpenTodos() {
        // TODO
    }

    public void printAllOpenTodosUntilDate() {
        // TODO
    }

    public void printAllDoneTodos() {
        // TODO
    }

    public void setTodosToDone() {
        // TODO
    }

    public void printAllDoneTodosUntilDate(LocalDate date) {
        // TODO
    }

    public void deleteAllDoneTodosUntilDate(LocalDate date) {
        // TODO
    }

}
