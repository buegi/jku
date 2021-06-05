package prswe2.ss21.ue08.inventory.impl;

import prswe2.ss21.ue08.inventory.model.InventoryChangeListener;
import prswe2.ss21.ue08.inventory.model.InventoryItem;
import prswe2.ss21.ue08.inventory.model.InventoryModel;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class InventoryModelImpl extends UnicastRemoteObject implements InventoryModel<InventoryItemImpl> {

	private final ExecutorService executor;
	private static final long serialVersionUID = 849550926131522335L;
	private final List<InventoryItemImpl> items;
    private final List<InventoryChangeListener<InventoryItemImpl>> listeners;

    public InventoryModelImpl() throws RemoteException {
		super();
		this.executor = Executors.newCachedThreadPool();
    	items = new CopyOnWriteArrayList<InventoryItemImpl>();
        listeners = new CopyOnWriteArrayList<InventoryChangeListener<InventoryItemImpl>>();
    }

	private void fireItemAdded(InventoryItemImpl addedItem) {
		listeners.forEach(l -> {
			executor.submit(() -> {
				try {
					l.onItemAdded(addedItem);
				} catch (RemoteException e) {
        			System.out.println("Connection to a client lost by adding an item. Remove listener: " + l);
        			listeners.remove(l);
				}
			}).isDone();
		});
	}

    private void fireItemChanged(InventoryItemImpl changedItem) {
        listeners.forEach(l -> {
        	executor.submit(() -> {
        		try {
        			l.onItemChanged(changedItem);
        		} catch (RemoteException e) {
        			System.out.println("Connection to a client lost by changing an item. Remove listener: " + l);
        			listeners.remove(l);
        		}
        	});
		});
        
        System.gc();
        System.runFinalization();
    }

    private void fireItemRemoved(InventoryItemImpl removedItem) {
        listeners.forEach(l -> {
        	executor.submit(() -> {
        		try {
        			l.onItemRemoved(removedItem);
        		} catch (RemoteException e) {
        			System.out.println("Connection to a client lost by removing an item. Remove listener: " + l);
        			listeners.remove(l);
        		}
        	});
		});
    }

    @Override
    public List<InventoryItemImpl> getItems() throws RemoteException {
        return Collections.unmodifiableList(new ArrayList<>(items));
    }

    @Override
    public InventoryItemImpl getItem(String name) throws IllegalArgumentException, NoSuchElementException, RemoteException {
        if (name == null) {
            throw new IllegalArgumentException("Invalid name for getting item!");
        }
        return items.stream().filter(i -> {
			try {
				return name.equals(i.getName());
			} catch (RemoteException e) {
				System.out.println("Trouble while filtering the right item: " + e);
			}
			return false;
		}).findAny().orElseThrow(() -> new NoSuchElementException("No item with name " + name + " to get!"));
    }

    @Override
    public void createItem(String name) throws IllegalArgumentException, RemoteException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Invalid name. Creating item failed!");
        }

        for (InventoryItem existingItem : items) {
            if (name.equals(existingItem.getName())) {
                throw new IllegalArgumentException("Duplicate item: " + name);
            }
        }

        final InventoryItemImpl item = new InventoryItemImpl(name);
        items.add(item);
        fireItemAdded(item);
		System.out.printf("%s\t%s\n",
				"Item created:", item);
    }

	@Override
	public void setDescription(String editDescriptionItemName, String description) throws IllegalArgumentException, RemoteException {
		setDescription(getItem(editDescriptionItemName), description);
		
	}
    
    private void setDescription(InventoryItemImpl item, String description) throws IllegalArgumentException, RemoteException {
        if (item == null || description == null) {
            throw new IllegalArgumentException("Invalid change. Item or description is null!");
        }

        String descriptionOld = item.getDescription();
        item.setDescription(description);
        fireItemChanged(item);
        
        if (descriptionOld.isEmpty()) {
    		System.out.printf("%s\t%s (%s)\n",
    				"Descr. added:", item, item.getDescription());
        } else {
        	if (item.getDescription().isEmpty()) {
        		System.out.printf("%s\t%s (%s)\n",
        				"Descr. deleted:", item, descriptionOld);
        	} else {
        		System.out.printf("%s\t%s (%s %s %s %s)\n",
        				"Descr. changed:", item,
        				"from", descriptionOld,
        				"to", item.getDescription());
        	}
        }
    }
    
    @Override
    public void increaseQuantity(String itemToIncreaseQuantity, int increase) throws IllegalArgumentException, RemoteException {
    	increaseQuantity(getItem(itemToIncreaseQuantity), increase);
    }

    private void increaseQuantity(InventoryItemImpl item, int increase) throws IllegalArgumentException, RemoteException {
        if (item == null) {
            throw new IllegalArgumentException("Invalid item to change. Item is null!");
        } else if (increase < 0) {
            throw new IllegalArgumentException("Invalid quantity increase: " + increase);
        } else if (increase == 0) {
            return;
        }

        if (!items.contains(item)) {
            throw new IllegalArgumentException("Invalid item to change!");
        } else if (Integer.MAX_VALUE - item.getQuantity() < increase) {
            throw new IllegalArgumentException("Maximum quantity is restricted to " + Integer.MAX_VALUE);
        } else {
            item.setQuantity(item.getQuantity() + increase);
            fireItemChanged(item);
			System.out.printf("%s\t%s (%s %d %s %d)\n",
					"Item increased:", item,
					"from", (item.getQuantity() - increase),
					"to", item.getQuantity());
        }
    }

	@Override
	public void decreaseQuantity(String itemToDecreaseQuantity, int decrease) throws IllegalArgumentException, RemoteException {
		decreaseQuantity(getItem(itemToDecreaseQuantity), decrease);
		
	}
    
    private void decreaseQuantity(InventoryItemImpl item, int decrease) throws IllegalArgumentException, RemoteException {
        if (decrease < 0) {
            throw new IllegalArgumentException("Invalid quantity decrease: " + decrease);
        } else if (decrease == 0) {
            return;
        }

        if (item.getQuantity() < decrease) {
            throw new IllegalArgumentException("Minimum quantity is 0!");
        } else {
            item.setQuantity(item.getQuantity() - decrease);
            fireItemChanged(item);
			System.out.printf("%s\t%s (%s %d %s %d)\n",
					"Item decreased:", item,
					"from", (item.getQuantity() + decrease),
					"to", item.getQuantity());
		}
    }
    
    @Override
    public void deleteItem(String deleteItemName) throws IllegalArgumentException, RemoteException {
    	deleteItem(getItem(deleteItemName));
    }

    private void deleteItem(InventoryItemImpl item) throws IllegalArgumentException {
    	if (item != null) {
    		final boolean removed = items.remove(item);
    		if (removed) {
    			fireItemRemoved(item);
    			System.out.printf("%s\t%s\n",
    					"Item deleted:", item);
    		}
    	} else {
    		System.out.println("Failed to delete Item. Item is null!");
    	}
    }

    @Override
    public void addListener(InventoryChangeListener<InventoryItemImpl> listener) throws RemoteException {
        if (listener != null) {
            listeners.add(listener);
        }
    }

    @Override
    public void removeListener(InventoryChangeListener<InventoryItemImpl> listener) throws RemoteException {
        if (listener != null) {
            listeners.remove(listener);
        }
    }
    
}
