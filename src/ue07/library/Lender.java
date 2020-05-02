package ue07.library;

import java.util.Objects;
import java.util.SortedSet;
import java.util.StringJoiner;
import java.util.TreeSet;

public class Lender implements Comparable<Lender> {
    private final String name;
    private SortedSet<Book> lentBooks;

    public Lender(String name) {
        this.name = name;
        this.lentBooks = new TreeSet<Book>();
    }

    public String getName() {
        return this.name;
    }

    protected void addBook(Book newBook) throws IllegalStateException {
        if (this.lentBooks.contains(newBook)) {
            throw new IllegalStateException("Book already lent!");
        }
        this.lentBooks.add(newBook);
    }

    protected Book removeBook(String bookName) throws IllegalStateException {
        if (!(lentBooks.contains(bookName))) {
            throw new IllegalStateException("Book not lent by lender!");
        }
        Book returnedBook = null;
        for (Book book : this.lentBooks) {
            if (book.getName().equals(bookName)) {
                this.lentBooks.remove(bookName);
                returnedBook = book;
                break;
            }
        }
        return returnedBook;
    }

    @Override
    public int compareTo(Lender other) {
        return this.getName().compareTo(other.getName());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Lender lender = (Lender) obj;

        return lender.getName() == this.getName();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        this.lentBooks.forEach(a -> sj.add(a.getName()));
        return this.getName() + sj.toString();
    }
}