package prswe2.ss21.ue08.inventory.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import prswe2.ss21.ue08.inventory.gui.InventoryGUI;
import prswe2.ss21.ue08.inventory.impl.InventoryItemImpl;
import prswe2.ss21.ue08.inventory.model.InventoryItem;
import prswe2.ss21.ue08.inventory.model.InventoryModel;

public class InventoryClient {
	
	private static InventoryModel<InventoryItemImpl> inventoryModel;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.getRegistry("127.0.0.1", Registry.REGISTRY_PORT);
			inventoryModel = (InventoryModel<InventoryItemImpl>) registry.lookup("Inventory");
			
			// Insert some fruits to the model
	        insertDemoData(inventoryModel);
	        
	        // Start GUI after inserting the fruits
	        InventoryGUI.startGui(inventoryModel);
		} catch (RemoteException e) {
			System.out.println("Trouble at client (RemoteException): No connection to server!");
			System.out.println(e);
		} catch (NotBoundException e) {
			System.out.println("Trouble at client (NotBoundException): " + e);
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			try {
				// Start GUI despite the fruits already exist
				InventoryGUI.startGui(inventoryModel);
			} catch (RemoteException error) {
				System.out.println("Trouble at client (RemoteException): No connection to server!");
				System.out.println(error);
			}
		}
	}

    private static void insertDemoData(InventoryModel<? extends InventoryItem> model) throws IllegalArgumentException, RemoteException {
        model.createItem("Apples");
        model.createItem("Oranges");
        model.createItem("Bananas");
        model.createItem("Cherries");
    }

}
