package prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.impl;

import prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.model.InventoryChangeListener;
import prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.model.VaccinationStationModel;
import prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.model.Vaccine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public final class VaccinationStationModelImpl implements VaccinationStationModel<VaccineImpl> {

    private final List<VaccineImpl> vaccines;
    private final List<InventoryChangeListener<VaccineImpl>> listeners;

    public VaccinationStationModelImpl() {
        vaccines = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    private void fireVaccineAdded(VaccineImpl addedVaccine) {
        listeners.forEach(l -> l.onVaccineAdded(addedVaccine));
    }

    private void fireVaccineChanged(VaccineImpl changedVaccine) {
        listeners.forEach(l -> l.onVaccineChanged(changedVaccine));
    }

    private void fireVaccineRemoved(VaccineImpl removedVaccine) {
        listeners.forEach(l -> l.onVaccineRemoved(removedVaccine));
    }

    @Override
    public List<VaccineImpl> getVaccines() {
        return Collections.unmodifiableList(new ArrayList<>(vaccines));
    }

    @Override
    public VaccineImpl getVaccine(String name) throws IllegalArgumentException, NoSuchElementException {
        if (name == null) {
            throw new IllegalArgumentException("Invalid name");
        }
        return vaccines.stream().filter(i -> name.equals(i.getName())).findAny().orElseThrow(() -> new NoSuchElementException("No vaccine with name " + name));
    }

    @Override
    public void createVaccine(String name) throws IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Invalid name");
        }

        for (Vaccine existingVaccine : vaccines) {
            if (name.equals(existingVaccine.getName())) {
                throw new IllegalArgumentException("Duplicate vaccine: " + name);
            }
        }

        final VaccineImpl vaccine = new VaccineImpl(name);
        vaccines.add(vaccine);
        fireVaccineAdded(vaccine);
    }

    @Override
    public void setDescription(VaccineImpl vaccine, String description) throws IllegalArgumentException {
        if (vaccine == null || description == null) {
            throw new IllegalArgumentException("Invalid change");
        }

        vaccine.setDescription(description);
        fireVaccineChanged(vaccine);
    }

    @Override
    public void increaseQuantity(VaccineImpl vaccine, int increase) throws IllegalArgumentException {
        if (vaccine == null) {
            throw new IllegalArgumentException("Invalid vaccine to change");
        } else if (increase < 0) {
            throw new IllegalArgumentException("Invalid quantity increase: " + increase);
        } else if (increase == 0) {
            return;
        }

        if (!vaccines.contains(vaccine)) {
            throw new IllegalArgumentException("Invalid vaccine to change");
        } else if (Integer.MAX_VALUE - vaccine.getQuantity() < increase) {
            throw new IllegalArgumentException("Maximum quantity is restricted to " + Integer.MAX_VALUE);
        } else {
            vaccine.setQuantity(vaccine.getQuantity() + increase);
            fireVaccineChanged(vaccine);
        }
    }

    @Override
    public void decreaseQuantity(VaccineImpl vaccine, int decrease) throws IllegalArgumentException {
        if (decrease < 0) {
            throw new IllegalArgumentException("Invalid quantity decrease: " + decrease);
        } else if (decrease == 0) {
            return;
        }

        if (vaccine.getQuantity() < decrease) {
            throw new IllegalArgumentException("Minimum quantity is 0");
        } else {
            vaccine.setQuantity(vaccine.getQuantity() - decrease);
            fireVaccineChanged(vaccine);
        }
    }

    @Override
    public void removeVaccine(VaccineImpl vaccine) throws IllegalArgumentException {
        final boolean removed = vaccines.remove(vaccine);
        if (removed) {
            fireVaccineRemoved(vaccine);
        }
    }

    @Override
    public void addListener(InventoryChangeListener<VaccineImpl> listener) {
        if (listener != null) {
            listeners.add(listener);
        }
    }

    @Override
    public void removeListener(InventoryChangeListener<VaccineImpl> listener) {
        if (listener != null) {
            listeners.remove(listener);
        }
    }
}