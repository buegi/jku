package swe2.ss20.ue10.demo.circledraw.model;

import java.util.EventObject;

@SuppressWarnings("serial")
public class CircleEvent extends EventObject {

    public enum Kind {ADDED, DELETED}

    private final Circle circle;

    private final Kind kind;

    public CircleEvent(CircleModel source, Circle circle, Kind kind) {
        super(source);
        this.circle = circle;
        this.kind = kind;
    }

    public Kind getKind() {
        return kind;
    }

    public Circle getCircle() {
        return circle;
    }
}