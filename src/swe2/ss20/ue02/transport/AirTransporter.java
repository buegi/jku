package swe2.ss20.ue02.transport;

public abstract class AirTransporter extends Transporter {

    protected double startingCosts = 50.0d;
    protected double landingCosts = 50.0d;

    public AirTransporter(String description, int transportCosts, int maxWeight, Location actualLocation) {
        super(description, transportCosts, maxWeight, actualLocation);
    }

    public void setStartingCosts(double newCosts) {
        this.startingCosts = newCosts;
    }

    public double getStartingCosts() {
        return this.startingCosts;
    }

    public double getLandingCosts() {
        return this.landingCosts;
    }

    public void setLandingCosts(double newCosts) {
        this.landingCosts = newCosts;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}