package algodat1.ss19.ue06;

public class RecursiveList {
	static Node head;
	static boolean found = false;
	static int counterEven = 0;
	static int counterOdd = 0;

	public static void main(String[] args) {
//		testA();
//		testB();
		testC();
	}

	private static void testC() {
		RecursiveList list = new RecursiveList();

		list.addNumber(3);
		list.addNumber(5);
		list.addNumber(2);
		list.addNumber(7);
		list.addNumber(5);
		list.addNumber(1);
		list.addNumber(4);
		list.addNumber(8);
		list.printList();

		int sumEven = list.sumEven(head);
		int sumOdd = list.sumOdd(head);

		System.out.println();
		System.out.println("Sum of even Positions: " + sumEven);
		System.out.println();
		System.out.println("Sum of odd Positions: " + sumOdd);
		System.out.println();
	}

	private int sumEven(Node n) {
		int sumEven = 0;

		counterEven = counterEven + 1;

		if (counterEven % 2 == 0) {
			sumEven = sumEven + n.value;
			if (n.next != null) {
				sumEven = sumEven + sumEven(n.next);
			}
		}

		else if (counterEven % 2 == 1) {
			if (n.next != null) {
				sumEven = sumEven + sumEven(n.next);
			}
		}

		return sumEven;
	}

	private int sumOdd(Node n) {
		int sumOdd = 0;

		counterOdd = counterOdd + 1;

		if (counterOdd % 2 == 0) {
			if (n.next != null) {
				sumOdd = sumOdd + sumOdd(n.next);
			}
		}

		else if (counterOdd % 2 == 1) {
			sumOdd = sumOdd + n.value;
			if (n.next != null) {
				sumOdd = sumOdd + sumOdd(n.next);
			}
		}

		return sumOdd;
	}

	private static void testB() {
		RecursiveList listB = new RecursiveList();

		listB.addNumber(3);
		listB.addNumber(5);
		listB.addNumber(2);
		listB.addNumber(7);
		listB.addNumber(5);
		listB.addNumber(1);
		listB.addNumber(4);
		listB.printList();

		int result = listB.sumLastOcc(head, 9);

		System.out.println();
		System.out.println(result);
		System.out.println();
	}

	private int sumLastOcc(Node n, int val) {
		// sum that accumulate until last occurrence
		int sumUpTo = 0;
		// sum that accumulate every value in the list
		int sumAfter = 0;

		// if there is a next node
		if (n.next != null) {
			// accumulate the sum of every nodes value to sumAfter
			sumAfter = sumAfter + n.value + sumLastOcc(n.next, val);

			// if the value we are looking for will be found
			if (n.value == val) {
				found = true;
			}

			// when the stack will be decreased, it starts summing up from this point
			if (found) {
				sumUpTo = sumUpTo + sumAfter;
			}
		}
		// if it is the last node
		else {
			// add this value to the sumAfter
			sumAfter = sumAfter + n.value;

			// if the value we are looking for will be found
			if (n.value == val) {
				sumUpTo = sumUpTo + sumAfter;
				found = true;
			}
		}

		return sumUpTo;
	}

	private static void testA() {
		RecursiveList listA = new RecursiveList();

		listA.addNumber(1);
		listA.addNumber(3);
		listA.addNumber(8);
		listA.addNumber(5);
		listA.addNumber(2);
		listA.printList();

		int result = listA.posFirstOcc(head, 5);

		System.out.println();
		System.out.println(result);
		System.out.println();
	}

	private int posFirstOcc(Node n, int val) {
		int counter = 0;

		if (n.value == val) {
			found = true;
			counter++;
		}

		else if (n.next != null) {
			counter++;
			counter = counter + posFirstOcc(n.next, val);
		}

		if (found == false) {
			counter = 0;
		}

		return counter;
	}

	private void addNumber(int number) {
		Node curr = head;
		Node prev = null;

		if (head == null) {
			head = new Node(number, null);
		} else {
			while (curr != null) {
				prev = curr;
				curr = curr.next;
			}

			Node newNode = new Node(number, null);
			prev.next = newNode;
		}
	}

	// inner node class
	private class Node {
		Node next;
		int value;

		Node(int value, Node next) {
			this.value = value;
			this.next = next;
		}
	}

	private void printList() {
		Node curr = head;
		Node prev = null;

		while (curr != null) {
			System.out.print(curr.value + " ");

			prev = curr;
			curr = curr.next;
		}
	}
}