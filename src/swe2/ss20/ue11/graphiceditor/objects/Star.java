package swe2.ss20.ue11.graphiceditor.objects;

import java.awt.*;

public class Star extends GraphicObject {

    private int[] xCoordinates;
    private int[] yCoordinates;
    private int nPoints;

    public Star(int x, int y, int... coordinates) {
        super(x, y);
        xCoordinates = new int[coordinates.length / 2];
        yCoordinates = new int[coordinates.length / 2];
        this.nPoints = (coordinates.length / 2);
        fillVarArgsToArray(coordinates);
    }

    private void fillVarArgsToArray(int[] coordinates) throws IllegalArgumentException {
        if (coordinates.length % 2 != 0) {
            throw new IllegalArgumentException("Uneven number of x/y coordinates in varargs!");
        }
        int i = 0;
        while (i < coordinates.length) {
            xCoordinates[i] = coordinates[i];
            yCoordinates[i] = coordinates[i + 1];
            i += 2;
        }
    }

    @Override
    public void paint(Graphics g) {
        g.fillPolygon(xCoordinates, yCoordinates, nPoints);
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    // TODO Task 2: copy method

    // TODO Task 4: accept method
}