package swe2.ss20.ue11.demo.patterns.command;

public class Client {

    public static void main(String[] args) {

        Light lamp = new Light();

        Command switchUp = new TurnOnCommand(lamp);
        Command switchDown = new TurnOffCommand(lamp);

        CmdHandler handler = new CmdHandler();

        handler.doCommand(switchUp);
        handler.doCommand(switchDown);
        handler.undoCommand();
        handler.undoCommand();
        handler.redoCommand();
        handler.redoCommand();
    }
}