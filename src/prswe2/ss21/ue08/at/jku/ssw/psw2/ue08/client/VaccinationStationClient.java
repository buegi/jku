package prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.client;

import prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.gui.VaccinationStationGUI;
import prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.impl.VaccineImpl;
import prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.model.VaccinationStationModel;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.server.Constants.*;

public class VaccinationStationClient {

    public static VaccinationStationModel<VaccineImpl> model;

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(SERVER_HOST, SERVER_PORT);
            System.out.println("Server allocated");
            model = (VaccinationStationModel<VaccineImpl>) registry.lookup(VACCINE_EXPORT_NAME);
            System.out.println("Registry allocated");
            insertExampleData(model);
            System.out.println("Example data inserted");
            VaccinationStationGUI.startGui(model);
            System.out.println("GUI started");
        } catch (RemoteException | NotBoundException re) {
            System.out.println("Couldn't initialize client");
        }
    }

    private static void insertExampleData(VaccinationStationModel<VaccineImpl> model) throws RemoteException {
        addVaccine(model, "Comirnaty", "Producer: BioNTech/Pfizer\nAge required: 16\nDoses required: 2", 100);
        addVaccine(model, "COVID-19 Vaccine Moderna", "Producer: Moderna\nAge required: 18\nDoses required: 2", 50);
        addVaccine(model, "Vaxzevria", "Producer: Astra Zeneca\nAge required: 18\nDoses required: 2", 5);
        addVaccine(model, "COVID-19 Vaccine Janssen", "Producer: Janssen\nAge required: 18\nDoses required: 1", 10);
    }

    private static void addVaccine(VaccinationStationModel<VaccineImpl> model, String name, String description, int quantity) throws RemoteException {
        model.createVaccine(name);
        VaccineImpl vaccine = model.getVaccine(name);
//        model.setDescription(vaccine, description);
//        model.increaseQuantity(vaccine, quantity);
    }
}
