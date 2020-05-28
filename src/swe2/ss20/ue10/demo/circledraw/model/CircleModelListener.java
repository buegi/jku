package at.jku.ssw.circledraw.model;

import java.util.EventListener;

public interface CircleModelListener extends EventListener {
    public void circleEvent(CircleEvent event);
}
