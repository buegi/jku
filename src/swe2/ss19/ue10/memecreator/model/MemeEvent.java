package swe2.ss19.ue10.memecreator.model;

import java.util.EventObject;

@SuppressWarnings("serial")
public class MemeEvent extends EventObject {

	private final Meme m;

	public MemeEvent(Object s, Meme m) {
		super(s);
		this.m = m;
	}

	public Meme getMeme() {
		return m;
	}
}