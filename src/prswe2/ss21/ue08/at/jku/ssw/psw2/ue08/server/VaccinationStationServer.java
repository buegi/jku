package prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.server;

import prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.impl.VaccinationStationModelImpl;
import prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.model.VaccinationStationModel;

import static prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.server.Constants.*;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class VaccinationStationServer {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(SERVER_PORT);
            VaccinationStationModel model = new VaccinationStationModelImpl();
            registry.bind(VACCINE_EXPORT_NAME, model);
            System.out.println("Server started");
        } catch (RemoteException | AlreadyBoundException re) {
            System.out.println("Couldn't start server");
        }
    }

    public void start() {
        try {
            Registry registry = LocateRegistry.createRegistry(SERVER_PORT);
            VaccinationStationModel model = new VaccinationStationModelImpl();
            registry.bind(VACCINE_EXPORT_NAME, model);
            System.out.println("Server started");
        } catch (RemoteException | AlreadyBoundException re) {
            System.out.println("Couldn't start server");
        }
    }
}