package swe2.ss20.ue08.main;

import swe2.ss20.ue08.stackoverflow.Data;

public class Main {

    public static void main(String[] args) {
        Data data = Data.loadFromWeb();

        // invoke stream()
        data.stream().forEach(b -> System.out.println(b.getTitle() + " " + b.getScore()));

        System.out.println("======================================================================================");

        // invoke sorted stream
        data.sortedStream().forEach(b -> System.out.println(b.getTitle() + " " + b.getScore()));

        System.out.println("======================================================================================");

        // invoke minimumViews
        // data.findHighestScoringQuestionWith(927).get().toString();

        System.out.println("======================================================================================");

        // invoke getLongestTitle
        data.getLongestTitle().stream().forEach(b -> System.out.println(b));


    }
}
