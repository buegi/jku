package ue02.exceptions;

import ue02.transport.Transporter;

public abstract class TransportException extends Exception {

    protected final Transporter transporter;

    public TransportException(Transporter transporter) {
        super();
        this.transporter = transporter;
    }

    public Transporter getTransporter() {
        return this.transporter;
    }

    public String toString() {
        return "EXCEPTION: " + this.transporter.toString();
    }
}