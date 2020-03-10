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

    public Todo[] insert(String description, LocalDate date) {
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
        Todo[] val = new Todo[1];
        val[0] = newNode.getTodo();
        return val;
    }

    public void remove(TodoNode node) {
        if (node == null) {
            return;
        }
        if (node == head) {
            head = head.getNext();
        }
        TodoNode previous = head;
        while (previous.getNext() != node) {
            previous = previous.getNext();
        }
        previous.setNext(node.getNext());
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
            while (current != null && (current.getTodo().getDate().isEqual(until) || (current.getTodo().getDate().isBefore(until)))) {
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
                if (state == current.getTodo().getState() && (current.getTodo().getDate().isEqual(until) || (current.getTodo().getDate().isBefore(until)))) {
                    counter++;
                }
                current = current.getNext();
            }
        }
        return counter;
    }

    Todo[] get(LocalDate strictDate) {
        int size = 0;
        TodoNode current = head;
        while (current != null) {
            if (current.getTodo().getDate().isEqual(strictDate) && current.getTodo().getState() == State.OPEN) {
                size++;
            }
            current = current.getNext();
        }
        current = head;
        Todo[] todoList = new Todo[size];
        int counter = 0;
        while (current != null) {
            if (current.getTodo().getDate().isEqual(strictDate) && current.getTodo().getState() == State.OPEN) {
                todoList[counter] = current.getTodo();
                counter++;
            }
            current = current.getNext();
        }
        return todoList;
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

        int counter = 0;
        Todo[] todoList = new Todo[size];

        if (until == null && state == null) {
            TodoNode current = head;
            while (current != null) {
                todoList[counter] = current.getTodo();
                current = current.getNext();
                counter++;
            }
        }

        if (until != null && state == null) {
            TodoNode current = head;
            while (current != null && (until.isAfter(current.getTodo().getDate()) || (until.isEqual(current.getTodo().getDate())))) {
                todoList[counter] = current.getTodo();
                counter++;
                current = current.getNext();
            }
        }

        if (until == null && state != null) {
            TodoNode current = head;
            while (current != null) {
                if (state == current.getTodo().getState()) {
                    todoList[counter] = current.getTodo();
                    counter++;
                }
                current = current.getNext();
            }
        }

        if (until != null && state != null) {
            TodoNode current = head;
            while (current != null) {
                if (current.getTodo().getState() == state && (until.isAfter(current.getTodo().getDate()) || (until.isEqual(current.getTodo().getDate())))) {
                    todoList[counter] = current.getTodo();
                    counter++;
                }
                current = current.getNext();
            }
        }
        return todoList;
    }
}