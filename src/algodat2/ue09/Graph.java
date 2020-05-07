package algodat2.ue09;

import java.util.Arrays;

public class Graph {
	// represents an edge in the graph
	protected class Edge extends MyEdge {
		// public int in, out; // Indices of the vertices
		// public int weight; // Weight of the edge

		protected Edge(int in, int out, int weight) {
			this.in = in;
			this.out = out;
			this.weight = weight;
		}
	}

	protected MyVertex vertices[]; // vertex array
	protected MyEdge edges[]; // edge array
	private int numberOfVertices;
	private int numberOfEdges;
	private boolean isCyclic;
	private int[] components;

	// increase the size of edge and vertex arrays
	private void doubleArraySize() {
		int arraySize = vertices.length;
		vertices = Arrays.copyOf(vertices, arraySize * 2);
		edges = Arrays.copyOf(edges, arraySize * 2 * (arraySize * 2 - 1));
	}

	// Creates an empty graph.
	public Graph() {
		this.vertices = new MyVertex[1];
		this.edges = new MyEdge[0];
		this.numberOfVertices = 0;
		this.numberOfEdges = 0;
		this.isCyclic = false;
		this.components = new int[0];
	}

	// Returns the number of vertices in the graph.
	public int getNumberOfVertices() {
		return this.numberOfVertices;
	}

	// Returns the number of edges in the graph.
	public int getNumberOfEdges() {
		return this.numberOfEdges;
	}

	// Returns an array of length getNumberOfVertices() with the inserted vertices.
	public MyVertex[] getVertices() {
		return Arrays.copyOf(this.vertices, this.getNumberOfVertices());
	}

	// Returns an array of length getNumberOfEdges() with the inserted edges.
	public MyEdge[] getEdges() {
		return Arrays.copyOf(this.edges, this.getNumberOfEdges());
	}

	// Insert a new vertex v into the graph and return its index in the vertex
	// array.
	// If the vertex array is already full, then the method doubleArraySize() shall
	// be called
	// before inserting.
	// Null elements are not allowed (IllegalArgumentException).
	public int insertVertex(MyVertex v) throws IllegalArgumentException {
		if (v == null) {
			throw new IllegalArgumentException();
		}
		if (this.contains(v)) {
			return -1;
		}
		if (this.numberOfVertices >= vertices.length) {
			this.doubleArraySize();
		}
		this.vertices[this.numberOfVertices] = v;
		this.numberOfVertices++;
		return this.numberOfVertices - 1;
	}

	// Returns the edge weight if there is an edge between index v1 and v2,
	// otherwise -1.
	// In case of unknown or identical vertex indices throw an
	// IllegalArgumentException.
	public int hasEdge(int v1, int v2) throws IllegalArgumentException {
		if (v1 == v2 || v1 >= this.numberOfVertices || v1 < 0 || v2 >= this.numberOfVertices || v2 < 0
				|| this.vertices[v1] == null || this.vertices[v2] == null) {
			throw new IllegalArgumentException();
		}
		for (MyEdge edge : this.edges) {
			if (edge != null && (v1 == edge.in && v2 == edge.out)) {
				return edge.weight;
			}
		}
		return -1;
	}

	// Inserts an edge between vertices with v1 and v2. False is returned if the
	// edge already exists,
	// true otherwise. An IllegalArgumentException shall be thrown if the vertex
	// indices are unknown or
	// if v1 == v2 (loop).
	public boolean insertEdge(int v1, int v2, int weight) throws IllegalArgumentException {
		if (v1 == v2 || v1 >= this.numberOfVertices || v1 < 0 || v2 >= this.numberOfVertices || v2 < 0
				|| this.vertices[v1] == null || this.vertices[v2] == null) {
			throw new IllegalArgumentException();
		}
		if (this.hasEdge(v1, v2) < 0) {
			if (this.numberOfEdges >= this.edges.length) {
				this.doubleArraySize();
			}
			this.edges[numberOfEdges] = new Edge(v1, v2, weight);
			this.numberOfEdges++;
			return true;
		}
		return false;
	}

	// Returns an NxN adjacency matrix for the graph, where N = getNumVertices().
	// The matrix contains 1 if there is an edge at the index position, otherwise 0.
	public int[][] getAdjacencyMatrix() {
		final int[][] adjMatrix = new int[this.numberOfVertices][this.numberOfVertices];
		for (int i = 0; i < this.numberOfVertices; i++) {
			for (int j = 0; j < this.numberOfVertices; j++) {
				if (i != j && this.hasEdge(i, j) >= 0) {
					adjMatrix[i][j] = 1;
				}
			}
		}
		return adjMatrix;
	}

	// Returns an array of vertices which are adjacent to the vertex with index v.
	// If the vertex index v is unknown an IllegalArgumentException shall be thrown.
	public MyVertex[] getAdjacentVertices(int v) throws IllegalArgumentException {
		if (v < 0 || v >= this.numberOfVertices) {
			throw new IllegalArgumentException();
		}
		int temp[] = new int[this.numberOfVertices];
		int counter = 0;

		for (MyEdge edge : edges) {
			if (edge != null && edge.in == v) {
				temp[counter] = edge.out;
				counter++;
			}
		}
		MyVertex vert[] = new MyVertex[counter];
		for (int i = 0; i < counter; i++) {
			vert[i] = this.vertices[temp[i]];
		}
		return vert;
	}

	// -------------------------------------------------------------------
	// --------- Example 2 Methods
	// -------------------------------------------------------------------

	// Returns true if the graph is connected, otherwise false.
	// For the duration of the calculation temporarily convert the directed graph to
	// an undirected graph.
	public boolean isConnected() {
		return this.getNumberOfComponents() == 1;
	}

	// Returns the number of all (weak) components
	// For the duration of the calculation temporarily convert the directed graph to
	// an undirected graph.
	public int getNumberOfComponents() {
		this.depthFirstSearch();
		int compCounter = 0;
		if (this.components.length > 0) {
			compCounter++;
		}
		// components sample
		// [indexOfVertex1][indexOfVertex5][-1][indexOfVertex4][indexOfVertex6][indexOfVertex4][-1][...]
		for (int i = 0; i < components.length; i++) {
			if (this.components[i] == -1) {
				compCounter++;
			}
		}
		return compCounter;
	}

	// Prints the vertices of all components (one line per component).
	// For the duration of the calculation temporarily convert the directed graph to
	// an undirected graph.
	public void printComponents() {
		this.depthFirstSearch();
		int compCounter = 0;

		if (this.components.length > 0) {
			compCounter++;
		}
		// components sample
		// [indexOfVertex1][indexOfVertex5][-1][indexOfVertex4][indexOfVertex6][indexOfVertex4][-1][...]
		for (int i = 0; i < components.length; i++) {
			if (i != -1) {
				System.out.print(this.vertices[i].toString() + " ");
			}

			if (i == -1) {
				compCounter++;
				System.out.println("Component " + compCounter);
			}
		}
	}

	// Returns true if the graphs contains cycles, otherwise false.
	// For the duration of the calculation temporarily convert the directed graph to
	// an undirected graph.
	public boolean isCyclic() {
		this.depthFirstSearch();
		return isCyclic;
	}

	@Override
	public String toString() {
		this.depthFirstSearch();
		StringBuffer graph = new StringBuffer();
		for (int i = 0; i < components.length; i++) {
			graph.append(this.components[i]).append("\n");
		}
		return graph.toString();
	}
// -------------------------------------------------------------------
// --------- private helper methods
// -------------------------------------------------------------------

	private int indexOf(final MyVertex v) {
		if (v == null) {
			throw new IllegalArgumentException("Vertices must not be null");
		}
		for (int i = 0; i < this.numberOfVertices; i++) {
			if (this.vertices[i].equals(v)) {
				return i;
			}
		}
		return -1;
	}

	private boolean contains(final MyVertex v) {
		if (v == null) {
			throw new IllegalArgumentException("Vertices must not be null");
		}
		return this.indexOf(v) >= 0;
	}

	public int[] getAdjacentVerticesIndices(int v) throws IllegalArgumentException {
		if (v < 0 || v >= this.numberOfVertices) {
			throw new IllegalArgumentException();
		}
		int temp[] = new int[this.numberOfVertices];
		int counter = 0;

		for (MyEdge edge : edges) {
			if (edge != null && edge.in == v) {
				temp[counter] = edge.out;
				counter++;
			}
		}
		int vertIndices[] = new int[counter];
		for (int i = 0; i < counter; i++) {
			vertIndices[i] = temp[i];
		}
		return vertIndices;
	}

	private void extendComponents() {
		this.components = Arrays.copyOf(components, components.length + 1);
	}

	private void addComponent(int index) {
		this.extendComponents();
		this.components[components.length - 1] = index;
	}

	private boolean[] depthFirstSearch() {
		boolean[] visited = new boolean[this.numberOfVertices];
		MyVertex[] vrt = this.getVertices();

		if (vrt.length > 0 && vrt[0] != null) {
			this.depthFirstSearchRecursion(-1, 0, visited);
		}
		return visited;
	}

	private void depthFirstSearchRecursion(int last, int index, boolean[] visited) {
		if (last >= 0 && last < visited.length && visited[index]) {
			this.isCyclic = true;
			return;
		} else {
			visited[index] = true;
		}
		MyEdge[] edg = this.getEdges();

		int lastIn = -1;
		int lastOut = -1;

		// TODO
		// after each component add -1 in components array to count and print right in
		// the above methods (printComponents, getNumberOfComponents, isConnected)
		// this.addComponent(-1);

		for (MyEdge edge : edg) {
			if (edge != null && edge.in == index && edge.out != last && edge.out != lastIn && edge.out != lastOut) {
				lastIn = index;
				this.addComponent(index);
				this.depthFirstSearchRecursion(lastIn, edge.out, visited);
			} else if (edge != null && edge.out == index && edge.in != last && edge.in != lastIn
					&& edge.in != lastOut) {
				lastOut = index;
				this.addComponent(index);
				this.depthFirstSearchRecursion(lastOut, edge.in, visited);
			}
		}
	}
}