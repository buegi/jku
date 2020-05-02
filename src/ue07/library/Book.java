package ue07.library;

import java.util.Comparator;

public class Book {

    private final String name;
    private int lendCount;

    public Book(String name) {
        this.name = name;
        this.lendCount = 0;
    }

    public String getName() {
        return this.name;
    }

    public int getLendCount() {
        return this.lendCount;
    }

    protected void incLendCount() {
        this.lendCount++;
    }

    @Override
    public String toString() {
        return "Title: " + this.name + ", wurde bisher " + this.lendCount + " mal ausgeliehen.";
    }

    public static Comparator<Book> getNameComparator() {
        Comparator<Book> bookNameComparator = Comparator.comparing(Book::getName);
        return bookNameComparator;
    }
}