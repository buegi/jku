package ue01.list;

import ue01.data.State;
import ue01.data.Todo;

import java.time.LocalDate;

public class TodoManager {

    private TodoList todoList;

    public TodoManager() {
        this.todoList = new TodoList();
    }

    // Anfügen eines neuen Todos mit Beschreibung und Datum
    public void addTodo(String description, LocalDate date) {
        boolean success = this.todoList.insert(description, date);
        if (success) {
            System.out.print("Todo successfully added!");
        } else {
            System.out.print("Todo could not be added!");
        }
    }

    // Suche nach einem Todo mit gegebener Id
    public Todo[] getTodo(int id) {
        return todoList.get(id);
    }

    // Zugriff auf alle Todos
    public Todo[] getAllTodos() {
        return todoList.get(null, null);
    }

    // Zugriff auf alle Todos bis zu einem bestimmten Datum
    public Todo[] getAllTodosUntil(LocalDate date) {
        return todoList.get(date, null);
    }

    // Zugriff auf alle offenen Todos
    public Todo[] getAllOpenTodos() {
        return todoList.get(null, State.OPEN);
    }

    // Zugriff auf offene Todos bis zu einem bestimmten Datum
    public Todo[] getAllOpenTodosUntil(LocalDate date) {
        return todoList.get(date, State.OPEN);
    }

    // Zugriff auf alle erledigten Todos
    public Todo[] getAllDoneTodos() {
        return todoList.get(null, State.DONE);
    }

    // Zugriff auf erledigte Todos bis zu einem bestimmten Datum
    public Todo[] getAllDoneTodosUntil(LocalDate date) {
        return todoList.get(date, State.DONE);
    }

    // die abgeschlossenen bis zu einem Datum löschen
    public void removeDoneTodosUntil(LocalDate date) {
        Todo[] toBeRemoved = getAllDoneTodosUntil(date);
        for (int i = 0; i < toBeRemoved.length; i++) {
            todoList.remove(todoList.lookup(toBeRemoved[i].getId()));
        }
    }
}