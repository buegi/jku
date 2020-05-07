package ss20.ue07;

import ss20.ue07.library.Library;

public class Main {

    public static void main(String[] args) {
        Library library = new Library();

        library.addLender("Robert C. Martin");
        library.addLender("Susi");
        library.addLender("Strolchi");

        library.addBook("Clean Code");
        library.addBook("Clean Code");
        library.addBook("Clean Agile");
        library.addBook("Clean Architecture");

        // print Status
        System.out.println("---------- Status after inserts ----------");
        System.out.println("LibraryStatus: ");
        library.getAvailableBooksOrderedByAlphabet().forEach(l -> System.out.println(l.toString()));
        System.out.println();
        System.out.println("LenderStatus: ");
        library.getLendersOrderedByName().forEach(l -> System.out.println(l.toString()));
        System.out.println("==========================================");

        // lend books
        library.lendBook("Clean Code", "Susi");
        library.lendBook("Clean Agile", "Strolchi");
        library.lendBook("Clean Architecture", "Robert C. Martin");

        // print Status
        System.out.println("----------- Status after lends -----------");
        System.out.println("LibraryStatus: ");
        library.getAvailableBooksOrderedByAlphabet().forEach(l -> System.out.println(l.toString()));
        System.out.println();
        System.out.println("LenderStatus: ");
        library.getLendersOrderedByName().forEach(l -> System.out.println(l.toString()));
        System.out.println("==========================================");

        // lend & return books
        library.returnBook("Clean Code", "Susi");
        library.lendBook("Clean Code", "Susi");
        library.lendBook("Clean Code", "Strolchi");
        library.returnBook("Clean Code", "Strolchi");
        library.returnBook("Clean Code", "Susi");

        // print Status
        System.out.println("---------- Status after returns ----------");
        System.out.println("LibraryStatus: ");
        library.getAvailableBooksOrderedByAlphabet().forEach(l -> System.out.println(l.toString()));
        System.out.println();
        System.out.println("LenderStatus: ");
        library.getLendersOrderedByName().forEach(l -> System.out.println(l.toString()));
        System.out.println("==========================================");

        // test exceptions
        System.out.println("--------------- Exceptions ---------------");
        try {
            library.lendBook("Clean Code", "Susi");
            library.lendBook("Clean Code", "Susi");
            System.out.println("Error: Susi should not be able to lend a book doubly!");
        } catch (Exception exc) {
            System.out.println("OK, expected exception: " + exc.getMessage());
        }
        try {
            library.lendBook("Clean Architecture", "Robert C. Martin");
            System.out.println("Error: Book not in librarty!");
        } catch (Exception exc) {
            System.out.println("OK, expected exception: " + exc.getMessage());
        }
        library.returnBook("Clean Code", "Susi");
        library.returnBook("Clean Agile", "Strolchi");
        library.returnBook("Clean Architecture", "Robert C. Martin");

        // print Status
        System.out.println("LibraryStatus: ");
        library.getAvailableBooksOrderedByAlphabet().forEach(l -> System.out.println(l.toString()));
        System.out.println();
        System.out.println("LenderStatus: ");
        library.getLendersOrderedByName().forEach(l -> System.out.println(l.toString()));
        System.out.println("==========================================");

        // test Comparators
        System.out.println("-------------- Comparators ---------------");
        System.out.println("LibraryStatus: ");
        library.getAvailableBooksOrderedByLentCount().forEach(l -> System.out.println(l.toString()));
        System.out.println("LenderStatus: ");
        library.getLendersOrderedByName().forEach(l -> System.out.println(l.toString()));
        System.out.println("==========================================");
    }
}
