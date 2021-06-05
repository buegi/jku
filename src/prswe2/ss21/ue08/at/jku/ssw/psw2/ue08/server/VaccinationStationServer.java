package prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.server;

import prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.impl.VaccinationStationModelImpl;
import prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.impl.VaccineImpl;
import prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.model.VaccinationStationModel;

import static prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.server.Constants.*;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class VaccinationStationServer {

    private VaccinationStationModel<VaccineImpl> model;
    private Registry registry;

    public VaccinationStationServer() {
        try {
            model = new VaccinationStationModelImpl();
            this.registry = LocateRegistry.createRegistry(SERVER_PORT);
            registry.bind(VACCINE_EXPORT_NAME, model);
            System.out.println("Server bound");
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        VaccinationStationServer server = new VaccinationStationServer();
        server.start();
    }

    public void start() {
        Thread terminateThread = new Thread(() -> {
            System.out.println("Server started");
            System.out.println("[ENTER] terminates server");
            Scanner sc = new Scanner(System.in);
            boolean terminate = false;
            while (!terminate) {
                sc.nextLine();
                terminate = true;
                System.out.println("Server terminated");
            }
            try {
                registry.unbind(VACCINE_EXPORT_NAME);
                UnicastRemoteObject.unexportObject(model, true);
                UnicastRemoteObject.unexportObject(registry, true);
            } catch (RemoteException | NotBoundException e) {
                e.printStackTrace();
            }
            sc.close();
            System.exit(0);
        });
        terminateThread.start();
    }
}