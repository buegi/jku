package swe2.ss20.exam.light;

import java.util.EventListener;

public interface LightListener extends EventListener {
    public void lightChanged(LightEvent e);
}
