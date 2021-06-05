package prswe2.ss21.ue08.inventory.model;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class describes a data model for a simple inventory system. It manages different kinds
 * of {@link ItemClass items} and the quantity available of each item. Changes to this model can be
 * observed using appropriate {@link InventoryChangeListener change listeners}.
 *
 * @param <ItemClass> type of items managed by this model
 */
public interface InventoryModel<ItemClass extends InventoryItem> extends Remote {
	
    /**
     * Returns a read-only list of all items in the model.
     *
     * @return the items
     * @throws RemoteException 
     */
    List<ItemClass> getItems() throws RemoteException;

    /**
     * Adds a new kind of item to the inventory system.
     *
     * @param name the name of the item to be added
     * @throws IllegalArgumentException if the given name is null or the model already contains an item with this name
     */
    void createItem(String name) throws IllegalArgumentException, RemoteException;

    /**
     * Returns the item with the given name.
     *
     * @param name the name of the item to retrieve
     *
     * @return the item
     *
     * @throws IllegalArgumentException if the name is null
     * @throws NoSuchElementException if there is no item with that name in the model
     * @throws RemoteException 
     */
    ItemClass getItem(String name) throws IllegalArgumentException, NoSuchElementException, RemoteException;

    /**
     * Changes the {@link InventoryItem#getDescription() description} of this item.
     *
     * @param editDescriptionItemName        the item to modify
     * @param description the new description
     * @throws IllegalArgumentException if the new description is null
     * @throws RemoteException 
     */
    void setDescription(String editDescriptionItemName, String description) throws IllegalArgumentException, RemoteException;

    /**
     * Increases the number of stocked instances of this item.
     *
     * @param itemToIncreaseQuantity the item whose stock to increase
     * @param increase the number of added instances
     * @throws IllegalArgumentException if the increase is negative or the increase would exceed the capacity of the store
     * @throws RemoteException 
     */
    void increaseQuantity(String itemToIncreaseQuantity, int increase) throws IllegalArgumentException, RemoteException;

    /**
     * Decreases the number of stocked instances of this item.
     *
     * @param itemToDecreaseQuantity the item whose stock to decrease
     * @param decrease the number of removed instances
     * @throws IllegalArgumentException if the decrease is negative or higher than the number of currently stocked items
     * @throws RemoteException 
     */
    void decreaseQuantity(String itemToDecreaseQuantity, int decrease) throws IllegalArgumentException, RemoteException;

    /**
     * Deletes the given item from the inventory system.
     *
     * @param deleteItemName the item to be removed
     * @throws IllegalArgumentException if the given item is null
     * @throws RemoteException 
     */
    void deleteItem(String deleteItemName) throws IllegalArgumentException, RemoteException;

    /**
     * Adds a listener that is invoked when items are added to or removed from this model.
     *
     * @param listener the listener to add
     * @throws RemoteException 
     */
    void addListener(InventoryChangeListener<ItemClass> listener) throws RemoteException;

    /**
     * Removes the given listener from this item, if it is currently registered.
     *
     * @param listener the listener to remove
     * @throws RemoteException 
     */
    void removeListener(InventoryChangeListener<ItemClass> listener) throws RemoteException;
}
