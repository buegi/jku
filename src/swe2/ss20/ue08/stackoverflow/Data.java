package swe2.ss20.ue08.stackoverflow;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;

public class Data {
    public static class Owner {
        private long reputation;
        private String display_name;

        public long getReputation() {
            return reputation;
        }

        public String getDisplayName() {
            return display_name;
        }

        @Override
        public String toString() {
            return "Owner{" +
                    "reputation=" + reputation +
                    ", display_name='" + display_name + '\'' +
                    '}';
        }
    }

    public static class Question {
        private String[] tags;
        private Owner owner;
        private long view_count;
        /**
         * Is 0 if now answer is accepted
         */
        private long accepted_answer_id;
        private long answer_count;
        private long score;
        private String title;

        public String[] getTags() {
            return tags;
        }

        public Owner getOwner() {
            return owner;
        }

        public long getViewCount() {
            return view_count;
        }

        /**
         * @return The ID of the accepted answer, or 0 if no answer has been accepted.
         */
        public long getAcceptedAnswerId() {
            return accepted_answer_id;
        }

        public long getAnswerCount() {
            return answer_count;
        }

        public long getScore() {
            return score;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public String toString() {
            return "Question{" +
                    "tags=" + Arrays.toString(tags) +
                    ", owner=" + owner +
                    ", view_count=" + view_count +
                    ", accepted_answer_id=" + accepted_answer_id +
                    ", answer_count=" + answer_count +
                    ", score=" + score +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    private static String STACKOVERFLOW_TOP_100_JAVA_QUESTIONS_URL_STRING = "https://api.stackexchange.com/2.2/questions?pagesize=100&order=desc&sort=votes&tagged=java&site=stackoverflow&filter=!FcbKfKGCsNP42X*WbKOg(Q-OgX";

    private Question[] items = new Question[0];

    private Data() {
    }

    /**
     * @return The loaded data, or null if an exception occured
     */
    public static Data loadFromWeb() {
        // HttpClient would be supported in Java 11, fall back to HttpURLConnection to ensure Java 8 compatibility
        try {
            URL url = new URL(STACKOVERFLOW_TOP_100_JAVA_QUESTIONS_URL_STRING);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            // StackExchange pages use compression in HTTP access, default is GZIP
            // See: https://api.stackexchange.com/docs/compression
            con.setRequestProperty("Accept-Encoding", "gzip");

            BufferedReader reader = null;
            if ("gzip".equals(con.getContentEncoding())) {
                // As expected, data has been returned compressed
                reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(con.getInputStream())));
            } else {
                // StackExchange should _never_ send uncompressed data
                // Still, why not have a fallback? :)
                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            }

            // Use Gson library to convert JSON into Data.Question and Data.Owner objects
            Gson gson = new Gson();
            Data data = gson.fromJson(reader, Data.class);
            reader.close();

            return data;
        } catch (MalformedURLException ex) {
            System.err.println("Malformed URL: " + STACKOVERFLOW_TOP_100_JAVA_QUESTIONS_URL_STRING);
            ex.printStackTrace();
        } catch (ProtocolException ex) {
            System.err.println("Protocol exception:");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.err.println("IO exception:");
            ex.printStackTrace();
        }
        return new Data();
    }

    public Stream<Question> stream() {
        // TODO: generate new Stream<Question> based on the array 'items'
        return Arrays.stream(this.items);
    }

    public Stream<Question> sortedStream() {
        // TODO: generate Stream<Question> based on stream() and by sorting it descending based on the question score
        // return Arrays.stream(this.items).sorted((q1, q2) -> q1.getScore() < q2.getScore() ? 1 : -1);
        // return Arrays.stream(this.items).sorted((q1, q2) -> Long.compare(q1.getScore(), q2.getScore()));
        return Arrays.stream(this.items).sorted(Comparator.comparingLong(Question::getScore));
    }

    public Optional<Question> findHighestScoringQuestionWith(int minimumViews) {
        // TODO: Return the question with the highest score that has at least minimumViews views.
        // return Arrays.stream(this.items).filter(q -> q.getViewCount() >= minimumViews).max((q1, q2) -> q1.getScore() > q2.getScore() ? 1 : -1);
        return Arrays.stream(this.items).filter(q -> q.getViewCount() >= minimumViews).max(Comparator.comparingLong(Question::getScore));
    }

    public Optional<String> getLongestTitle() {
        // TODO: return the longest title
        // return Arrays.stream(this.items).map(q -> q.getTitle()).max((s1, s2) -> s1.length() > s2.length() ? 1 : -1);
        // return Arrays.stream(this.items).map(q -> q.getTitle()).max((s1, s2) -> Integer.compare(s1.length(), s2.length()));
        // return Arrays.stream(this.items).map(q -> q.getTitle()).max(Comparator.comparing(String::length));
        return Arrays.stream(this.items).map(q -> q.getTitle()).max(Comparator.comparingInt(String::length));
    }

    public List<Question> findQuestions(String titlePart) {
        // TODO: return a list of all question that contain titlePart in their title
        return Arrays.stream(this.items).filter(q -> q.getTitle().contains(titlePart)).collect(Collectors.toList());
    }

    public long countQuestionsWithoutAcceptedAnswer() {
        // TODO: return the number of questions that have no accepted answer
        return Arrays.stream(this.items).filter(q -> q.getAcceptedAnswerId() == 0).count();
    }

    public OptionalDouble averageOwnerReputation() {
        // TODO: return the average reputation of the question owners using mapToLong() and average()
        return Arrays.stream(this.items).mapToLong(o -> o.getOwner().getReputation()).average();
    }

    public double averageOwnerReputation2() {
        // TODO: return the average reputation of the question owners using collect() in conjunction with Collectors.averagingLong()
        return Arrays.stream(this.items).collect(Collectors.averagingLong(o -> o.getOwner().getReputation()));
    }

    public long sumOfAllAnswerCounts() {
        // TODO: return the total number of answers that have been given to all questions
        return Arrays.stream(this.items).mapToLong(q -> q.getAnswerCount()).sum();
    }

    public Map<Integer, List<Question>> groupQuestionsByTagCount() {
        // TODO: return questions grouped by their number of tags
        return Arrays.stream(this.items).collect(Collectors.groupingBy(q -> q.getTags().length));
    }

    public Map<Boolean, List<Question>> partitionByAcceptedAnswer() {
        // TODO: return all questions, partitioned by the fact if the question has an answer (true) or not (false)
        return Arrays.stream(this.items).collect(Collectors.partitioningBy(q -> q.getAcceptedAnswerId() == 0));
    }

    public Optional<Owner> getOwnerWithShortestName() {
        // TODO: return the owner with the shortest name that asked a question
        return Arrays.stream(this.items)
                .map(q -> q.getOwner()).min((o1, o2) -> o1.getDisplayName().length() > o2.getDisplayName().length() ? 1 : -1);
    }

    public List<String> distinctTags() {
        // TODO: return a list of all distinct tags, sorted ascending
        return Arrays.stream(this.items)
                .flatMap(q -> Stream.of(q.getTags())).distinct().sorted().collect(Collectors.toList());
    }

    public List<String> topTags(int n) {
        // TODO: return the top n tags (i.e., those n tags that appear the most often) in conjunction with frequency (i.e., "<tag> x <frequency>")
        return Arrays.stream(this.items).flatMap(q -> Stream.of(q.getTags()))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().sorted((l1, l2) -> l2.getValue().compareTo(l1.getValue())).limit(n)
                .map(x -> x.getKey() + " x " + x.getValue()).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Data{" +
                "items=" + Arrays.toString(items) +
                '}';
    }
}