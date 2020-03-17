package ue02.exceptions;

import ue02.Location;
import ue02.transporters.Transporter;

public class UnreachableLocationException extends TransportException {

    private final Location destination;

    public UnreachableLocationException(String msg, Transporter transporter, Location destination) {
        super(msg, transporter);
        this.destination = destination;
    }

    public Location getDestination() {
        return this.destination;
    }
}