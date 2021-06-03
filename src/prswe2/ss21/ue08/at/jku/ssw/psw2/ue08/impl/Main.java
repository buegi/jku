package prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.impl;

import prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.gui.VaccinationStationGUI;

public class Main {
    public static void main(String[] args) {
        final VaccinationStationModelImpl model = new VaccinationStationModelImpl();
        insertExampleData(model);
        VaccinationStationGUI.startGui(model);
    }

    private static void insertExampleData(VaccinationStationModelImpl model) {
        addVaccine(model, "Comirnaty", "Producer: BioNTech/Pfizer\nAge required: 16\nDoses required: 2", 100);
        addVaccine(model, "COVID-19 Vaccine Moderna", "Producer: Moderna\nAge required: 18\nDoses required: 2", 50);
        addVaccine(model, "Vaxzevria", "Producer: Astra Zeneca\nAge required: 18\nDoses required: 2", 5);
        addVaccine(model, "COVID-19 Vaccine Janssen", "Producer: Janssen\nAge required: 18\nDoses required: 1", 10);
    }

    private static void addVaccine(VaccinationStationModelImpl model, String name, String description, int quantity) {
        model.createVaccine(name);
        final VaccineImpl vaccine = model.getVaccine(name);
        model.setDescription(vaccine, description);
        model.increaseQuantity(vaccine, quantity);
    }
}
