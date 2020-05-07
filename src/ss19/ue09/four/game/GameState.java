package ss19.ue09.four.game;

public enum GameState {

	REDsTurn, BLUEsTurn, REDWon, BLUEWon, Draw;

	@Override
	public String toString() {
		switch (this) {

		case REDsTurn:
			return "It's RED's turn!";

		case BLUEsTurn:
			return "It's BLUE's turn!";

		case REDWon:
			return "Player RED has won!";

		case BLUEWon:
			return "Player BLUE has won!";

		case Draw:
			return "it's a Draw!";
		}
		return "";
	}
}