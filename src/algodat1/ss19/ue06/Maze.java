package algodat1.ss19.ue06;

public class Maze {
	
	static int m = 10;
	static int n = 9;
	int len;
	
	static char[][] M = new char[m][n];
	
	public static void main(String[] args) {
		Maze maze = new Maze();
		
		M[0][6] = 'X';
		M[1][2] = 'X';
		M[1][6] = 'X';
		M[2][2] = 'X';
		M[2][7] = 'X';
		M[3][3] = 'X';
		M[3][6] = 'X';
		M[4][0] = 'X';
		M[4][2] = 'E';
		M[5][2] = 'X';
		M[5][7] = 'E';
		M[5][8] = 'X';
		M[6][7] = 'X';
		M[7][2] = 'X';
		M[8][4] = 'X';
		M[8][5] = 'X';
		M[8][8] = 'E';
		M[9][2] = 'E';
		M[9][3] = 'X';
		M[9][7] = 'X';
		M[9][8] = 'X';
		
		for (int i = 0; i < M.length; i++) {
			for (int j = 0; j < M[i].length; j++) {
				if (M[i][j] != 'E' && M[i][j] != 'X') {
					M[i][j] = ' ';
				}
			}
		}
		
		maze.printMaze();
		int i = 0; 
		int j = 0;
		int length = 0;
		boolean foundExit = maze.nCells(M, m, n, i, j, length);
		
		if (foundExit) {
			System.out.println("Found an Exit!");
		}
		
		maze.printMaze();
		maze.printLength();
	}
	
	public boolean nCells(char[][] L, int m, int n, int i, int j, int len) {
		// At first call the len is 0, so we write a S for "startpoint"
//		if (len == 0) {
//			if (L[i][j] == 'E') {			// If the startpoint is the exit
//				L[i][j] = 'U';				// Write a "U" for "Used Exit" instead of the "E"
//				this.len = len;				
//				M = L;
//				return true;
//			} else if (L[i][j] == 'X') {	// If the startpoint is invalid (lies on the "X")
//				return false; 
//
//			} else {						// If the current position is "empty" (no "X" or "E")
//				L[i][j] = 'S';
//				return nCells(L, m, n, i, j + 1, len + 1) || nCells(L, m, n, i + 1, j, len + 1);
//			}
//		}

		// If i or j is at the end of the array size
		if (i == m - 1 || j == n - 1) {
			if (L[i][j] == 'E') {			// If the current position is the exit
				L[i][j] = 'U';				// Write a "U" for "Used Exit" instead of the "E"
				this.len = len;
				M = L;
				return true;
			} else if (L[i][j] == 'X') {	// If the current position is marked with an "X"
				return false;
			} else {						// If the current position is "empty" (no "X" or "E")
				L[i][j] = '.';				// Write a "." for a way-point
				if (i == m - 1 && j != n - 1) {
					// Check the last corner (bottom right)
					return nCells(L, m, n, i, j + 1, len + 1);
				} else if (i != m - 1 && j == n - 1) {
					// Check the last corner (bottom right)
					return nCells(L, m, n, i + 1, j, len + 1);
				} else {					// If (i == m - 1 && j == n - 1)
					return false;
				}
			}
		}

		// If len > 0 and i and j are not at the end of the array size
		else {
			if (L[i][j] == 'E') {			// If the current position is the exit
				L[i][j] = 'U';				// Write a "U" for "Used Exit" instead of the "E"
				this.len = len;
				M = L;
				return true;
			} else if (L[i][j] == 'X') {	// If the current position is marked with an "X"
				return false;
			} else {						// If the current position is "empty" (no "X" or "E")
				L[i][j] = '.';
				return nCells(L, m, n, i, j + 1, len + 1) || nCells(L, m, n, i + 1, j, len + 1);
			}
		}
	}
	
	void printMaze() {
		int counter = 0;
		
		System.out.println("   0   1   2   3   4   5   6   7   8   ");
		
		for (int i = 0; i < M.length; i++) {
			System.out.println(" -------------------------------------");
			System.out.print(counter + "|");
			
			for (int j = 0; j < M[i].length; j++) {
				System.out.print(" " + M[i][j] + " |");
			}
			
			System.out.println();
			counter++;
		}
		System.out.println(" -------------------------------------");
	}
	
	void printLength() {
		System.out.println("Length = " + len);
	}
}