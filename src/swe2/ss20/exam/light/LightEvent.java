package swe2.ss20.exam.light;

import java.util.EventObject;

public class LightEvent extends EventObject {
    private final boolean lightState;

    public LightEvent(Object source, boolean lightState) {
        super(source);
        this.lightState = lightState;
    }

    public boolean isLightState() {
        return lightState;
    }
}