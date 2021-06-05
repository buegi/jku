package prswe2.ss21.ue08.inventory.impl;

import java.rmi.RemoteException;

import prswe2.ss21.ue08.inventory.gui.InventoryGUI;
import prswe2.ss21.ue08.inventory.model.InventoryItem;
import prswe2.ss21.ue08.inventory.model.InventoryModel;

public class Main {
	// old main without remote support
    public static void main(String[] args) {
		try {
			InventoryModelImpl model = new InventoryModelImpl();
			insertDemoData(model);
			InventoryGUI.startGui(model);
		} catch (RemoteException e) {
			System.out.println("Trouble: " + e);
		}
    }

    private static void insertDemoData(InventoryModel<? extends InventoryItem> model) throws IllegalArgumentException, RemoteException {
        model.createItem("Apples");
        model.createItem("Oranges");
        model.createItem("Bananas");
        model.createItem("Cherries");
    }
    
}
