package algodat2.ue06;

import java.util.Arrays;

public class RabinKarp {

	private final static int ASCII = 128;
	private final static int PRIME = 31;

	static public Result search(String pattern, String text) throws IllegalArgumentException {
		if (pattern == null || text == null || pattern.isEmpty()) {
			throw new IllegalArgumentException();
		}
		if (pattern.length() > text.length() || text.isEmpty()) {
			return new Result(new Integer[0], 0);
		}
		char[] pat = pattern.toCharArray();
		char[] txt = text.toCharArray();

		int i;
		int j;
		int pathash = 0;
		int txthash = 0;
		int h = 1;
		Integer[] resultIndices = new Integer[0];

		h = (pow(ASCII, pat.length - 1) % PRIME);
		pathash = getHashValue(Arrays.copyOf(pat, pat.length));
		txthash = getHashValue(Arrays.copyOf(txt, pat.length));

		for (i = 0; i <= txt.length - pat.length; i++) {
			if (pathash == txthash) {
				for (j = 0; j < pat.length; j++) {
					if (txt[i + j] != pat[j]) {
						break;
					}
				}
				if (j == pat.length) {
					resultIndices = Arrays.copyOf(resultIndices, resultIndices.length + 1);
					resultIndices[resultIndices.length - 1] = i;
				}
			}

			if (i < txt.length - pat.length) {
				txthash = (ASCII * (txthash - txt[i] * h) + txt[i + pat.length]) % PRIME;

				if (txthash < 0) {
					txthash = (txthash + PRIME);
				}
			}
		}
		return new Result(resultIndices, resultIndices.length);
	}

	/**********************
	 * auxiliary methods
	 ***********************/

	// return the hash value using the rolling hash function
	static int getHashValue(char[] a) {
		int hash = 0;
		for (int i = 0; i < a.length; i++) {
			hash = (ASCII * hash + a[i]) % PRIME;
		}
		return hash;
	}

	// used for Integer Arithmetic
	static int pow(int base, int exp) {
		return exp == 0 ? 1 : (base * pow(base, --exp) % PRIME);
	}

	// if necessary, define other private auxiliary methods
	// TODO
}