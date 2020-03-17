package ue02;

import ue02.exceptions.OverloadedException;

public class ContainerTruck extends Transporter{

    ContainerTruck(String description, int maxWeight, int transportCosts, Location actualLocation, Cargo actualCargo) {
        super(description, maxWeight, transportCosts, actualLocation, actualCargo);
    }

    @Override
    double goTo(Location destination) {
        return 0;
    }

    @Override
    void load(Cargo cargo) throws OverloadedException {

    }

    @Override
    Cargo unload() {
        return null;
    }
}