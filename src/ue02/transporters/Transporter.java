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

    double goTo(Location destination) throws TransportException {
        return actualLocation.getDistance(destination) * transportCosts;
    }

    void load(Cargo cargo) throws CargoException {
        if (actualCargo != null || cargo.getWeight() > maxWeight) {
            throw new OverloadedException(this, cargo);
        } else {
            actualCargo = cargo;
        }
    }

    Cargo unload() {
        Cargo actual = actualCargo;
        actualCargo = null;
        return actual;
    }
}