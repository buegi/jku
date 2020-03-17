package ue02.exceptions;

import ue02.transporters.Transporter;

public class TransportException extends Exception {

    private final Transporter transporter;

    public TransportException(String msg, Transporter transporter) {
        super(msg);
        this.transporter = transporter;
    }

    public Transporter getTransporter() {
        return this.transporter;
    }
}