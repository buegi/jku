package swe2.ss19.ue10.demo.patterns.command;

import java.util.ArrayDeque;
import java.util.Deque;

public class CmdHandler {

	private final Deque<Command> undoStack;
	private final Deque<Command> redoStack;
	
	public CmdHandler() {
		super();
		this.undoStack = new ArrayDeque<>();
		this.redoStack = new ArrayDeque<>();
	}

	public void doCommand(Command cmd) {
		cmd.doCmd();
		undoStack.addFirst(cmd);
		redoStack.clear();
	}

	public void undoCommand() {
		if (undoStack.isEmpty()) {
			return;
		}
		Command cmd = undoStack.getFirst();
		cmd.undoCmd();
		undoStack.removeFirst();
		redoStack.addFirst(cmd);
	}

	public void redoCommand() {
		if (redoStack.isEmpty()) {
			return;
		}
		Command cmd = redoStack.removeFirst();
		cmd.doCmd();
		undoStack.addFirst(cmd);
	}
	
}
