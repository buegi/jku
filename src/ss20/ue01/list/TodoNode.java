package ss20.ue01.list;

public final class TodoNode {

    private Todo todo;
    private TodoNode next;

    TodoNode(Todo todo) {
        this.todo = todo;
    }

    Todo getTodo() {
        return this.todo;
    }

    TodoNode getNext() {
        return this.next;
    }

    void setTodo(Todo todo) {
        this.todo = todo;
    }

    void setNext(TodoNode next) {
        this.next = next;
    }
}