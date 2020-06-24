package swe2.ss20.exam.light;

import java.util.ArrayList;
import java.util.List;

public class LightModel {
    private boolean on;
    private List<LightListener> listeners = new ArrayList<LightListener>();

    public boolean isOn() {
        return on;
    }

    public void switchLight() {
        this.on = !this.on;
        fireLightChanged();
    }

    public void addLightListener(LightListener l) {
        listeners.add(l);
    }

    public void removeLightListener(LightListener l) {
        listeners.remove(l);
    }

    private void fireLightChanged() {
        LightEvent e = new LightEvent(this, this.on);
        for (LightListener listener : listeners) {
            listener.lightChanged(e);
        }
    }
}