package ue01.list;

import ue01.data.Todo;

public class TodoNode {

    private Todo todo;
    private TodoNode next;

    TodoNode(Todo todo, TodoNode next) {
        this.todo = todo;
        this.next = next;
    }

    public Todo getTodo() {
        return this.todo;
    }

    public TodoNode getNext() {
        return this.next;
    }

    public void setTodo(Todo todo) {
        this.todo = todo;
    }

    public void setNext(TodoNode next) {
        this.next = next;
    }
}