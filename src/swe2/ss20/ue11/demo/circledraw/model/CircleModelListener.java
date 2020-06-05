package swe2.ss20.ue11.demo.circledraw.model;

import java.util.EventListener;

public interface CircleModelListener extends EventListener {
    void circleEvent(CircleEvent event);
}