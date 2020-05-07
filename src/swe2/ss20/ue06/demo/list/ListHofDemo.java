package swe2.ss20.ue06.demo.list;

import java.util.Comparator;
import java.util.Optional;
import java.util.Random;

public class ListHofDemo {

    private static final Random RAND = new Random();

    public static void main(String[] args) {

        List<String> words = List.of("Funktionale", "Programmierung", "in", "Java");
//		List<String> words = List.of();

        // --- map, filter, forEach ---

        List<Integer> lengths = words.map(w -> w.length());

        List<String> longWords = words.filter(w -> w.length() > 5);

        words.forEach(w -> {
            System.out.println(w);
        });
        System.out.println();
        lengths.forEach(System.out::println);
        System.out.println();
        longWords.forEach(System.out::println);

        // --- reduce ---

        int totalLength = words.reduce(0, (length, w) -> length + w.length());
        System.out.format("%ntotalLength = %d", totalLength);

        Optional<String> longestWord = words.reduce((max, w) -> w.length() > max.length() ? w : max);
        System.out.format("%nlongestWord = %s", longestWord.orElse("no word"));

        Optional<Integer> maxLength = words.map(String::length).reduce((max, l) -> l > max ? l : max);
        System.out.format("%nmaxLength = %s", maxLength.orElse(-1));

        Optional<Integer> minLengthOfWords =
                words
                        .map(String::length)
                        .filter(l -> l > 1)
                        .reduce((min, l) -> l < min ? l : min);
        System.out.format("%nminLengthOfWords = %s", minLengthOfWords.orElse(-1));

        // --- find ---

        Optional<String> foundProgramm = words.find(w -> w.contains("Programm"));
        System.out.format("%nfoundProgramm = %s", foundProgramm.orElse("Not found"));

        // --- max und min ---

        Optional<String> maxWord = words.max(Comparator.comparing(String::length));
        System.out.format("%nmax = %s", maxWord.orElse("Not found"));

        Optional<Integer> maxLength2 = words.max(Comparator.comparing(String::length)).map(w -> w.length());
        System.out.format("%nmaxLength2 = %s", maxLength2.orElse(-1));

        // --- all und any ---

        boolean allLonger2 = words.all(w -> w.length() > 2);
        System.out.format("%nallLonger2 = %s", allLonger2);

        boolean anyLength2 = words.any(w -> w.length() == 2);
        System.out.format("%nanyLength2 = %s", anyLength2);

        // --- generate und iterate ---

        List<Integer> randInts = List.generate(10, () -> RAND.nextInt(10));
        System.out.println();
        randInts.forEach(System.out::println);

        List<String> aas = List.iterate(10, "a", w -> w + "a");
        System.out.println();
        aas.forEach(System.out::println);
    }
}