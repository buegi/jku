package ue02.exceptions;

import ue02.Cargo;
import ue02.transporters.Transporter;

public class InvalidCargoException extends CargoException {

    public InvalidCargoException(String msg, Transporter transporter, Cargo cargo) {
        super(msg, transporter, cargo);
    }
}