package ue02.transporters;

import ue02.Cargo;
import ue02.Location;
import ue02.exceptions.TransportException;
import ue02.exceptions.UnreachableLocationException;

public abstract class OverlandTransporter extends Transporter {

    public OverlandTransporter(String description, int transportCosts, int maxWeight, Location actualLocation) {
        super(description, transportCosts, maxWeight, actualLocation);
    }

    @Override
    public double goTo(Location destination) throws TransportException {
        if (!this.getActualLocation().reachableOverland(destination)) {
            throw new UnreachableLocationException("Unreachable location: " + destination, this, destination);
        } else {
            return super.goTo(destination);
        }
    }
}