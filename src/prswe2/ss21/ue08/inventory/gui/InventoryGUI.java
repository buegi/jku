package prswe2.ss21.ue08.inventory.gui;

import prswe2.ss21.ue08.inventory.model.InventoryChangeListener;
import prswe2.ss21.ue08.inventory.model.InventoryItem;
import prswe2.ss21.ue08.inventory.model.InventoryModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public final class InventoryGUI<ItemClass extends InventoryItem> {
	
    private static final class DisplayItem<ItemClass extends InventoryItem> {

        private final ItemClass item;

        private DisplayItem(ItemClass item) {
            assert item != null;
            this.item = item;
        }

        public ItemClass getItem() {
            return item;
        }

        /**
         * This method is invoked to generate the string displayed in the list view.
         *
         * @return the description of the item
         */
        @Override
        public String toString() {
            try {
				return item.getName();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			return null;
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof DisplayItem && item.equals(((DisplayItem<?>) obj).getItem());
        }
    }

    private final JFrame frame;
    private final JList<DisplayItem<ItemClass>> stockListView;
    private final JTextArea itemName;
    private final JTextArea itemDescription;
    private final JTextArea itemQuantity;
    private final JButton addStockButton;
    private final JButton removeStockButton;
    private final JButton editDescriptionButton;
    private InventoryGUI(InventoryModel<ItemClass> model) throws RemoteException {
		frame = new JFrame();
        frame.setTitle("Inventory Management");
        frame.setSize(800, 600);
        frame.setResizable(true);
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setLocation(50, 50);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel mainPanel = new JPanel();
        frame.getContentPane().add(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        final JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(250);
        mainPanel.add(splitPane, BorderLayout.CENTER);

        final DefaultListModel<DisplayItem<ItemClass>> stockListModel = new DefaultListModel<>();
        stockListView = new JList<>(stockListModel);
        splitPane.setLeftComponent(stockListView);

        final JPanel detailPanel = new JPanel();
        detailPanel.setLayout(new BorderLayout());
        splitPane.setRightComponent(detailPanel);

        final JPanel detailNamePanel = new JPanel();
        detailNamePanel.setLayout(new BoxLayout(detailNamePanel, BoxLayout.Y_AXIS));
        detailNamePanel.add(createDetailLabel("Item Name:"));
        detailNamePanel.add(wrapTextArea(itemName = createTextField()));
        detailPanel.add(detailNamePanel, BorderLayout.NORTH);

        final JPanel detailDescriptionPanel = new JPanel();
        detailDescriptionPanel.setLayout(new BoxLayout(detailDescriptionPanel, BoxLayout.Y_AXIS));
        detailDescriptionPanel.add(new JSeparator(JSeparator.HORIZONTAL));
        detailDescriptionPanel.add(createDetailLabel("Item Description:"));
        detailDescriptionPanel.add(wrapTextArea(itemDescription = createTextField()));
        detailDescriptionPanel.add(new JSeparator(JSeparator.HORIZONTAL));
        detailDescriptionPanel.add(createDetailLabel("Item Quantity:"));
        detailDescriptionPanel.add(wrapTextArea(itemQuantity = createTextField()));
        detailPanel.add(detailDescriptionPanel, BorderLayout.CENTER);

        final JPanel editPanel = new JPanel();
        editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.Y_AXIS));
        detailPanel.add(editPanel, BorderLayout.SOUTH);

        final JPanel changeItemPanel = new JPanel();
        final SpinnerModel quantitySpinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        changeItemPanel.add(new JSpinner(quantitySpinnerModel));
        changeItemPanel.setLayout(new BoxLayout(changeItemPanel, BoxLayout.X_AXIS));
        changeItemPanel.add(addStockButton = createQuantityButton("Increase Qty."));
        changeItemPanel.add(removeStockButton = createQuantityButton("Decrease Qty."));
        editPanel.add(changeItemPanel);
        changeItemPanel.add(editDescriptionButton = new JButton("Edit Desc."));

        final JPanel editListPanel = new JPanel();
        editListPanel.setLayout(new BoxLayout(editListPanel, BoxLayout.X_AXIS));
        JButton createItemButton;
        editListPanel.add(createItemButton = new JButton("Create Item"));
        JButton deleteItemButton;
        editListPanel.add(deleteItemButton = new JButton("Delete Item"));
        editPanel.add(editListPanel);

        stockListView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        stockListView.addListSelectionListener(listSelectionEvent -> updateDetailsView());

        addStockButton.addActionListener(e -> {
            final DisplayItem<ItemClass> selectedItem = stockListView.getSelectedValue();
            if (selectedItem != null) {
                try {
                    try {
                    	String itemToIncreaseQuantity = selectedItem.getItem().getName();
						model.increaseQuantity(itemToIncreaseQuantity, (int) quantitySpinnerModel.getValue());
					} catch (RemoteException error) {
						JOptionPane.showMessageDialog(frame, error.getMessage(), "Remote Error while changing quantity", JOptionPane.ERROR_MESSAGE);
					}
                } catch (IllegalArgumentException error) {
                    JOptionPane.showMessageDialog(frame, error.getMessage(), "Error while changing quantity", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        removeStockButton.addActionListener(e -> {
            final DisplayItem<ItemClass> selectedItem = stockListView.getSelectedValue();
            if (selectedItem != null) {
                try {
                    try {
                    	String itemToDecreaseQuantity = selectedItem.getItem().getName();
						model.decreaseQuantity(itemToDecreaseQuantity, (int) quantitySpinnerModel.getValue());
					} catch (RemoteException error) {
						JOptionPane.showMessageDialog(frame, error.getMessage(), "Remote Error while changing quantity", JOptionPane.ERROR_MESSAGE);
					}
                } catch (IllegalArgumentException error) {
                    JOptionPane.showMessageDialog(frame, error.getMessage(), "Error while changing quantity", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        editDescriptionButton.addActionListener(e -> {
            final DisplayItem<ItemClass> selectedItem = stockListView.getSelectedValue();
            if (selectedItem != null) {
                SwingUtilities.invokeLater(() -> showEditDescriptionDialog(model, selectedItem.getItem()));
            }
        });

        createItemButton.addActionListener(e -> {
            final String newName = JOptionPane.showInputDialog(frame, "Please enter a unique name for the new item:", "Create Item", JOptionPane.PLAIN_MESSAGE);
            if (newName != null) {
                try {
                    try {
						model.createItem(newName);
					} catch (RemoteException error) {
						JOptionPane.showMessageDialog(frame, error.getMessage(), "Remote Error while creating a new item", JOptionPane.ERROR_MESSAGE);
					}
                } catch (IllegalArgumentException error) {
                    JOptionPane.showMessageDialog(frame, error.getMessage(), "Error while creating a new item", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteItemButton.addActionListener(e -> {
            final DisplayItem<ItemClass> selectedItem = stockListView.getSelectedValue();
            try {
                try {
                	String deleteItemName = selectedItem.getItem().getName();
					model.deleteItem(deleteItemName);
				} catch (RemoteException error) {
					JOptionPane.showMessageDialog(frame, error.getMessage(), "Remote Error while deleting item", JOptionPane.ERROR_MESSAGE);
				}
            } catch (IllegalArgumentException error) {
                JOptionPane.showMessageDialog(frame, error.getMessage(), "Error while deleting item", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // Class for the inventorychangelistener to perform callback
		class InventoryChangeListenerClient extends UnicastRemoteObject implements InventoryChangeListener<ItemClass> {

			private static final long serialVersionUID = -4276006573559271628L;

			protected InventoryChangeListenerClient() throws RemoteException {
				super();
			}

			@Override
			public void onItemAdded(ItemClass addedItem) throws RemoteException {
				stockListModel.addElement(new DisplayItem<>(addedItem));
				
			}

			@Override
			public void onItemChanged(ItemClass changedItem) throws RemoteException {
				updateDetailsView();
				
			}

			@Override
			public void onItemRemoved(ItemClass removedItem) throws RemoteException {
				stockListModel.removeElement(new DisplayItem<>(removedItem));
				
			}
    	}
        
        // connect inventory model and display model
        final InventoryChangeListener<ItemClass> inventoryChangeListener = new InventoryChangeListenerClient();
        
        try {
            try {
				model.addListener(inventoryChangeListener);
			} catch (RemoteException error) {
				error.printStackTrace();
				JOptionPane.showMessageDialog(frame, error.getMessage(), "Remote Error while adding listener", JOptionPane.ERROR_MESSAGE);
			}
            try {
				model.getItems().forEach(element -> stockListModel.addElement(new DisplayItem<>(element)));
			} catch (RemoteException error) {
				JOptionPane.showMessageDialog(frame, error.getMessage(), "Remote Error while getting items", JOptionPane.ERROR_MESSAGE);
			}
        } catch (IllegalArgumentException error) {
            throw new AssertionError("Error during initialization", error);
        }
        
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    try {
						model.removeListener(inventoryChangeListener);
					} catch (RemoteException error) {
						JOptionPane.showMessageDialog(frame, error.getMessage(), "Remote Error while removing listener", JOptionPane.ERROR_MESSAGE);
					}
                } catch (IllegalArgumentException error) {
                    // the application will now terminate anyways
                    throw new AssertionError("Error during closing", error);
                }
            }
        });

        // initialize the details view
        updateDetailsView();
    }

	public static <ItemClass extends InventoryItem> void startGui(InventoryModel<ItemClass> model) throws RemoteException {
        new InventoryGUI<>(model).show();
    }

    private static JButton createQuantityButton(String label) {
        return new JButton(label);
    }

    private static JLabel createDetailLabel(String labelText) {
        return new JLabel(labelText, JLabel.CENTER);
    }

    private static JScrollPane wrapTextArea(JTextArea textArea) {
        final JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        return scrollPane;
    }

    private static JTextArea createTextField() {
        final JTextArea textPane = new JTextArea();
        textPane.setWrapStyleWord(true);
        textPane.setEditable(true);
        textPane.setEditable(false);
        return textPane;
    }

    private void updateDetailsView() {
        SwingUtilities.invokeLater(() -> {
            final DisplayItem<ItemClass> selectedItem = stockListView.getSelectedValue();
            if (selectedItem != null) {
                setDetails(selectedItem.getItem());
            } else {
                clearDetails();
            }

            addStockButton.setEnabled(selectedItem != null);
            removeStockButton.setEnabled(selectedItem != null);
            editDescriptionButton.setEnabled(selectedItem != null);
        });
    }

    private void setDetails(InventoryItem item) {
        try {
            try {
				itemName.setText(item.getName());
			} catch (RemoteException error) {
				JOptionPane.showMessageDialog(frame, error.getMessage(), "Remote Error while setting item name", JOptionPane.ERROR_MESSAGE);
			}
            try {
				itemDescription.setText(item.getDescription());
			} catch (RemoteException error) {
				JOptionPane.showMessageDialog(frame, error.getMessage(), "Remote Error while setting item description", JOptionPane.ERROR_MESSAGE);
			}
            try {
				itemQuantity.setText(String.valueOf(item.getQuantity()));
			} catch (RemoteException error) {
				JOptionPane.showMessageDialog(frame, error.getMessage(), "Remote Error while setting item quantity", JOptionPane.ERROR_MESSAGE);
			}
        } catch (IllegalArgumentException error) {
            clearDetails();
            JOptionPane.showMessageDialog(frame, error.getMessage(), "Error while retrieving item details", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearDetails() {
        itemName.setText("");
        itemDescription.setText("");
        itemQuantity.setText("");
    }

    private void showEditDescriptionDialog(InventoryModel<ItemClass> model, ItemClass editedItem) {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Edit Description");
        frame.setMinimumSize(new Dimension(400, 400));
        frame.setResizable(true);

        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        frame.getContentPane().add(mainPanel);

        final JPanel descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.X_AXIS));
        final JTextArea descriptionPane = new JTextArea(50, 50);
        descriptionPane.setWrapStyleWord(true);
        descriptionPane.setEnabled(true);
        descriptionPane.setEditable(true);
        JScrollPane scrollPane = new JScrollPane(descriptionPane);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        descriptionPanel.add(scrollPane);
        mainPanel.add(descriptionPanel, BorderLayout.CENTER);

        final JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        final JButton saveButton = new JButton("Save");
        final JButton reloadButton = new JButton("Reload");
        buttonsPanel.add(saveButton);
        buttonsPanel.add(reloadButton);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        saveButton.addActionListener(e -> editDescription(model, editedItem, descriptionPane.getText()));
        reloadButton.addActionListener(e -> SwingUtilities.invokeLater(() -> {
            try {
                try {
					descriptionPane.setText(editedItem.getDescription());
				} catch (RemoteException error) {
					JOptionPane.showMessageDialog(frame, error.getMessage(), "Remote Error while setting item description", JOptionPane.ERROR_MESSAGE);
				}
            } catch (IllegalArgumentException error) {
                descriptionPane.setText("");
                JOptionPane.showMessageDialog(frame, error.getMessage(), "Error while loading description", JOptionPane.ERROR_MESSAGE);
            }
        }));
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                editDescription(model, editedItem, descriptionPane.getText());
            }
        });

        try {
			descriptionPane.setText(editedItem.getDescription());
		} catch (RemoteException error) {
			JOptionPane.showMessageDialog(frame, error.getMessage(), "Remote Error while setting item description", JOptionPane.ERROR_MESSAGE);
		}

        frame.setVisible(true);
        descriptionPane.requestFocus();
    }

    private void editDescription(InventoryModel<ItemClass> model, ItemClass editedItem, String newDescription) {
        SwingUtilities.invokeLater(() -> {
            try {
                try {
                	String editDescriptionItemName = editedItem.getName();
					model.setDescription(editDescriptionItemName, newDescription);
				} catch (RemoteException error) {
					JOptionPane.showMessageDialog(frame, error.getMessage(), "Remote Error while editing description", JOptionPane.ERROR_MESSAGE);
				}
            } catch (IllegalArgumentException error) {
                JOptionPane.showMessageDialog(frame, error.getMessage(), "Error while editing description", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    void show() {
        frame.setVisible(true);
    }
    
}
