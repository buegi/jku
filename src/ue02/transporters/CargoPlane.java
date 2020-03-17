package ue02.transporters;

import ue02.Cargo;
import ue02.CargoType;
import ue02.Location;
import ue02.exceptions.CargoException;
import ue02.exceptions.InvalidCargoException;
import ue02.exceptions.TransportException;

public class CargoPlane extends Transporter {

    static final double STARTINGCOSTS = 50.0d;
    static final double LANDINGCOSTS = 50.0d;

    public CargoPlane(String description, int transportCosts, int maxWeight, Location actualLocation) {
        super(description, transportCosts, maxWeight, actualLocation);
    }

    public double goTo(Location destination) throws TransportException {
        return super.goTo(destination) + STARTINGCOSTS + LANDINGCOSTS;
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