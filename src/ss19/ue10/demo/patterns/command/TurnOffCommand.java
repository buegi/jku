package ss19.ue10.demo.patterns.command;

public class TurnOffCommand implements Command {

	private Light light;
	   
	   public TurnOffCommand(Light light) {
	      this.light = light;
	   }
	 
	   @Override
	   public void doCmd() {
	      light.turnOff();
	   }
	   
	   @Override
	   public void undoCmd() {
		   light.turnOn();
	   }
	
}
