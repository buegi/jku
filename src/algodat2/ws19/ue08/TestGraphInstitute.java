package algodat2.ws19.ue08;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class TestGraphInstitute {

	private final boolean undirectedGraphFlag = false;

	// internal class for vertex
	public class Station implements MyVertex {
		protected String name;

		public Station(String stationName) {
			name = stationName;
		}

		public String toString() {
			return name;
		}
	}

	private Graph g;

	// --------------------------------------
	// --- TEST EXAMPLE1

	@Test
	public void testCatchesLoops() {
		Graph map = new Graph();
		int linz = map.insertVertex(new Station("Linz"));
		try {
			map.insertEdge(linz, linz, (int) Math.random() * 100);
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}

	@Test
	public void testGetNumberOfVertices() {
		g = this.initializeLargeGraph();
		assertEquals(18, g.getNumberOfVertices());
	}

	@Test
	public void testGetVertices() {
		g = this.initializeLargeGraph();
		assertEquals(18, g.getNumberOfVertices());
		MyVertex[] v = g.getVertices();
		assertEquals(18, v.length);

		assertTrue(v[0].toString().equals("Linz"));
		assertTrue(v[1].toString().equals("St.Poelten"));
		assertTrue(v[2].toString().equals("Wien"));
		assertTrue(v[3].toString().equals("Innsbruck"));
		assertTrue(v[4].toString().equals("Bregenz"));
		assertTrue(v[5].toString().equals("Eisenstadt"));
		assertTrue(v[6].toString().equals("Graz"));
		assertTrue(v[7].toString().equals("Klagenfurt"));
		assertTrue(v[8].toString().equals("Salzburg"));
		assertTrue(v[9].toString().equals("A"));
		assertTrue(v[10].toString().equals("B"));
		assertTrue(v[11].toString().equals("C"));
		assertTrue(v[12].toString().equals("D"));
		assertTrue(v[13].toString().equals("E"));
		assertTrue(v[14].toString().equals("F"));
		assertTrue(v[15].toString().equals("G"));
		assertTrue(v[16].toString().equals("H"));
		assertTrue(v[17].toString().equals("I"));
	}

	@Test
	public void testGetEdgeWeight() {
		g = this.initializeLargeGraph();
		assertEquals(1, g.hasEdge(0, 2));
		assertEquals(2, g.hasEdge(2, 5));
		assertEquals(3, g.hasEdge(2, 6));
		assertEquals(4, g.hasEdge(6, 7));
		assertEquals(-1, g.hasEdge(3, 4));
		assertEquals(5, g.hasEdge(4, 3));
		assertEquals(-1, g.hasEdge(3, 8));
		assertEquals(7, g.hasEdge(8, 3));
		assertEquals(-1, g.hasEdge(1, 8));
		assertEquals(-1, g.hasEdge(3, 1));

		assertEquals(-1, g.hasEdge(10, 8));
		assertEquals(8, g.hasEdge(8, 10));
		assertEquals(11, g.hasEdge(10, 9));
		assertEquals(9, g.hasEdge(7, 11));
		assertEquals(-1, g.hasEdge(12, 16));
		assertEquals(15, g.hasEdge(16, 12));
		assertEquals(-1, g.hasEdge(11, 12));
	}

	int[][] expectedMatrix_directedGraph = { { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

	int[][] expectedMatrix_undirectedGraph = { { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 } };

	@Test
	public void testGetAdjacencyMatrix() {
		g = this.initializeLargeGraph();
		int[][] expectedMatrix = null;

		if (undirectedGraphFlag) {
			expectedMatrix = expectedMatrix_undirectedGraph;
		} else {
			expectedMatrix = expectedMatrix_directedGraph;
		}

		int[][] matrix = g.getAdjacencyMatrix();

		// expected result for directed graphs:
		// 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		// 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0
		// 0 0 0 0 0 1 1 0 0 0 0 0 0 0 0 0 0 0
		// 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		// 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		// 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		// 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0
		// 0 0 0 1 0 0 0 0 0 0 0 1 0 0 0 0 0 0
		// 0 0 0 1 0 0 0 0 0 0 1 0 0 0 0 0 0 0
		// 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		// 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0
		// 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		// 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		// 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0
		// 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0
		// 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0
		// 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 1
		// 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0

		// expected result for undirected graphs:
		// 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		// 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0
		// 1 0 0 0 0 1 1 0 0 0 0 0 0 0 0 0 0 0
		// 0 0 0 0 1 0 0 1 1 0 0 0 0 0 0 0 0 0
		// 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		// 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
		// 0 0 1 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0
		// 0 0 0 1 0 0 1 0 0 0 0 1 0 0 0 0 0 0
		// 0 0 0 1 0 0 0 0 0 0 1 0 0 0 0 0 0 0
		// 0 1 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0
		// 0 0 0 0 0 0 0 0 1 1 0 0 0 0 0 0 0 0
		// 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0
		// 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0
		// 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0
		// 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 1 0 0
		// 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 1 0
		// 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 1 0 1
		// 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0

		ArrayList<int[]> err = new ArrayList<int[]>();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if (matrix[i][j] != expectedMatrix[i][j]) {
					err.add(new int[] { i, j });
				}
			}
		}
		for (int i = 0; i < err.size(); i++)
			System.out.println("Error @ matrix position: " + err.get(i)[0] + "," + err.get(i)[1]);
		assertEquals(err.size(), 0);
	}

	@Test
	public void testgetAdjacentVertices() {
		g = this.initializeLargeGraph();
		MyVertex[] v0 = g.getAdjacentVertices(0);
		assertEquals(1, v0.length);
		MyVertex[] v1 = g.getAdjacentVertices(1);
		assertEquals(1, v1.length);
		MyVertex[] v2 = g.getAdjacentVertices(2);
		assertEquals(2, v2.length);
		MyVertex[] v3 = g.getAdjacentVertices(3);
		assertEquals(0, v3.length);
		MyVertex[] v4 = g.getAdjacentVertices(4);
		assertEquals(1, v4.length);
		MyVertex[] v5 = g.getAdjacentVertices(5);
		assertEquals(0, v5.length);
		MyVertex[] v6 = g.getAdjacentVertices(6);
		assertEquals(1, v6.length);
		MyVertex[] v7 = g.getAdjacentVertices(7);
		assertEquals(2, v7.length);
		MyVertex[] v8 = g.getAdjacentVertices(8);
		assertEquals(2, v8.length);

		MyVertex[] v9 = g.getAdjacentVertices(9);
		assertEquals(0, v9.length);
		MyVertex[] v10 = g.getAdjacentVertices(10);
		assertEquals(1, v10.length);
		MyVertex[] v11 = g.getAdjacentVertices(11);
		assertEquals(0, v11.length);
		MyVertex[] v12 = g.getAdjacentVertices(12);
		assertEquals(0, v12.length);
		MyVertex[] v13 = g.getAdjacentVertices(13);
		assertEquals(1, v13.length);
		MyVertex[] v14 = g.getAdjacentVertices(14);
		assertEquals(1, v14.length);
		MyVertex[] v15 = g.getAdjacentVertices(15);
		assertEquals(1, v15.length);
		MyVertex[] v16 = g.getAdjacentVertices(16);
		assertEquals(2, v16.length);
		MyVertex[] v17 = g.getAdjacentVertices(17);
		assertEquals(0, v17.length);
	}

	// --------------------------------------
	// --- TEST BSP2

	@Test
	public void testIsConnected() {
		g = this.initializeLargeGraph();
		assertFalse(g.isConnected());
		g = this.initializeConnectedGraph();
		assertTrue(g.isConnected());
	}

	@Test
	public void testGetNumberOfComponents() {
		g = this.initializeLargeGraph();
		assertEquals(2, g.getNumberOfComponents());
		g = this.initializeConnectedGraph();
		assertEquals(1, g.getNumberOfComponents());
	}

	@Test
	public void testPrintComponents() {
		System.out.println();
		System.out.println("-- Visual Test (PrintComponents) --");

		System.out.println("- Test1: ");
		g = this.initializeLargeGraph();
		System.out.println(
				"Expected:\n" + "Linz Wien Eisenstadt Graz Klagenfurt Innsbruck Bregenz Salzburg B A St.Poelten C\n"
						+ "D H G F E I\n" + "Returned: ");
		g.printComponents();
		System.out.println("\n");
		System.out.println("- Test2: ");
		g = this.initializeConnectedGraph();
		System.out.println("Expected:\n"
				+ "Linz St.Poelten Wien Eisenstadt Graz Klagenfurt Salzburg Innsbruck Bregenz\n" + "Returned: ");
		g.printComponents();

	}

	@Test
	public void testIsCyclic() {
		g = this.initializeLargeGraph();
		assertFalse(g.isCyclic());
		g = this.initializeConnectedGraph();
		assertFalse(g.isCyclic());
		g = this.initializeCyclicGraph();
		assertTrue(g.isCyclic());
	}

	// --------------------------------------
	// --- PRIVATE Methods

	private Graph initializeLargeGraph() {
		Graph map = new Graph();

		int linz = map.insertVertex(new Station("Linz"));
		int stpoelten = map.insertVertex(new Station("St.Poelten"));
		int wien = map.insertVertex(new Station("Wien"));
		int innsbruck = map.insertVertex(new Station("Innsbruck"));
		int bregenz = map.insertVertex(new Station("Bregenz"));
		int eisenstadt = map.insertVertex(new Station("Eisenstadt"));
		int graz = map.insertVertex(new Station("Graz"));
		int klagenfurt = map.insertVertex(new Station("Klagenfurt"));
		int salzburg = map.insertVertex(new Station("Salzburg"));

		map.insertEdge(linz, wien, 1);
		map.insertEdge(wien, eisenstadt, 2);
		map.insertEdge(wien, graz, 3);
		map.insertEdge(graz, klagenfurt, 4);
		map.insertEdge(bregenz, innsbruck, 5);
		map.insertEdge(klagenfurt, innsbruck, 6);
		map.insertEdge(salzburg, innsbruck, 7);

		int a = map.insertVertex(new Station("A"));
		int b = map.insertVertex(new Station("B"));
		int c = map.insertVertex(new Station("C"));
		int d = map.insertVertex(new Station("D"));
		int e = map.insertVertex(new Station("E"));
		int f = map.insertVertex(new Station("F"));
		int g = map.insertVertex(new Station("G"));
		int h = map.insertVertex(new Station("H"));
		int i = map.insertVertex(new Station("I"));

		map.insertEdge(salzburg, b, 8);
		map.insertEdge(klagenfurt, c, 9);
		map.insertEdge(stpoelten, a, 10);
		map.insertEdge(b, a, 11);

		map.insertEdge(e, f, 12);
		map.insertEdge(f, g, 13);
		map.insertEdge(g, h, 14);
		map.insertEdge(h, d, 15);
		map.insertEdge(h, i, 16);

		try {
			map.insertEdge(i, i, 1);
		} catch (Exception ex) {
			assertTrue(ex instanceof IllegalArgumentException);
		}

		return map;
	}

	private Graph initializeConnectedGraph() {
		Graph map = new Graph();
		int linz = map.insertVertex(new Station("Linz"));
		int stpoelten = map.insertVertex(new Station("St.Poelten"));
		int wien = map.insertVertex(new Station("Wien"));
		int innsbruck = map.insertVertex(new Station("Innsbruck"));
		int bregenz = map.insertVertex(new Station("Bregenz"));
		int eisenstadt = map.insertVertex(new Station("Eisenstadt"));
		int graz = map.insertVertex(new Station("Graz"));
		int klagenfurt = map.insertVertex(new Station("Klagenfurt"));
		int salzburg = map.insertVertex(new Station("Salzburg"));

		map.insertEdge(linz, stpoelten, 1);
		map.insertEdge(linz, wien, 2);
		map.insertEdge(wien, eisenstadt, 3);
		map.insertEdge(wien, graz, 4);
		map.insertEdge(graz, klagenfurt, 5);
		map.insertEdge(bregenz, innsbruck, 6);
		map.insertEdge(salzburg, innsbruck, 7);
		map.insertEdge(salzburg, graz, 8);
		return map;
	}

	private Graph initializeCyclicGraph() {
		Graph map = new Graph();
		int linz = map.insertVertex(new Station("Linz"));
		int stpoelten = map.insertVertex(new Station("St.Poelten"));
		int wien = map.insertVertex(new Station("Wien"));
		int innsbruck = map.insertVertex(new Station("Innsbruck"));
		int bregenz = map.insertVertex(new Station("Bregenz"));
		int eisenstadt = map.insertVertex(new Station("Eisenstadt"));
		int graz = map.insertVertex(new Station("Graz"));
		int klagenfurt = map.insertVertex(new Station("Klagenfurt"));
		int salzburg = map.insertVertex(new Station("Salzburg"));

		map.insertEdge(linz, stpoelten, 1);
		map.insertEdge(linz, wien, 2);
		map.insertEdge(wien, eisenstadt, 3);
		map.insertEdge(wien, graz, 4);
		map.insertEdge(graz, klagenfurt, 5);
		map.insertEdge(bregenz, innsbruck, 6);
		map.insertEdge(innsbruck, salzburg, 7);
		map.insertEdge(salzburg, graz, 8);
		map.insertEdge(graz, linz, 9);
		map.insertEdge(klagenfurt, innsbruck, 10);

		return map;
	}
}
