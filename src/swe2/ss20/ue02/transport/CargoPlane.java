package swe2.ss20.ue02.transport;

import swe2.ss20.ue02.exceptions.CargoException;
import swe2.ss20.ue02.exceptions.InvalidCargoException;
import swe2.ss20.ue02.exceptions.TransportException;

public class CargoPlane extends AirTransporter {

    public CargoPlane(String description, int transportCosts, int maxWeight, Location actualLocation) {
        super(description, transportCosts, maxWeight, actualLocation);
    }

    public double goTo(Location destination) throws TransportException {
        return super.goTo(destination) + this.getStartingCosts() + this.getLandingCosts();
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
        return "CargoPlane:" + super.toString() + ", Starting Costs: " + this.getStartingCosts() + ", Landing Costs: " + this.getLandingCosts();
    }
}