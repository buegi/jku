package prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.client;

import java.rmi.NotBoundException;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.gui.VaccinationStationGUI;
import prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.impl.VaccineImpl;
import prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.model.VaccinationStationModel;
import prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.model.Vaccine;

import static prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.constants.Constants.*;

public class VaccinationStationClient {

    private static VaccinationStationModel<VaccineImpl> vaccinationModel;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(SERVER_ADDRESS, SERVER_PORT);
            vaccinationModel = (VaccinationStationModel<VaccineImpl>) registry.lookup(REGISTRY_NAME);
            insertExampleData(vaccinationModel);
            VaccinationStationGUI.startGui(vaccinationModel);
        } catch (RemoteException e) {
            System.out.println("client <-> server connection error");
        } catch (NotBoundException e) {
            System.out.println("client registry error");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            try {
                // start gui, even if example date is already inserted
                VaccinationStationGUI.startGui(vaccinationModel);
            } catch (RemoteException error) {
                System.out.println("client <-> server connection error");
                System.out.println(error);
            }
        }
    }

    private static void insertExampleData(VaccinationStationModel<VaccineImpl> model) {
        addVaccine(model, "Comirnaty", "Producer: BioNTech/Pfizer\nAge required: 16\nDoses required: 2", 100);
        addVaccine(model, "COVID-19 Vaccine Moderna", "Producer: Moderna\nAge required: 18\nDoses required: 2", 50);
        addVaccine(model, "Vaxzevria", "Producer: Astra Zeneca\nAge required: 18\nDoses required: 2", 5);
        addVaccine(model, "COVID-19 Vaccine Janssen", "Producer: Janssen\nAge required: 18\nDoses required: 1", 10);
    }

    private static void addVaccine(VaccinationStationModel<VaccineImpl> model, String name, String description, int quantity) {
        try {
            model.createVaccine(name);
            final Vaccine vaccine = model.getVaccine(name);
            //model.setDescription(vaccine, description);
            // model.increaseQuantity(vaccine, quantity);
        } catch (IllegalArgumentException | RemoteException e) {
            System.out.println("Error inserting example data");
        }
    }
}
