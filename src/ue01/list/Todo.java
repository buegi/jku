package ue01.list;

import java.time.LocalDate;

public class Todo {
    private final int id;
    private final String description;
    private final LocalDate date;
    private State state;

    private static int counter = 0;

    public enum State {
        OPEN, DONE
    }

    Todo(String description, LocalDate date) {
        this.id = counter++;
        this.description = description;
        this.date = date;
        this.state = State.OPEN;
    }

    int getId() {
        return this.id;
    }

    String getDescr() {
        return this.description;
    }

    LocalDate getDate() {
        return this.date;
    }

    State getState() {
        return this.state;
    }

    void setState(State newState) {
        this.state = newState;
    }

    // Override
    @Override
    public String toString() {
        return (this.id + ": " + this.date + " - " + String.format("%-32s", this.description) + " : " + this.state);
    }
}