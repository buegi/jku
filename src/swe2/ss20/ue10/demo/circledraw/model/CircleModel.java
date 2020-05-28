package at.jku.ssw.circledraw.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CircleModel implements Iterable<Circle> {
	
    private List<Circle> circles = new ArrayList<>();
    private List<CircleModelListener> listeners = new ArrayList<>();

    public void addCircle(Circle circle) {
        circles.add(circle);
        fireModelEvent(CircleEvent.Kind.ADDED, circle);
    }

    public void removeCircle(Circle circle) {
        if (circles.remove(circle)) {
            fireModelEvent(CircleEvent.Kind.DELETED, circle);
        }
    }
    
    public void clear() {
    	for (Circle c: getCircles()) {
    		this.removeCircle(c);
    	}
    }

    @Override
    public Iterator<Circle> iterator() {
        return circles.iterator();
    }
    
    public Circle[] getCircles() {
    	return circles.toArray(new Circle[circles.size()]); 
    }

    public void addCircleModelListener(CircleModelListener listener) {
        listeners.add(listener);
    }

    public void removeCircleModelListener(CircleModelListener listener) {
        listeners.remove(listener);
    }

    protected void fireModelEvent(CircleEvent.Kind kind, Circle circle) {
        CircleEvent event = new CircleEvent(this, circle, kind);
        for (CircleModelListener listener : listeners) {
            listener.circleEvent(event);
        }
    }
}
