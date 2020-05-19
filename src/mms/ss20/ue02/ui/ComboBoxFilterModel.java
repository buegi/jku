package mms.ss20.ue02.ui;

import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import mms.ss20.ue02.filters.*;

/**
 * List of registered filters
 */
public class ComboBoxFilterModel implements ComboBoxModel<FilterInterface> {

    private ArrayList<FilterInterface> backing = new ArrayList<FilterInterface>();

    private FilterInterface selectedItem;

    private ArrayList<ListDataListener> listeners = new ArrayList<ListDataListener>();

    /**
     * Initializes this filter model
     */
    public ComboBoxFilterModel() {
        backing.add(new DummyFilterImplementation());
        /* 		@TODO Place your implementation here		 */
        selectedItem = backing.get(0);
        backing.add(new GreyScaleFilter());
        backing.add(new Threshold());
        backing.add(new SepiaFilter());
        backing.add(new Subsampling());
        backing.add(new ChromaSubsampling());
    }

    @Override
    public FilterInterface getElementAt(int i) {
        return backing.get(i);
    }

    @Override
    public int getSize() {
        return backing.size();
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        listeners.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        listeners.remove(l);
    }

    @Override
    public Object getSelectedItem() {
        return selectedItem;
    }

    @Override
    public void setSelectedItem(Object selectedItem) {
        this.selectedItem = (FilterInterface) selectedItem;
    }
}