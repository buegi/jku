package algodat2.ws19.ue03.ex02;

public class RadixSort2 {

	private static final int BASE = 10;
	private static final MyLinkedList MERGE_LIST = new MyLinkedList();

	public static MyLinkedList sort(final Integer[] list) throws IllegalArgumentException {
		inputCheck(list);
		final MyLinkedList[] buckets = resetLists();

		final int digits = String.valueOf(getHighest(list)).length();
		for (int i = 0; i < digits; i++) {
			bucketSort(i, buckets);
		}
		return MERGE_LIST;
	}

	private static void inputCheck(final Integer[] list) {
		if (list == null) throw new IllegalArgumentException();
		for (final Integer integer : list) {
			if (integer == null) throw new IllegalArgumentException();
		}
	}

	private static int getHighest(final Integer[] list) {
		// TODO kann IOOB ausl�sen
		int highest = list[0];
		for (final Integer integer : list) {
			MERGE_LIST.add(integer);
			if (integer > highest) {
				highest = integer;
			}
		}
		return highest;
	}

	private static MyLinkedList[] resetLists() {
		final MyLinkedList[] buckets = new MyLinkedList[BASE];
		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = new MyLinkedList();
		}
		// TODO da
		MERGE_LIST.clear();
		return buckets;
	}

	private static int getDigit(final int val, final int pos) {
		return (int) (val / Math.pow(BASE, pos) % BASE);
	}

	private static void bucketSort(final int pos, final MyLinkedList[] buckets) {
		for (MyNode curr = MERGE_LIST.head; curr != null; curr = curr.next) {
			buckets[getDigit(curr.value, pos)].add(curr.value);
		}
		merge(buckets);
	}

	private static void merge(final MyLinkedList[] buckets) {
		MERGE_LIST.clear();

		for (final MyLinkedList bucket : buckets) {
			MERGE_LIST.link(bucket);
			bucket.clear();
		}
	}
}
