package ue02.transporters;

import ue02.Cargo;
import ue02.Location;
import ue02.exceptions.CargoException;
import ue02.exceptions.OverloadedException;
import ue02.exceptions.TransportException;

public abstract class Transporter extends Object {

    private String description;
    private int maxWeight;
    private int transportCosts;
    private Location actualLocation;
    private Cargo actualCargo;

    public Transporter(String description, int transportCosts, int maxWeight, Location actualLocation) {
        this.description = description;
        this.transportCosts = transportCosts;
        this.maxWeight = maxWeight;
        this.actualLocation = actualLocation;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getMaxWeight() {
        return this.maxWeight;
    }

    public void setActualCargo(Cargo cargo) {
        this.actualCargo = cargo;
    }

    public Cargo getActualCargo() {
        return this.actualCargo;
    }

    public void setTransportCosts(int transportCosts) {
        this.transportCosts = transportCosts;
    }

    public int getTransportCosts() {
        return this.transportCosts;
    }

    public void setActualLocation(Location destination) {
        this.actualLocation = destination;
    }

    public Location getActualLocation() {
        return this.actualLocation;
    }

    public double goTo(Location destination) throws TransportException {
        return actualLocation.getDistance(destination) * transportCosts;
    }

    public void load(Cargo cargo) throws CargoException {
        if (actualCargo != null || cargo.getWeight() > maxWeight) {
            throw new OverloadedException("Transporter overloaded!", this, cargo);
        } else {
            actualCargo = cargo;
        }
    }

    public Cargo unload() {
        Cargo actual = actualCargo;
        actualCargo = null;
        return actual;
    }

    @Override
    public String toString() {
        return "Description:" + this.getDescription() + " Costs:" + this.getTransportCosts() + " MaxWeight:" + this.getMaxWeight() + " Actual Location:"
                + this.getActualLocation() + " Actual Cargo:" + this.getActualCargo();
    }
}