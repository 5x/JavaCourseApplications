package matrix;

import java.util.Collection;

public final class MatrixImmutable<T> extends Matrix<T> {

    /**
     * Constructs an matrix object.
     *
     * @param matrix Two-dimensional array of matrix values.
     * @throws IllegalArgumentException Invalid matrix dimensions.
     * @throws NullPointerException     if {@code matrix} is {@code null}
     */
    public MatrixImmutable(T[][] matrix) {
        super(matrix);
    }

    /**
     * Copy constructor.
     *
     * @param matrix An matrix instance, that would be copied.
     */
    public MatrixImmutable(Matrix<T> matrix) {
        super(matrix);
    }

    /**
     * Construct matrix from collection, size of collection must be eq or more
     * that product of {@code height * matrix}. Elements fill from left to
     * right by row wise.
     *
     * @param height     Matrix height, number of columns
     * @param width      Matrix width, number of rows
     * @param collection Collection of matrix elements.
     * @throws NullPointerException if {@code collection} is {@code null}
     */
    public MatrixImmutable(int height, int width, Collection<T> collection) {
        super(height, width, collection);
    }

    /**
     * Construct a matrix by selected size with initial value.
     *
     * @param height Matrix height, number of columns
     * @param width  Matrix width, number of rows
     * @param value  Initial value to fill a matrix
     */
    public MatrixImmutable(int height, int width, T value) {
        super(height, width, value);
    }

    /**
     * Construct a square matrix and fill it by initial value.
     *
     * @param dimension Length of matrix dimension(rows, columns)
     * @param value     Initial value to fill a matrix
     */
    public MatrixImmutable(int dimension, T value) {
        super(dimension, value);
    }
}
