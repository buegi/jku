package algodat2.ue06;

import java.util.Arrays;

public class KMP {

	static public Result search(String pattern, String text) throws IllegalArgumentException {
		if (pattern == null || text == null || pattern.isEmpty()) {
			throw new IllegalArgumentException();
		}
		if (pattern.length() > text.length() || text.isEmpty()) {
			return new Result(new Integer[0], 0);
		}
		char[] pat = pattern.toCharArray();
		char[] txt = text.toCharArray();
		int[] preSufArr = createPreSufTab(pat);
		int j = 0;
		int i = 0;
		Integer[] resultIndices = new Integer[0];
		while (i < txt.length) {
			if (pat[j] == txt[i]) {
				j++;
				i++;
			}
			if (j == pat.length) {
				resultIndices = Arrays.copyOf(resultIndices, resultIndices.length + 1);
				resultIndices[resultIndices.length - 1] = (i - j);
				j = preSufArr[j - 1];
			} else if (i < txt.length && pat[j] != txt[i]) {
				if (j != 0) {
					j = preSufArr[j - 1];
				} else {
					i = i + 1;
				}
			}
		}
		return new Result(resultIndices, resultIndices.length);
	}

	private static int[] createPreSufTab(char[] pat) {
		int[] preSufArr = new int[pat.length];
		int length = 0;
		int i = 1;
		preSufArr[0] = 0;

		while (i < pat.length) {
			if (pat[i] == pat[length]) {
				length++;
				preSufArr[i] = length;
				i++;
			} else {
				if (length != 0) {
					length = preSufArr[length - 1];
				} else {
					preSufArr[i] = length;
					i++;
				}
			}
		}
		return preSufArr;
	}
}