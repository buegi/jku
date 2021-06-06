package prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.server;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.impl.VaccinationStationModelImpl;
import prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.impl.VaccineImpl;
import prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.model.VaccinationStationModel;

import static prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.constants.Constants.*;

public class VaccinationStationServer {

    private VaccinationStationModel<VaccineImpl> vaccinationModel;
    private Registry registry;

    private VaccinationStationServer() {
        try {
            vaccinationModel = new VaccinationStationModelImpl();
            registry = LocateRegistry.createRegistry(SERVER_PORT);
            registry.bind(REGISTRY_NAME, vaccinationModel);
        } catch (Exception e) {
            System.out.println("Error initializing model/registry");
        }
    }

    public static void main(String[] args) {
        VaccinationStationServer server = new VaccinationStationServer();
        server.start();
    }

    private void start() {
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
                registry.unbind(REGISTRY_NAME);
                UnicastRemoteObject.unexportObject(vaccinationModel, true);
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