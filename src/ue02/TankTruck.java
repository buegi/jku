package ue02;

import ue02.exceptions.OverloadedException;

public class TankTruck extends Transporter{

    TankTruck(String description, int maxWeight, int transportCosts, Location actualLocation, Cargo actualCargo) {
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