package ss19.ue08.demo.app;

import at.jku.ssw.tictactoe.Field;
import at.jku.ssw.tictactoe.Player;
import inout.In;
import inout.Out;

public class Game {
	public static void main(String[] args) {
		Field field = new Field();
		
		while (!field.isGameOver()) {
			printField(field);
			Out.print("Please choose your next move: ");
			int row = In.readInt();
			int column = In.readInt();
			try {
				field.set(row, column, field.getNextPlayer());
			} catch (IllegalStateException | IllegalArgumentException | NullPointerException e) {
				Out.println(e.getMessage());
			}
		}
		printField(field);
		Player winner = field.getWinner();
		if (winner == Player.NIL) {
			Out.println("This game is a draw!");
		} else {
			Out.println("The winner is: " + winner);
		}
		
	}

	private static void printField(Field field) {
		for (int row = 0; row < Field.SIZE; ++row) {
			for (int column = 0; column < Field.SIZE; ++column) {
				Player cell = field.get(row, column);
				Out.print(cell == Player.NIL ? "." : cell);
			}
			Out.println();
		}
	}
}
