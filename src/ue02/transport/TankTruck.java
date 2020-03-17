package ue02.transport;

import ue02.exceptions.CargoException;
import ue02.exceptions.InvalidCargoException;

public class TankTruck extends LandTransporter {

    public TankTruck(String description, int transportCosts, int maxWeight, Location actualLocation) {
        super(description, transportCosts, maxWeight, actualLocation);
    }

    @Override
    public void load(Cargo cargo) throws CargoException {
        if (cargo.getType() != CargoType.LIQUID) {
            throw new InvalidCargoException(this, cargo);
        } else {
            super.load(cargo);
        }
    }

    @Override
    public String toString() {
        return "TankTruck:" + super.toString();
    }
}