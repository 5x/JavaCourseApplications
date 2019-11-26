package ellipse;

import java.util.*;

public class Ellipse {

    private Point top;
    private Point right;
    private Point bottom;
    private Point left;

    public Ellipse(Point top, Point right, Point bottom, Point left) throws IllegalCallerException {
        if (Ellipse.isVerticesIdentical(top, right, bottom, left)) {
            throw new IllegalArgumentException();
        }

        this.setTop(top);
        this.setRight(right);
        this.setBottom(bottom);
        this.setLeft(left);
    }

    public Ellipse(Point center, int height, int width) {
        this(
                new Point(center.getX(), center.getY() + height / 2),
                new Point(center.getX() + width / 2, center.getY()),
                new Point(center.getX(), center.getY() - height / 2),
                new Point(center.getX() - width / 2, center.getY())
        );
    }

    public static double distanceBetweenCenters(Ellipse first, Ellipse second) {
        return first.distanceBetweenCenters(second);
    }

    public static double distanceBetweenCenters(Point a, Point b) {
        return Math.sqrt((a.getX() - b.getX()) + (a.getY() - b.getY()));
    }

    public static Point getCenter(Point top, Point right, Point bottom, Point left) {
        Ellipse shape = new Ellipse(top, right, bottom, left);
        return shape.getCenter();
    }

    public static double getArea(Point top, Point right, Point bottom, Point left) {
        Ellipse shape = new Ellipse(top, right, bottom, left);

        return shape.getArea();
    }

    private static boolean isVerticesIdentical(Point top, Point right, Point bottom, Point left) {
        return Ellipse.distanceBetweenCenters(top, bottom) == 0 &&
                Ellipse.distanceBetweenCenters(right, left) == 0 &&
                Ellipse.distanceBetweenCenters(top, right) == 0;
    }

    public static void sort(List<Ellipse> list) {
        Collections.sort(list, new EllipseComparator());
    }

    public static void removeDuplicates(Map<String, Ellipse> first, Map<String, Ellipse> second) {
        for (Map.Entry<String, Ellipse> entry : second.entrySet()) {
            String key = entry.getKey();

            if (first.containsKey(key)) {
                first.remove(key);
            }
        }
    }

    public static Ellipse findByKey(String key, Map<String, Ellipse> collection) {
        return collection.get(key);
    }

    public static ArrayList<String> getKeysByValue(Map<String, Ellipse> map, Ellipse value) {
        ArrayList<String> keys = new ArrayList<>();

        for (Map.Entry<String, Ellipse> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                keys.add(entry.getKey());
            }
        }

        return keys;
    }

    public Point getTop() {
        return top;
    }

    private void setTop(Point top) {
        this.top = top;
    }

    public Point getRight() {
        return right;
    }

    private void setRight(Point right) {
        this.right = right;
    }

    public Point getBottom() {
        return bottom;
    }

    private void setBottom(Point bottom) {
        this.bottom = bottom;
    }

    public Point getLeft() {
        return left;
    }

    private void setLeft(Point left) {
        this.left = left;
    }

    private int getHeight() {
        return Math.abs(this.getBottom().getY() - this.getTop().getY());
    }

    private int getWidth() {
        return Math.abs(this.getRight().getX() - this.getLeft().getX());
    }

    public Point getCenter() {
        int y = this.getBottom().getY() + (this.getHeight() / 2);
        int x = this.getLeft().getX() + (this.getWidth() / 2);

        return new Point(x, y);
    }

    public double getArea() {
        int height = this.getHeight();
        int width = this.getWidth();

        return Math.PI * height * width;
    }

    public double distanceBetweenCenters(Ellipse ellipse) {
        Point a = this.getCenter();
        Point b = ellipse.getCenter();

        return Ellipse.distanceBetweenCenters(a, b);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Ellipse) {
            Ellipse ellipse = (Ellipse) obj;

            return this.getHeight() == ellipse.getHeight() && this.getWidth() == ellipse.getWidth();
        }

        return false;
    }

    @Override
    public String toString() {
        String centerStr = this.getCenter().toString();
        double area = this.getArea();
        
        return String.format("Ellipse.Ellipse(%s), Area: %f", centerStr, area);
    }

}

class EllipseComparator implements Comparator<Ellipse> {

    @Override
    public int compare(Ellipse o1, Ellipse o2) {
        return (int) (o1.getArea() - o2.getArea());
    }
}
