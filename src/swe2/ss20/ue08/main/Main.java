package swe2.ss20.ue08.main;

import swe2.ss20.ue08.stackoverflow.Data;

public class Main {

    public static void main(String[] args) {
        Data data = Data.loadFromWeb();

        System.out.println("======================================================================================");
        // invoke stream()
        data.stream().forEach(b -> System.out.println(b));

        System.out.println("======================================================================================");
        // invoke sorted stream
        data.sortedStream().forEach(b -> System.out.println(b));

        System.out.println("======================================================================================");
        // invoke findHighestScoringQuestionWith(int minimumViews)
        data.findHighestScoringQuestionWith(3010).ifPresent(b -> System.out.println(b));

        System.out.println("======================================================================================");
        // invoke getLongestTitle
        data.getLongestTitle().ifPresent(b -> System.out.println(b));

        System.out.println("======================================================================================");
        // invoke findQuestions(String titlePart)
        data.findQuestions("Java 8").stream().forEach(b -> System.out.println(b));

        System.out.println("======================================================================================");
        // invoke countQuestionsWithoutAcceptedAnswer
        System.out.println("QuestionsWithoutAcceptedAnswer: " + data.countQuestionsWithoutAcceptedAnswer());

        System.out.println("======================================================================================");
        // invoke averageOwnerReputation
        System.out.print("averageOwnerReputation: ");
        data.averageOwnerReputation().ifPresent(b -> System.out.println(b));

        System.out.println("======================================================================================");
        // invoke averageOwnerReputation2
        System.out.print("averageOwnerReputation2: ");
        System.out.println(data.averageOwnerReputation2());

        System.out.println("======================================================================================");
        // invoke sumOfAllAnswerCounts
        System.out.println("Sum of all Answer Counts: " + data.sumOfAllAnswerCounts());

        System.out.println("======================================================================================");
        // invoke groupQuestionsByTagCount
        data.groupQuestionsByTagCount().values().forEach(b -> System.out.println(b));

        System.out.println("======================================================================================");
        // invoke partitionByAcceptedAnswer
        data.partitionByAcceptedAnswer().values().forEach(b -> System.out.println(b));

        System.out.println("======================================================================================");
        // invoke getOwnerWithShortestName
        System.out.print("getOwnerWithShortestName: ");
        data.getOwnerWithShortestName().ifPresent(b -> System.out.println(b));

        System.out.println("======================================================================================");
        // invoke distinctTags
        data.distinctTags().forEach(b -> System.out.println(b));

        System.out.println("======================================================================================");
        // invoke topTags
        data.topTags(100).stream().forEach(b -> System.out.println(b));
    }
}