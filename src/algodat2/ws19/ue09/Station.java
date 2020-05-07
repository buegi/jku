package algodat2.ws19.ue09;

public class Station implements MyVertex  {
	protected String name;
	public Station(String stationName) { name = stationName; }
	public String toString() { return name; }
}
