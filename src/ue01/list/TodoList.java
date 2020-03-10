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
        TodoNode previous = null;
        TodoNode current = head;
        while (current != null && date.isAfter(current.getTodo().getDate())) {
            previous = current;
            current = current.getNext();
        }
        TodoNode newNode = new TodoNode(new Todo(description, date));
        if (previous == null) {
            head = newNode;
        } else {
            previous.setNext(newNode);
        }
        newNode.setNext(current);
        noOfEntries++;
        return true;
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
        this.noOfEntries--;
    }


    public TodoNode lookup(int id) {
        if (head == null) {
            return null;
        }
        TodoNode current = head;
        while (current != null) {
            if (current.getTodo().getId() == id) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    private int count(LocalDate until, State state) {
        // TODO
        int counter = -1;

        if (until == null && state == null) {
            counter = noOfEntries;
            return counter;
        }

        if (until != null && state == null) {
            counter = 0;
            TodoNode current = head;
            while (current != null && (until.isBefore(current.getTodo().getDate()) || (until.isEqual(current.getTodo().getDate())))) {
                counter++;
                current = current.getNext();
            }
        }

        if (until == null && state != null) {
            counter = 0;
            TodoNode current = head;
            while (current != null) {
                if (state == current.getTodo().getState()) {
                    counter++;
                }
                current = current.getNext();
            }
        }

        if (until != null && state != null) {
            counter = 0;
            TodoNode current = head;
            while (current != null) {
                if (state == current.getTodo().getState() && (until.isBefore(current.getTodo().getDate()) || (until.isEqual(current.getTodo().getDate())))) {
                    counter++;
                }
                current = current.getNext();
            }
        }
        return counter;
    }

    Todo[] get(int id) {
        Todo[] temp = new Todo[1];
        TodoNode found = lookup(id);
        if (found == null) {
            return new Todo[0];
        } else {
            temp[0] = found.getTodo();
            return temp;
        }
    }

    Todo[] get(LocalDate until, State state) {
        int size = count(until, state);
        if (size <= 0) {
            return new Todo[0];
        }







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