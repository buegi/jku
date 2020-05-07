package algodat2.ws19.ue02;

public class MyTests {

	public static void main(String[] args) {

		Long one = 1L;
		Long two = 2L;
		Long three = 3L;
		Long four = 4L;
		Long five = 5L;
		Long six = 6L;
		Long seven = 7L;
		Long eight = 8L;
		Long nine = 9L;
		Long ten = 10L;

		// create list and insert some elements in unsorted order
		MyLinkedList<Long> newList = new MyLinkedList<Long>();
		newList.addFirst(one);
		newList.addFirst(two);
		newList.addFirst(three);
		newList.addFirst(four);
		newList.addLast(five);
		newList.addLast(six);
		newList.addFirst(seven);
		newList.addLast(eight);
		newList.addFirst(nine);
		newList.addLast(ten);

		// print actual list
		Long[] array = (Long[]) newList.toArray();
		System.out.println("------- Actual elements in list --------");
		printArray(array);
		System.out.println("------------ Actual size ---------------");
		System.out.println(newList.size());

		// test clearing list
		newList.clear();
		array = (Long[]) newList.toArray();
		System.out.println("------ Check size after clearing -------");
		System.out.println("Size after clearing: " + newList.size());

		// check get first and last when empty
		System.out.println("- Check get first & get last (cleared) -");
		System.out.println(newList.getFirst());
		System.out.println(newList.getLast());
		System.out.println("----------------------------------------");

		// check contains when empty
		System.out.println("------- Check contains when empty ------");
		System.out.println(newList.contains(9L));

		// check get index when empty
		System.out.println("------ Check get index when empty ------");
		System.out.println(newList.get(1));

		// re-insert elements in unsorted order
		newList.addFirst(one);
		newList.addFirst(two);
		newList.addFirst(three);
		newList.addFirst(four);
		newList.addLast(five);
		newList.addLast(six);
		newList.addFirst(seven);
		newList.addLast(eight);
		newList.addFirst(nine);
		newList.addLast(ten);

		// print actual list
		System.out.println("------- Actual elements in list --------");
		array = (Long[]) newList.toArray();
		printArray(array);

		// check size after re-inserting
		System.out.println("---- check size after re-inserting -----");
		System.out.println(newList.size());

		// check get first and last when not empty
		System.out.println("Check get first & get last (re-inserted)");
		System.out.print(newList.getFirst());
		System.out.print(newList.getLast());
		System.out.println();

		// check contains when not empty
		System.out.println("----- Check contains when not empty ----");
		System.out.println(newList.contains(9L));
		System.out.println(newList.contains(2L));
		System.out.println(newList.contains(10L));

		// print actual list
		System.out.println("------- Actual elements in list --------");
		array = (Long[]) newList.toArray();
		printArray(array);

		// check get index when not empty
		System.out.println("---- Check get index when not empty ----");
		System.out.print(newList.get(-1) + " ");
		System.out.print(newList.get(0) + " ");
		System.out.print(newList.get(1) + " ");
		System.out.print(newList.get(2) + " ");
		System.out.print(newList.get(3) + " ");
		System.out.print(newList.get(4) + " ");
		System.out.print(newList.get(5) + " ");
		System.out.print(newList.get(6) + " ");
		System.out.print(newList.get(7) + " ");
		System.out.print(newList.get(8) + " ");
		System.out.print(newList.get(9) + " ");
		System.out.print(newList.get(10) + " ");
		System.out.println();

		// check full remove when not empty
		System.out.println("---- Check remove when not empty ----");
		System.out.print(newList.remove(-1) + " ");
		System.out.print(newList.remove(9) + " ");
		System.out.print(newList.remove(8) + " ");
		System.out.print(newList.remove(7) + " ");
		System.out.print(newList.remove(6) + " ");
		System.out.print(newList.remove(5) + " ");
		System.out.print(newList.remove(4) + " ");
		System.out.print(newList.remove(3) + " ");
		System.out.print(newList.remove(2) + " ");
		System.out.print(newList.remove(1) + " ");
		System.out.print(newList.remove(0) + " ");
		System.out.print(newList.remove(10) + " ");
		System.out.println();

		// size after clearing list in single steps
		System.out.println("- check size after removing el-by-el -");
		System.out.println(newList.size());

		// print actual list
		System.out.println("------- Actual elements in list --------");
		if (newList.toArray() != null) {
			array = (Long[]) newList.toArray();
			printArray(array);
		} else {
			System.out.println("No elements in list");
		}

		// re-insert elements in unsorted order
		newList.addFirst(one);
		newList.addFirst(two);
		newList.addFirst(three);
		newList.addFirst(four);
		newList.addLast(five);
		newList.addLast(six);
		newList.addFirst(seven);
		newList.addLast(eight);
		newList.addFirst(nine);
		newList.addLast(ten);

		// check size after re-inserting
		System.out.println("---- check size after re-inserting -----");
		System.out.println(newList.size());

		// print actual list
		System.out.println("------- Actual elements in list --------");
		array = (Long[]) newList.toArray();
		printArray(array);

		// check remove first and remove last
		System.out.println("---- check remove first and remove last ----");
		System.out.print(newList.removeFirst() + " ");
		System.out.print(newList.removeLast() + " ");
		System.out.println();

		// check size after remove first & remove last
		System.out.println("---- check size after remove first and last-----");
		System.out.println(newList.size());

		// print actual list
		System.out.println("------- Actual elements in list --------");
		array = (Long[]) newList.toArray();
		printArray(array);

		// test clearing list
		newList.clear();
		array = (Long[]) newList.toArray();
		System.out.println("------ Check size after clearing -------");
		System.out.println("Size after clearing: " + newList.size());

		// print actual list
		System.out.println("------- Actual elements in list --------");
		if (newList.toArray() != null) {
			array = (Long[]) newList.toArray();
			printArray(array);
		} else {
			System.out.println("No elements in list");
		}

		// re-insert elements in unsorted order
		newList.addFirst(one);
		newList.addFirst(two);
		newList.addFirst(three);
		newList.addFirst(four);
		newList.addLast(five);
		newList.addLast(six);
		newList.addFirst(seven);
		newList.addLast(eight);
		newList.addFirst(nine);
		newList.addLast(ten);

		// print actual list
		System.out.println("------- Actual elements in list --------");
		array = (Long[]) newList.toArray();
		printArray(array);

		// sort list ASC
		newList.sortASC();

		// print actual list
		System.out.println("------- list after sorting ASC --------");
		array = (Long[]) newList.toArray();
		printArray(array);

		// sort list DESC
		newList.sortDES();

		// print actual list
		System.out.println("------- list after sorting DES --------");
		array = (Long[]) newList.toArray();
		printArray(array);

		System.out.println("------- insert sorted elements --------");
		newList.addSorted(11L);
		newList.addSorted(0L);
		newList.addSorted(4L);

		// print after inserting sorted
		System.out.println("---- list after inserting sorted => asc ----");
		array = (Long[]) newList.toArray();
		printArray(array);

		// test to String
		System.out.println("---- test to String ----");
		System.out.println(newList.toString());
	
		// clear list
		System.out.println("---- clear list ----");
		newList.clear();
		
		// test to String
		System.out.println("---- test to String ----");
		System.out.println(newList.toString());
		
	}

	public static void printArray(Long[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
}