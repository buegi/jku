package prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.impl;

import prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.gui.VaccinationStationGUI;
import prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.model.VaccinationStationModel;
import prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.model.Vaccine;
import prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.server.VaccinationStationServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.server.Constants.*;

public class Main {

    public static void main(String[] args) {
        // server can be manually started by main method.
        // For ease of use and testing this class/main starts both, server and client
        VaccinationStationServer server = new VaccinationStationServer();
        server.start();

        try {
            Registry registry = LocateRegistry.getRegistry(SERVER_HOST, SERVER_PORT);
            VaccinationStationModel model = (VaccinationStationModel) registry.lookup(VACCINE_EXPORT_NAME);
            insertExampleData(model);
            System.out.println("Remote model created, test data inserted");
            VaccinationStationGUI.startGui(model);
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Couldn't register client");
        }
    }

    private static void insertExampleData(VaccinationStationModel model) {
        addVaccine(model, "Comirnaty", "Producer: BioNTech/Pfizer\nAge required: 16\nDoses required: 2", 100);
        addVaccine(model, "COVID-19 Vaccine Moderna", "Producer: Moderna\nAge required: 18\nDoses required: 2", 50);
        addVaccine(model, "Vaxzevria", "Producer: Astra Zeneca\nAge required: 18\nDoses required: 2", 5);
        addVaccine(model, "COVID-19 Vaccine Janssen", "Producer: Janssen\nAge required: 18\nDoses required: 1", 10);
    }

    private static void addVaccine(VaccinationStationModel model, String name, String description, int quantity) {
        try {
            model.createVaccine(name);
            final Vaccine vaccine = model.getVaccine(name);
            model.setDescription(vaccine, description);
            model.increaseQuantity(vaccine, quantity);
        } catch (RemoteException e) {
            System.out.println("Couldn't insert test data!");
        }
    }
}