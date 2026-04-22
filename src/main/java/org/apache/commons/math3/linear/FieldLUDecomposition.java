/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.math3.linear;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.MathArrays;

/**
 * Calculates the LUP-decomposition of a square matrix.
 * <p>The LUP-decomposition of a matrix A consists of three matrices
 * L, U and P that satisfy: PA = LU, L is lower triangular, and U is
 * upper triangular and P is a permutation matrix. All matrices are
 * m&times;m.</p>
 * <p>Since {@link FieldElement field elements} do not provide an ordering
 * operator, the permutation matrix is computed here only in order to avoid
 * a zero pivot element, no attempt is done to get the largest pivot
 * element.</p>
 * <p>This class is based on the class with similar name from the
 * <a href="http://math.nist.gov/javanumerics/jama/">JAMA</a> library.</p>
 * <ul>
 *   <li>a {@link #getP() getP} method has been added,</li>
 *   <li>the {@code det} method has been renamed as {@link #getDeterminant()
 *   getDeterminant},</li>
 *   <li>the {@code getDoublePivot} method has been removed (but the int based
 *   {@link #getPivot() getPivot} method has been kept),</li>
 *   <li>the {@code solve} and {@code isNonSingular} methods have been replaced
 *   by a {@link #getSolver() getSolver} method and the equivalent methods
 *   provided by the returned {@link DecompositionSolver}.</li>
 * </ul>
 *
 * @param <T> the type of the field elements
 * @see <a href="http://mathworld.wolfram.com/LUDecomposition.html">MathWorld</a>
 * @see <a href="http://en.wikipedia.org/wiki/LU_decomposition">Wikipedia</a>
 * @since 2.0 (changed to concrete class in 3.0)
 */
public class FieldLUDecomposition<T extends FieldElement<T>> {

    /**
     * Field to which the elements belong.
     */
    private final Field<T> field;

    /**
     * Entries of LU decomposition.
     */
    private T[][] lu;

    /**
     * Pivot permutation associated with LU decomposition.
     */
    private int[] pivot;

    /**
     * Parity of the permutation associated with the LU decomposition.
     */
    private boolean even;

    /**
     * Singularity indicator.
     */
    private boolean singular;

    /**
     * Cached value of L.
     */
    private FieldMatrix<T> cachedL;

    /**
     * Cached value of U.
     */
    private FieldMatrix<T> cachedU;

    /**
     * Cached value of P.
     */
    private FieldMatrix<T> cachedP;

    /**
     * Calculates the LU-decomposition of the given matrix.
     * @param matrix The matrix to decompose.
     * @throws NonSquareMatrixException if matrix is not square
     */
    public FieldLUDecomposition(FieldMatrix<T> matrix) {
        if (!matrix.isSquare()) {
            throw new NonSquareMatrixException(matrix.getRowDimension(), matrix.getColumnDimension());
        }
        final int m = matrix.getColumnDimension();
        field = matrix.getField();
        lu = matrix.getData();
        pivot = new int[m];
        cachedL = null;
        cachedU = null;
        cachedP = null;
        // Initialize permutation array and parity
        for (int row = 0; row < m; row++) {
            pivot[row] = row;
        }
        even = true;
        singular = false;
        // Loop over columns
        for (int col = 0; col < m; col++) {
            T sum = field.getZero();
            // upper
            for (int row = 0; row < col; row++) {
                final T[] luRow = lu[row];
                sum = luRow[col];
                for (int i = 0; i < row; i++) {
                    sum = sum.subtract(luRow[i].multiply(lu[i][col]));
                }
                luRow[col] = sum;
            }
            // lower
            // permutation row
            int nonZero = col;
            for (int row = col; row < m; row++) {
                final T[] luRow = lu[row];
                sum = luRow[col];
                for (int i = 0; i < col; i++) {
                    sum = sum.subtract(luRow[i].multiply(lu[i][col]));
                }
                luRow[col] = sum;
                if (lu[nonZero][col].equals(field.getZero())) {
                    // try to select a better permutation choice
                    ++nonZero;
                }
            }
            // Singularity check
            if (nonZero >= m) {
                singular = true;
                return;
            }
            // Pivot if necessary
            if (nonZero != col) {
                T tmp = field.getZero();
                for (int i = 0; i < m; i++) {
                    tmp = lu[nonZero][i];
                    lu[nonZero][i] = lu[col][i];
                    lu[col][i] = tmp;
                }
                int temp = pivot[nonZero];
                pivot[nonZero] = pivot[col];
                pivot[col] = temp;
                even = !even;
            }
            // Divide the lower elements by the "winning" diagonal elt.
            final T luDiag = lu[col][col];
            for (int row = col + 1; row < m; row++) {
                final T[] luRow = lu[row];
                luRow[col] = luRow[col].divide(luDiag);
            }
        }
    }

    /**
     * Returns the matrix L of the decomposition.
     * <p>L is a lower-triangular matrix</p>
     * @return the L matrix (or null if decomposed matrix is singular)
     */
    public FieldMatrix<T> getL() {
        // STUB: not implemented
        return null;
    }

    /**
     * Returns the matrix U of the decomposition.
     * <p>U is an upper-triangular matrix</p>
     * @return the U matrix (or null if decomposed matrix is singular)
     */
    public FieldMatrix<T> getU() {
        // STUB: not implemented
        return null;
    }

    /**
     * Returns the P rows permutation matrix.
     * <p>P is a sparse matrix with exactly one element set to 1.0 in
     * each row and each column, all other elements being set to 0.0.</p>
     * <p>The positions of the 1 elements are given by the {@link #getPivot()
     * pivot permutation vector}.</p>
     * @return the P rows permutation matrix (or null if decomposed matrix is singular)
     * @see #getPivot()
     */
    public FieldMatrix<T> getP() {
        // STUB: not implemented
        return null;
    }

    /**
     * Returns the pivot permutation vector.
     * @return the pivot permutation vector
     * @see #getP()
     */
    public int[] getPivot() {
        // STUB: not implemented
        return null;
    }

    /**
     * Return the determinant of the matrix.
     * @return determinant of the matrix
     */
    public T getDeterminant() {
        // STUB: not implemented
        return null;
    }

    /**
     * Get a solver for finding the A &times; X = B solution in exact linear sense.
     * @return a solver
     */
    public FieldDecompositionSolver<T> getSolver() {
        // STUB: not implemented
        return null;
    }

    /**
     * Specialized solver.
     * @param <T> the type of the field elements
     */
    private static class Solver<T extends FieldElement<T>> implements FieldDecompositionSolver<T> {

        /**
         * Field to which the elements belong.
         */
        private final Field<T> field;

        /**
         * Entries of LU decomposition.
         */
        private final T[][] lu;

        /**
         * Pivot permutation associated with LU decomposition.
         */
        private final int[] pivot;

        /**
         * Singularity indicator.
         */
        private final boolean singular;

        /**
         * Build a solver from decomposed matrix.
         * @param field field to which the matrix elements belong
         * @param lu entries of LU decomposition
         * @param pivot pivot permutation associated with LU decomposition
         * @param singular singularity indicator
         */
        private Solver(final Field<T> field, final T[][] lu, final int[] pivot, final boolean singular) {
            this.field = field;
            this.lu = lu;
            this.pivot = pivot;
            this.singular = singular;
        }

        /**
         * {@inheritDoc}
         */
        public boolean isNonSingular() {
            // STUB: not implemented
            return false;
        }

        /**
         * {@inheritDoc}
         */
        public FieldVector<T> solve(FieldVector<T> b) {
            // STUB: not implemented
            return null;
        }

        /**
         * Solve the linear equation A &times; X = B.
         * <p>The A matrix is implicit here. It is </p>
         * @param b right-hand side of the equation A &times; X = B
         * @return a vector X such that A &times; X = B
         * @throws DimensionMismatchException if the matrices dimensions do not match.
         * @throws SingularMatrixException if the decomposed matrix is singular.
         */
        public ArrayFieldVector<T> solve(ArrayFieldVector<T> b) {
            // STUB: not implemented
            return null;
        }

        /**
         * {@inheritDoc}
         */
        public FieldMatrix<T> solve(FieldMatrix<T> b) {
            // STUB: not implemented
            return null;
        }

        /**
         * {@inheritDoc}
         */
        public FieldMatrix<T> getInverse() {
            // STUB: not implemented
            return null;
        }
    }
}
