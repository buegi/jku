package ue02;

public abstract class Transporter {

    private String description;
    private int maxWeight;
    private int transportCosts;
    private Location actualLocation;
    private Cargo actualCargo;

    Transporter(String description, int maxWeight, int transportCosts, Location actualLocation, Cargo actualCargo) {
        this.description = description;
        this.maxWeight = maxWeight;
        this.transportCosts = transportCosts;
        this.actualLocation = actualLocation;
        this.actualCargo = actualCargo;
    }

    void setDescription(String description) {
        this.description = description;
    }

    String getDescription() {
        return this.description;
    }

    void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    int getMaxWeight() {
        return this.maxWeight;
    }

    void setTransportCosts(int transportCosts) {
        this.transportCosts = transportCosts;
    }

    int getTransportCosts() {
        return this.transportCosts;
    }

    void setActualLocation(Location destination) {
        this.actualLocation = destination;
    }

    Location getActualLocation() {
        return this.actualLocation;
    }

    void goTo(Location destination) {
        this.actualLocation = destination;
    }
}
