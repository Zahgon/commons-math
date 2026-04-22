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
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;

/**
 * Basic implementation of {@link FieldMatrix} methods regardless of the underlying storage.
 * <p>All the methods implemented here use {@link #getEntry(int, int)} to access
 * matrix elements. Derived class can provide faster implementations. </p>
 *
 * @param <T> Type of the field elements.
 *
 * @since 2.0
 */
public abstract class AbstractFieldMatrix<T extends FieldElement<T>> implements FieldMatrix<T> {

    /**
     * Field to which the elements belong.
     */
    private final Field<T> field;

    /**
     * Constructor for use with Serializable
     */
    protected AbstractFieldMatrix() {
        field = null;
    }

    /**
     * Creates a matrix with no data
     * @param field field to which the elements belong
     */
    protected AbstractFieldMatrix(final Field<T> field) {
        this.field = field;
    }

    /**
     * Create a new FieldMatrix<T> with the supplied row and column dimensions.
     *
     * @param field Field to which the elements belong.
     * @param rowDimension Number of rows in the new matrix.
     * @param columnDimension Number of columns in the new matrix.
     * @throws NotStrictlyPositiveException if row or column dimension is not
     * positive.
     */
    protected AbstractFieldMatrix(final Field<T> field, final int rowDimension, final int columnDimension) throws NotStrictlyPositiveException {
        if (rowDimension <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.DIMENSION, rowDimension);
        }
        if (columnDimension <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.DIMENSION, columnDimension);
        }
        this.field = field;
    }

    /**
     * Get the elements type from an array.
     *
     * @param <T> Type of the field elements.
     * @param d Data array.
     * @return the field to which the array elements belong.
     * @throws NullArgumentException if the array is {@code null}.
     * @throws NoDataException if the array is empty.
     */
    protected static <T extends FieldElement<T>> Field<T> extractField(final T[][] d) throws NoDataException, NullArgumentException {
        // STUB: not implemented
        return null;
    }

    /**
     * Get the elements type from an array.
     *
     * @param <T> Type of the field elements.
     * @param d Data array.
     * @return the field to which the array elements belong.
     * @throws NoDataException if array is empty.
     */
    protected static <T extends FieldElement<T>> Field<T> extractField(final T[] d) throws NoDataException {
        // STUB: not implemented
        return null;
    }

    /**
     * Build an array of elements.
     * <p>
     * Complete arrays are filled with field.getZero()
     * </p>
     * @param <T> Type of the field elements
     * @param field field to which array elements belong
     * @param rows number of rows
     * @param columns number of columns (may be negative to build partial
     * arrays in the same way <code>new Field[rows][]</code> works)
     * @return a new array
     * @deprecated as of 3.2, replaced by {@link MathArrays#buildArray(Field, int, int)}
     */
    @Deprecated
    protected static <T extends FieldElement<T>> T[][] buildArray(final Field<T> field, final int rows, final int columns) {
        return MathArrays.buildArray(field, rows, columns);
    }

    /**
     * Build an array of elements.
     * <p>
     * Arrays are filled with field.getZero()
     * </p>
     * @param <T> the type of the field elements
     * @param field field to which array elements belong
     * @param length of the array
     * @return a new array
     * @deprecated as of 3.2, replaced by {@link MathArrays#buildArray(Field, int)}
     */
    @Deprecated
    protected static <T extends FieldElement<T>> T[] buildArray(final Field<T> field, final int length) {
        return MathArrays.buildArray(field, length);
    }

    /**
     * {@inheritDoc}
     */
    public Field<T> getField() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public abstract FieldMatrix<T> createMatrix(final int rowDimension, final int columnDimension) throws NotStrictlyPositiveException;

    /**
     * {@inheritDoc}
     */
    public abstract FieldMatrix<T> copy();

    /**
     * {@inheritDoc}
     */
    public FieldMatrix<T> add(FieldMatrix<T> m) throws MatrixDimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public FieldMatrix<T> subtract(final FieldMatrix<T> m) throws MatrixDimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public FieldMatrix<T> scalarAdd(final T d) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public FieldMatrix<T> scalarMultiply(final T d) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public FieldMatrix<T> multiply(final FieldMatrix<T> m) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public FieldMatrix<T> preMultiply(final FieldMatrix<T> m) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public FieldMatrix<T> power(final int p) throws NonSquareMatrixException, NotPositiveException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public T[][] getData() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public FieldMatrix<T> getSubMatrix(final int startRow, final int endRow, final int startColumn, final int endColumn) throws NumberIsTooSmallException, OutOfRangeException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public FieldMatrix<T> getSubMatrix(final int[] selectedRows, final int[] selectedColumns) throws NoDataException, NullArgumentException, OutOfRangeException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void copySubMatrix(final int startRow, final int endRow, final int startColumn, final int endColumn, final T[][] destination) throws MatrixDimensionMismatchException, NumberIsTooSmallException, OutOfRangeException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    public void copySubMatrix(int[] selectedRows, int[] selectedColumns, T[][] destination) throws MatrixDimensionMismatchException, NoDataException, NullArgumentException, OutOfRangeException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    public void setSubMatrix(final T[][] subMatrix, final int row, final int column) throws DimensionMismatchException, OutOfRangeException, NoDataException, NullArgumentException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    public FieldMatrix<T> getRowMatrix(final int row) throws OutOfRangeException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void setRowMatrix(final int row, final FieldMatrix<T> matrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    public FieldMatrix<T> getColumnMatrix(final int column) throws OutOfRangeException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void setColumnMatrix(final int column, final FieldMatrix<T> matrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> getRowVector(final int row) throws OutOfRangeException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void setRowVector(final int row, final FieldVector<T> vector) throws OutOfRangeException, MatrixDimensionMismatchException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> getColumnVector(final int column) throws OutOfRangeException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void setColumnVector(final int column, final FieldVector<T> vector) throws OutOfRangeException, MatrixDimensionMismatchException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    public T[] getRow(final int row) throws OutOfRangeException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void setRow(final int row, final T[] array) throws OutOfRangeException, MatrixDimensionMismatchException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    public T[] getColumn(final int column) throws OutOfRangeException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void setColumn(final int column, final T[] array) throws OutOfRangeException, MatrixDimensionMismatchException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    public abstract T getEntry(int row, int column) throws OutOfRangeException;

    /**
     * {@inheritDoc}
     */
    public abstract void setEntry(int row, int column, T value) throws OutOfRangeException;

    /**
     * {@inheritDoc}
     */
    public abstract void addToEntry(int row, int column, T increment) throws OutOfRangeException;

    /**
     * {@inheritDoc}
     */
    public abstract void multiplyEntry(int row, int column, T factor) throws OutOfRangeException;

    /**
     * {@inheritDoc}
     */
    public FieldMatrix<T> transpose() {
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
     * {@inheritDoc}
     */
    public abstract int getRowDimension();

    /**
     * {@inheritDoc}
     */
    public abstract int getColumnDimension();

    /**
     * {@inheritDoc}
     */
    public T getTrace() throws NonSquareMatrixException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public T[] operate(final T[] v) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> operate(final FieldVector<T> v) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public T[] preMultiply(final T[] v) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public FieldVector<T> preMultiply(final FieldVector<T> v) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public T walkInRowOrder(final FieldMatrixChangingVisitor<T> visitor) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public T walkInRowOrder(final FieldMatrixPreservingVisitor<T> visitor) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public T walkInRowOrder(final FieldMatrixChangingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws NumberIsTooSmallException, OutOfRangeException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public T walkInRowOrder(final FieldMatrixPreservingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws NumberIsTooSmallException, OutOfRangeException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public T walkInColumnOrder(final FieldMatrixChangingVisitor<T> visitor) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public T walkInColumnOrder(final FieldMatrixPreservingVisitor<T> visitor) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public T walkInColumnOrder(final FieldMatrixChangingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws NumberIsTooSmallException, OutOfRangeException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public T walkInColumnOrder(final FieldMatrixPreservingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws NumberIsTooSmallException, OutOfRangeException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public T walkInOptimizedOrder(final FieldMatrixChangingVisitor<T> visitor) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public T walkInOptimizedOrder(final FieldMatrixPreservingVisitor<T> visitor) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public T walkInOptimizedOrder(final FieldMatrixChangingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws NumberIsTooSmallException, OutOfRangeException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public T walkInOptimizedOrder(final FieldMatrixPreservingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws NumberIsTooSmallException, OutOfRangeException {
        // STUB: not implemented
        return null;
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
     * <code>FieldMatrix</code> instance with the same dimensions as this
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

    /**
     * Check if a row index is valid.
     *
     * @param row Row index to check.
     * @throws OutOfRangeException if {@code index} is not valid.
     */
    protected void checkRowIndex(final int row) throws OutOfRangeException {
        // STUB: not implemented
    }

    /**
     * Check if a column index is valid.
     *
     * @param column Column index to check.
     * @throws OutOfRangeException if {@code index} is not valid.
     */
    protected void checkColumnIndex(final int column) throws OutOfRangeException {
        // STUB: not implemented
    }

    /**
     * Check if submatrix ranges indices are valid.
     * Rows and columns are indicated counting from 0 to n-1.
     *
     * @param startRow Initial row index.
     * @param endRow Final row index.
     * @param startColumn Initial column index.
     * @param endColumn Final column index.
     * @throws OutOfRangeException if the indices are not valid.
     * @throws NumberIsTooSmallException if {@code endRow < startRow} or
     * {@code endColumn < startColumn}.
     */
    protected void checkSubMatrixIndex(final int startRow, final int endRow, final int startColumn, final int endColumn) throws NumberIsTooSmallException, OutOfRangeException {
        // STUB: not implemented
    }

    /**
     * Check if submatrix ranges indices are valid.
     * Rows and columns are indicated counting from 0 to n-1.
     *
     * @param selectedRows Array of row indices.
     * @param selectedColumns Array of column indices.
     * @throws NullArgumentException if the arrays are {@code null}.
     * @throws NoDataException if the arrays have zero length.
     * @throws OutOfRangeException if row or column selections are not valid.
     */
    protected void checkSubMatrixIndex(final int[] selectedRows, final int[] selectedColumns) throws NoDataException, NullArgumentException, OutOfRangeException {
        // STUB: not implemented
    }

    /**
     * Check if a matrix is addition compatible with the instance.
     *
     * @param m Matrix to check.
     * @throws MatrixDimensionMismatchException if the matrix is not
     * addition-compatible with instance.
     */
    protected void checkAdditionCompatible(final FieldMatrix<T> m) throws MatrixDimensionMismatchException {
        // STUB: not implemented
    }

    /**
     * Check if a matrix is subtraction compatible with the instance.
     *
     * @param m Matrix to check.
     * @throws MatrixDimensionMismatchException if the matrix is not
     * subtraction-compatible with instance.
     */
    protected void checkSubtractionCompatible(final FieldMatrix<T> m) throws MatrixDimensionMismatchException {
        // STUB: not implemented
    }

    /**
     * Check if a matrix is multiplication compatible with the instance.
     *
     * @param m Matrix to check.
     * @throws DimensionMismatchException if the matrix is not
     * multiplication-compatible with instance.
     */
    protected void checkMultiplicationCompatible(final FieldMatrix<T> m) throws DimensionMismatchException {
        // STUB: not implemented
    }
}
