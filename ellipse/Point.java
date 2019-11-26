package ellipse;

public class Point {

    private final int x;
    private final int y;

    public Point() {
        this(0, 0);
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return String.format("Ellipse.Point(x: %d, y: %d)", this.x, this.y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            Point point = (Point) obj;
            
            return this.getX() == point.getX() && this.getY() == point.getY();
        }

        return false;
    }

}