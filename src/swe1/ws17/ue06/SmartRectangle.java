package swe1.ws17.ue06;

public class SmartRectangle {

    private String name = null;
    private int x = 0;
    private int y = 0;
    private int height = 0;
    private int width = 0;

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public int getX() {
        return x;
    }

    public void setX(int newX) {
        x = newX;
    }

    public int getY() {
        return y;
    }

    public void setY(int newY) {
        y = newY;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int newHeight) {
        height = newHeight;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int newWidth) {
        width = newWidth;
    }

    // void move
    public void move(int xDist, int yDist) {
        setX(x + xDist);
        setY(y + yDist);
    }

    // void moveTo
    public void moveTo(int newX, int newY) {
        setX(newX);
        setY(newY);
    }

    // void changeArea (+ dheight dwidth)
    public void changeArea(int dHeight, int dWidth) {
        setHeight(height + dHeight);
        setWidth(width + dWidth);
    }

    // getArea
    public int getArea() {
        int area = x * y;
        return area;
    }

    // boolean isQuadratic
    public boolean isQuadratic() {
        if (getHeight() == getWidth()) {
            return true;
        } else
            return false;
    }

    // boolean compareWith
    public boolean compareWith(SmartRectangle rect) {
        if (getArea() == rect.getArea()) {
            return true;
        } else
            return false;
    }

    public boolean overlapWith(SmartRectangle rect) {
        if ((getX() + getWidth() < rect.getX()) || (rect.getX() + rect.getWidth() < getX())
                || (getY() + getHeight() < rect.getX()) || (rect.getY() + rect.getHeight() < getY())) {
            return false;
        } else
            return true;
    }

    // string getquadrant
    public String getQuadrant() {
        String quadrant = null;
        if (getX() >= 0 && getY() >= 0) {
            quadrant = "1. Quadrant";
        }
        if (getX() < 0 && getY() >= 0) {
            quadrant = "2. Quadrant";
        }
        if (getX() < 0 && getY() < 0) {
            quadrant = "3. Quadrant";
        }
        if (getX() >= 0 && getY() < 0) {
            quadrant = "4. Quadrant";
        }
        return quadrant;
    }
}
