package ss19.ue08.four.game;

import four.player.Player;
import inout.In;

public class Game {

	private Board board;
	private GameState gameState;
	private Player xPlayer;
	private Player oPlayer;
	private Player current;

	public Game(Player xPlayer, Player oPlayer) {
		super();
		board = new BoardImpl();
		this.xPlayer = xPlayer;
		this.oPlayer = oPlayer;
	}

	private void printGameState() {
		System.out.println("GameState: " + gameState);

		if (gameState == GameState.OWon) {
			System.out.println("");
			System.out.println("Game finished. O has won!");
		}

		if (gameState == GameState.XWon) {
			System.out.println("");
			System.out.println("Game finished. X has won!");
		}

	}

	private void nextPlayer() {
		if (gameState == GameState.XsTurn) {
			gameState = GameState.OsTurn;
			current = oPlayer;
		} else if (gameState == GameState.OsTurn) {
			gameState = GameState.XsTurn;
			current = xPlayer;
		}
	}

	private void firstPlayer() {
		System.out.println("Enter Player that starts (x or o): ");
		char player = ' ';
		while (player != 'x' && player != 'o') {
			player = In.readChar();
			if (player != 'x' && player != 'o') {
				System.out.println("Invalid Input! (x or o allowed), You typed: " + player);
			}
			if (player == 'x') {
				current = xPlayer;
			}
			if (player == 'o') {
				current = oPlayer;
			}
		}
		gameState = (current == xPlayer) ? GameState.XsTurn : GameState.OsTurn;
	}

	public void play() {
		firstPlayer();
		while (gameState != GameState.Draw || gameState != GameState.XWon || gameState != GameState.OWon) {
			int row = -1;
			int col = -1;
			printGameState();
			board.print();
			col = current.getMove(board);
			row = (gameState == GameState.XsTurn) ? board.setStone(col, Stone.X) : board.setStone(col, Stone.O);
			if (board.hasFourConnected(row, col)) {
				if (current == xPlayer) {
					gameState = GameState.XWon;
					board.print();
					printGameState();
					break;
				} else if (current == oPlayer) {
					gameState = GameState.OWon;
					board.print();
					printGameState();
					break;
				}
			}
			if (board.isFull()) {
				gameState = GameState.Draw;
				board.print();
				printGameState();
				break;
			}
			nextPlayer();
		}
	}
}