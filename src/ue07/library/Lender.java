package ue07.library;

import java.util.SortedSet;
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

    /*  public static Comparator<Book> getNameComparator(), liefert einen Comparator, um Buecher nach Namen
        alphabetisch aufsteigend zu sortieren. Nutzen Sie Comparator.comparing, um einen passenden
        Comparator zu erzeugen */


    protected void addBook(Book newBook) throws IllegalStateException {
        if (this.lentBooks.contains(newBook)) {
            throw new IllegalStateException("Book already lent!");
        }
        this.lentBooks.add(newBook);
    }

    protected void removeBook(String bookName) {
        // TODO
    }

    /*  Implementieren Sie das Interface Comparable<Lender> sowie die Objekt-Methoden equals und hashCode
        Vergleichen Sie den Namen des Kunden. Benutzen Sie Objects.equals und Objects.hash in Ihren Methoden */

    /*  protected Book remove(String bookName), entfernt ein ausgeliehenes Buch vom Kunden und liefert es
        zurück. Durchlaufen Sie die geliehen Bücher nur soweit wie nötig, um das Buch zu finden. Verwenden
        Sie dazu den Iterator des TreeSet */

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
        /* public String toString(), liefert eine Beschreibung aus Namen und aktuell geliehener Bücher */
        return null;
    }
}