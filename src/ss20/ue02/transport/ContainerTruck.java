package ss20.ue02.transport;

import ss20.ue02.exceptions.CargoException;
import ss20.ue02.exceptions.InvalidCargoException;

public class ContainerTruck extends LandTransporter {

    public ContainerTruck(String description, int transportCosts, int maxWeight, Location actualLocation) {
        super(description, transportCosts, maxWeight, actualLocation);
    }

    @Override
    public void load(Cargo cargo) throws CargoException {
        if (cargo.getType() != CargoType.SOLID) {
            throw new InvalidCargoException(this, cargo);
        } else {
            super.load(cargo);
        }
    }

    @Override
    public String toString() {
        return "ContainerTruck:" + super.toString();
    }
}