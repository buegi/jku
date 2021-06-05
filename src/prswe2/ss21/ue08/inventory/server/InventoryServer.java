package prswe2.ss21.ue08.inventory.server;

import java.rmi.AccessException;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import prswe2.ss21.ue08.inventory.impl.InventoryItemImpl;
import prswe2.ss21.ue08.inventory.impl.InventoryModelImpl;
import prswe2.ss21.ue08.inventory.model.InventoryModel;

public class InventoryServer {
	
	private InventoryModel<InventoryItemImpl> inventory;
	private Registry registry;
	
	private InventoryServer() {
		try {
			inventory = new InventoryModelImpl();
			registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			registry.bind("Inventory", inventory);
		} catch (Exception e) {
			System.out.println("Trouble: " + e);
		}
	}
	
	private void start() {
		Thread terminateThread = new Thread(() -> {
			System.out.println("Server has started.");
			System.out.println("Press ENTER to Terminate Server!");
			Scanner sc = new Scanner(System.in);
			boolean terminate = false;
			while(!terminate) {
				sc.nextLine();
				terminate = true;
				System.out.println("Server has terminated.");
			}
			
			try {
				registry.unbind("Inventory");
				UnicastRemoteObject.unexportObject(inventory, true);
				UnicastRemoteObject.unexportObject(registry, true);
			} catch (NoSuchObjectException e) {
				e.printStackTrace();
			} catch (AccessException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (NotBoundException e) {
				e.printStackTrace();
			}
			
			sc.close();
			System.exit(0);
		});
		
		terminateThread.start();
	}
	
	public static void main(String[] args) {
		InventoryServer server = new InventoryServer();
		server.start();
	}

}
