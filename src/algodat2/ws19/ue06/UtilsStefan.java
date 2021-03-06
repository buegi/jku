package algodat2.ws19.ue06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class UtilsStefan {

	static final String HAYSTACK_ASSIGNMENT = "abcdexxxunbxxxxke";
	static final String NEEDLE_ASSIGNMENT = "xxx";

	static final String HAYSTACK_LOREM_IPSUM = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.";
	static final String NEEDLE_LOREM_IPSUM = "et";

	static final String HAYSTACK_FAUST;
	static final String NEEDLE_FAUST = "Pudels Kern";

	static {
		String temp;
		try {
			// TODO replace for Java 8
			temp = Files.readString(Paths.get("src/algodat2/ws19/ue06/Faust.txt"));
		} catch (final IOException e) {
			e.printStackTrace();
			temp = "Dann halt nicht.";
		}
		HAYSTACK_FAUST = temp;
	}

	private UtilsStefan() {
	}

	static Result initResult(final Integer count, final Integer... vars) {
		return new Result(vars, count);
	}
}
