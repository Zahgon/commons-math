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
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.moment.GeometricMean;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.SecondMoment;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.apache.commons.math3.stat.descriptive.rank.Max;
import org.apache.commons.math3.stat.descriptive.rank.Min;
import org.apache.commons.math3.stat.descriptive.summary.Sum;
import org.apache.commons.math3.stat.descriptive.summary.SumOfLogs;
import org.apache.commons.math3.stat.descriptive.summary.SumOfSquares;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;
import org.apache.commons.math3.util.FastMath;

/**
 * <p>
 * Computes summary statistics for a stream of data values added using the
 * {@link #addValue(double) addValue} method. The data values are not stored in
 * memory, so this class can be used to compute statistics for very large data
 * streams.
 * </p>
 * <p>
 * The {@link StorelessUnivariateStatistic} instances used to maintain summary
 * state and compute statistics are configurable via setters. For example, the
 * default implementation for the variance can be overridden by calling
 * {@link #setVarianceImpl(StorelessUnivariateStatistic)}. Actual parameters to
 * these methods must implement the {@link StorelessUnivariateStatistic}
 * interface and configuration must be completed before <code>addValue</code>
 * is called. No configuration is necessary to use the default, commons-math
 * provided implementations.
 * </p>
 * <p>
 * Note: This class is not thread-safe. Use
 * {@link SynchronizedSummaryStatistics} if concurrent access from multiple
 * threads is required.
 * </p>
 */
public class SummaryStatistics implements StatisticalSummary, Serializable {

    /**
     * Serialization UID
     */
    private static final long serialVersionUID = -2021321786743555871L;

    /**
     * count of values that have been added
     */
    private long n = 0;

    /**
     * SecondMoment is used to compute the mean and variance
     */
    private SecondMoment secondMoment = new SecondMoment();

    /**
     * sum of values that have been added
     */
    private Sum sum = new Sum();

    /**
     * sum of the square of each value that has been added
     */
    private SumOfSquares sumsq = new SumOfSquares();

    /**
     * min of values that have been added
     */
    private Min min = new Min();

    /**
     * max of values that have been added
     */
    private Max max = new Max();

    /**
     * sumLog of values that have been added
     */
    private SumOfLogs sumLog = new SumOfLogs();

    /**
     * geoMean of values that have been added
     */
    private GeometricMean geoMean = new GeometricMean(sumLog);

    /**
     * mean of values that have been added
     */
    private Mean mean = new Mean(secondMoment);

    /**
     * variance of values that have been added
     */
    private Variance variance = new Variance(secondMoment);

    /**
     * Sum statistic implementation - can be reset by setter.
     */
    private StorelessUnivariateStatistic sumImpl = sum;

    /**
     * Sum of squares statistic implementation - can be reset by setter.
     */
    private StorelessUnivariateStatistic sumsqImpl = sumsq;

    /**
     * Minimum statistic implementation - can be reset by setter.
     */
    private StorelessUnivariateStatistic minImpl = min;

    /**
     * Maximum statistic implementation - can be reset by setter.
     */
    private StorelessUnivariateStatistic maxImpl = max;

    /**
     * Sum of log statistic implementation - can be reset by setter.
     */
    private StorelessUnivariateStatistic sumLogImpl = sumLog;

    /**
     * Geometric mean statistic implementation - can be reset by setter.
     */
    private StorelessUnivariateStatistic geoMeanImpl = geoMean;

    /**
     * Mean statistic implementation - can be reset by setter.
     */
    private StorelessUnivariateStatistic meanImpl = mean;

    /**
     * Variance statistic implementation - can be reset by setter.
     */
    private StorelessUnivariateStatistic varianceImpl = variance;

    /**
     * Construct a SummaryStatistics instance
     */
    public SummaryStatistics() {
    }

    /**
     * A copy constructor. Creates a deep-copy of the {@code original}.
     *
     * @param original the {@code SummaryStatistics} instance to copy
     * @throws NullArgumentException if original is null
     */
    public SummaryStatistics(SummaryStatistics original) throws NullArgumentException {
        copy(original, this);
    }

    /**
     * Return a {@link StatisticalSummaryValues} instance reporting current
     * statistics.
     * @return Current values of statistics
     */
    public StatisticalSummary getSummary() {
        // STUB: not implemented
        return null;
    }

    /**
     * Add a value to the data
     * @param value the value to add
     */
    public void addValue(double value) {
        // STUB: not implemented
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
     * Returns the sum of the values that have been added
     * @return The sum or <code>Double.NaN</code> if no values have been added
     */
    public double getSum() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns the sum of the squares of the values that have been added.
     * <p>
     * Double.NaN is returned if no values have been added.
     * </p>
     * @return The sum of squares
     */
    public double getSumsq() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns the mean of the values that have been added.
     * <p>
     * Double.NaN is returned if no values have been added.
     * </p>
     * @return the mean
     */
    public double getMean() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns the standard deviation of the values that have been added.
     * <p>
     * Double.NaN is returned if no values have been added.
     * </p>
     * @return the standard deviation
     */
    public double getStandardDeviation() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns the quadratic mean, a.k.a.
     * <a href="http://mathworld.wolfram.com/Root-Mean-Square.html">
     * root-mean-square</a> of the available values
     * @return The quadratic mean or {@code Double.NaN} if no values
     * have been added.
     */
    public double getQuadraticMean() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns the (sample) variance of the available values.
     *
     * <p>This method returns the bias-corrected sample variance (using {@code n - 1} in
     * the denominator).  Use {@link #getPopulationVariance()} for the non-bias-corrected
     * population variance.</p>
     *
     * <p>Double.NaN is returned if no values have been added.</p>
     *
     * @return the variance
     */
    public double getVariance() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns the <a href="http://en.wikibooks.org/wiki/Statistics/Summary/Variance">
     * population variance</a> of the values that have been added.
     *
     * <p>Double.NaN is returned if no values have been added.</p>
     *
     * @return the population variance
     */
    public double getPopulationVariance() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns the maximum of the values that have been added.
     * <p>
     * Double.NaN is returned if no values have been added.
     * </p>
     * @return the maximum
     */
    public double getMax() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns the minimum of the values that have been added.
     * <p>
     * Double.NaN is returned if no values have been added.
     * </p>
     * @return the minimum
     */
    public double getMin() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns the geometric mean of the values that have been added.
     * <p>
     * Double.NaN is returned if no values have been added.
     * </p>
     * @return the geometric mean
     */
    public double getGeometricMean() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns the sum of the logs of the values that have been added.
     * <p>
     * Double.NaN is returned if no values have been added.
     * </p>
     * @return the sum of logs
     * @since 1.2
     */
    public double getSumOfLogs() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns a statistic related to the Second Central Moment.  Specifically,
     * what is returned is the sum of squared deviations from the sample mean
     * among the values that have been added.
     * <p>
     * Returns <code>Double.NaN</code> if no data values have been added and
     * returns <code>0</code> if there is just one value in the data set.</p>
     * <p>
     * @return second central moment statistic
     * @since 2.0
     */
    public double getSecondMoment() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Generates a text report displaying summary statistics from values that
     * have been added.
     * @return String with line feeds displaying statistics
     * @since 1.2
     */
    @Override
    public String toString() {
        // STUB: not implemented
        return null;
    }

    /**
     * Resets all statistics and storage
     */
    public void clear() {
        // STUB: not implemented
    }

    /**
     * Returns true iff <code>object</code> is a
     * <code>SummaryStatistics</code> instance and all statistics have the
     * same values as this.
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
     * @return hash code
     */
    @Override
    public int hashCode() {
        // STUB: not implemented
        return 0;
    }

    // Getters and setters for statistics implementations
    /**
     * Returns the currently configured Sum implementation
     * @return the StorelessUnivariateStatistic implementing the sum
     * @since 1.2
     */
    public StorelessUnivariateStatistic getSumImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * <p>
     * Sets the implementation for the Sum.
     * </p>
     * <p>
     * This method cannot be activated after data has been added - i.e.,
     * after {@link #addValue(double) addValue} has been used to add data.
     * If it is activated after data has been added, an IllegalStateException
     * will be thrown.
     * </p>
     * @param sumImpl the StorelessUnivariateStatistic instance to use for
     *        computing the Sum
     * @throws MathIllegalStateException if data has already been added (i.e if n >0)
     * @since 1.2
     */
    public void setSumImpl(StorelessUnivariateStatistic sumImpl) throws MathIllegalStateException {
        // STUB: not implemented
    }

    /**
     * Returns the currently configured sum of squares implementation
     * @return the StorelessUnivariateStatistic implementing the sum of squares
     * @since 1.2
     */
    public StorelessUnivariateStatistic getSumsqImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * <p>
     * Sets the implementation for the sum of squares.
     * </p>
     * <p>
     * This method cannot be activated after data has been added - i.e.,
     * after {@link #addValue(double) addValue} has been used to add data.
     * If it is activated after data has been added, an IllegalStateException
     * will be thrown.
     * </p>
     * @param sumsqImpl the StorelessUnivariateStatistic instance to use for
     *        computing the sum of squares
     * @throws MathIllegalStateException if data has already been added (i.e if n > 0)
     * @since 1.2
     */
    public void setSumsqImpl(StorelessUnivariateStatistic sumsqImpl) throws MathIllegalStateException {
        // STUB: not implemented
    }

    /**
     * Returns the currently configured minimum implementation
     * @return the StorelessUnivariateStatistic implementing the minimum
     * @since 1.2
     */
    public StorelessUnivariateStatistic getMinImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * <p>
     * Sets the implementation for the minimum.
     * </p>
     * <p>
     * This method cannot be activated after data has been added - i.e.,
     * after {@link #addValue(double) addValue} has been used to add data.
     * If it is activated after data has been added, an IllegalStateException
     * will be thrown.
     * </p>
     * @param minImpl the StorelessUnivariateStatistic instance to use for
     *        computing the minimum
     * @throws MathIllegalStateException if data has already been added (i.e if n > 0)
     * @since 1.2
     */
    public void setMinImpl(StorelessUnivariateStatistic minImpl) throws MathIllegalStateException {
        // STUB: not implemented
    }

    /**
     * Returns the currently configured maximum implementation
     * @return the StorelessUnivariateStatistic implementing the maximum
     * @since 1.2
     */
    public StorelessUnivariateStatistic getMaxImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * <p>
     * Sets the implementation for the maximum.
     * </p>
     * <p>
     * This method cannot be activated after data has been added - i.e.,
     * after {@link #addValue(double) addValue} has been used to add data.
     * If it is activated after data has been added, an IllegalStateException
     * will be thrown.
     * </p>
     * @param maxImpl the StorelessUnivariateStatistic instance to use for
     *        computing the maximum
     * @throws MathIllegalStateException if data has already been added (i.e if n > 0)
     * @since 1.2
     */
    public void setMaxImpl(StorelessUnivariateStatistic maxImpl) throws MathIllegalStateException {
        // STUB: not implemented
    }

    /**
     * Returns the currently configured sum of logs implementation
     * @return the StorelessUnivariateStatistic implementing the log sum
     * @since 1.2
     */
    public StorelessUnivariateStatistic getSumLogImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * <p>
     * Sets the implementation for the sum of logs.
     * </p>
     * <p>
     * This method cannot be activated after data has been added - i.e.,
     * after {@link #addValue(double) addValue} has been used to add data.
     * If it is activated after data has been added, an IllegalStateException
     * will be thrown.
     * </p>
     * @param sumLogImpl the StorelessUnivariateStatistic instance to use for
     *        computing the log sum
     * @throws MathIllegalStateException if data has already been added (i.e if n > 0)
     * @since 1.2
     */
    public void setSumLogImpl(StorelessUnivariateStatistic sumLogImpl) throws MathIllegalStateException {
        // STUB: not implemented
    }

    /**
     * Returns the currently configured geometric mean implementation
     * @return the StorelessUnivariateStatistic implementing the geometric mean
     * @since 1.2
     */
    public StorelessUnivariateStatistic getGeoMeanImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * <p>
     * Sets the implementation for the geometric mean.
     * </p>
     * <p>
     * This method cannot be activated after data has been added - i.e.,
     * after {@link #addValue(double) addValue} has been used to add data.
     * If it is activated after data has been added, an IllegalStateException
     * will be thrown.
     * </p>
     * @param geoMeanImpl the StorelessUnivariateStatistic instance to use for
     *        computing the geometric mean
     * @throws MathIllegalStateException if data has already been added (i.e if n > 0)
     * @since 1.2
     */
    public void setGeoMeanImpl(StorelessUnivariateStatistic geoMeanImpl) throws MathIllegalStateException {
        // STUB: not implemented
    }

    /**
     * Returns the currently configured mean implementation
     * @return the StorelessUnivariateStatistic implementing the mean
     * @since 1.2
     */
    public StorelessUnivariateStatistic getMeanImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * <p>
     * Sets the implementation for the mean.
     * </p>
     * <p>
     * This method cannot be activated after data has been added - i.e.,
     * after {@link #addValue(double) addValue} has been used to add data.
     * If it is activated after data has been added, an IllegalStateException
     * will be thrown.
     * </p>
     * @param meanImpl the StorelessUnivariateStatistic instance to use for
     *        computing the mean
     * @throws MathIllegalStateException if data has already been added (i.e if n > 0)
     * @since 1.2
     */
    public void setMeanImpl(StorelessUnivariateStatistic meanImpl) throws MathIllegalStateException {
        // STUB: not implemented
    }

    /**
     * Returns the currently configured variance implementation
     * @return the StorelessUnivariateStatistic implementing the variance
     * @since 1.2
     */
    public StorelessUnivariateStatistic getVarianceImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * <p>
     * Sets the implementation for the variance.
     * </p>
     * <p>
     * This method cannot be activated after data has been added - i.e.,
     * after {@link #addValue(double) addValue} has been used to add data.
     * If it is activated after data has been added, an IllegalStateException
     * will be thrown.
     * </p>
     * @param varianceImpl the StorelessUnivariateStatistic instance to use for
     *        computing the variance
     * @throws MathIllegalStateException if data has already been added (i.e if n > 0)
     * @since 1.2
     */
    public void setVarianceImpl(StorelessUnivariateStatistic varianceImpl) throws MathIllegalStateException {
        // STUB: not implemented
    }

    /**
     * Throws IllegalStateException if n > 0.
     * @throws MathIllegalStateException if data has been added
     */
    private void checkEmpty() throws MathIllegalStateException {
        if (n > 0) {
            throw new MathIllegalStateException(LocalizedFormats.VALUES_ADDED_BEFORE_CONFIGURING_STATISTIC, n);
        }
    }

    /**
     * Returns a copy of this SummaryStatistics instance with the same internal state.
     *
     * @return a copy of this
     */
    public SummaryStatistics copy() {
        // STUB: not implemented
        return null;
    }

    /**
     * Copies source to dest.
     * <p>Neither source nor dest can be null.</p>
     *
     * @param source SummaryStatistics to copy
     * @param dest SummaryStatistics to copy to
     * @throws NullArgumentException if either source or dest is null
     */
    public static void copy(SummaryStatistics source, SummaryStatistics dest) throws NullArgumentException {
        // STUB: not implemented
    }
}
