package swe2.ss20.ue11.graphiceditor.model;

import java.util.EventObject;

import swe2.ss20.ue11.graphiceditor.objects.GraphicObject;

/**
 * The event object for the change event.
 */
public class GraphicChangedEvent extends EventObject {

    private static final long serialVersionUID = -2537643017564655754L;

    /**
     * the changed objects
     */
    private final GraphicObject[] objs;

    /**
     * Constructor with event source and the changed objects.
     *
     * @param source the event soure
     * @param objs   the changed objects
     */
    public GraphicChangedEvent(Object source, GraphicObject[] objs) {
        super(source);
        this.objs = objs;
    }

    /**
     * Gets the object changed in this event.
     *
     * @return the changed objects
     */
    public GraphicObject[] getObjs() {
        return objs;
    }
}