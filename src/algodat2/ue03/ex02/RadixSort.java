package algodat2.ue03.ex02;

public class RadixSort {

	// 5 for loops O(5n) = O(n)
	// one nested (bucketsort) = O(k)
	// log_BASE k bucketsorts = O(log n)
	// Total is O(n log k)

	private static final int BASE = 10;
	private static MyLinkedList mergeList = new MyLinkedList();

	public static MyLinkedList sort(Integer list[]) throws IllegalArgumentException {
		inputCheck(list);
		mergeList.clear();
		final MyLinkedList[] buckets = new MyLinkedList[BASE];

		// TODO put elements into MergeList & find highest digit
		int highest = list[0];
		for (int i = 0; i < list.length; i++) {
			mergeList.add(list[i]);
			if (list[i] > highest) {
				highest = list[i];
			}
		}

		// TODO create empty buckets
		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = new MyLinkedList();
		}

		// TODO find max digit count
		int digits = String.valueOf(highest).length();

		// TODO iterate from smallest to highest digit
		for (int i = 0; i < digits; i++) {
			bucketSort(i, buckets);
		}

		return mergeList;
	}

	// returns digit (BASE) of val at pos (rightmost = 0)
	private static int getDigit(int val, int pos) {
		return (int) ((val / Math.pow(BASE, pos)) % BASE);
	}

	// calculates buckets for pos
	private static void bucketSort(int pos, MyLinkedList[] buckets) {
		// TODO fill buckets
		MyNode actual = mergeList.head;
		while (actual != null) {
			buckets[getDigit(actual.value, pos)].add(actual.value);
			actual = actual.next;
		}
		// TODO merge
		merge(buckets);
	}

	// Merges bucket lists into one list
	private static void merge(MyLinkedList[] buckets) {
		// TODO empty mergelist
		mergeList.clear();

		// TODO link bucket-lists & clear buckets
		for (int i = 0; i < buckets.length; i++) {
			mergeList.link(buckets[i]);
			buckets[i].clear();
		}
	}

	private static void inputCheck(Integer[] list) {
		if (list == null) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < list.length; i++) {
			if (list[i] == null) {
				throw new IllegalArgumentException();
			}
		}
	}
}