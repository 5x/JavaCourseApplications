package ellipse;

public class Circle extends Ellipse {

    private int radius;

    public Circle(Point topLeft, int radius) throws IllegalArgumentException {
        super(
                new Point(topLeft.getX(), topLeft.getY()),
                new Point(topLeft.getX() + radius, topLeft.getY()),
                new Point(topLeft.getX() + radius, topLeft.getY() - radius),
                new Point(topLeft.getX(), topLeft.getY() - radius)
        );

        if (radius <= 0) {
            throw new IllegalArgumentException();
        }

        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public boolean isPointInside(Point point) {
        Point center = this.getCenter();
        double a = Math.sqrt(center.getX() - point.getX());
        double b = Math.sqrt(center.getY() - point.getY());
        double c = Math.sqrt(a + b);

        return c < this.getRadius();
    }

    @Override
    public String toString() {
        String centerStr = this.getCenter().toString();
        double area = this.getArea();
        int radius = this.getRadius();
        
        return String.format("Ellipse.Circle(%s), Area: %f, R: %d", centerStr, area, radius);
    }

}
