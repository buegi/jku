package swe1.ws17.ue08;

import java.util.*;

public class MessageCompressor {
    private String message = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String compressMessage(String message) {
        if (message.isEmpty() || message == "") {
            System.out.println("No valid value(s) entered!");
            return message;
        } else {
            int[] msgascii = convertToIntArray(message);
            int[] occurences = countOccurences(message);
            int[][] result = {msgascii, occurences};
            int[] most = (getMostOccuringCharacter(result));
            int mostvalue = most[0];
            char mostcharacter = (char) most[1];

            printOrderedListWithOccurences(message, msgascii, occurences);
            System.out.println();
            System.out.println("Compressed Message: " + removeDuplicates(message));
            System.out.println(
                    "Character " + "'" + mostcharacter + "'" + " occured most often with " + mostvalue + " times");

            return message;
        }
    }

    // fill array with char values of message characters
    private char[] convertToCharArray(String message) {
        char[] msgchars = message.toCharArray();
        return msgchars;
    }

    // fill array with int values of message characters
    private int[] convertToIntArray(String message) {
        int[] msgascii = new int[message.length()];
        for (int i = 0; i < message.length(); i++) {
            msgascii[i] = (char) message.charAt(i);
        }
        return msgascii;
    }

    // count occurences and fill in array
    private int[] countOccurences(String message) {
        int[] occurencies = new int[message.length()];
        for (int i = 0; i < message.length(); i++) {
            int counter = 0;
            for (int j = 0; j < message.length(); j++) {
                if (message.charAt(i) == message.charAt(j)) {
                    counter += 1;
                    occurencies[i] = counter;
                }
            }
        }
        return occurencies;
    }

    // remove duplicates
    private String removeDuplicates(String message) {
        char[] chars = message.toCharArray();
        Set<Character> charSet = new LinkedHashSet<Character>();
        for (char c : chars) {
            charSet.add(c);
        }

        StringBuilder sb = new StringBuilder();
        for (Character character : charSet) {
            sb.append(character);
        }
        return sb.toString();
    }

    // get most occuring character with lowest ascii value
    private int[] getMostOccuringCharacter(int[][] result) {
        int[] mostoccuring = new int[2];
        java.util.Arrays.sort(result, new java.util.Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[0], b[0]);
            }
        });
        int max = 0;
        for (int i = 0; i < result[0].length; i++) {
            if (result[0][i] > max) {
                max = result[0][i];
                mostoccuring[0] = result[0][i];
                mostoccuring[1] = result[1][i];
            }
        }
        return mostoccuring;
    }

    private void printOrderedListWithOccurences(String message, int[] msgascii, int[] occurences) {
        char[] chars = convertToCharArray(removeDuplicates(message));
        int[] ascii = convertToIntArray(removeDuplicates(message));
        Arrays.sort(chars);
        Arrays.sort(ascii);
        // print results
        for (int i = 0; i < ascii.length; i++) {
            int index = -1;
            for (int j = 0; j < msgascii.length; j++) {
                if (msgascii[j] == ascii[i]) {
                    index = occurences[j];
                }
            }
            System.out.println("'" + chars[i] + "'" + " " + index);

        }
    }
}