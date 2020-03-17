package ue02.exceptions;

import ue02.transport.Cargo;
import ue02.transport.Transporter;

public abstract class CargoException extends TransportException {

    protected final Cargo cargo;

    public CargoException(Transporter transporter, Cargo cargo) {
        super(transporter);
        this.cargo = cargo;
    }

    public Cargo getCargo() {
        return this.cargo;
    }

    @Override
    public String toString() {
        return super.toString() + " FAILED TO LOAD " + this.cargo.toString();
    }
}