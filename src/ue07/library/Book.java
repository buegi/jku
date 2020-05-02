package ue07.library;

import java.util.Comparator;

public class Book {
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

    protected void incLendCount() {
        this.lentCount++;
    }

    @Override
    public String toString() {
        return "Title: " + this.name + ", wurde bisher " + this.lentCount + " mal ausgeliehen.";
    }

    public static Comparator<Book> getLentComparator() {
        // TODO
        /*  public static Comparator<Book> getNameComparator(), liefert einen Comparator, um Buecher nach Namen
            alphabetisch aufsteigend zu sortieren. Nutzen Sie Comparator.comparing, um einen passenden
            Comparator zu erzeugen */
        return null;
    }


    public static Comparator<Book> getNameComparator() {
        // TODO
        /*  public static Comparator<Book> getLentComparator(),  liefert einen Comparator, um Buecher nach
            Leihvorgangszaehler zu sortieren. Ist der Leihvorgangszaehler gleich, soll alphabetisch aufsteigend nach
            Namen sortiert werden. Implementieren Sie den Comparator als anonyme Klasse direkt in der Methode */
        Comparator<Book> bookNameComparator = Comparator.comparing(Book::getName);
        return bookNameComparator;
    }
}