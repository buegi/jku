package algodat2.ws19.ue06;

import java.util.Arrays;

public class BoyerMoore {
	private final static int ASCII = 128;

	static public Result search(String pattern, String text) throws IllegalArgumentException {
		if (pattern == null || text == null || pattern.isEmpty()) {
			throw new IllegalArgumentException();
		}
		if (pattern.length() > text.length() || text.isEmpty()) {
			return new Result(new Integer[0], 0);
		}
		char[] pat = pattern.toCharArray();
		char[] txt = text.toCharArray();
		int[] charArr = new int[ASCII];
		Integer[] resultIndices = new Integer[0];

		fillCharArr(pat, pat.length, charArr);

		int s = 0;

		while (s <= (txt.length - pat.length)) {
			int j = pat.length - 1;
			while (j >= 0 && pat[j] == txt[s + j]) {
				j--;
			}
			if (j < 0) {
				resultIndices = Arrays.copyOf(resultIndices, resultIndices.length + 1);
				resultIndices[resultIndices.length - 1] = s;
				s = (s + pat.length < txt.length) ? s + pat.length - charArr[txt[s + pat.length]] : s + 1;
			} else {
				s = s + getMaxOf(1, j - charArr[txt[s + j]]);
			}
		}
		return new Result(resultIndices, resultIndices.length);
	}

	/**********************
	 * auxiliary methods
	 ***********************/

	// if necessary, define other private auxiliary methods
	// TODO

	private static void fillCharArr(char[] str, int size, int charArr[]) {
		for (int i = 0; i < ASCII; i++) {
			charArr[i] = -1;
		}
		for (int i = 0; i < size; i++) {
			charArr[(int) str[i]] = i;
		}
	}

	private static int getMaxOf(int a, int b) {
		return (a > b) ? a : b;
	}

	// --> see the exercise slide set for explanations

	/**
	 * @param pattern
	 * @param k
	 * @return is pattern[i:m-s-1] a suffix of pattern[k=i+1:m-1]?
	 */
	static boolean checkIsSuffix(char[] pattern, int k) {
		// TODO can be removed (see forum, gruenberger)
		return false;
	}

	/**
	 * @param pattern
	 * @param k
	 * @return length of longest string in pattern[0:m-s-1] that is a suffix of
	 *         pattern[k=i+1:m-1]
	 */
	private static int getLongestSuffixLength(char[] pattern, int k) {
		// TODO can be removed (see forum, gruenberger)
		return 0;
	}
}