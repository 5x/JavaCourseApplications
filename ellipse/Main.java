package ellipse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Ellipse first = new Ellipse(
                new Point(0, 1),
                new Point(1, 0),
                new Point(0, -1),
                new Point(-1, 0)
        );
        Ellipse second = new Ellipse(new Point(10, 4), 5, 2);
        Ellipse third = new Ellipse(new Point(2, 2), 2, 2);
        Circle firstCircle = new Circle(new Point(0, 5), 5);
        Circle secondCircle = new Circle(new Point(10, 6), 1);

        Ellipse ellipses[] = {first, second, third, firstCircle, secondCircle};
        for (Ellipse ellipse : ellipses) {
            String className = ellipse.getClass().getName();
            String center = ellipse.getCenter().toString();
            double area = ellipse.getArea();

            String repr = String.format("Class name: %s%nCenter: %s, Area: %f", className, center, area);
            System.out.println(repr);

            double distance = ellipse.distanceBetweenCenters(first);
            repr = String.format("Distance between %s is: %f", first.getCenter(), distance);
            System.out.println(repr);

            if (ellipse instanceof Circle) {
                Point zero = new Point();
                Boolean isInside = ((Circle) ellipse).isPointInside(zero);
                repr = String.format("Is %s inside circle: %b", zero.toString(), isInside);
                System.out.println(repr);
            }
        }

        System.out.println("Not sorted:");
        ArrayList<Ellipse> list = new ArrayList<>(Arrays.asList(ellipses));
        printEllipsesList(list);

        Ellipse.sort(list);
        System.out.println("Sorted:");
        printEllipsesList(list);

        System.out.println("Sorted without Collections methods:");
        list = new ArrayList<>(Arrays.asList(ellipses));
        BubbleSortForEllipses.sort(list);
        printEllipsesList(list);

        Map<String, Ellipse> hashMap = new HashMap<>();
        for (Ellipse obj : list) {
            hashMap.put(obj.toString(), obj);
        }

        Map<String, Ellipse> exclude = new HashMap<>();
        exclude.put(first.toString(), first);

        Ellipse.removeDuplicates(hashMap, exclude);
        for (Map.Entry<String, Ellipse> entry : hashMap.entrySet()) {
            String key = entry.getKey();

            String repr = String.format("Map Key: %s", key);
            System.out.println(repr);
        }
    }

    private static void printEllipsesList(ArrayList<Ellipse> list) {
        for (Object obj : list) {
            System.out.println(obj);
        }
    }

}
