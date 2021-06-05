package prswe2.ss21.ue08.inventory.model;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Represents an item in an {@link InventoryModel inventory system}. Each item provides a name, a more detailed description and how many instances of this item are currently in stock.
 */
public interface InventoryItem extends Remote {
	
    /**
     * Provides a short description / name for this item.
     *
     * @return the item's name
     */
    String getName() throws RemoteException;

    /**
     * Provides details about this item.
     *
     * @return a prose-text description of this item
     */
    String getDescription() throws RemoteException;

    /**
     * Gets the number of instances of this product that are currently available.
     *
     * @return how much of this item is in stock
     */
    int getQuantity() throws RemoteException;
}
