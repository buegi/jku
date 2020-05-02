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
        int index = books.get(bookName).size() - 1;
        if (!(books.containsKey(bookName)) || (index == -1)) {
            throw new NoSuchElementException("Book not in Library, or all books of that type lent!");
        }
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
        final List<Book> bookList = new ArrayList<>();
        this.books.values().stream().forEach(l -> l.forEach(b -> bookList.add(b)));
        bookList.sort(Book.getLentComparator());
        return bookList;
    }

    public SortedSet<Book> getAvailableBooksOrderedByLentCount() {
        // TODO
        return null;
    }

    public SortedSet<Lender> getLendersOrderedByName() {
        final TreeSet<Lender> lenderSet = new TreeSet<>();
        this.lenders.values().stream().forEach((l -> lenderSet.add(l)));
        return lenderSet;
    }
}