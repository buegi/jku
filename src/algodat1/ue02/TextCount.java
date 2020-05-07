package algodat1.ue02;

public class TextCount {

	private static final String givenText = "     Ich bin da. Du nicht. Er auch. Vielleicht gehe ich morgen nicht auf die Universitaet!      ";
	// empty String test (uncomment above, un-uncomment below)
	// private static final String givenText = "";

	public static void main(String[] args) {
		counts(givenText);
	}

	private static float[] counts(String givenText) {
		float counts[] = { 0, 0 };
		int words = 0;
		int sentences = 0;
		int chars = 0;

		for (int i = 0; i < givenText.length(); i++) {

			if ((givenText.charAt(i) != ' ') && (givenText.charAt(i) != '.') && (givenText.charAt(i) != '!')
					&& (givenText.charAt(i) != '?')) {
				chars++;
			}

			if (((i - 1 >= 0) && (givenText.charAt(i - 1) != ' '))
					&& ((givenText.charAt(i) == ' ') || (i > givenText.length() - 2))) {
				words++;
			}

			if ((givenText.charAt(i) == '.') || (givenText.charAt(i) == '!') || (givenText.charAt(i) == '?')) {
				sentences++;
			}
		}

		System.out.println("Sentences: " + sentences + " Words: " + words + " Chars: " + chars);

		if ((words > 0) && (sentences > 0)) {
			counts[0] = (float) words / (float) sentences;
			counts[1] = (float) chars / (float) words;
		}

		System.out.println("Average number of words per sentence: " + counts[0]);
		System.out.println("Average number of characters per word: " + counts[1]);

		return counts;
	}
}