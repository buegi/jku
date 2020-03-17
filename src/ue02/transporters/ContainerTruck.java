package ue02.transporters;

import ue02.Cargo;
import ue02.CargoType;
import ue02.Location;
import ue02.exceptions.CargoException;
import ue02.exceptions.InvalidCargoException;

public class ContainerTruck extends OverlandTransporter {

    public ContainerTruck(String description, int transportCosts, int maxWeight, Location actualLocation) {
        super(description, transportCosts, maxWeight, actualLocation);
    }

    @Override
    public void load(Cargo cargo) throws CargoException {
        if (cargo.getType() != CargoType.SOLID) {
            throw new InvalidCargoException("Invalid CargoType: " + CargoType.SOLID, this, cargo);
        } else {
            super.load(cargo);
        }
    }
}