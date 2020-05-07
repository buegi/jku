package algodat2.ws19.ue04;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestAssignment04s {

	// Test sequence: 5, 18, 1, 8, 14, 16, 13, 3
	// Resulting tree after successively inserting of the elements:
	//
	// 5
	// 1 18
	// 3 8
	// 14
	// 13 16
	//
	// Inorder: 1, 3, 5, 8, 13, 14, 16, 18
	// Preorder: 5, 1, 3, 18, 8, 14, 13, 16
	// Postorder: 3, 1, 13, 16, 14, 8, 18, 5

	MyBinarySearchTree tree;

	@Test
	public void testInsert() {
		tree = new MyBinarySearchTree();
		assertEquals(0, tree.size());
		tree.insert(5, "5");
		assertEquals(1, tree.size());
		tree.insert(18, "18");
		assertEquals(2, tree.size());
		tree.insert(1, "1");
		assertEquals(3, tree.size());
		tree.insert(8, "8");
		assertEquals(4, tree.size());
		tree.insert(14, "14");
		assertEquals(5, tree.size());
		tree.insert(16, "16");
		assertEquals(6, tree.size());
		tree.insert(13, "13");
		assertEquals(7, tree.size());
		tree.insert(3, "3");
		assertEquals(8, tree.size());

		boolean ret = tree.insert(Integer.valueOf(3), "3");
		assertEquals(ret, false);
		ret = tree.insert(Integer.valueOf(18), "18");
		assertEquals(ret, false);

		try {
			tree.insert(null, null);
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}

		try {
			tree.insert(0, null);
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}

		assertEquals(8, tree.size());
	}

	@Test
	public void testFind() {
		tree = new MyBinarySearchTree();
		assertEquals(0, tree.size());
		tree.insert(5, "5");
		assertEquals(1, tree.size());
		tree.insert(18, "18");
		assertEquals(2, tree.size());
		tree.insert(1, "1");
		assertEquals(3, tree.size());
		tree.insert(8, "8");
		assertEquals(4, tree.size());
		tree.insert(14, "14");
		assertEquals(5, tree.size());
		tree.insert(16, "16");
		assertEquals(6, tree.size());
		tree.insert(13, "13");
		assertEquals(7, tree.size());
		tree.insert(3, "3");
		assertEquals(8, tree.size());

		assertEquals("5", tree.find(Integer.valueOf(5)));
		assertEquals("18", tree.find(Integer.valueOf(18)));
		assertEquals("1", tree.find(Integer.valueOf(1)));
		assertEquals("8", tree.find(Integer.valueOf(8)));
		assertEquals("14", tree.find(Integer.valueOf(14)));
		assertEquals("16", tree.find(Integer.valueOf(16)));
		assertEquals("13", tree.find(Integer.valueOf(13)));
		assertEquals("3", tree.find(Integer.valueOf(3)));
		assertEquals(null, tree.find(Integer.valueOf(2)));

		try {
			tree.find(null);
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}

	@Test
	public void testRemove() {
		tree = new MyBinarySearchTree();
		assertEquals(0, tree.size());
		tree.insert(5, "5");
		assertEquals(1, tree.size());
		tree.insert(18, "18");
		assertEquals(2, tree.size());
		tree.insert(1, "1");
		assertEquals(3, tree.size());
		tree.insert(8, "8");
		assertEquals(4, tree.size());
		tree.insert(14, "14");
		assertEquals(5, tree.size());
		tree.insert(16, "16");
		assertEquals(6, tree.size());
		tree.insert(13, "13");
		assertEquals(7, tree.size());
		tree.insert(3, "3");
		assertEquals(8, tree.size());

		tree.remove(5);
		assertEquals(tree.getRoot().elem, tree.toArrayPreOrder()[0]);
		assertEquals(tree.isInternal(14), true);
		assertEquals(tree.isInternal(16), false);
		assertEquals(tree.isInternal(13), false);
		tree.remove(13);
		tree.remove(16);
		assertEquals(tree.isInternal(14), false);
		assertEquals(tree.isInternal(18), true);
		tree.remove(18);
		assertEquals(tree.getRoot().right.elem, "14");

		try {
			tree.remove(null);
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}

		assertEquals(4, tree.size());
	}

	@Test
	public void testSize() {
		tree = new MyBinarySearchTree();
		assertEquals(0, tree.size());
		tree.insert(5, "5");
		assertEquals(1, tree.size());
		tree.insert(18, "18");
		assertEquals(2, tree.size());
		tree.insert(1, "1");
		assertEquals(3, tree.size());
		tree.insert(8, "8");
		assertEquals(4, tree.size());
		tree.insert(14, "14");
		assertEquals(5, tree.size());
		tree.insert(16, "16");
		assertEquals(6, tree.size());
		tree.insert(13, "13");
		assertEquals(7, tree.size());
		tree.insert(3, "3");
		assertEquals(8, tree.size());

		tree.remove(1);
		assertEquals(7, tree.size());
		tree.remove(8);
		assertEquals(6, tree.size());
		tree.remove(8);
		assertEquals(6, tree.size());
	}

	@Test
	public void testToArrayInOrder() {
		tree = new MyBinarySearchTree();
		assertEquals(0, tree.size());
		tree.insert(5, "5");
		assertEquals(1, tree.size());
		tree.insert(18, "18");
		assertEquals(2, tree.size());
		tree.insert(1, "1");
		assertEquals(3, tree.size());
		tree.insert(8, "8");
		assertEquals(4, tree.size());
		tree.insert(14, "14");
		assertEquals(5, tree.size());
		tree.insert(16, "16");
		assertEquals(6, tree.size());
		tree.insert(13, "13");
		assertEquals(7, tree.size());
		tree.insert(3, "3");
		assertEquals(8, tree.size());

		Object[] array = tree.toArrayInOrder();
		assertEquals(array[0], "1");
		assertEquals(array[1], "3");
		assertEquals(array[2], "5");
		assertEquals(array[3], "8");
		assertEquals(array[4], "13");
		assertEquals(array[5], "14");
		assertEquals(array[6], "16");
		assertEquals(array[7], "18");

		System.out.print("Inorder:   ");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	@Test
	public void testToArrayPreOrder() {
		tree = new MyBinarySearchTree();
		assertEquals(0, tree.size());
		tree.insert(5, "5");
		assertEquals(1, tree.size());
		tree.insert(18, "18");
		assertEquals(2, tree.size());
		tree.insert(1, "1");
		assertEquals(3, tree.size());
		tree.insert(8, "8");
		assertEquals(4, tree.size());
		tree.insert(14, "14");
		assertEquals(5, tree.size());
		tree.insert(16, "16");
		assertEquals(6, tree.size());
		tree.insert(13, "13");
		assertEquals(7, tree.size());
		tree.insert(3, "3");
		assertEquals(8, tree.size());

		Object[] array = tree.toArrayPreOrder();
		assertEquals(array[0], "5");
		assertEquals(array[1], "1");
		assertEquals(array[2], "3");
		assertEquals(array[3], "18");
		assertEquals(array[4], "8");
		assertEquals(array[5], "14");
		assertEquals(array[6], "13");
		assertEquals(array[7], "16");

		System.out.print("Preorder:  ");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	@Test
	public void testToArrayPostOrder() {
		tree = new MyBinarySearchTree();
		assertEquals(0, tree.size());
		tree.insert(5, "5");
		assertEquals(1, tree.size());
		tree.insert(18, "18");
		assertEquals(2, tree.size());
		tree.insert(1, "1");
		assertEquals(3, tree.size());
		tree.insert(8, "8");
		assertEquals(4, tree.size());
		tree.insert(14, "14");
		assertEquals(5, tree.size());
		tree.insert(16, "16");
		assertEquals(6, tree.size());
		tree.insert(13, "13");
		assertEquals(7, tree.size());
		tree.insert(3, "3");
		assertEquals(8, tree.size());

		Object[] array = tree.toArrayPostOrder();
		assertEquals(array[0], "3");
		assertEquals(array[1], "1");
		assertEquals(array[2], "13");
		assertEquals(array[3], "16");
		assertEquals(array[4], "14");
		assertEquals(array[5], "8");
		assertEquals(array[6], "18");
		assertEquals(array[7], "5");

		System.out.print("Postorder: ");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	@Test
	public void testGetParent() {
		tree = new MyBinarySearchTree();
		assertEquals(0, tree.size());
		tree.insert(5, "5");
		assertEquals(1, tree.size());
		tree.insert(18, "18");
		assertEquals(2, tree.size());
		tree.insert(1, "1");
		assertEquals(3, tree.size());
		tree.insert(8, "8");
		assertEquals(4, tree.size());
		tree.insert(14, "14");
		assertEquals(5, tree.size());
		tree.insert(16, "16");
		assertEquals(6, tree.size());
		tree.insert(13, "13");
		assertEquals(7, tree.size());
		tree.insert(3, "3");
		assertEquals(8, tree.size());

		assertEquals(Integer.valueOf(1), tree.getParent(Integer.valueOf(3)));
		assertEquals(Integer.valueOf(8), tree.getParent(Integer.valueOf(14)));
		assertEquals(Integer.valueOf(5), tree.getParent(Integer.valueOf(18)));
	}

	@Test
	public void testIsRoot() {
		tree = new MyBinarySearchTree();
		assertEquals(0, tree.size());
		tree.insert(5, "5");
		assertEquals(1, tree.size());
		tree.insert(18, "18");
		assertEquals(2, tree.size());
		tree.insert(1, "1");
		assertEquals(3, tree.size());
		tree.insert(8, "8");
		assertEquals(4, tree.size());
		tree.insert(14, "14");
		assertEquals(5, tree.size());
		tree.insert(16, "16");
		assertEquals(6, tree.size());
		tree.insert(13, "13");
		assertEquals(7, tree.size());
		tree.insert(3, "3");
		assertEquals(8, tree.size());

		assertTrue(tree.isRoot(Integer.valueOf(5)));
		assertFalse(tree.isRoot(Integer.valueOf(18)));
	}

	@Test
	public void testIsInternal() {
		tree = new MyBinarySearchTree();
		assertEquals(0, tree.size());
		tree.insert(5, "5");
		assertEquals(1, tree.size());
		tree.insert(18, "18");
		assertEquals(2, tree.size());
		tree.insert(1, "1");
		assertEquals(3, tree.size());
		tree.insert(8, "8");
		assertEquals(4, tree.size());
		tree.insert(14, "14");
		assertEquals(5, tree.size());
		tree.insert(16, "16");
		assertEquals(6, tree.size());
		tree.insert(13, "13");
		assertEquals(7, tree.size());
		tree.insert(3, "3");
		assertEquals(8, tree.size());

		assertTrue(tree.isInternal(Integer.valueOf(5)));
		assertTrue(tree.isInternal(Integer.valueOf(18)));
		assertTrue(tree.isInternal(Integer.valueOf(1)));
		assertTrue(tree.isInternal(Integer.valueOf(8)));
		assertTrue(tree.isInternal(Integer.valueOf(14)));
		assertFalse(tree.isInternal(Integer.valueOf(16)));
		assertFalse(tree.isInternal(Integer.valueOf(13)));
		assertFalse(tree.isInternal(Integer.valueOf(3)));
	}

	@Test
	public void testIsExternal() {
		tree = new MyBinarySearchTree();
		assertEquals(0, tree.size());
		tree.insert(5, "5");
		assertEquals(1, tree.size());
		tree.insert(18, "18");
		assertEquals(2, tree.size());
		tree.insert(1, "1");
		assertEquals(3, tree.size());
		tree.insert(8, "8");
		assertEquals(4, tree.size());
		tree.insert(14, "14");
		assertEquals(5, tree.size());
		tree.insert(16, "16");
		assertEquals(6, tree.size());
		tree.insert(13, "13");
		assertEquals(7, tree.size());
		tree.insert(3, "3");
		assertEquals(8, tree.size());

		assertFalse(tree.isExternal(Integer.valueOf(5)));
		assertFalse(tree.isExternal(Integer.valueOf(18)));
		assertFalse(tree.isExternal(Integer.valueOf(1)));
		assertFalse(tree.isExternal(Integer.valueOf(8)));
		assertFalse(tree.isExternal(Integer.valueOf(14)));
		assertTrue(tree.isExternal(Integer.valueOf(16)));
		assertTrue(tree.isExternal(Integer.valueOf(13)));
		assertTrue(tree.isExternal(Integer.valueOf(3)));
	}
}
