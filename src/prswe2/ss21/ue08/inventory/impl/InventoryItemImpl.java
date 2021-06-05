package prswe2.ss21.ue08.inventory.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import prswe2.ss21.ue08.inventory.model.InventoryItem;

public final class InventoryItemImpl extends UnicastRemoteObject implements InventoryItem {

	private static final long serialVersionUID = -8575938206383986452L;

	private final String name;

    private String description;
    private int quantity;

    protected InventoryItemImpl(String name) throws RemoteException {
    	super();
        this.name = name;
        this.description = "";
        this.quantity = 0;
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public String getDescription() throws RemoteException {
        return description;
    }

    public void setDescription(String description) throws RemoteException {
        this.description = description;
    }

    @Override
    public int getQuantity() throws RemoteException {
        return quantity;
    }

    public void setQuantity(int quantity) throws RemoteException {
        this.quantity = quantity;
    }

    /*
     * This method is called to get the string displayed in the gui's list view of available items. We override it to display the item's name in the list.
     */
    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
