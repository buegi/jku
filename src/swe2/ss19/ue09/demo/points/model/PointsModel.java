package swe2.ss19.ue09.demo.points.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PointsModel {

	private final List<Point> points;
	private final List<PointsListener> listeners;

	public PointsModel() {
		super();
		this.points = new ArrayList<Point>();
		this.listeners = new ArrayList<PointsListener>();
	}

	public List<Point> getPoints() {
		return Collections.unmodifiableList(points);
	}

	public void add(int x, int y) {
		Point p = new Point(x, y);
		points.add(p);
		firePointsAdded(p);
	}

	public void clear() {
		Point[] ps = getPoints().toArray(new Point[0]);
		points.clear();
		firePointsCleared(ps);
	}

	public void addPointsListener(PointsListener l) {
		listeners.add(l);
	}

	public void removePointsListener(PointsListener l) {
		listeners.remove(l);
	}

	private void firePointsAdded(Point p) {
		PointsEvent pe = new PointsEvent(this, p);
		for (PointsListener l : listeners) {
			l.pointAdded(pe);
		}
	}

	private void firePointsCleared(Point[] points) {
		PointsEvent pe = new PointsEvent(this, points);
		for (PointsListener l : listeners) {
			l.pointsCleared(pe);
		}
	}
}