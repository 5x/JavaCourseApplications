package matrix;

import java.util.Collection;
import java.util.Objects;

public final class ArrayHelpers {

    private ArrayHelpers() {
    }

    public static <T> T[][] arrayFromCollection(int height, int width, Collection<T> collection) {
        int row = 0;
        int col = 0;

        Objects.requireNonNull(collection);
        T[][] resultArray = ArrayHelpers.createEmptyData(height, width);

        for (T value : collection) {
            resultArray[row][col] = value;
            col++;

            if (col >= width) {
                col = 0;
                row++;
            }
        }

        return resultArray;
    }

    public static <T> T[][] deepCopy(T[][] originalArray) {
        Objects.requireNonNull(originalArray);
        T[][] newArray = originalArray.clone();

        for (int x = 0; x < originalArray.length; x++) {
            newArray[x] = originalArray[x].clone();
        }

        return newArray;
    }

    @SuppressWarnings("unchecked")
    public static <T> T[][] createEmptyData(int height, int width) {
        return (T[][]) new Object[height][width];
    }

}
