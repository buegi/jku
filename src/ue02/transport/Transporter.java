package ue02.transport;

import ue02.exceptions.CargoException;
import ue02.exceptions.OverloadedException;
import ue02.exceptions.TransportException;

public abstract class Transporter {

    protected String description;
    protected int maxWeight;
    protected int transportCosts;
    protected Location actualLocation;
    protected Cargo actualCargo;

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
        return this.actualLocation.getDistance(destination) * this.transportCosts;
    }

    public void load(Cargo cargo) throws CargoException {
        if (this.actualCargo != null || cargo.getWeight() > this.maxWeight) {
            throw new OverloadedException(this, cargo);
        } else {
            this.actualCargo = cargo;
        }
    }

    public Cargo unload() {
        Cargo actual = this.actualCargo;
        this.actualCargo = null;
        return actual;
    }

    @Override
    public String toString() {
        return this.getDescription() + ", Costs:" + this.getTransportCosts() + ", Maximum Weight:" + this.getMaxWeight() + ", Actual location:"
                + this.getActualLocation() + ", Actual cargo:" + this.getActualCargo();
    }
}