package ue02.exceptions;

import ue02.Cargo;
import ue02.transporters.Transporter;

public class OverloadedException extends CargoException {

    public OverloadedException(String msg, Transporter transporter, Cargo cargo) {
        super(msg, transporter, cargo);
    }
}