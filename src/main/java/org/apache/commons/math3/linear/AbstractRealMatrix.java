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

import java.util.ArrayList;
import java.util.Locale;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.FastMath;

/**
 * Basic implementation of RealMatrix methods regardless of the underlying storage.
 * <p>All the methods implemented here use {@link #getEntry(int, int)} to access
 * matrix elements. Derived class can provide faster implementations.</p>
 *
 * @since 2.0
 */
public abstract class AbstractRealMatrix extends RealLinearOperator implements RealMatrix {

    /**
     * Default format.
     */
    private static final RealMatrixFormat DEFAULT_FORMAT = RealMatrixFormat.getInstance(Locale.US);

    static {
        // set the minimum fraction digits to 1 to keep compatibility
        DEFAULT_FORMAT.getFormat().setMinimumFractionDigits(1);
    }

    /**
     * Creates a matrix with no data
     */
    protected AbstractRealMatrix() {
    }

    /**
     * Create a new RealMatrix with the supplied row and column dimensions.
     *
     * @param rowDimension  the number of rows in the new matrix
     * @param columnDimension  the number of columns in the new matrix
     * @throws NotStrictlyPositiveException if row or column dimension is not positive
     */
    protected AbstractRealMatrix(final int rowDimension, final int columnDimension) throws NotStrictlyPositiveException {
        if (rowDimension < 1) {
            throw new NotStrictlyPositiveException(rowDimension);
        }
        if (columnDimension < 1) {
            throw new NotStrictlyPositiveException(columnDimension);
        }
    }

    /**
     * {@inheritDoc}
     */
    public RealMatrix add(RealMatrix m) throws MatrixDimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public RealMatrix subtract(final RealMatrix m) throws MatrixDimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public RealMatrix scalarAdd(final double d) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public RealMatrix scalarMultiply(final double d) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public RealMatrix multiply(final RealMatrix m) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public RealMatrix preMultiply(final RealMatrix m) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public RealMatrix power(final int p) throws NotPositiveException, NonSquareMatrixException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public double[][] getData() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public double getNorm() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public double getFrobeniusNorm() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public RealMatrix getSubMatrix(final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public RealMatrix getSubMatrix(final int[] selectedRows, final int[] selectedColumns) throws NullArgumentException, NoDataException, OutOfRangeException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void copySubMatrix(final int startRow, final int endRow, final int startColumn, final int endColumn, final double[][] destination) throws OutOfRangeException, NumberIsTooSmallException, MatrixDimensionMismatchException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    public void copySubMatrix(int[] selectedRows, int[] selectedColumns, double[][] destination) throws OutOfRangeException, NullArgumentException, NoDataException, MatrixDimensionMismatchException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    public void setSubMatrix(final double[][] subMatrix, final int row, final int column) throws NoDataException, OutOfRangeException, DimensionMismatchException, NullArgumentException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    public RealMatrix getRowMatrix(final int row) throws OutOfRangeException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void setRowMatrix(final int row, final RealMatrix matrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    public RealMatrix getColumnMatrix(final int column) throws OutOfRangeException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void setColumnMatrix(final int column, final RealMatrix matrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    public RealVector getRowVector(final int row) throws OutOfRangeException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void setRowVector(final int row, final RealVector vector) throws OutOfRangeException, MatrixDimensionMismatchException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    public RealVector getColumnVector(final int column) throws OutOfRangeException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void setColumnVector(final int column, final RealVector vector) throws OutOfRangeException, MatrixDimensionMismatchException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    public double[] getRow(final int row) throws OutOfRangeException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void setRow(final int row, final double[] array) throws OutOfRangeException, MatrixDimensionMismatchException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    public double[] getColumn(final int column) throws OutOfRangeException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void setColumn(final int column, final double[] array) throws OutOfRangeException, MatrixDimensionMismatchException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    public void addToEntry(int row, int column, double increment) throws OutOfRangeException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    public void multiplyEntry(int row, int column, double factor) throws OutOfRangeException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    public RealMatrix transpose() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSquare() {
        // STUB: not implemented
        return false;
    }

    /**
     * Returns the number of rows of this matrix.
     *
     * @return the number of rows.
     */
    @Override
    public abstract int getRowDimension();

    /**
     * Returns the number of columns of this matrix.
     *
     * @return the number of columns.
     */
    @Override
    public abstract int getColumnDimension();

    /**
     * {@inheritDoc}
     */
    public double getTrace() throws NonSquareMatrixException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public double[] operate(final double[] v) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RealVector operate(final RealVector v) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public double[] preMultiply(final double[] v) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public RealVector preMultiply(final RealVector v) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public double walkInRowOrder(final RealMatrixChangingVisitor visitor) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public double walkInRowOrder(final RealMatrixPreservingVisitor visitor) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public double walkInRowOrder(final RealMatrixChangingVisitor visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public double walkInRowOrder(final RealMatrixPreservingVisitor visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public double walkInColumnOrder(final RealMatrixChangingVisitor visitor) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public double walkInColumnOrder(final RealMatrixPreservingVisitor visitor) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public double walkInColumnOrder(final RealMatrixChangingVisitor visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public double walkInColumnOrder(final RealMatrixPreservingVisitor visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public double walkInOptimizedOrder(final RealMatrixChangingVisitor visitor) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public double walkInOptimizedOrder(final RealMatrixPreservingVisitor visitor) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public double walkInOptimizedOrder(final RealMatrixChangingVisitor visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public double walkInOptimizedOrder(final RealMatrixPreservingVisitor visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Get a string representation for this matrix.
     * @return a string representation for this matrix
     */
    @Override
    public String toString() {
        // STUB: not implemented
        return null;
    }

    /**
     * Returns true iff <code>object</code> is a
     * <code>RealMatrix</code> instance with the same dimensions as this
     * and all corresponding matrix entries are equal.
     *
     * @param object the object to test equality against.
     * @return true if object equals this
     */
    @Override
    public boolean equals(final Object object) {
        // STUB: not implemented
        return false;
    }

    /**
     * Computes a hashcode for the matrix.
     *
     * @return hashcode for matrix
     */
    @Override
    public int hashCode() {
        // STUB: not implemented
        return 0;
    }

    /*
     * Empty implementations of these methods are provided in order to allow for
     * the use of the @Override tag with Java 1.5.
     */
    /**
     * {@inheritDoc}
     */
    public abstract RealMatrix createMatrix(int rowDimension, int columnDimension) throws NotStrictlyPositiveException;

    /**
     * {@inheritDoc}
     */
    public abstract RealMatrix copy();

    /**
     * {@inheritDoc}
     */
    public abstract double getEntry(int row, int column) throws OutOfRangeException;

    /**
     * {@inheritDoc}
     */
    public abstract void setEntry(int row, int column, double value) throws OutOfRangeException;
}
