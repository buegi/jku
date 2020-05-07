package swe2.ss19.ue09.four.game;

import java.util.EventObject;

@SuppressWarnings("serial")
public class StoneEvent extends EventObject {

	private final Stone[] stones;

	public StoneEvent(Object source, Stone s) {
		super(source);
		this.stones = new Stone[] { s };
	}

	public StoneEvent(Object source, Stone[] stones) {
		super(source);
		this.stones = stones;
	}

	public Stone[] getStones() {
		return stones;
	}
}