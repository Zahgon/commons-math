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
package org.apache.commons.math3.stat.descriptive;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.descriptive.moment.GeometricMean;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance;
import org.apache.commons.math3.stat.descriptive.rank.Max;
import org.apache.commons.math3.stat.descriptive.rank.Min;
import org.apache.commons.math3.stat.descriptive.summary.Sum;
import org.apache.commons.math3.stat.descriptive.summary.SumOfLogs;
import org.apache.commons.math3.stat.descriptive.summary.SumOfSquares;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.Precision;
import org.apache.commons.math3.util.FastMath;

/**
 * <p>Computes summary statistics for a stream of n-tuples added using the
 * {@link #addValue(double[]) addValue} method. The data values are not stored
 * in memory, so this class can be used to compute statistics for very large
 * n-tuple streams.</p>
 *
 * <p>The {@link StorelessUnivariateStatistic} instances used to maintain
 * summary state and compute statistics are configurable via setters.
 * For example, the default implementation for the mean can be overridden by
 * calling {@link #setMeanImpl(StorelessUnivariateStatistic[])}. Actual
 * parameters to these methods must implement the
 * {@link StorelessUnivariateStatistic} interface and configuration must be
 * completed before <code>addValue</code> is called. No configuration is
 * necessary to use the default, commons-math provided implementations.</p>
 *
 * <p>To compute statistics for a stream of n-tuples, construct a
 * MultivariateStatistics instance with dimension n and then use
 * {@link #addValue(double[])} to add n-tuples. The <code>getXxx</code>
 * methods where Xxx is a statistic return an array of <code>double</code>
 * values, where for <code>i = 0,...,n-1</code> the i<sup>th</sup> array element is the
 * value of the given statistic for data range consisting of the i<sup>th</sup> element of
 * each of the input n-tuples.  For example, if <code>addValue</code> is called
 * with actual parameters {0, 1, 2}, then {3, 4, 5} and finally {6, 7, 8},
 * <code>getSum</code> will return a three-element array with values
 * {0+3+6, 1+4+7, 2+5+8}</p>
 *
 * <p>Note: This class is not thread-safe. Use
 * {@link SynchronizedMultivariateSummaryStatistics} if concurrent access from multiple
 * threads is required.</p>
 *
 * @since 1.2
 */
public class MultivariateSummaryStatistics implements StatisticalMultivariateSummary, Serializable {

    /**
     * Serialization UID
     */
    private static final long serialVersionUID = 2271900808994826718L;

    /**
     * Dimension of the data.
     */
    private int k;

    /**
     * Count of values that have been added
     */
    private long n = 0;

    /**
     * Sum statistic implementation - can be reset by setter.
     */
    private StorelessUnivariateStatistic[] sumImpl;

    /**
     * Sum of squares statistic implementation - can be reset by setter.
     */
    private StorelessUnivariateStatistic[] sumSqImpl;

    /**
     * Minimum statistic implementation - can be reset by setter.
     */
    private StorelessUnivariateStatistic[] minImpl;

    /**
     * Maximum statistic implementation - can be reset by setter.
     */
    private StorelessUnivariateStatistic[] maxImpl;

    /**
     * Sum of log statistic implementation - can be reset by setter.
     */
    private StorelessUnivariateStatistic[] sumLogImpl;

    /**
     * Geometric mean statistic implementation - can be reset by setter.
     */
    private StorelessUnivariateStatistic[] geoMeanImpl;

    /**
     * Mean statistic implementation - can be reset by setter.
     */
    private StorelessUnivariateStatistic[] meanImpl;

    /**
     * Covariance statistic implementation - cannot be reset.
     */
    private VectorialCovariance covarianceImpl;

    /**
     * Construct a MultivariateSummaryStatistics instance
     * @param k dimension of the data
     * @param isCovarianceBiasCorrected if true, the unbiased sample
     * covariance is computed, otherwise the biased population covariance
     * is computed
     */
    public MultivariateSummaryStatistics(int k, boolean isCovarianceBiasCorrected) {
        this.k = k;
        sumImpl = new StorelessUnivariateStatistic[k];
        sumSqImpl = new StorelessUnivariateStatistic[k];
        minImpl = new StorelessUnivariateStatistic[k];
        maxImpl = new StorelessUnivariateStatistic[k];
        sumLogImpl = new StorelessUnivariateStatistic[k];
        geoMeanImpl = new StorelessUnivariateStatistic[k];
        meanImpl = new StorelessUnivariateStatistic[k];
        for (int i = 0; i < k; ++i) {
            sumImpl[i] = new Sum();
            sumSqImpl[i] = new SumOfSquares();
            minImpl[i] = new Min();
            maxImpl[i] = new Max();
            sumLogImpl[i] = new SumOfLogs();
            geoMeanImpl[i] = new GeometricMean();
            meanImpl[i] = new Mean();
        }
        covarianceImpl = new VectorialCovariance(k, isCovarianceBiasCorrected);
    }

    /**
     * Add an n-tuple to the data
     *
     * @param value  the n-tuple to add
     * @throws DimensionMismatchException if the length of the array
     * does not match the one used at construction
     */
    public void addValue(double[] value) throws DimensionMismatchException {
        // STUB: not implemented
    }

    /**
     * Returns the dimension of the data
     * @return The dimension of the data
     */
    public int getDimension() {
        // STUB: not implemented
        return 0;
    }

    /**
     * Returns the number of available values
     * @return The number of available values
     */
    public long getN() {
        // STUB: not implemented
        return 0;
    }

    /**
     * Returns an array of the results of a statistic.
     * @param stats univariate statistic array
     * @return results array
     */
    private double[] getResults(StorelessUnivariateStatistic[] stats) {
        double[] results = new double[stats.length];
        for (int i = 0; i < results.length; ++i) {
            results[i] = stats[i].getResult();
        }
        return results;
    }

    /**
     * Returns an array whose i<sup>th</sup> entry is the sum of the
     * i<sup>th</sup> entries of the arrays that have been added using
     * {@link #addValue(double[])}
     *
     * @return the array of component sums
     */
    public double[] getSum() {
        // STUB: not implemented
        return null;
    }

    /**
     * Returns an array whose i<sup>th</sup> entry is the sum of squares of the
     * i<sup>th</sup> entries of the arrays that have been added using
     * {@link #addValue(double[])}
     *
     * @return the array of component sums of squares
     */
    public double[] getSumSq() {
        // STUB: not implemented
        return null;
    }

    /**
     * Returns an array whose i<sup>th</sup> entry is the sum of logs of the
     * i<sup>th</sup> entries of the arrays that have been added using
     * {@link #addValue(double[])}
     *
     * @return the array of component log sums
     */
    public double[] getSumLog() {
        // STUB: not implemented
        return null;
    }

    /**
     * Returns an array whose i<sup>th</sup> entry is the mean of the
     * i<sup>th</sup> entries of the arrays that have been added using
     * {@link #addValue(double[])}
     *
     * @return the array of component means
     */
    public double[] getMean() {
        // STUB: not implemented
        return null;
    }

    /**
     * Returns an array whose i<sup>th</sup> entry is the standard deviation of the
     * i<sup>th</sup> entries of the arrays that have been added using
     * {@link #addValue(double[])}
     *
     * @return the array of component standard deviations
     */
    public double[] getStandardDeviation() {
        // STUB: not implemented
        return null;
    }

    /**
     * Returns the covariance matrix of the values that have been added.
     *
     * @return the covariance matrix
     */
    public RealMatrix getCovariance() {
        // STUB: not implemented
        return null;
    }

    /**
     * Returns an array whose i<sup>th</sup> entry is the maximum of the
     * i<sup>th</sup> entries of the arrays that have been added using
     * {@link #addValue(double[])}
     *
     * @return the array of component maxima
     */
    public double[] getMax() {
        // STUB: not implemented
        return null;
    }

    /**
     * Returns an array whose i<sup>th</sup> entry is the minimum of the
     * i<sup>th</sup> entries of the arrays that have been added using
     * {@link #addValue(double[])}
     *
     * @return the array of component minima
     */
    public double[] getMin() {
        // STUB: not implemented
        return null;
    }

    /**
     * Returns an array whose i<sup>th</sup> entry is the geometric mean of the
     * i<sup>th</sup> entries of the arrays that have been added using
     * {@link #addValue(double[])}
     *
     * @return the array of component geometric means
     */
    public double[] getGeometricMean() {
        // STUB: not implemented
        return null;
    }

    /**
     * Generates a text report displaying
     * summary statistics from values that
     * have been added.
     * @return String with line feeds displaying statistics
     */
    @Override
    public String toString() {
        // STUB: not implemented
        return null;
    }

    /**
     * Append a text representation of an array to a buffer.
     * @param buffer buffer to fill
     * @param data data array
     * @param prefix text prefix
     * @param separator elements separator
     * @param suffix text suffix
     */
    private void append(StringBuilder buffer, double[] data, String prefix, String separator, String suffix) {
        buffer.append(prefix);
        for (int i = 0; i < data.length; ++i) {
            if (i > 0) {
                buffer.append(separator);
            }
            buffer.append(data[i]);
        }
        buffer.append(suffix);
    }

    /**
     * Resets all statistics and storage
     */
    public void clear() {
        // STUB: not implemented
    }

    /**
     * Returns true iff <code>object</code> is a <code>MultivariateSummaryStatistics</code>
     * instance and all statistics have the same values as this.
     * @param object the object to test equality against.
     * @return true if object equals this
     */
    @Override
    public boolean equals(Object object) {
        // STUB: not implemented
        return false;
    }

    /**
     * Returns hash code based on values of statistics
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        // STUB: not implemented
        return 0;
    }

    // Getters and setters for statistics implementations
    /**
     * Sets statistics implementations.
     * @param newImpl new implementations for statistics
     * @param oldImpl old implementations for statistics
     * @throws DimensionMismatchException if the array dimension
     * does not match the one used at construction
     * @throws MathIllegalStateException if data has already been added
     * (i.e. if n > 0)
     */
    private void setImpl(StorelessUnivariateStatistic[] newImpl, StorelessUnivariateStatistic[] oldImpl) throws MathIllegalStateException, DimensionMismatchException {
        checkEmpty();
        checkDimension(newImpl.length);
        System.arraycopy(newImpl, 0, oldImpl, 0, newImpl.length);
    }

    /**
     * Returns the currently configured Sum implementation
     *
     * @return the StorelessUnivariateStatistic implementing the sum
     */
    public StorelessUnivariateStatistic[] getSumImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * <p>Sets the implementation for the Sum.</p>
     * <p>This method must be activated before any data has been added - i.e.,
     * before {@link #addValue(double[]) addValue} has been used to add data;
     * otherwise an IllegalStateException will be thrown.</p>
     *
     * @param sumImpl the StorelessUnivariateStatistic instance to use
     * for computing the Sum
     * @throws DimensionMismatchException if the array dimension
     * does not match the one used at construction
     * @throws MathIllegalStateException if data has already been added
     *  (i.e if n > 0)
     */
    public void setSumImpl(StorelessUnivariateStatistic[] sumImpl) throws MathIllegalStateException, DimensionMismatchException {
        // STUB: not implemented
    }

    /**
     * Returns the currently configured sum of squares implementation
     *
     * @return the StorelessUnivariateStatistic implementing the sum of squares
     */
    public StorelessUnivariateStatistic[] getSumsqImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * <p>Sets the implementation for the sum of squares.</p>
     * <p>This method must be activated before any data has been added - i.e.,
     * before {@link #addValue(double[]) addValue} has been used to add data;
     * otherwise an IllegalStateException will be thrown.</p>
     *
     * @param sumsqImpl the StorelessUnivariateStatistic instance to use
     * for computing the sum of squares
     * @throws DimensionMismatchException if the array dimension
     * does not match the one used at construction
     * @throws MathIllegalStateException if data has already been added
     *  (i.e if n > 0)
     */
    public void setSumsqImpl(StorelessUnivariateStatistic[] sumsqImpl) throws MathIllegalStateException, DimensionMismatchException {
        // STUB: not implemented
    }

    /**
     * Returns the currently configured minimum implementation
     *
     * @return the StorelessUnivariateStatistic implementing the minimum
     */
    public StorelessUnivariateStatistic[] getMinImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * <p>Sets the implementation for the minimum.</p>
     * <p>This method must be activated before any data has been added - i.e.,
     * before {@link #addValue(double[]) addValue} has been used to add data;
     * otherwise an IllegalStateException will be thrown.</p>
     *
     * @param minImpl the StorelessUnivariateStatistic instance to use
     * for computing the minimum
     * @throws DimensionMismatchException if the array dimension
     * does not match the one used at construction
     * @throws MathIllegalStateException if data has already been added
     *  (i.e if n > 0)
     */
    public void setMinImpl(StorelessUnivariateStatistic[] minImpl) throws MathIllegalStateException, DimensionMismatchException {
        // STUB: not implemented
    }

    /**
     * Returns the currently configured maximum implementation
     *
     * @return the StorelessUnivariateStatistic implementing the maximum
     */
    public StorelessUnivariateStatistic[] getMaxImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * <p>Sets the implementation for the maximum.</p>
     * <p>This method must be activated before any data has been added - i.e.,
     * before {@link #addValue(double[]) addValue} has been used to add data;
     * otherwise an IllegalStateException will be thrown.</p>
     *
     * @param maxImpl the StorelessUnivariateStatistic instance to use
     * for computing the maximum
     * @throws DimensionMismatchException if the array dimension
     * does not match the one used at construction
     * @throws MathIllegalStateException if data has already been added
     *  (i.e if n > 0)
     */
    public void setMaxImpl(StorelessUnivariateStatistic[] maxImpl) throws MathIllegalStateException, DimensionMismatchException {
        // STUB: not implemented
    }

    /**
     * Returns the currently configured sum of logs implementation
     *
     * @return the StorelessUnivariateStatistic implementing the log sum
     */
    public StorelessUnivariateStatistic[] getSumLogImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * <p>Sets the implementation for the sum of logs.</p>
     * <p>This method must be activated before any data has been added - i.e.,
     * before {@link #addValue(double[]) addValue} has been used to add data;
     * otherwise an IllegalStateException will be thrown.</p>
     *
     * @param sumLogImpl the StorelessUnivariateStatistic instance to use
     * for computing the log sum
     * @throws DimensionMismatchException if the array dimension
     * does not match the one used at construction
     * @throws MathIllegalStateException if data has already been added
     *  (i.e if n > 0)
     */
    public void setSumLogImpl(StorelessUnivariateStatistic[] sumLogImpl) throws MathIllegalStateException, DimensionMismatchException {
        // STUB: not implemented
    }

    /**
     * Returns the currently configured geometric mean implementation
     *
     * @return the StorelessUnivariateStatistic implementing the geometric mean
     */
    public StorelessUnivariateStatistic[] getGeoMeanImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * <p>Sets the implementation for the geometric mean.</p>
     * <p>This method must be activated before any data has been added - i.e.,
     * before {@link #addValue(double[]) addValue} has been used to add data;
     * otherwise an IllegalStateException will be thrown.</p>
     *
     * @param geoMeanImpl the StorelessUnivariateStatistic instance to use
     * for computing the geometric mean
     * @throws DimensionMismatchException if the array dimension
     * does not match the one used at construction
     * @throws MathIllegalStateException if data has already been added
     *  (i.e if n > 0)
     */
    public void setGeoMeanImpl(StorelessUnivariateStatistic[] geoMeanImpl) throws MathIllegalStateException, DimensionMismatchException {
        // STUB: not implemented
    }

    /**
     * Returns the currently configured mean implementation
     *
     * @return the StorelessUnivariateStatistic implementing the mean
     */
    public StorelessUnivariateStatistic[] getMeanImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * <p>Sets the implementation for the mean.</p>
     * <p>This method must be activated before any data has been added - i.e.,
     * before {@link #addValue(double[]) addValue} has been used to add data;
     * otherwise an IllegalStateException will be thrown.</p>
     *
     * @param meanImpl the StorelessUnivariateStatistic instance to use
     * for computing the mean
     * @throws DimensionMismatchException if the array dimension
     * does not match the one used at construction
     * @throws MathIllegalStateException if data has already been added
     *  (i.e if n > 0)
     */
    public void setMeanImpl(StorelessUnivariateStatistic[] meanImpl) throws MathIllegalStateException, DimensionMismatchException {
        // STUB: not implemented
    }

    /**
     * Throws MathIllegalStateException if the statistic is not empty.
     * @throws MathIllegalStateException if n > 0.
     */
    private void checkEmpty() throws MathIllegalStateException {
        if (n > 0) {
            throw new MathIllegalStateException(LocalizedFormats.VALUES_ADDED_BEFORE_CONFIGURING_STATISTIC, n);
        }
    }

    /**
     * Throws DimensionMismatchException if dimension != k.
     * @param dimension dimension to check
     * @throws DimensionMismatchException if dimension != k
     */
    private void checkDimension(int dimension) throws DimensionMismatchException {
        if (dimension != k) {
            throw new DimensionMismatchException(dimension, k);
        }
    }
}
