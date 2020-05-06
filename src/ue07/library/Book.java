package ue07.library;

import java.util.Comparator;
import java.util.Objects;

public class Book implements Comparable<Book> {
    private final String name;
    private int lentCount;

    public Book(String name) {
        this.name = name;
        this.lentCount = 0;
    }

    public String getName() {
        return this.name;
    }

    public int getLentCount() {
        return this.lentCount;
    }

    protected void setLentCount(int newVal) {
        this.lentCount = newVal;
    }

    protected void incLendCount() {
        this.lentCount++;
    }

    @Override
    public String toString() {
        return "\"" + this.name + "\", was lent " + this.lentCount + " times.";
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getLentCount());
    }

    @Override
    public int compareTo(Book other) {
        return this.getName().compareTo(other.getName());
    }

    public static Comparator<Book> getNameComparator() {
        return Comparator.comparing(Book::getName);
    }

    public static Comparator<Book> getLentComparator() {
        return (b1, b2) -> {
            if (b1.lentCount == b2.lentCount) {
                return b1.getName().compareTo(b2.getName());
            }
            return b1.getLentCount() < b2.getLentCount() ? -1 : 1;
        };
    }
}