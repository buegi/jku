package ss19.ue09.four.game;

import java.util.EventObject;

@SuppressWarnings("serial")
public class GameStateEvent extends EventObject {

	private final GameState gameState;

	public GameStateEvent(Object source, GameState gameState) {
		super(source);
		this.gameState = gameState;
	}

	public GameState getGameState() {
		return gameState;
	}
}