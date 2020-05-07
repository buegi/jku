package ss20.ue02.transport;

import ss20.ue02.exceptions.TransportException;
import ss20.ue02.exceptions.UnreachableLocationException;

public abstract class LandTransporter extends Transporter {

    public LandTransporter(String description, int transportCosts, int maxWeight, Location actualLocation) {
        super(description, transportCosts, maxWeight, actualLocation);
    }

    @Override
    public double goTo(Location destination) throws TransportException {
        if (!this.getActualLocation().reachableOverland(destination)) {
            throw new UnreachableLocationException(this, destination);
        } else {
            return super.goTo(destination);
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}