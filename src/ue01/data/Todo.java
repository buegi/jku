package ue01.data;

import java.time.LocalDate;

public class Todo {
    private final int id;
    private final String description;
    private final LocalDate date;
    private State state;

    private static int counter = 0;

    public Todo(String description, LocalDate date, State state) {
        this.id = counter++;
        this.description = description;
        this.date = date;
        this.state = state;
    }

    // getters
    public int getId() {
        return this.id;
    }

    public String getDescr() {
        return this.description;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public State getState() {
        return this.state;
    }

    // setter(s)
    public void setState(State newState) {
        this.state = newState;
    }

    // Override
    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", state=" + state +
                '}';
    }
}