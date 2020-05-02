package ue07.library;

import java.util.TreeSet;

public class Lender implements Comparable<Lender> {
    private final String name;
    private TreeSet<Book> lentBooks;

    public Lender(String name) {
        this.name = name;
        this.lentBooks = new TreeSet<Book>();
    }

    public String getName() {
        return this.name;
    }

    protected void addBook(Book newBook) throws IllegalArgumentException {
        if (this.lentBooks.contains(newBook)) {
            throw new IllegalArgumentException("Book already lent!");
        }
        this.lentBooks.add(newBook);
    }

    protected void removeBook(String bookName) {
        // TODO
    }


    @Override
    public int compareTo(Lender o) {
        // TODO
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        // TODO
        return false;
    }

    @Override
    public int hashCode() {
        // TODO
        return 0;
    }

    @Override
    public String toString() {
        // TODO
        return null;
    }
}