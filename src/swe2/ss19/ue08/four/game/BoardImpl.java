package swe2.ss19.ue08.four.game;

import swe2.inout.Out;

public class BoardImpl implements Board {

	public static final int ROWS = 6;
	public static final int COLS = 7;
	private final Stone[][] field = new Stone[ROWS][COLS];
	private int[] nextFree = new int[COLS];

	public BoardImpl() {
		this.initBoard();
	}

	private void initBoard() {
		for (int r = 0; r < field.length; r++) {
			for (int c = 0; c < field[r].length; c++) {
				field[r][c] = Stone.None;
				nextFree[c] = ROWS - 1;
			}
		}
	}

	public void print() {
		Out.println("|----------------------|");
		Out.print("|");
		for (int c = 0; c < COLS; c++) {
			Out.print(" " + (c + 1) + " ");
		}
		Out.println(" |");
		Out.println("|----------------------|");
		for (int r = 0; r < ROWS; r++) {
			Out.print("|");
			for (int c = 0; c < COLS; c++) {
				Out.print(getStone(r, c).outputString());
			}
			Out.println(" | ");
		}
		Out.println("|----------------------|");
	}

	@Override
	public Stone getStone(int row, int col) {
		return field[row][col];
	}

	@Override
	public int setStone(int col, Stone stone) {
		if (!isValidMove(col) || stone == Stone.None) {
			return -1;
		} else {
			field[nextFree[col]][col] = stone;
			return nextFree[col]--;
		}
	}

	@Override
	public boolean isValidMove(int col) {
		return col >= 0 && col < COLS && field[0][col] == Stone.None;
	}

	@Override
	public boolean isEmpty(int row, int col) {
		if (row >= ROWS || col >= COLS) {
			return false;
		}
		return field[row][col] == Stone.None;
	}

	@Override
	public boolean isFull() {
		int counter = 0;
		for (int c = 0; c < field[0].length; c++) {
			if (field[0][c] != Stone.None) {
				counter++;
			}
		}
		return counter == COLS;
	}

	@Override
	public boolean hasFourConnected(int row, int col) {
		return maxCount(row, col) >= 4;
	}

	@Override
	public int countInRow(int row, int col) {
		if (field[row][col] == Stone.None)
			return 0;
		int counter = 0;
		for (int c = 0; c < field[row].length; c++) {
			if (field[row][c] == field[row][col]) {
				counter++;
				if (counter >= 4) {
					return counter;
				}
			} else {
				counter = 0;
			}
		}
		return counter;
	}

	@Override
	public int countInCol(int row, int col) {
		if (field[row][col] == Stone.None)
			return 0;
		int counter = 0;
		for (int r = 0; r < field.length; r++) {
			if (field[r][col] == field[row][col]) {
				counter++;
				if (counter >= 4) {
					return counter;
				}
			} else {
				counter = 0;
			}
		}
		return counter;
	}

	@Override
	// diagonal from bottom left to top right
	public int countInDiagRight(int row, int col) {
		if (field[row][col] == Stone.None)
			return 0;
		int counter = 0;
		int posX = col;
		int posY = row;

		// count left down
		while (((posX >= 0 && posX < COLS) && (posY >= 0 && posY < ROWS)) && field[posY][posX] == field[row][col]) {
			counter++;
			posX--;
			posY++;
		}
		// reset position
		posX = col + 1;
		posY = row - 1;

		// count right up
		while (((posX >= 0 && posX < COLS) && (posY >= 0 && posY < ROWS)) && field[posY][posX] == field[row][col]) {
			counter++;
			posX++;
			posY--;
		}
		return counter;
	}

	@Override
	// diagonal from top left to bottom right
	public int countInDiagLeft(int row, int col) {
		if (field[row][col] == Stone.None)
			return 0;
		int counter = 0;
		int posX = col;
		int posY = row;

		// count left up
		while (((posX >= 0 && posX < COLS) && (posY >= 0 && posY < ROWS)) && field[posY][posX] == field[row][col]) {
			counter++;
			posX--;
			posY--;
		}
		// reset position
		posX = col + 1;
		posY = row + 1;

		// count right down
		while (((posX >= 0 && posX < COLS) && (posY >= 0 && posY < ROWS)) && field[posY][posX] == field[row][col]) {
			counter++;
			posX++;
			posY++;
		}
		return counter;
	}

	@Override
	public int maxCount(int row, int col) {
		return Math.max(countInRow(row, col),
				Math.max(countInCol(row, col), Math.max(countInDiagRight(row, col), countInDiagRight(row, col))));
	}
}