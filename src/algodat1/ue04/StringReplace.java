package algodat1.ue04;

public class StringReplace {
	// case sensitive String replacer

	public static void main(String[] args) {

		// text and length
		// example 1
		 String inputText = "aaxxxaaaxxxaaaxxxaaaxxxaaaaxxx";
		 String oldS = "xxx";
		 String newS = "aaa";

		// example 2
//		String inputText = "aaaaaaaaaa";
//		String oldS = "a";
//		String newS = "x";

		 // example 3
//		 String inputText = "aaaaaaaaaaaa";
//		 String oldS = "xxx";
//		 String newS = "aaa";
		 
		 
		char[] text = inputText.toCharArray();
		int n = text.length;

		// Strings and length
		char[] strOld = oldS.toCharArray();

		char[] strNew = newS.toCharArray();
		int k = strOld.length;

		System.out.println(text);
		System.out.println(replace(text, n, strOld, strNew, k));
	}

	private static char[] replace(char[] text, int n, char[] strOld, char[] strNew, int k) {

		int loop1 = 0;	// loop counter for outer loop
		int loop2 = 0;	// loop counter for inner loop 1 => check if identical
		int loop3 = 0;	// loop counter for inner loop 2 => replace-loop

		// if old String longer than text
		if (n >= k) {

			// iterate array
			for (int i = 0; i < n; i++) {
				loop1++;
				// if i + length of old String bigger than text length break loop
				if ((i + k) > n) {
					break;
				}

				// check for occurrence of first char of old String in text
				if (text[i] == strOld[0]) {
					int index = i;
					int indexCount = index;

					for (int j = 0; j < k - 1; j++) {
						loop2++;
						if (text[indexCount] == strOld[j]) {
							indexCount++;
						} else {
							break;
						}
					}

					// if every character matches ==> replace
					if (indexCount - index == k - 1) {
						int counter = 0;
						for (int l = index; l <= indexCount; l++) {
							loop3++;
							text[l] = strNew[counter];
							counter++;
						}
						// set i to index after replaced text
						i = i + (counter - 1);
					}
				}
			}
		}
		System.out.println(loop1 + " " + loop2 + " " + loop3);
		return text;
	}
}