package ue07.library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SortedSet;

public class Library {
    private HashMap<String, List<Book>> books;
    private HashMap<String, Lender> lenders;

    public Library() {
        this.books = new HashMap<String, List<Book>>();
        this.lenders = new HashMap<String, Lender>();
    }

    public void addBook(String bookName) {
        // TODO

    }

    public void addLender(String lenderName) throws IllegalStateException {
        // TODO
    }

    public void lendBook(String bookName, String lenderName) {
        // TODO
    }

    public void returnBook(String bookName, String lenderName) {
        // TODO
    }

    public List<Book> getAvailableBooksOrderedByAlphabet() {
        // TODO
        return null;
    }

    public SortedSet<Book> getAvailableBooksOrderedByLentCount() {
        // TODO

        return null;
    }

    public SortedSet<Lender> getLendersOrderedByName() {
        // TODO

        return null;
    }
}