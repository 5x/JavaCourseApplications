package matrix;


public interface IMatrix<T> {

    /**
     * Return the sum of two M by N matrices.
     *
     * @param b Secondary matrix
     * @return The sum of two M by N matrices
     */
    T plus(T b);

    /**
     * Matrix multiplication(product). Each element of which is equal to the
     * sum of the products of the elements in the corresponding row of the
     * first factor and the column of the second.
     *
     * @param b Secondary(B) matrix.
     * @return Matrix product of multiplication by a (B) matrix.
     */
    T product(T b);
}
