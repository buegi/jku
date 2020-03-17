package ue02.exceptions;

import ue02.Cargo;
import ue02.transporters.Transporter;

public class CargoException extends TransportException {

    private final Cargo cargo;

    public CargoException(String msg, Transporter transporter, Cargo cargo) {
        super(msg, transporter);
        this.cargo = cargo;
    }

    public Cargo getCargo() {
        return this.cargo;
    }
}