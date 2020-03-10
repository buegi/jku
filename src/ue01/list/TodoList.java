package ue01.list;

import ue01.data.State;
import ue01.data.Todo;

import java.time.LocalDate;

public class TodoList {

    private TodoNode head;
    private int noOfEntries;

    public TodoList() {
        this.head = null;
        this.noOfEntries = 0;
    }

    public TodoNode getHead() {
        return this.head;
    }

    void setHead(TodoNode newHead) {
        this.head = newHead;
    }

    public boolean isEmpty() {
        return this.noOfEntries == 0;
    }

    public boolean insert(String description, LocalDate date) {
        // TODO
        return false;
    }

    public void remove(TodoNode node) {
        if (node == head) {
            head = head.getNext();
        }
        TodoNode prev = head;
        while (prev.getNext() != node) {
            prev = prev.getNext();
        }
        prev.setNext(node.getNext());

        // TODO

    }

    public Todo lookup(String name) {
        // TODO
        return null;
    }

    public Todo lookup(int id) {
        // TODO
        return null;
    }

    private int count(LocalDate until, State state) {
        // TODO
        return 0;
    }


    Todo[] get(LocalDate until, State state) {
        int size = count(until, state);
        Todo[] todoList = new Todo[size];
        int counter = 0;
        TodoNode temp = head;
        while (temp != null) {
            todoList[counter] = temp.getTodo();
            temp = temp.getNext();
            counter++;
        }
        return todoList;
    }
}