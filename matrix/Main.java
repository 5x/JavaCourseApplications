package matrix;

import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        Matrix<Integer> matrix1 = new MatrixImmutable<>(new Integer[][]{
                {1, 3, 2},
                {1, 0, 0},
                {1, 2, 2}
        });

        Matrix<Integer> matrix2 = new Matrix<>(new Integer[][]{
                {0, 0, 5},
                {7, 5, 0},
                {2, 1, 1}
        });

        printMatrix("Matrix1", matrix1);
        printMatrix("Matrix2", matrix2);

        Matrix<Integer> sum = matrix1.plus(matrix2);
        printMatrix("MatrixSUM(Matrix1+Matrix2)", sum);

        Matrix<Integer> matrixA = new Matrix<>(2, 4, 5);
        Matrix<Integer> matrixB = new Matrix<>(4, 2, 2);
        Matrix<Integer> timesByTwo = matrixA.product(matrixB);
        printMatrix("matrixA", matrixA);
        printMatrix("matrixB", matrixB);
        printMatrix("MatrixPRODUCT(matrixA*matrixB)", timesByTwo);


        Matrix<Double> m1 = new Matrix<>(new Double[][]{
                {1., 0., 2.},
                {-1., 3., 1.}
        });
        Matrix<Double> m2 = new Matrix<>(new Double[][]{
                {3., 1.},
                {2., 1.},
                {1., 0.}
        });

        Matrix<Double> timesMatrix = m1.product(m2);
        printMatrix("m1", m1);
        printMatrix("m2", m2);
        printMatrix("timesMatrix = MatrixPRODUCT(m1*m2)", timesMatrix);

        Matrix<Double> timesScalar = timesMatrix.scalarMultiplication(2.0);
        printMatrix("timesMatrix * Scalar(2.0)", timesScalar);

    }

    private static void printMatrix(String message, Matrix<?> matrix) {
        System.out.println(message);
        
        for (Collection<?> value : matrix) {
            System.out.println(value);
        }

        System.out.println();
    }
}
