package ue07.library;

import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private final HashMap<String, List<Book>> books;
    private final HashMap<String, Lender> lenders;

    public Library() {
        this.books = new HashMap();
        this.lenders = new HashMap();
    }

    public void addBook(String bookName) {
        // TODO
        if (!(books.containsKey(bookName))) {
            books.put(bookName, new ArrayList<Book>());
        }
        books.get(bookName).add(new Book(bookName));
    }

    public void addLender(String lenderName) throws IllegalStateException {
        // TODO
        if (lenders.containsKey(lenderName)) {
            throw new IllegalStateException("Lender already present!");
        }
        lenders.put(lenderName, new Lender(lenderName));
    }

    public void lendBook(String bookName, String lenderName) throws NoSuchElementException {
        // TODO
        if (!(books.containsKey(bookName)) || books.get(bookName).size() <= 0) {
            throw new NoSuchElementException("Book not in Library, or all books of that type lent!");
        }
        int index = books.get(bookName).lastIndexOf(bookName);
        lenders.get(lenderName).addBook(books.get(bookName).get(index));
        books.get(bookName).remove(index);
    }

    public void returnBook(String bookName, String lenderName) throws NoSuchElementException {
        // TODO
        if (!(books.containsKey(bookName))) {
            throw new NoSuchElementException("Book has never been in library!");
        }
        if (!(lenders.containsKey(lenderName))) {
            throw new NoSuchElementException("Lender not registered!");
        }
        Book returnedBook = lenders.get(lenderName).removeBook(bookName);
        returnedBook.incLendCount();
        books.get(bookName).add(returnedBook);
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