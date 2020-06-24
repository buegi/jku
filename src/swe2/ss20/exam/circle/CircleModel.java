package swe2.ss20.exam.circle;

import java.util.ArrayList;
import java.util.List;

public class CircleModel {
    // Feld-Deklarationen (alle Felder müssen private sein)
    private int radius;
    private List<RadiusListener> listeners;

    // Initialisierung mit beliebigen default-Radius
    public CircleModel() {
        this.radius = 5;
        this.listeners = new ArrayList<>();
    }

    public int getRadius() {
        return this.radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        fireRadiusChanged();
    }


    public void addRadiusListener(RadiusListener listener) {
        listeners.add(listener);
    }

    public void removeRadiusListener(RadiusListener listener) {
        listeners.remove(listener);
    }

    private void fireRadiusChanged() {
        for (RadiusListener listener : listeners) {
            listener.radiusChanged();
        }
    }
}