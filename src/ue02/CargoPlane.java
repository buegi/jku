package ue02;

import ue02.exceptions.OverloadedException;

public class CargoPlane extends Transporter{

    static final double STARTINGCOSTS = 50.0d;
    static final double LANDINGCOSTS = 50.0d;

    CargoPlane(String description, int maxWeight, int transportCosts, Location actualLocation, Cargo actualCargo) {
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
