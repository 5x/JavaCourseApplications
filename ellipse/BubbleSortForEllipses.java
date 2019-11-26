package ellipse;

import java.util.List;

public class BubbleSortForEllipses {
    private static void swap(List<Ellipse> list, int i, int j) {
        Ellipse tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

    public static void sort(List<Ellipse> sort) {
        int min;
        for (int i = 0; i < sort.size(); ++i) {
            min = i;
            for (int j = i + 1; j < sort.size(); ++j) {
                if (sort.get(j).getArea() < sort.get(min).getArea()) {
                    min = j;
                }
            }

            swap(sort, i, min);
        }
    }

}
