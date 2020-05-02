package ue07;

import ue07.library.Library;

public class Main {

    public static void main(String[] args) {

        /* TODO - Fügen Sie in die Bibliothek mindestens drei Bücher ein, darunter auch welche mit gleichem Namen, um
                  die Entwicklung der Leihvorgänge zu sehen
                - Fügen Sie mindestens zwei Kunden in die Bibliothek ein
                - Testen Sie die Leih- und Rückgabe-Methode, insbesondere alle Fälle wo Exceptions geworfen werden.
                  Im folgenden Beispiel versucht Susi das Buch Clean Code mehrfach auszuleihen, was laut unseren
                  Bibliotheksregeln verboten ist. Sie können diesen Code als Muster nutzen, wenn Sie auf erwartete
                  Exceptions testen müssen.
                  try {
                  library.lendBook("Clean Code", "Susi");
                  System.out.println("Error: Susi should not be able to lend a book doubly!");
                  } catch (Exception exc) {
                  System.out.println("OK, expected exception: " + exc.getMessage());
                  }
                - Geben Sie die Liste der verfügbaren Bücher auf der Konsole aus.
                - Geben Sie die Menge der verfügbaren Bücher sortiert nach abgeschlossenen Leihvorgängen aus
                - Geben Sie die Menge der registrierten Kunden sortiert nach Namen aus
                Ihre Ausgabe könnte wie folgt aussehen:
                OK, expected exception: Lender name already taken: Robert C. Martin
                OK, expected exception: Lender Susi already lends Clean Code
                OK, expected exception: Not our book: Clean Code, return tried by Strolchi
                OK, expected exception: Book not available: Clean Code
                OK, expected exception: Book not available: Clean Agile
                OK, expected exception: Unknown lender: Max
                OK, expected exception: Unknown lender: Max tries to return: Clean Code
                Books:
                Book: Clean Architecture, lent count: 0
                Book: Clean Coder, lent count: 3
                Most lent books:
                Book: Clean Coder, lent count: 3
                Book: Clean Architecture, lent count: 0
                Lenders:
                Lender: Robert C. Martin, books:
                Lender: Strolchi, books: 'Clean Code'
                Lender: Susi, books: 'Clean Code'
         */

        Library library = new Library();

        library.addLender("Robert C. Martin");
        library.addLender("Susi");
        library.addLender("Strolchi");

        library.addBook("Clean Code");
        library.addBook("Clean Agile");
        library.addBook("Clean Architecture");
        library.addBook("Clean Architecture");

        library.lendBook("Clean Code", "Susi");
        library.lendBook("Clean Agile", "Strolchi");

        System.out.println("Available Books: ");
        library.getAvailableBooksOrderedByAlphabet().forEach(l -> System.out.println(l.toString()));
        System.out.println("Lenders: ");
        library.getLendersOrderedByName().forEach(l -> System.out.println(l.toString()));
    }
}
