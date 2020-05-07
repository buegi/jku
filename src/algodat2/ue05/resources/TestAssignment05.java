package algodat2.ue05.resources;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class TestAssignment05 {

	AVLTree tree;	
	void reset() {
		tree = new AVLTree();
	}
	
	public boolean insert (Integer key, String value) {
		return tree.insert (key, value);
	}
	
	public boolean insert (Integer key) {
		return tree.insert (key, key.toString());
	}
	
	@Test
	public void testSize() {
		reset();
		assertEquals(0, tree.size());
		insert(Integer.valueOf( 5)); 	assertEquals(1, tree.size());
		insert(Integer.valueOf(18)); 	assertEquals(2, tree.size());
		insert(Integer.valueOf( 2)); 	assertEquals(3, tree.size());
		insert(Integer.valueOf( 8)); 	assertEquals(4, tree.size());
		insert(Integer.valueOf(14));	assertEquals(5, tree.size());
		insert(Integer.valueOf(16));	assertEquals(6, tree.size());
		insert(Integer.valueOf(13));	assertEquals(7, tree.size());
		insert(Integer.valueOf( 3));	assertEquals(8, tree.size());
		insert(Integer.valueOf(12));	assertEquals(9, tree.size());
		insert(Integer.valueOf(21));	assertEquals(10, tree.size());
		insert(Integer.valueOf( 1));	assertEquals(11, tree.size());
		insert(Integer.valueOf( 0));	assertEquals(12, tree.size());
	}
	
	@Test
	public void testHeight() {
		reset();
		assertEquals(-1, tree.height());
		insert(Integer.valueOf( 5));	assertEquals(0, tree.height());
		insert(Integer.valueOf(18)); 	assertEquals(1, tree.height());
		insert(Integer.valueOf( 2)); 	assertEquals(1, tree.height());
		insert(Integer.valueOf( 8)); 	assertEquals(2, tree.height());
		insert(Integer.valueOf(14));	assertEquals(2, tree.height());
		insert(Integer.valueOf(16));	assertEquals(2, tree.height());
		insert(Integer.valueOf(13));	assertEquals(3, tree.height());
		insert(Integer.valueOf( 3));	assertEquals(3, tree.height());
		insert(Integer.valueOf(12));	assertEquals(3, tree.height());
		insert(Integer.valueOf(21));	assertEquals(3, tree.height());
		insert(Integer.valueOf( 1));	assertEquals(3, tree.height());
		insert(Integer.valueOf( 0));	assertEquals(3, tree.height());
	}
	
	@Test
	public void testInsert() {
		reset();
		try {
			tree.insert(null, null);
		} catch(Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		testSize(); testHeight();
	}
	
	@Test
	public void testFind() {
		reset();
		insert(Integer.valueOf( 5));	
		insert(Integer.valueOf(18));
		insert(Integer.valueOf( 2));
		insert(Integer.valueOf( 8));
		insert(Integer.valueOf(14));
		insert(Integer.valueOf(16));
		insert(Integer.valueOf(13));
		insert(Integer.valueOf( 3));
		insert(Integer.valueOf(12));
		insert(Integer.valueOf(21));
		insert(Integer.valueOf( 1));
		insert(Integer.valueOf( 0));
		assertEquals("5",	tree.find(Integer.valueOf(  5)));
		assertEquals("18", tree.find(Integer.valueOf( 18)));
		assertEquals("2", 	tree.find(Integer.valueOf(  2)));
		assertEquals("8", 	tree.find(Integer.valueOf(  8)));
		assertEquals("14", tree.find(Integer.valueOf( 14)));
		assertEquals("16", tree.find(Integer.valueOf( 16)));
		assertEquals("13", tree.find(Integer.valueOf( 13)));
		assertEquals("3", 	tree.find(Integer.valueOf(  3)));
		assertEquals("12", tree.find(Integer.valueOf( 12)));
		assertEquals("21", tree.find(Integer.valueOf( 21)));
		assertEquals("1", 	tree.find(Integer.valueOf(  1)));
		assertEquals("0", 	tree.find(Integer.valueOf(  0)));
		assertEquals(null,	tree.find(Integer.valueOf(  4)));
		assertEquals(null,	tree.find(Integer.valueOf( 99)));
		assertEquals(null,	tree.find(Integer.valueOf(100)));
	}
	
	@Test
	public void testRemove() {
		reset();
		insert(Integer.valueOf( 5));	
		insert(Integer.valueOf(18));
		insert(Integer.valueOf( 2));
		insert(Integer.valueOf( 8));
		insert(Integer.valueOf(14));
		insert(Integer.valueOf(16));
		insert(Integer.valueOf(13));
		insert(Integer.valueOf( 3));
		insert(Integer.valueOf(12));
		insert(Integer.valueOf(21));
		insert(Integer.valueOf( 1));
		insert(Integer.valueOf( 0)); 
		
		assertFalse	(tree.remove(Integer.valueOf(  4))); 	assertEquals(12, tree.size()); assertEquals(3, tree.height());
		assertFalse	(tree.remove(Integer.valueOf( 99))); 	assertEquals(12, tree.size()); assertEquals(3, tree.height());
		assertFalse	(tree.remove(Integer.valueOf(100))); 	assertEquals(12, tree.size()); assertEquals(3, tree.height());

		assertTrue	(tree.remove(Integer.valueOf( 14))); 	assertEquals(11, tree.size()); assertEquals(3, tree.height());
		assertTrue	(tree.remove(Integer.valueOf(  3))); 	assertEquals(10, tree.size()); assertEquals(3, tree.height());
		assertTrue	(tree.remove(Integer.valueOf( 21))); 	assertEquals( 9, tree.size()); assertEquals(3, tree.height());
		assertTrue	(tree.remove(Integer.valueOf( 18))); 	assertEquals( 8, tree.size()); assertEquals(3, tree.height());
		assertTrue	(tree.remove(Integer.valueOf( 16))); 	assertEquals( 7, tree.size()); assertEquals(2, tree.height());
		
		assertFalse	(tree.remove(Integer.valueOf( 14)));	assertEquals (7, tree.size()); assertEquals(2, tree.height());
	}
	
	
	@Test
	public void testStructure1RotationHardcoded() {
		reset();
		insert(Integer.valueOf( 5));	
		insert(Integer.valueOf(18));
		insert(Integer.valueOf( 2));
		insert(Integer.valueOf( 8));
		insert(Integer.valueOf(14));
		AVLNode root = tree.getRoot();
		
		// test structure
		assertEquals(root.key, Integer.valueOf(5));
		assertEquals(root.height, 2);
		// left branch
		assertEquals(root.left.key, Integer.valueOf(2));
		assertEquals(root.left.height, 0);
		// right branch
		assertEquals(root.right.key, Integer.valueOf(14));
		assertEquals(root.right.height, 1);
		assertEquals(root.left.left, null);
		assertEquals(root.left.right, null);
		assertEquals(root.right.left.key, Integer.valueOf(8));
		assertEquals(root.right.left.height, 0);
		assertEquals(root.right.left.left, null);
		assertEquals(root.right.left.right, null);
		assertEquals(root.right.right.key, Integer.valueOf(18));
		assertEquals(root.right.right.height, 0);
		assertEquals(root.right.right.left, null);
		assertEquals(root.right.right.right, null);
	}
	

	@Test
	public void testToArray() {
		reset();
		insert(Integer.valueOf( 5));	
		insert(Integer.valueOf(18));
		insert(Integer.valueOf( 2));
		insert(Integer.valueOf( 8));
		insert(Integer.valueOf(14));
		insert(Integer.valueOf(16));
		insert(Integer.valueOf(13));
		insert(Integer.valueOf( 3));
		insert(Integer.valueOf(12));
		insert(Integer.valueOf(21));
		insert(Integer.valueOf( 1));
		insert(Integer.valueOf( 0));
		
		Object[] array = tree.toArray();
		assertEquals(array[0], 	"5");
		assertEquals(array[1], 	"2");
		assertEquals(array[2], 	"1");
		assertEquals(array[3], 	"0");
		assertEquals(array[4], 	"3");
		assertEquals(array[5], 	"14");
		assertEquals(array[6], 	"12");
		assertEquals(array[7], 	"8");
		assertEquals(array[8], 	"13");
		assertEquals(array[9], 	"18");
		assertEquals(array[10], "16");
		assertEquals(array[11], "21");
		
		try {
			@SuppressWarnings("unused")
			Object x = array[12];
		} catch(Exception e) {
			assertTrue(e instanceof ArrayIndexOutOfBoundsException);
		}

		System.out.println("-------------------------------------------------------");
		System.out.println("testToArray\nOrdered (Pre):   5 2 1 0 3 14 12 8 13 18 16 21");
		System.out.print("Ordered Student: ");
		for(int i=0; i<array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
	
	@Test
	public void testMultipleValues() {
		reset();
		assertEquals(-1, tree.height()); assertEquals(0, tree.size());
		
		// insert 2x the same 
		insert(Integer.valueOf(4), "4");
		assertEquals(0, tree.height()); assertEquals(1, tree.size());
		assertEquals(insert(Integer.valueOf(4), "2"), false);
		assertEquals(0, tree.height()); assertEquals(1, tree.size());
		tree.remove(Integer.valueOf(4));
		assertEquals(-1, tree.height()); assertEquals(0, tree.size());
		
		// create simple AVL tree
		int ub = 10;
		for (int f = 0; f < ub; ++f) {
			insert(Integer.valueOf(f), Integer.toString(f));
			assertEquals(f + 1, tree.size());
			assertTrue (tree.height() <= 4);
		}
		// insert new values
		for (int f = ub; f < ub + ub; ++f) {
			insert(Integer.valueOf(f), Integer.toString(f));
			assertEquals(f + 1, tree.size());
			assertTrue (tree.height() <= 5);
		}
		
		int totalSize = 1000000;
		int inserts = 2000;
		int removes = 200;
		
		reset();
		System.out.println("-------------------------------------------------------");
		System.out.println("testMultipleValues\nChecking Integrity while Inserting/Deleting");
		while(tree.size()<totalSize) {
			insertMultipleKeys(inserts, totalSize*2);
			removeMultipleKeys(removes, totalSize*2);
			//System.out.println(tree.size()+";"+tree.height()+";"+(1.44*(Math.log(tree.size()+2)/Math.log(2))-0.328));
			
			assertTrue(checkAVLIntegrity());
			assertTrue(tree.height() <= (1.44*(Math.log(tree.size()+2)/Math.log(2))-0.328));
		}
		System.out.println("Checking Integrity Successfull");
	}

	private int insertMultipleKeys(int numberOfKeys, int valueRange) {
		int inserted = 0;
		try {
			for(int i= 0; i<numberOfKeys; i++) {
				int val = (int)(Math.random()*valueRange);
				if(tree.insert(Integer.valueOf(val), Integer.toString(val))) inserted++;
			}
		} catch(Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		return inserted;
	}
	
	private int removeMultipleKeys(int numberOfKeys, int valueRange) {
		int removed = 0;
		try {
			for(int i= 0; i<numberOfKeys; i++) {
				int val = (int)(Math.random()*valueRange);
				if(tree.remove(Integer.valueOf(val))) removed++;
			}
		} catch(Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		return removed;
	}
	private boolean checkAVLIntegrity() {
		return checkAVLIntegrity(tree.root);
	}
	private boolean checkAVLIntegrity(AVLNode n) {
		boolean isAVL = true;
		if(n == null) return true;
		if(!isAVLTree(n)) isAVL = false;
		
		if(!isAVL) {
			int lh = n.left==null ? -1:n.left.height;
			int rh = n.right==null ? -1:n.right.height;
			System.out.println(n.height+ ";"+lh+";" +rh);
		}
		
		if(!checkAVLIntegrity(n.left)) isAVL = false;
		if(!checkAVLIntegrity(n.right)) isAVL = false;
		
		return isAVL;
	}
	private boolean isAVLTree(AVLNode n) {
		int diff = (n.left == null ? -1 : n.left.height)
				- (n.right == null ? -1 : n.right.height);
		return -1 <= diff && diff <= 1;
	}

}
