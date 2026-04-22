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
package org.apache.commons.math3.stat.regression;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.InsufficientDataException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.NonSquareMatrixException;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.apache.commons.math3.util.FastMath;

/**
 * Abstract base class for implementations of MultipleLinearRegression.
 * @since 2.0
 */
public abstract class AbstractMultipleLinearRegression implements MultipleLinearRegression {

    /**
     * X sample data.
     */
    private RealMatrix xMatrix;

    /**
     * Y sample data.
     */
    private RealVector yVector;

    /**
     * Whether or not the regression model includes an intercept.  True means no intercept.
     */
    private boolean noIntercept = false;

    /**
     * @return the X sample data.
     */
    protected RealMatrix getX() {
        // STUB: not implemented
        return null;
    }

    /**
     * @return the Y sample data.
     */
    protected RealVector getY() {
        // STUB: not implemented
        return null;
    }

    /**
     * @return true if the model has no intercept term; false otherwise
     * @since 2.2
     */
    public boolean isNoIntercept() {
        // STUB: not implemented
        return false;
    }

    /**
     * @param noIntercept true means the model is to be estimated without an intercept term
     * @since 2.2
     */
    public void setNoIntercept(boolean noIntercept) {
        // STUB: not implemented
    }

    /**
     * <p>Loads model x and y sample data from a flat input array, overriding any previous sample.
     * </p>
     * <p>Assumes that rows are concatenated with y values first in each row.  For example, an input
     * <code>data</code> array containing the sequence of values (1, 2, 3, 4, 5, 6, 7, 8, 9) with
     * <code>nobs = 3</code> and <code>nvars = 2</code> creates a regression dataset with two
     * independent variables, as below:
     * <pre>
     *   y   x[0]  x[1]
     *   --------------
     *   1     2     3
     *   4     5     6
     *   7     8     9
     * </pre>
     * </p>
     * <p>Note that there is no need to add an initial unitary column (column of 1's) when
     * specifying a model including an intercept term.  If {@link #isNoIntercept()} is <code>true</code>,
     * the X matrix will be created without an initial column of "1"s; otherwise this column will
     * be added.
     * </p>
     * <p>Throws IllegalArgumentException if any of the following preconditions fail:
     * <ul><li><code>data</code> cannot be null</li>
     * <li><code>data.length = nobs * (nvars + 1)</li>
     * <li><code>nobs > nvars</code></li></ul>
     * </p>
     *
     * @param data input data array
     * @param nobs number of observations (rows)
     * @param nvars number of independent variables (columns, not counting y)
     * @throws NullArgumentException if the data array is null
     * @throws DimensionMismatchException if the length of the data array is not equal
     * to <code>nobs * (nvars + 1)</code>
     * @throws InsufficientDataException if <code>nobs</code> is less than
     * <code>nvars + 1</code>
     */
    public void newSampleData(double[] data, int nobs, int nvars) {
        // STUB: not implemented
    }

    /**
     * Loads new y sample data, overriding any previous data.
     *
     * @param y the array representing the y sample
     * @throws NullArgumentException if y is null
     * @throws NoDataException if y is empty
     */
    protected void newYSampleData(double[] y) {
        // STUB: not implemented
    }

    /**
     * <p>Loads new x sample data, overriding any previous data.
     * </p>
     * The input <code>x</code> array should have one row for each sample
     * observation, with columns corresponding to independent variables.
     * For example, if <pre>
     * <code> x = new double[][] {{1, 2}, {3, 4}, {5, 6}} </code></pre>
     * then <code>setXSampleData(x) </code> results in a model with two independent
     * variables and 3 observations:
     * <pre>
     *   x[0]  x[1]
     *   ----------
     *     1    2
     *     3    4
     *     5    6
     * </pre>
     * </p>
     * <p>Note that there is no need to add an initial unitary column (column of 1's) when
     * specifying a model including an intercept term.
     * </p>
     * @param x the rectangular array representing the x sample
     * @throws NullArgumentException if x is null
     * @throws NoDataException if x is empty
     * @throws DimensionMismatchException if x is not rectangular
     */
    protected void newXSampleData(double[][] x) {
        // STUB: not implemented
    }

    /**
     * Validates sample data.  Checks that
     * <ul><li>Neither x nor y is null or empty;</li>
     * <li>The length (i.e. number of rows) of x equals the length of y</li>
     * <li>x has at least one more row than it has columns (i.e. there is
     * sufficient data to estimate regression coefficients for each of the
     * columns in x plus an intercept.</li>
     * </ul>
     *
     * @param x the [n,k] array representing the x data
     * @param y the [n,1] array representing the y data
     * @throws NullArgumentException if {@code x} or {@code y} is null
     * @throws DimensionMismatchException if {@code x} and {@code y} do not
     * have the same length
     * @throws NoDataException if {@code x} or {@code y} are zero-length
     * @throws MathIllegalArgumentException if the number of rows of {@code x}
     * is not larger than the number of columns + 1
     */
    protected void validateSampleData(double[][] x, double[] y) throws MathIllegalArgumentException {
        // STUB: not implemented
    }

    /**
     * Validates that the x data and covariance matrix have the same
     * number of rows and that the covariance matrix is square.
     *
     * @param x the [n,k] array representing the x sample
     * @param covariance the [n,n] array representing the covariance matrix
     * @throws DimensionMismatchException if the number of rows in x is not equal
     * to the number of rows in covariance
     * @throws NonSquareMatrixException if the covariance matrix is not square
     */
    protected void validateCovarianceData(double[][] x, double[][] covariance) {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    public double[] estimateRegressionParameters() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public double[] estimateResiduals() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public double[][] estimateRegressionParametersVariance() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public double[] estimateRegressionParametersStandardErrors() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public double estimateRegressandVariance() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Estimates the variance of the error.
     *
     * @return estimate of the error variance
     * @since 2.2
     */
    public double estimateErrorVariance() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Estimates the standard error of the regression.
     *
     * @return regression standard error
     * @since 2.2
     */
    public double estimateRegressionStandardError() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Calculates the beta of multiple linear regression in matrix notation.
     *
     * @return beta
     */
    protected abstract RealVector calculateBeta();

    /**
     * Calculates the beta variance of multiple linear regression in matrix
     * notation.
     *
     * @return beta variance
     */
    protected abstract RealMatrix calculateBetaVariance();

    /**
     * Calculates the variance of the y values.
     *
     * @return Y variance
     */
    protected double calculateYVariance() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * <p>Calculates the variance of the error term.</p>
     * Uses the formula <pre>
     * var(u) = u &middot; u / (n - k)
     * </pre>
     * where n and k are the row and column dimensions of the design
     * matrix X.
     *
     * @return error variance estimate
     * @since 2.2
     */
    protected double calculateErrorVariance() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Calculates the residuals of multiple linear regression in matrix
     * notation.
     *
     * <pre>
     * u = y - X * b
     * </pre>
     *
     * @return The residuals [n,1] matrix
     */
    protected RealVector calculateResiduals() {
        // STUB: not implemented
        return null;
    }
}
