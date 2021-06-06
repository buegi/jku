package prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.impl;

import prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.model.InventoryChangeListener;
import prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.model.VaccinationStationModel;
import prswe2.ss21.ue08.at.jku.ssw.psw2.ue08.model.Vaccine;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class VaccinationStationModelImpl extends UnicastRemoteObject implements VaccinationStationModel<VaccineImpl> {

    private final ExecutorService executor;
    private static final long serialVersionUID = 6016326677308718480L;
    private final List<VaccineImpl> vaccines;
    private final List<InventoryChangeListener<VaccineImpl>> listeners;

    public VaccinationStationModelImpl() throws RemoteException {
        super();
        this.executor = Executors.newCachedThreadPool();
        vaccines = new CopyOnWriteArrayList<>();
        listeners = new CopyOnWriteArrayList<>();
    }

    private void fireVaccineAdded(VaccineImpl addedVaccine) {
        listeners.forEach(l -> {
            executor.submit(() -> {
                try {
                    l.onVaccineAdded(addedVaccine);
                } catch (RemoteException e) {
                    System.out.println("Connection to a client lost by adding an item. Remove listener: " + l);
                    listeners.remove(l);
                }
            }).isDone();
        });
    }

    private void fireVaccineChanged(VaccineImpl changedVaccine) {
        listeners.forEach(l -> {
            executor.submit(() -> {
                try {
                    l.onVaccineChanged(changedVaccine);
                } catch (RemoteException e) {
                    System.out.println("Connection to a client lost by changing an item. Remove listener: " + l);
                    listeners.remove(l);
                }
            });
        });

        System.gc();
        System.runFinalization();
    }

    private void fireVaccineRemoved(VaccineImpl removedVaccine) {
        listeners.forEach(l -> {
            executor.submit(() -> {
                try {
                    l.onVaccineRemoved(removedVaccine);
                } catch (RemoteException e) {
                    System.out.println("Connection to a client lost by removing an item. Remove listener: " + l);
                    listeners.remove(l);
                }
            });
        });
    }

    @Override
    public List<VaccineImpl> getVaccines() throws RemoteException {
        return Collections.unmodifiableList(new ArrayList<>(vaccines));
    }

    @Override
    public VaccineImpl getVaccine(String name) throws IllegalArgumentException, NoSuchElementException, RemoteException {
        if (name == null) {
            throw new IllegalArgumentException("Invalid name");
        }
        return vaccines.stream().filter(i -> {
            try {
                return name.equals(i.getName());
            } catch (RemoteException e) {
                System.out.println("Trouble while filtering the right item: " + e);
            }
            return false;
        }).findAny().orElseThrow(() -> new NoSuchElementException("No vaccine with name " + name));
    }

    @Override
    public void createVaccine(String name) throws IllegalArgumentException, RemoteException {
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
    public void setDescription(VaccineImpl vaccine, String description) throws IllegalArgumentException, RemoteException {
        if (vaccine == null || description == null) {
            throw new IllegalArgumentException("Invalid change");
        }
        vaccine.setDescription(description);
        fireVaccineChanged(vaccine);
    }

    @Override
    public void increaseQuantity(VaccineImpl vaccine, int increase) throws IllegalArgumentException, RemoteException {
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
    public void decreaseQuantity(VaccineImpl vaccine, int decrease) throws IllegalArgumentException, RemoteException {
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
    public void removeVaccine(VaccineImpl vaccine) throws IllegalArgumentException, RemoteException {
        final boolean removed = vaccines.remove(vaccine);
        if (removed) {
            fireVaccineRemoved(vaccine);
        }
    }

    @Override
    public void addListener(InventoryChangeListener<VaccineImpl> listener) throws RemoteException {
        if (listener != null) {
            listeners.add(listener);
        }
    }

    @Override
    public void removeListener(InventoryChangeListener<VaccineImpl> listener) throws RemoteException {
        if (listener != null) {
            listeners.remove(listener);
        }
    }
}