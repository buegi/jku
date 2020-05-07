package ss20.ue02.exceptions;

import ss20.ue02.transport.Location;
import ss20.ue02.transport.Transporter;

public class UnreachableLocationException extends TransportException {

    private final Location destination;

    public UnreachableLocationException(Transporter transporter, Location destination) {
        super(transporter);
        this.destination = destination;
    }

    public Location getDestination() {
        return this.destination;
    }

    public String toString() {
        return super.toString() + " CANNOT REACH " + this.destination.toString() + " OVER LAND!";
    }
}