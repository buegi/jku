package swe2.ss19.ue10.demo.patterns.command;

public class TurnOnCommand implements Command {
	  
	private Light light;
	   
	   public TurnOnCommand(Light light) {
	      this.light = light;
	   }
	 
	   @Override
	   public void doCmd(){
	      light.turnOn();
	   }
	   
	   @Override
	   public void undoCmd(){
		   light.turnOff();
	   }
}
