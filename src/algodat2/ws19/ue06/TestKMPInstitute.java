package algodat2.ws19.ue06;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;

public class TestKMPInstitute {
	private class IntegerComparator implements Comparator<Integer> {
		public int compare(Integer a, Integer b) {
			return a.compareTo(b);
		}
	}

	@Test
	public void testKMPEmpty() {
		Result result = KMP.search("aaa", "abcdexxxunbexxxke");

		System.out.println();
		System.out.print("E: Size: " + result.count + "; Indices: ");
		for (Integer i : result.resultIndices)
			System.out.print(i + "; ");
		System.out.println();

		assertTrue(result.count == 0);
		assertTrue(result.resultIndices.length == 0);
	}

	@Test
	public void testKMPShort() {
		Result result = KMP.search("xxx", "abcdexxxunbxxxxke");

		System.out.println();
		System.out.print("SO: Size: " + result.count + "; Indices: ");
		for (Integer i : result.resultIndices)
			System.out.print(i + "; ");
		System.out.println();

		assertTrue(result.count > 0);
		assertEquals(new Integer(3), result.count);

		int[] targets = { 5, 11, 12 };
		Integer[] indices = result.resultIndices;
		Arrays.sort(indices, new IntegerComparator());

		boolean resultCorrect = true;
		int targetToFind = 0;
		for (int i = 0; i < indices.length; i++) {
			if (targetToFind >= targets.length)
				resultCorrect = false;
			else if (indices[i].intValue() == targets[targetToFind])
				targetToFind++;
		}
		if (targetToFind < targets.length)
			resultCorrect = false;

		assertTrue(resultCorrect);

	}

	@Test
	public void testKMPSpecial() {
		Result result = KMP.search("xxx", "xxxxxA");

		System.out.println();
		System.out.print("SP: Size: " + result.count + "; Indices: ");
		for (Integer i : result.resultIndices)
			System.out.print(i + "; ");
		System.out.println();

		assertTrue(result.count > 0);
		assertEquals(new Integer(3), result.count);

		int[] targets = { 0, 1, 2 };
		Integer[] indices = result.resultIndices;
		Arrays.sort(indices, new IntegerComparator());

		boolean resultCorrect = true;
		int targetToFind = 0;
		for (int i = 0; i < indices.length; i++) {
			if (targetToFind >= targets.length)
				resultCorrect = false;
			else if (indices[i].intValue() == targets[targetToFind])
				targetToFind++;
		}
		if (targetToFind < targets.length)
			resultCorrect = false;

		assertTrue(resultCorrect);
	}

	@Test
	public void testKMPLong() {
		Result result = KMP.search("is",
				"Compares two strings lexicographically. The comparison is based on the Unicode value of each character in the strings. The character sequence represented by this String object is compared lexicographically to the character sequence represented by the argument string. The result is a negative integer if this String object lexicographically precedes the argument string. The result is a positive integer if this String object lexicographically follows the argument string. The result is zero if the strings are equal; compareTo returns 0 exactly when the equals(Object) method would return true.");

		System.out.println();
		System.out.print("L: Size: " + result.count + "; Indices: ");
		for (Integer i : result.resultIndices)
			System.out.print(i + "; ");
		System.out.println();

		assertTrue(result.count > 0);
		assertEquals(new Integer(9), result.count);

		int[] targets = { 50, 55, 159, 176, 279, 306, 382, 409, 484 };
		Integer[] indices = result.resultIndices;
		Arrays.sort(indices, new IntegerComparator());

		boolean resultCorrect = true;
		int targetToFind = 0;
		for (int i = 0; i < indices.length; i++) {
			if (targetToFind >= targets.length)
				resultCorrect = false;
			else if (indices[i].intValue() == targets[targetToFind])
				targetToFind++;
		}
		if (targetToFind < targets.length)
			resultCorrect = false;

		assertTrue(resultCorrect);

	}

	@Test
	public void testKMPVeryLong() {
		Result result = KMP.search("Lorem ipsum dolor sit amet",
				"Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod "
						+ "tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo "
						+ "dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum "
						+ "dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam "
						+ "erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea "
						+ "takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam "
						+ "nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et "
						+ "justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. "
						+ "Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat "
						+ "nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue "
						+ "duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy "
						+ "nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud "
						+ "exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor "
						+ "in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et "
						+ "accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla "
						+ "facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer "
						+ "possim assum. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut "
						+ "laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper "
						+ "suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate "
						+ "velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis. At vero eos et accusam et justo duo dolores "
						+ "et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit "
						+ "amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed "
						+ "diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata "
						+ "sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, At accusam aliquyam "
						+ "diam diam dolore dolores duo eirmod eos erat, et nonumy sed tempor et et invidunt justo labore Stet clita ea et "
						+ "gubergren, kasd magna no rebum. sanctus sea sed takimata ut vero voluptua. est Lorem ipsum dolor sit amet. Lorem ipsum "
						+ "dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam "
						+ "erat. Consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed "
						+ "diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata "
						+ "sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod "
						+ "tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores "
						+ "et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit "
						+ "amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed "
						+ "diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata "
						+ "sanctus. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et "
						+ "dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita "
						+ "kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur "
						+ "sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. "
						+ "At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem "
						+ "ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt "
						+ "ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. "
						+ "Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Duis autem vel eum iriure dolor in"
						+ " hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et "
						+ "accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla "
						+ "facilisi. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut "
						+ "laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper "
						+ "suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate "
						+ "velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio "
						+ "dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor "
						+ "cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Lorem ipsum "
						+ "dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam "
						+ "erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip "
						+ "ex ea commodo "
						+ "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod "
						+ "tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo "
						+ "dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum "
						+ "dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam "
						+ "erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea "
						+ "takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam "
						+ "nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et "
						+ "justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. "
						+ "Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat "
						+ "nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue "
						+ "duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy "
						+ "nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud "
						+ "exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor "
						+ "in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et "
						+ "accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla "
						+ "facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer "
						+ "possim assum. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut "
						+ "laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper "
						+ "suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate "
						+ "velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis. At vero eos et accusam et justo duo dolores "
						+ "et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit "
						+ "amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed "
						+ "diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata "
						+ "sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, At accusam aliquyam "
						+ "diam diam dolore dolores duo eirmod eos erat, et nonumy sed tempor et et invidunt justo labore Stet clita ea et "
						+ "gubergren, kasd magna no rebum. sanctus sea sed takimata ut vero voluptua. est Lorem ipsum dolor sit amet. Lorem ipsum "
						+ "dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam "
						+ "erat. Consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed "
						+ "diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata "
						+ "sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod "
						+ "tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores "
						+ "et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit "
						+ "amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed "
						+ "diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata "
						+ "sanctus. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et "
						+ "dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita "
						+ "kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur "
						+ "sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. "
						+ "At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem "
						+ "ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt "
						+ "ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. "
						+ "Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Duis autem vel eum iriure dolor in"
						+ " hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et "
						+ "accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla "
						+ "facilisi. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut "
						+ "laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper "
						+ "suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate "
						+ "velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio "
						+ "dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor "
						+ "cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Lorem ipsum "
						+ "dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam "
						+ "erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip "
						+ "ex ea commodo.");

		System.out.println();
		System.out.print("VL: Size: " + result.count + "; Indices: ");
		for (Integer i : result.resultIndices)
			System.out.print(i + "; ");
		System.out.println();

		assertTrue(result.count > 0);
		assertEquals(new Integer(52), result.count);

		int[] targets = { 0, 268, 296, 564, 592, 860, 1159, 1826, 2345, 2373, 2641, 2669, 2937, 2965, 3342, 3370, 3638,
				3666, 3931, 4199, 4227, 4495, 4523, 4791, 5090, 5757, 6019, 6287, 6315, 6583, 6611, 6879, 7178, 7845,
				8364, 8392, 8660, 8688, 8956, 8984, 9361, 9389, 9657, 9685, 9950, 10218, 10246, 10514, 10542, 10810,
				11109, 11776 };
		Integer[] indices = result.resultIndices;
		Arrays.sort(indices, new IntegerComparator());

		boolean resultCorrect = true;
		int targetToFind = 0;
		for (int i = 0; i < indices.length; i++) {
			if (targetToFind >= targets.length)
				resultCorrect = false;
			else if (indices[i].intValue() == targets[targetToFind])
				targetToFind++;
		}
		if (targetToFind < targets.length)
			resultCorrect = false;

		assertTrue(resultCorrect);
	}
}
