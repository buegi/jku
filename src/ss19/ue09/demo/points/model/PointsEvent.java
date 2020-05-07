package ss19.ue09.demo.points.model;

import java.util.EventObject;

@SuppressWarnings("serial")
public class PointsEvent extends EventObject {

	private final Point[] points;

	public PointsEvent(Object source, Point p) {
		super(source);
		this.points = new Point[] { p };
	}

	public PointsEvent(Object source, Point[] points) {
		super(source);
		this.points = points;
	}

	public Point[] getPoints() {
		return points;
	}
}