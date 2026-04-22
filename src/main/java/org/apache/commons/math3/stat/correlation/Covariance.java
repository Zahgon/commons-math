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
package org.apache.commons.math3.stat.correlation;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.Variance;

/**
 * Computes covariances for pairs of arrays or columns of a matrix.
 *
 * <p>The constructors that take <code>RealMatrix</code> or
 * <code>double[][]</code> arguments generate covariance matrices.  The
 * columns of the input matrices are assumed to represent variable values.</p>
 *
 * <p>The constructor argument <code>biasCorrected</code> determines whether or
 * not computed covariances are bias-corrected.</p>
 *
 * <p>Unbiased covariances are given by the formula</p>
 * <code>cov(X, Y) = &Sigma;[(x<sub>i</sub> - E(X))(y<sub>i</sub> - E(Y))] / (n - 1)</code>
 * where <code>E(X)</code> is the mean of <code>X</code> and <code>E(Y)</code>
 * is the mean of the <code>Y</code> values.
 *
 * <p>Non-bias-corrected estimates use <code>n</code> in place of <code>n - 1</code>
 *
 * @since 2.0
 */
public class Covariance {

    /**
     * covariance matrix
     */
    private final RealMatrix covarianceMatrix;

    /**
     * Create an empty covariance matrix.
     */
    /**
     * Number of observations (length of covariate vectors)
     */
    private final int n;

    /**
     * Create a Covariance with no data
     */
    public Covariance() {
        super();
        covarianceMatrix = null;
        n = 0;
    }

    /**
     * Create a Covariance matrix from a rectangular array
     * whose columns represent covariates.
     *
     * <p>The <code>biasCorrected</code> parameter determines whether or not
     * covariance estimates are bias-corrected.</p>
     *
     * <p>The input array must be rectangular with at least one column
     * and two rows.</p>
     *
     * @param data rectangular array with columns representing covariates
     * @param biasCorrected true means covariances are bias-corrected
     * @throws MathIllegalArgumentException if the input data array is not
     * rectangular with at least two rows and one column.
     * @throws NotStrictlyPositiveException if the input data array is not
     * rectangular with at least one row and one column.
     */
    public Covariance(double[][] data, boolean biasCorrected) throws MathIllegalArgumentException, NotStrictlyPositiveException {
        this(new BlockRealMatrix(data), biasCorrected);
    }

    /**
     * Create a Covariance matrix from a rectangular array
     * whose columns represent covariates.
     *
     * <p>The input array must be rectangular with at least one column
     * and two rows</p>
     *
     * @param data rectangular array with columns representing covariates
     * @throws MathIllegalArgumentException if the input data array is not
     * rectangular with at least two rows and one column.
     * @throws NotStrictlyPositiveException if the input data array is not
     * rectangular with at least one row and one column.
     */
    public Covariance(double[][] data) throws MathIllegalArgumentException, NotStrictlyPositiveException {
        this(data, true);
    }

    /**
     * Create a covariance matrix from a matrix whose columns
     * represent covariates.
     *
     * <p>The <code>biasCorrected</code> parameter determines whether or not
     * covariance estimates are bias-corrected.</p>
     *
     * <p>The matrix must have at least one column and two rows</p>
     *
     * @param matrix matrix with columns representing covariates
     * @param biasCorrected true means covariances are bias-corrected
     * @throws MathIllegalArgumentException if the input matrix does not have
     * at least two rows and one column
     */
    public Covariance(RealMatrix matrix, boolean biasCorrected) throws MathIllegalArgumentException {
        checkSufficientData(matrix);
        n = matrix.getRowDimension();
        covarianceMatrix = computeCovarianceMatrix(matrix, biasCorrected);
    }

    /**
     * Create a covariance matrix from a matrix whose columns
     * represent covariates.
     *
     * <p>The matrix must have at least one column and two rows</p>
     *
     * @param matrix matrix with columns representing covariates
     * @throws MathIllegalArgumentException if the input matrix does not have
     * at least two rows and one column
     */
    public Covariance(RealMatrix matrix) throws MathIllegalArgumentException {
        this(matrix, true);
    }

    /**
     * Returns the covariance matrix
     *
     * @return covariance matrix
     */
    public RealMatrix getCovarianceMatrix() {
        // STUB: not implemented
        return null;
    }

    /**
     * Returns the number of observations (length of covariate vectors)
     *
     * @return number of observations
     */
    public int getN() {
        // STUB: not implemented
        return 0;
    }

    /**
     * Compute a covariance matrix from a matrix whose columns represent
     * covariates.
     * @param matrix input matrix (must have at least one column and two rows)
     * @param biasCorrected determines whether or not covariance estimates are bias-corrected
     * @return covariance matrix
     * @throws MathIllegalArgumentException if the matrix does not contain sufficient data
     */
    protected RealMatrix computeCovarianceMatrix(RealMatrix matrix, boolean biasCorrected) throws MathIllegalArgumentException {
        // STUB: not implemented
        return null;
    }

    /**
     * Create a covariance matrix from a matrix whose columns represent
     * covariates. Covariances are computed using the bias-corrected formula.
     * @param matrix input matrix (must have at least one column and two rows)
     * @return covariance matrix
     * @throws MathIllegalArgumentException if matrix does not contain sufficient data
     * @see #Covariance
     */
    protected RealMatrix computeCovarianceMatrix(RealMatrix matrix) throws MathIllegalArgumentException {
        // STUB: not implemented
        return null;
    }

    /**
     * Compute a covariance matrix from a rectangular array whose columns represent
     * covariates.
     * @param data input array (must have at least one column and two rows)
     * @param biasCorrected determines whether or not covariance estimates are bias-corrected
     * @return covariance matrix
     * @throws MathIllegalArgumentException if the data array does not contain sufficient
     * data
     * @throws NotStrictlyPositiveException if the input data array is not
     * rectangular with at least one row and one column.
     */
    protected RealMatrix computeCovarianceMatrix(double[][] data, boolean biasCorrected) throws MathIllegalArgumentException, NotStrictlyPositiveException {
        // STUB: not implemented
        return null;
    }

    /**
     * Create a covariance matrix from a rectangular array whose columns represent
     * covariates. Covariances are computed using the bias-corrected formula.
     * @param data input array (must have at least one column and two rows)
     * @return covariance matrix
     * @throws MathIllegalArgumentException if the data array does not contain sufficient data
     * @throws NotStrictlyPositiveException if the input data array is not
     * rectangular with at least one row and one column.
     * @see #Covariance
     */
    protected RealMatrix computeCovarianceMatrix(double[][] data) throws MathIllegalArgumentException, NotStrictlyPositiveException {
        // STUB: not implemented
        return null;
    }

    /**
     * Computes the covariance between the two arrays.
     *
     * <p>Array lengths must match and the common length must be at least 2.</p>
     *
     * @param xArray first data array
     * @param yArray second data array
     * @param biasCorrected if true, returned value will be bias-corrected
     * @return returns the covariance for the two arrays
     * @throws  MathIllegalArgumentException if the arrays lengths do not match or
     * there is insufficient data
     */
    public double covariance(final double[] xArray, final double[] yArray, boolean biasCorrected) throws MathIllegalArgumentException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Computes the covariance between the two arrays, using the bias-corrected
     * formula.
     *
     * <p>Array lengths must match and the common length must be at least 2.</p>
     *
     * @param xArray first data array
     * @param yArray second data array
     * @return returns the covariance for the two arrays
     * @throws  MathIllegalArgumentException if the arrays lengths do not match or
     * there is insufficient data
     */
    public double covariance(final double[] xArray, final double[] yArray) throws MathIllegalArgumentException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Throws MathIllegalArgumentException if the matrix does not have at least
     * one column and two rows.
     * @param matrix matrix to check
     * @throws MathIllegalArgumentException if the matrix does not contain sufficient data
     * to compute covariance
     */
    private void checkSufficientData(final RealMatrix matrix) throws MathIllegalArgumentException {
        int nRows = matrix.getRowDimension();
        int nCols = matrix.getColumnDimension();
        if (nRows < 2 || nCols < 1) {
            throw new MathIllegalArgumentException(LocalizedFormats.INSUFFICIENT_ROWS_AND_COLUMNS, nRows, nCols);
        }
    }
}
