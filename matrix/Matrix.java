package matrix;

import java.util.*;

/**
 * Generic matrix class, that provide basic matrix operations.
 *
 * @param <T> the type of matrix elements.
 */
public class Matrix<T> implements IMatrix<Matrix<T>>, Iterable<Collection<T>> {

    /**
     * Two-dimensional array for store matrix values.
     */
    private final T[][] matrix;

    /**
     * Number of rows in a matrix.
     */
    private final int height;

    /**
     * Number of columns in a matrix.
     */
    private final int width;

    /**
     * Constructs an matrix object.
     *
     * @param matrix Two-dimensional array of matrix values.
     * @throws IllegalArgumentException Invalid matrix dimensions.
     * @throws NullPointerException     if {@code matrix} is {@code null}
     */
    public Matrix(T[][] matrix) {
        this.matrix = ArrayHelpers.deepCopy(matrix);

        if (matrix.length <= 0) {
            throw new IllegalArgumentException("Invalid matrix size. Matrix must contain at least one column.");
        }

        this.height = matrix.length;


        if (matrix[0].length <= 0) {
            throw new IllegalArgumentException("Invalid matrix size. Matrix must contain at least one row.");
        }

        this.width = matrix[0].length;

        this.checkMatrixAligned();
    }

    /**
     * Copy constructor.
     *
     * @param matrix An matrix instance, that would be copied.
     */
    public Matrix(Matrix<T> matrix) {
        this(matrix.matrix);
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
    public Matrix(int height, int width, Collection<T> collection) {
        this(ArrayHelpers.arrayFromCollection(height, width, collection));
    }

    /**
     * Construct a matrix by selected size with initial value.
     *
     * @param height Matrix height, number of columns
     * @param width  Matrix width, number of rows
     * @param value  Initial value to fill a matrix
     */
    public Matrix(int height, int width, T value) {
        this(height, width, Collections.nCopies(height * width, value));
    }

    /**
     * Construct a square matrix and fill it by initial value.
     *
     * @param dimension Length of matrix dimension(rows, columns)
     * @param value     Initial value to fill a matrix
     */
    public Matrix(int dimension, T value) {
        this(dimension, dimension, value);
    }

    private void checkMatrixAligned() {
        for (T[] row : this.matrix) {
            if (row.length != this.getWidth()) {
                throw new IllegalArgumentException("All matrix rows must be aligned.");
            }
        }
    }

    /**
     * Return a matrix height(number of columns, N-dimension size of matrix).
     *
     * @return Matrix height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Return a matrix width(number of rows, M-dimension size of matrix).
     *
     * @return Matrix width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Return a matrix value at {@code row}, {@code col} position indexes.
     *
     * @param row Row index of a matrix
     * @param col Column index of a matrix
     * @return Value at {@code row}, {@code col} position indexes
     * @throws IndexOutOfBoundsException Index is out of matrix dimensions
     */
    public T getValue(int row, int col) throws IndexOutOfBoundsException {
        return this.matrix[row][col];
    }

    /**
     * Compare dimensions of a matrix.
     *
     * @param that A matrix instance to compare.
     * @return {@code true} if both dimensions equal.
     */
    private boolean isDimensionsEq(Matrix<T> that) {
        return this.width == that.width && this.height == that.height;
    }

    /**
     * Return the sum of two M by N matrices.
     *
     * @param b Secondary matrix
     * @return The sum of two M by N matrices
     * @throws IllegalArgumentException Dimensions a matrix A and B not agree.
     */
    @Override
    public Matrix<T> plus(Matrix<T> b) throws IllegalArgumentException {
        if (!isDimensionsEq(b)) {
            throw new IllegalArgumentException("Matrix dimensions must agree.");
        }

        T firstElement = b.getValue(0, 0);
        Addition<T> addition = OperationDelegateFactory.getAdditionDelegate(firstElement);
        T[][] matrix = ArrayHelpers.createEmptyData(this.getHeight(), this.getWidth());

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                T value = addition.add(this.getValue(row, col), b.getValue(row, col));
                matrix[row][col] = value;
            }
        }

        return new Matrix<>(matrix);
    }

    /**
     * Multiplication of a matrix by a number(scalar value).
     *
     * @param scalar Scalar multiplier
     * @return The new matrix.
     */
    public Matrix<T> scalarMultiplication(T scalar) {
        Multiply<T> multiplyFactory = OperationDelegateFactory.getMultiplyDelegate(scalar);
        T[][] matrix = ArrayHelpers.createEmptyData(this.getHeight(), this.getWidth());

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                T value = multiplyFactory.multiply(this.getValue(row, col), scalar);
                matrix[row][col] = value;
            }
        }

        return new Matrix<>(matrix);
    }

    /**
     * Matrix multiplication(product). Each element of which is equal to the
     * sum of the products of the elements in the corresponding row of the
     * first factor and the column of the second.
     *
     * @param b Secondary(B) matrix.
     * @return Matrix product of multiplication by a (B) matrix.
     * @throws IllegalArgumentException matrix sizes must be transpose equals.
     */
    @Override
    public Matrix<T> product(Matrix<T> b) throws IllegalArgumentException {
        Matrix<T> a = this;
        
        if (a.width != b.height) {
            throw new IllegalArgumentException("For matrix product their sizes must be transpose equals.");
        }

        T[][] matrix = ArrayHelpers.createEmptyData(a.getHeight(), b.getWidth());
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                T value = a.intersectionProduct(b, row, col);
                matrix[row][col] = value;
            }
        }

        return new Matrix<>(matrix);
    }

    /**
     * Sum of the products of the elements in the corresponding row of the
     * first factor and the column of the second(intersection).
     *
     * @param b   Secondary matrix
     * @param row Row index
     * @param col Column index
     * @return Intersection value by row/col element.
     */
    private T intersectionProduct(Matrix<T> b, int row, int col) {
        Matrix<T> a = this;

        T firstElement = b.getValue(0, 0);
        Multiply<T> multiplyFactory = OperationDelegateFactory.getMultiplyDelegate(firstElement);
        Addition<T> additionFactory = OperationDelegateFactory.getAdditionDelegate(firstElement);

        T resultSum = null;
        for (int i = 0; i < a.getWidth(); i++) {
            T aiValue = a.getValue(row, i);
            T biValue = b.getValue(i, col);
            T iterationProduct = multiplyFactory.multiply(aiValue, biValue);

            if (resultSum == null) {
                resultSum = iterationProduct;
            } else {
                resultSum = additionFactory.add(resultSum, iterationProduct);
            }
        }

        return resultSum;
    }

    /**
     * Returns an iterator over rows of a matrix elements of type
     * {@code Collection<T>}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Collection<T>> iterator() {
        return new MatrixIterator();
    }


    /**
     * Value-based iterator. Provide iteration over elements of matrix from
     * left to right columns, over down rows.
     */
    private class MatrixValueIterator implements Iterator<T> {
        /**
         * Number of values in a matrix, product matrix dimension(N x M).
         */
        private final int length;

        /**
         * The index of the current iteration of a matrix.
         */
        private int currentIndex = 0;

        /**
         * Constructs an value-based matrix iterator.
         */
        private MatrixValueIterator() {
            this.length = getWidth() * getHeight();
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return this.currentIndex < this.length;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public T next() {
            int row = this.currentIndex / getWidth();
            int col = this.currentIndex % getWidth();

            this.currentIndex++;

            return getValue(row, col);
        }
    }

    /**
     * Row-based matrix iterator.
     */
    private class MatrixIterator implements Iterator<Collection<T>> {

        /**
         * Base value iterator, that provide iteration over elements of matrix
         * from left to right columns, over down rows.
         */
        private final Iterator<T> valueIterator;

        /**
         * Constructs an row-based matrix iterator.
         */
        private MatrixIterator() {
            this.valueIterator = new MatrixValueIterator();
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return this.valueIterator.hasNext();
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public Collection<T> next() {
            int capacity = getWidth();
            Collection<T> collection = new ArrayList<>(capacity);

            int currentIndex = 0;
            while (currentIndex < capacity) {
                currentIndex++;
                T value = this.valueIterator.next();
                collection.add(value);
            }

            return collection;
        }
    }
}
