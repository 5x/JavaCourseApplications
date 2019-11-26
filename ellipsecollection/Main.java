package ellipsecollection;

import ellipse.Circle;
import ellipse.Ellipse;
import ellipse.Point;

public class Main {
    public static void main(String[] args) {
        EllipseCollection collection = new EllipseCollection();
        collection.add(new Ellipse(new Point(10, 4), 5, 2));
        collection.add(new Ellipse(new Point(2, 2), 2, 2));
        collection.add(new Ellipse(new Point(10, 4), 5, 2));
        collection.add(new Circle(new Point(0, 5), 5));
        collection.add(new Circle(new Point(0, 5), 80));

        System.out.println("SearchableCollection:");
        for (Ellipse obj : collection) {
            System.out.println(obj);
        }

        collection.setCondition(new Condition<Ellipse>() {
            @Override
            public boolean check(Ellipse element) {
                return element.getArea() > 20;
            }
        });

        System.out.println("Area > 20:");
        for (Ellipse obj : collection) {
            System.out.println(obj);
        }


        testCaseWrapper("Distance to [x:0, y:0] less that 3:", collection, new Condition<Ellipse>() {
            @Override
            public boolean check(Ellipse element) {
                return Ellipse.distanceBetweenCenters(element.getCenter(), new Point(0, 0)) < 3;
            }
        });

        testCaseWrapper("Impossible condition", collection, element -> element.getArea() < 0);

        try {
            testCaseWrapper("Throw/Catch IllegalArgumentException exception:", collection, element -> {
                if (element.getArea() > 100) {
                    String message = String.format("Exception, element with >100 Area: %s", element.toString());
                    throw new IllegalArgumentException(message);
                }

                return true;
            });
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            testCaseWrapper("Throw/Catch custom exception:", collection, element -> {
                throw new ImpossibleConditionCheckException();
            });
        } catch (ImpossibleConditionCheckException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void testCaseWrapper(String message, EllipseCollection collection, Condition<Ellipse> condition) {
        collection.setCondition(condition);
        System.out.println(message);
        
        for (Ellipse obj : collection) {
            System.out.println(obj);
        }
    }
}
