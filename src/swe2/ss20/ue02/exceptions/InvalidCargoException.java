package swe2.ss20.ue02.exceptions;

import swe2.ss20.ue02.transport.Cargo;
import swe2.ss20.ue02.transport.Transporter;

public class InvalidCargoException extends CargoException {

    public InvalidCargoException(Transporter transporter, Cargo cargo) {
        super(transporter, cargo);
    }

    @Override
    public String toString() {
        return super.toString() + " BECAUSE IT CANNOT TRANSPORT " + super.cargo.getType() + "s!";
    }
}