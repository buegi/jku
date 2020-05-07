package swe2.ss19.ue10.memecreator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemeModel {

	private final List<Meme> memes = new ArrayList<>();
	// TODO: 4. MVC: list of listeners with methods to add and remove listeners
	private final List<MemeListener> memeListeners = new ArrayList<>();

	public void addMeme(Meme m) {
		memes.add(m);
		fireMemeAdded(m);
		// TODO: 4. MVC: notify listeners of added meme
	}

	public List<Meme> getMemes() {
		return Collections.unmodifiableList(memes);
	}

	public void memeAdded(Meme m) {
		memes.add(m);
		fireMemeAdded(m);
	}

	public void addMemeListener(MemeListener l) {
		memeListeners.add(l);
	}

	public void remMemeListener(MemeListener l) {
		memeListeners.remove(l);
	}

	public void fireMemeAdded(Meme m) {
		MemeEvent me = new MemeEvent(this, m);
		for (MemeListener l : memeListeners) {
			l.memeChanged(me);
		}
	}
}