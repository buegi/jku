package algodat1.ss19.ue06;

public class Test {

	protected int index;

	public static void main(String[] args) {

		// Example 1a)
		List listA = new List();
		listA.add(2);
		listA.add(5);
		listA.add(8);
		listA.add(3);
		listA.add(1);

		Node current = listA.getHead();
		System.out.print("Actual list values: ");
		while (current != null) {
			System.out.print(current.getValue() + " ");
			current = current.getNext();
		}
		int value = 2;
		System.out.println();
		System.out.println("Position of " + value + " in list: " + posFirstOcc(listA.head, value));

		List listB = new List();
		listB.add(4);
		listB.add(1);
		listB.add(5);
		listB.add(7);
		listB.add(2);
		listB.add(5);
		listB.add(3);

		current = listB.getHead();
		System.out.print("Actual list values: ");
		while (current != null) {
			System.out.print(current.getValue() + " ");
			current = current.getNext();
		}

		value = 9;
		System.out.println();
		System.out.println(
				"Sum all values up to last occurence of " + value + " in list: " + sumLastOcc(listB.head, value));
		System.out.println("Sum of numbers in even positions: " + sumEven(listB.head));
		System.out.println("Sum of numbers in odd positions: " + sumOdd(listB.head));
	}

	private static int posFirstOcc(Node n, int val) {
		int index = 1;
		if (n == null) {
			return 0;
		}
		if (n.value == val) {
			return 1;
		}
		if (posFirstOcc(n.next, val) == 0) {
			return 0;
		} else {
			return index + posFirstOcc(n.next, val);
		}
	}

	private static int sumLastOcc(Node n, int val) {
		if (n == null) {
			return 0;
		}
		if (n.value == val)
			return val + sumLastOcc(n.next, val);
		final int i = sumLastOcc(n.next, val);
		if (i > 0) {
			return i + n.value;
		}
		return 0;
	}

	private static int sumEven(Node n) {
		if (n == null || n.next == null) {
			return 0;
		}
		return sumOdd(n.next);
	}

	private static int sumOdd(Node n) {
		if (n == null) {
			return 0;
		}
		if (n.next == null) {
			return n.value;
		}
		return n.value + sumOdd(n.next.next);
	}
}