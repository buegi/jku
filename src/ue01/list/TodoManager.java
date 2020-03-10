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
    public Todo[] addTodo(String description, LocalDate date) {
        return this.todoList.insert(description, date);
    }

    // Suche nach einem Todo mit gegebener Id
    public Todo[] getTodo(int id) {
        return todoList.get(id);
    }

    public Todo[] setDone(int id) {
        Todo[] finished = getTodo(id);
        for (Todo todo : finished) {
            todo.setState(State.DONE);
        }
        return finished;
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

    public Todo[] getAllOpenTodosPerDate(LocalDate date) {
        return todoList.get(date);
    }

    // die abgeschlossenen bis zu einem Datum löschen
    public Todo[] removeDoneTodosUntil(LocalDate date) {
        Todo[] toBeRemoved = getAllDoneTodosUntil(date);
        for (Todo todo : toBeRemoved) {
            todoList.remove(todoList.lookup(todo.getId()));
        }
        return toBeRemoved;
    }
}