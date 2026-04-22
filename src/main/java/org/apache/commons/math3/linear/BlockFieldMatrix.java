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

import java.io.Serializable;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

/**
 * Cache-friendly implementation of FieldMatrix using a flat arrays to store
 * square blocks of the matrix.
 * <p>
 * This implementation is specially designed to be cache-friendly. Square blocks are
 * stored as small arrays and allow efficient traversal of data both in row major direction
 * and columns major direction, one block at a time. This greatly increases performances
 * for algorithms that use crossed directions loops like multiplication or transposition.
 * </p>
 * <p>
 * The size of square blocks is a static parameter. It may be tuned according to the cache
 * size of the target computer processor. As a rule of thumbs, it should be the largest
 * value that allows three blocks to be simultaneously cached (this is necessary for example
 * for matrix multiplication). The default value is to use 36x36 blocks.
 * </p>
 * <p>
 * The regular blocks represent {@link #BLOCK_SIZE} x {@link #BLOCK_SIZE} squares. Blocks
 * at right hand side and bottom side which may be smaller to fit matrix dimensions. The square
 * blocks are flattened in row major order in single dimension arrays which are therefore
 * {@link #BLOCK_SIZE}<sup>2</sup> elements long for regular blocks. The blocks are themselves
 * organized in row major order.
 * </p>
 * <p>
 * As an example, for a block size of 36x36, a 100x60 matrix would be stored in 6 blocks.
 * Block 0 would be a Field[1296] array holding the upper left 36x36 square, block 1 would be
 * a Field[1296] array holding the upper center 36x36 square, block 2 would be a Field[1008]
 * array holding the upper right 36x28 rectangle, block 3 would be a Field[864] array holding
 * the lower left 24x36 rectangle, block 4 would be a Field[864] array holding the lower center
 * 24x36 rectangle and block 5 would be a Field[672] array holding the lower right 24x28
 * rectangle.
 * </p>
 * <p>
 * The layout complexity overhead versus simple mapping of matrices to java
 * arrays is negligible for small matrices (about 1%). The gain from cache efficiency leads
 * to up to 3-fold improvements for matrices of moderate to large size.
 * </p>
 * @param <T> the type of the field elements
 * @since 2.0
 */
public class BlockFieldMatrix<T extends FieldElement<T>> extends AbstractFieldMatrix<T> implements Serializable {

    /**
     * Block size.
     */
    public static final int BLOCK_SIZE = 36;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = -4602336630143123183L;

    /**
     * Blocks of matrix entries.
     */
    private final T[][] blocks;

    /**
     * Number of rows of the matrix.
     */
    private final int rows;

    /**
     * Number of columns of the matrix.
     */
    private final int columns;

    /**
     * Number of block rows of the matrix.
     */
    private final int blockRows;

    /**
     * Number of block columns of the matrix.
     */
    private final int blockColumns;

    /**
     * Create a new matrix with the supplied row and column dimensions.
     *
     * @param field Field to which the elements belong.
     * @param rows Number of rows in the new matrix.
     * @param columns Number of columns in the new matrix.
     * @throws NotStrictlyPositiveException if row or column dimension is not
     * positive.
     */
    public BlockFieldMatrix(final Field<T> field, final int rows, final int columns) throws NotStrictlyPositiveException {
        super(field, rows, columns);
        this.rows = rows;
        this.columns = columns;
        // number of blocks
        blockRows = (rows + BLOCK_SIZE - 1) / BLOCK_SIZE;
        blockColumns = (columns + BLOCK_SIZE - 1) / BLOCK_SIZE;
        // allocate storage blocks, taking care of smaller ones at right and bottom
        blocks = createBlocksLayout(field, rows, columns);
    }

    /**
     * Create a new dense matrix copying entries from raw layout data.
     * <p>The input array <em>must</em> already be in raw layout.</p>
     * <p>Calling this constructor is equivalent to call:
     * <pre>matrix = new BlockFieldMatrix<T>(getField(), rawData.length, rawData[0].length,
     *                                   toBlocksLayout(rawData), false);</pre>
     * </p>
     *
     * @param rawData Data for the new matrix, in raw layout.
     * @throws DimensionMismatchException if the {@code blockData} shape is
     * inconsistent with block layout.
     * @see #BlockFieldMatrix(int, int, FieldElement[][], boolean)
     */
    public BlockFieldMatrix(final T[][] rawData) throws DimensionMismatchException {
        this(rawData.length, rawData[0].length, toBlocksLayout(rawData), false);
    }

    /**
     * Create a new dense matrix copying entries from block layout data.
     * <p>The input array <em>must</em> already be in blocks layout.</p>
     * @param rows  the number of rows in the new matrix
     * @param columns  the number of columns in the new matrix
     * @param blockData data for new matrix
     * @param copyArray if true, the input array will be copied, otherwise
     * it will be referenced
     *
     * @throws DimensionMismatchException if the {@code blockData} shape is
     * inconsistent with block layout.
     * @throws NotStrictlyPositiveException if row or column dimension is not
     * positive.
     * @see #createBlocksLayout(Field, int, int)
     * @see #toBlocksLayout(FieldElement[][])
     * @see #BlockFieldMatrix(FieldElement[][])
     */
    public BlockFieldMatrix(final int rows, final int columns, final T[][] blockData, final boolean copyArray) throws DimensionMismatchException, NotStrictlyPositiveException {
        super(extractField(blockData), rows, columns);
        this.rows = rows;
        this.columns = columns;
        // number of blocks
        blockRows = (rows + BLOCK_SIZE - 1) / BLOCK_SIZE;
        blockColumns = (columns + BLOCK_SIZE - 1) / BLOCK_SIZE;
        if (copyArray) {
            // allocate storage blocks, taking care of smaller ones at right and bottom
            blocks = MathArrays.buildArray(getField(), blockRows * blockColumns, -1);
        } else {
            // reference existing array
            blocks = blockData;
        }
        int index = 0;
        for (int iBlock = 0; iBlock < blockRows; ++iBlock) {
            final int iHeight = blockHeight(iBlock);
            for (int jBlock = 0; jBlock < blockColumns; ++jBlock, ++index) {
                if (blockData[index].length != iHeight * blockWidth(jBlock)) {
                    throw new DimensionMismatchException(blockData[index].length, iHeight * blockWidth(jBlock));
                }
                if (copyArray) {
                    blocks[index] = blockData[index].clone();
                }
            }
        }
    }

    /**
     * Convert a data array from raw layout to blocks layout.
     * <p>
     * Raw layout is the straightforward layout where element at row i and
     * column j is in array element <code>rawData[i][j]</code>. Blocks layout
     * is the layout used in {@link BlockFieldMatrix} instances, where the matrix
     * is split in square blocks (except at right and bottom side where blocks may
     * be rectangular to fit matrix size) and each block is stored in a flattened
     * one-dimensional array.
     * </p>
     * <p>
     * This method creates an array in blocks layout from an input array in raw layout.
     * It can be used to provide the array argument of the {@link
     * #BlockFieldMatrix(int, int, FieldElement[][], boolean)}
     * constructor.
     * </p>
     * @param <T> Type of the field elements.
     * @param rawData Data array in raw layout.
     * @return a new data array containing the same entries but in blocks layout
     * @throws DimensionMismatchException if {@code rawData} is not rectangular
     *  (not all rows have the same length).
     * @see #createBlocksLayout(Field, int, int)
     * @see #BlockFieldMatrix(int, int, FieldElement[][], boolean)
     */
    public static <T extends FieldElement<T>> T[][] toBlocksLayout(final T[][] rawData) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * Create a data array in blocks layout.
     * <p>
     * This method can be used to create the array argument of the {@link
     * #BlockFieldMatrix(int, int, FieldElement[][], boolean)}
     * constructor.
     * </p>
     * @param <T> Type of the field elements.
     * @param field Field to which the elements belong.
     * @param rows Number of rows in the new matrix.
     * @param columns Number of columns in the new matrix.
     * @return a new data array in blocks layout.
     * @see #toBlocksLayout(FieldElement[][])
     * @see #BlockFieldMatrix(int, int, FieldElement[][], boolean)
     */
    public static <T extends FieldElement<T>> T[][] createBlocksLayout(final Field<T> field, final int rows, final int columns) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FieldMatrix<T> createMatrix(final int rowDimension, final int columnDimension) throws NotStrictlyPositiveException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FieldMatrix<T> copy() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FieldMatrix<T> add(final FieldMatrix<T> m) throws MatrixDimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute the sum of {@code this} and {@code m}.
     *
     * @param m matrix to be added
     * @return {@code this + m}
     * @throws MatrixDimensionMismatchException if {@code m} is not the same
     * size as {@code this}
     */
    public BlockFieldMatrix<T> add(final BlockFieldMatrix<T> m) throws MatrixDimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FieldMatrix<T> subtract(final FieldMatrix<T> m) throws MatrixDimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute {@code this - m}.
     *
     * @param m matrix to be subtracted
     * @return {@code this - m}
     * @throws MatrixDimensionMismatchException if {@code m} is not the same
     * size as {@code this}
     */
    public BlockFieldMatrix<T> subtract(final BlockFieldMatrix<T> m) throws MatrixDimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FieldMatrix<T> scalarAdd(final T d) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FieldMatrix<T> scalarMultiply(final T d) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FieldMatrix<T> multiply(final FieldMatrix<T> m) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * Returns the result of postmultiplying {@code this} by {@code m}.
     *
     * @param m matrix to postmultiply by
     * @return {@code this * m}
     * @throws DimensionMismatchException if the matrices are not compatible.
     */
    public BlockFieldMatrix<T> multiply(BlockFieldMatrix<T> m) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T[][] getData() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FieldMatrix<T> getSubMatrix(final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        // STUB: not implemented
        return null;
    }

    /**
     * Copy a part of a block into another one
     * <p>This method can be called only when the specified part fits in both
     * blocks, no verification is done here.</p>
     * @param srcBlock source block
     * @param srcWidth source block width ({@link #BLOCK_SIZE} or smaller)
     * @param srcStartRow start row in the source block
     * @param srcEndRow end row (exclusive) in the source block
     * @param srcStartColumn start column in the source block
     * @param srcEndColumn end column (exclusive) in the source block
     * @param dstBlock destination block
     * @param dstWidth destination block width ({@link #BLOCK_SIZE} or smaller)
     * @param dstStartRow start row in the destination block
     * @param dstStartColumn start column in the destination block
     */
    private void copyBlockPart(final T[] srcBlock, final int srcWidth, final int srcStartRow, final int srcEndRow, final int srcStartColumn, final int srcEndColumn, final T[] dstBlock, final int dstWidth, final int dstStartRow, final int dstStartColumn) {
        final int length = srcEndColumn - srcStartColumn;
        int srcPos = srcStartRow * srcWidth + srcStartColumn;
        int dstPos = dstStartRow * dstWidth + dstStartColumn;
        for (int srcRow = srcStartRow; srcRow < srcEndRow; ++srcRow) {
            System.arraycopy(srcBlock, srcPos, dstBlock, dstPos, length);
            srcPos += srcWidth;
            dstPos += dstWidth;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSubMatrix(final T[][] subMatrix, final int row, final int column) throws DimensionMismatchException, OutOfRangeException, NoDataException, NullArgumentException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FieldMatrix<T> getRowMatrix(final int row) throws OutOfRangeException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRowMatrix(final int row, final FieldMatrix<T> matrix) throws MatrixDimensionMismatchException, OutOfRangeException {
        // STUB: not implemented
    }

    /**
     * Sets the entries in row number <code>row</code>
     * as a row matrix.  Row indices start at 0.
     *
     * @param row the row to be set
     * @param matrix row matrix (must have one row and the same number of columns
     * as the instance)
     * @throws MatrixDimensionMismatchException if the matrix dimensions do
     * not match one instance row.
     * @throws OutOfRangeException if the specified row index is invalid.
     */
    public void setRowMatrix(final int row, final BlockFieldMatrix<T> matrix) throws MatrixDimensionMismatchException, OutOfRangeException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FieldMatrix<T> getColumnMatrix(final int column) throws OutOfRangeException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setColumnMatrix(final int column, final FieldMatrix<T> matrix) throws MatrixDimensionMismatchException, OutOfRangeException {
        // STUB: not implemented
    }

    /**
     * Sets the entries in column number {@code column}
     * as a column matrix.  Column indices start at 0.
     *
     * @param column Column to be set.
     * @param matrix Column matrix (must have one column and the same number of rows
     * as the instance).
     * @throws MatrixDimensionMismatchException if the matrix dimensions do
     * not match one instance column.
     * @throws OutOfRangeException if the specified column index is invalid.
     */
    void setColumnMatrix(final int column, final BlockFieldMatrix<T> matrix) throws MatrixDimensionMismatchException, OutOfRangeException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FieldVector<T> getRowVector(final int row) throws OutOfRangeException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRowVector(final int row, final FieldVector<T> vector) throws MatrixDimensionMismatchException, OutOfRangeException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FieldVector<T> getColumnVector(final int column) throws OutOfRangeException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setColumnVector(final int column, final FieldVector<T> vector) throws OutOfRangeException, MatrixDimensionMismatchException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T[] getRow(final int row) throws OutOfRangeException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRow(final int row, final T[] array) throws OutOfRangeException, MatrixDimensionMismatchException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T[] getColumn(final int column) throws OutOfRangeException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setColumn(final int column, final T[] array) throws MatrixDimensionMismatchException, OutOfRangeException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getEntry(final int row, final int column) throws OutOfRangeException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEntry(final int row, final int column, final T value) throws OutOfRangeException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToEntry(final int row, final int column, final T increment) throws OutOfRangeException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void multiplyEntry(final int row, final int column, final T factor) throws OutOfRangeException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FieldMatrix<T> transpose() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRowDimension() {
        // STUB: not implemented
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getColumnDimension() {
        // STUB: not implemented
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T[] operate(final T[] v) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T[] preMultiply(final T[] v) throws DimensionMismatchException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T walkInRowOrder(final FieldMatrixChangingVisitor<T> visitor) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T walkInRowOrder(final FieldMatrixPreservingVisitor<T> visitor) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T walkInRowOrder(final FieldMatrixChangingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T walkInRowOrder(final FieldMatrixPreservingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T walkInOptimizedOrder(final FieldMatrixChangingVisitor<T> visitor) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T walkInOptimizedOrder(final FieldMatrixPreservingVisitor<T> visitor) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T walkInOptimizedOrder(final FieldMatrixChangingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T walkInOptimizedOrder(final FieldMatrixPreservingVisitor<T> visitor, final int startRow, final int endRow, final int startColumn, final int endColumn) throws OutOfRangeException, NumberIsTooSmallException {
        // STUB: not implemented
        return null;
    }

    /**
     * Get the height of a block.
     * @param blockRow row index (in block sense) of the block
     * @return height (number of rows) of the block
     */
    private int blockHeight(final int blockRow) {
        return (blockRow == blockRows - 1) ? rows - blockRow * BLOCK_SIZE : BLOCK_SIZE;
    }

    /**
     * Get the width of a block.
     * @param blockColumn column index (in block sense) of the block
     * @return width (number of columns) of the block
     */
    private int blockWidth(final int blockColumn) {
        return (blockColumn == blockColumns - 1) ? columns - blockColumn * BLOCK_SIZE : BLOCK_SIZE;
    }
}
