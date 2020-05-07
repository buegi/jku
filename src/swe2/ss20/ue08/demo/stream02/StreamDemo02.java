package swe2.ss20.ue08.demo.stream02;

import swe2.inout.Out;;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class StreamDemo02 {
    enum Sport {
        TENNIS, GOLF, SKIING, SOCCER, SWIMMING
    }

    private static List<String> list = new ArrayList<String>();
    private static Map<String, List<Sport>> sports = new HashMap<String, List<Sport>>();

    static {
        list.add("Ann");
        list.add("Pat");
        list.add("Mary");
        list.add("Joe");

        sports.put("Ann", Arrays.asList(Sport.TENNIS, Sport.SKIING));
        sports.put("Pat", Arrays.asList(Sport.SOCCER, Sport.GOLF));
        sports.put("Mary", Arrays.asList(Sport.SOCCER, Sport.TENNIS));
        sports.put("Joe", Arrays.asList(Sport.SKIING, Sport.GOLF));
    }

    public static void main(String[] args) {
        // ==== intermediate operations ============================================

        // map

        Out.println("\nmap: Initial characters of names:");
        Stream<String> names = list.stream();
        Stream<Character> initials = names.map(n -> n.length() != 0 ? n.charAt(0) : ' ');
        initials.forEach(a -> Out.println(a));

        names = list.stream();

        Out.println("\nmapToInt: Lengths of names");
        IntStream nameLengths = names.mapToInt(a -> a.length());
        nameLengths.forEach(a -> Out.println(a));

        // filter

        names = list.stream();

        Out.println("\nfilter: Names with length 3:");
        Stream<String> names3 = names.filter(n -> n.length() == 3);
        names3.forEach(n -> Out.println(n));

        IntStream range0_99 = IntStream.range(0, 100);
        IntStream odds = range0_99.filter(i -> i % 2 != 0);
        Out.println("\nfilter: Odds from 0 to 99");
        odds.forEach(n -> Out.println(n));

        range0_99 = IntStream.range(0, 100);
        // Nothing is written here
        odds = range0_99.filter(i -> {
            boolean isOdd = i % 2 != 0;
            Out.println("(filter) " + i + " is odd: " + isOdd);
            return isOdd;
        });
        Out.println("\nfilter: Odds from 0 to 99");
        // Uncommenting the next line causes the stream to execute (because of terminal operation forEach)
        // odds.forEach(n -> Out.println("(forEach) " + n));

        // limit

        Out.println("\nlimit: 100 rands");
        final Random r = new Random();
        IntStream randStream = IntStream.generate(() -> r.nextInt(100));
        randStream
                .limit(100)
                .forEach(x -> Out.println(x));

        // skip

        Out.println("\nskip: primes from 20 to 100:");
        range0_99 = IntStream.range(0, 100);
        range0_99
                .skip(20)
                .filter(x -> isPrime(x))
                .forEach(x -> Out.println(x));

        // distinct

        Out.println("\ndistinct: 10 distinct rands");
        randStream = IntStream.generate(() -> r.nextInt(100));
        randStream
                .distinct()
                .limit(10)
                .forEach(x -> Out.println(x));
        // sorted

        Out.println("\nsorted: 10 rands sorted");
        randStream = IntStream.generate(() -> r.nextInt(100));
        randStream
                .limit(10)
                .sorted()
                .forEach(x -> Out.println(x));

        // sorted mit Comparator

        names = list.stream();

        Out.println("\nsorted with comparator: sorted names mit compareToIgnoreCase ");
        names
                .sorted((n1, n2) -> n1.compareToIgnoreCase(n2))
                .forEach(n -> Out.println(n));

        // flatMap

        Out.println("\nflatMapToInt: distinct characters of names");
        names = list.stream();
        names
                .flatMapToInt(name -> name.chars())  // name.chars() returns IntStream
                .map(c -> Character.toLowerCase((char) c))
                .distinct()
                .sorted()
                .forEach(c -> Out.println((char) c));

        Out.println("\nflatMap: sports of all persons from names");
        names = list.stream();
        names
                .flatMap(name -> sports.get(name).stream())
                .forEach(h -> Out.println(h));

        // Reihenfolge von Operationen
        list.stream().filter(n -> {
            Out.println("(filter) " + n);
            return n.length() > 3;
        }).map(n -> {
            Out.println("(map) " + n);
            return n.toLowerCase();
        }).forEach(n -> {
            Out.println("(forEach) " + n);
        });

        Out.println();

        list.stream().map(n -> {
            Out.println("(map) " + n);
            return n.toLowerCase();
        }).filter(n -> {
            Out.println("(filter) " + n);
            return n.length() > 3;
        }).forEach(n -> {
            Out.println("(forEach) " + n);
        });
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i < n / 2; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
}
