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
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.moment.GeometricMean;
import org.apache.commons.math3.stat.descriptive.moment.Kurtosis;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.Skewness;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.apache.commons.math3.stat.descriptive.rank.Max;
import org.apache.commons.math3.stat.descriptive.rank.Min;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;
import org.apache.commons.math3.stat.descriptive.summary.Sum;
import org.apache.commons.math3.stat.descriptive.summary.SumOfSquares;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.ResizableDoubleArray;
import org.apache.commons.math3.util.FastMath;

/**
 * Maintains a dataset of values of a single variable and computes descriptive
 * statistics based on stored data. The {@link #getWindowSize() windowSize}
 * property sets a limit on the number of values that can be stored in the
 * dataset.  The default value, INFINITE_WINDOW, puts no limit on the size of
 * the dataset.  This value should be used with caution, as the backing store
 * will grow without bound in this case.  For very large datasets,
 * {@link SummaryStatistics}, which does not store the dataset, should be used
 * instead of this class. If <code>windowSize</code> is not INFINITE_WINDOW and
 * more values are added than can be stored in the dataset, new values are
 * added in a "rolling" manner, with new values replacing the "oldest" values
 * in the dataset.
 *
 * <p>Note: this class is not threadsafe.  Use
 * {@link SynchronizedDescriptiveStatistics} if concurrent access from multiple
 * threads is required.</p>
 */
public class DescriptiveStatistics implements StatisticalSummary, Serializable {

    /**
     * Represents an infinite window size.  When the {@link #getWindowSize()}
     * returns this value, there is no limit to the number of data values
     * that can be stored in the dataset.
     */
    public static final int INFINITE_WINDOW = -1;

    /**
     * Serialization UID
     */
    private static final long serialVersionUID = 4133067267405273064L;

    /**
     * Name of the setQuantile method.
     */
    private static final String SET_QUANTILE_METHOD_NAME = "setQuantile";

    /**
     * hold the window size *
     */
    protected int windowSize = INFINITE_WINDOW;

    /**
     *  Stored data values
     */
    private ResizableDoubleArray eDA = new ResizableDoubleArray();

    /**
     * Mean statistic implementation - can be reset by setter.
     */
    private UnivariateStatistic meanImpl = new Mean();

    /**
     * Geometric mean statistic implementation - can be reset by setter.
     */
    private UnivariateStatistic geometricMeanImpl = new GeometricMean();

    /**
     * Kurtosis statistic implementation - can be reset by setter.
     */
    private UnivariateStatistic kurtosisImpl = new Kurtosis();

    /**
     * Maximum statistic implementation - can be reset by setter.
     */
    private UnivariateStatistic maxImpl = new Max();

    /**
     * Minimum statistic implementation - can be reset by setter.
     */
    private UnivariateStatistic minImpl = new Min();

    /**
     * Percentile statistic implementation - can be reset by setter.
     */
    private UnivariateStatistic percentileImpl = new Percentile();

    /**
     * Skewness statistic implementation - can be reset by setter.
     */
    private UnivariateStatistic skewnessImpl = new Skewness();

    /**
     * Variance statistic implementation - can be reset by setter.
     */
    private UnivariateStatistic varianceImpl = new Variance();

    /**
     * Sum of squares statistic implementation - can be reset by setter.
     */
    private UnivariateStatistic sumsqImpl = new SumOfSquares();

    /**
     * Sum statistic implementation - can be reset by setter.
     */
    private UnivariateStatistic sumImpl = new Sum();

    /**
     * Construct a DescriptiveStatistics instance with an infinite window
     */
    public DescriptiveStatistics() {
    }

    /**
     * Construct a DescriptiveStatistics instance with the specified window
     *
     * @param window the window size.
     * @throws MathIllegalArgumentException if window size is less than 1 but
     * not equal to {@link #INFINITE_WINDOW}
     */
    public DescriptiveStatistics(int window) throws MathIllegalArgumentException {
        setWindowSize(window);
    }

    /**
     * Construct a DescriptiveStatistics instance with an infinite window
     * and the initial data values in double[] initialDoubleArray.
     * If initialDoubleArray is null, then this constructor corresponds to
     * DescriptiveStatistics()
     *
     * @param initialDoubleArray the initial double[].
     */
    public DescriptiveStatistics(double[] initialDoubleArray) {
        if (initialDoubleArray != null) {
            eDA = new ResizableDoubleArray(initialDoubleArray);
        }
    }

    /**
     * Copy constructor.  Construct a new DescriptiveStatistics instance that
     * is a copy of original.
     *
     * @param original DescriptiveStatistics instance to copy
     * @throws NullArgumentException if original is null
     */
    public DescriptiveStatistics(DescriptiveStatistics original) throws NullArgumentException {
        copy(original, this);
    }

    /**
     * Adds the value to the dataset. If the dataset is at the maximum size
     * (i.e., the number of stored elements equals the currently configured
     * windowSize), the first (oldest) element in the dataset is discarded
     * to make room for the new value.
     *
     * @param v the value to be added
     */
    public void addValue(double v) {
        // STUB: not implemented
    }

    /**
     * Removes the most recent value from the dataset.
     *
     * @throws MathIllegalStateException if there are no elements stored
     */
    public void removeMostRecentValue() throws MathIllegalStateException {
        // STUB: not implemented
    }

    /**
     * Replaces the most recently stored value with the given value.
     * There must be at least one element stored to call this method.
     *
     * @param v the value to replace the most recent stored value
     * @return replaced value
     * @throws MathIllegalStateException if there are no elements stored
     */
    public double replaceMostRecentValue(double v) throws MathIllegalStateException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns the <a href="http://www.xycoon.com/arithmetic_mean.htm">
     * arithmetic mean </a> of the available values
     * @return The mean or Double.NaN if no values have been added.
     */
    public double getMean() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns the <a href="http://www.xycoon.com/geometric_mean.htm">
     * geometric mean </a> of the available values.
     * <p>
     * See {@link GeometricMean} for details on the computing algorithm.</p>
     *
     * @return The geometricMean, Double.NaN if no values have been added,
     * or if any negative values have been added.
     */
    public double getGeometricMean() {
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
     * @return The variance, Double.NaN if no values have been added
     * or 0.0 for a single value set.
     */
    public double getVariance() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns the <a href="http://en.wikibooks.org/wiki/Statistics/Summary/Variance">
     * population variance</a> of the available values.
     *
     * @return The population variance, Double.NaN if no values have been added,
     * or 0.0 for a single value set.
     */
    public double getPopulationVariance() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns the standard deviation of the available values.
     * @return The standard deviation, Double.NaN if no values have been added
     * or 0.0 for a single value set.
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
     * Returns the skewness of the available values. Skewness is a
     * measure of the asymmetry of a given distribution.
     *
     * @return The skewness, Double.NaN if less than 3 values have been added.
     */
    public double getSkewness() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns the Kurtosis of the available values. Kurtosis is a
     * measure of the "peakedness" of a distribution.
     *
     * @return The kurtosis, Double.NaN if less than 4 values have been added.
     */
    public double getKurtosis() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns the maximum of the available values
     * @return The max or Double.NaN if no values have been added.
     */
    public double getMax() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns the minimum of the available values
     * @return The min or Double.NaN if no values have been added.
     */
    public double getMin() {
        // STUB: not implemented
        return 0.0;
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
     * Returns the sum of the values that have been added to Univariate.
     * @return The sum or Double.NaN if no values have been added
     */
    public double getSum() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns the sum of the squares of the available values.
     * @return The sum of the squares or Double.NaN if no
     * values have been added.
     */
    public double getSumsq() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Resets all statistics and storage
     */
    public void clear() {
        // STUB: not implemented
    }

    /**
     * Returns the maximum number of values that can be stored in the
     * dataset, or INFINITE_WINDOW (-1) if there is no limit.
     *
     * @return The current window size or -1 if its Infinite.
     */
    public int getWindowSize() {
        // STUB: not implemented
        return 0;
    }

    /**
     * WindowSize controls the number of values that contribute to the
     * reported statistics.  For example, if windowSize is set to 3 and the
     * values {1,2,3,4,5} have been added <strong> in that order</strong> then
     * the <i>available values</i> are {3,4,5} and all reported statistics will
     * be based on these values. If {@code windowSize} is decreased as a result
     * of this call and there are more than the new value of elements in the
     * current dataset, values from the front of the array are discarded to
     * reduce the dataset to {@code windowSize} elements.
     *
     * @param windowSize sets the size of the window.
     * @throws MathIllegalArgumentException if window size is less than 1 but
     * not equal to {@link #INFINITE_WINDOW}
     */
    public void setWindowSize(int windowSize) throws MathIllegalArgumentException {
        // STUB: not implemented
    }

    /**
     * Returns the current set of values in an array of double primitives.
     * The order of addition is preserved.  The returned array is a fresh
     * copy of the underlying data -- i.e., it is not a reference to the
     * stored data.
     *
     * @return returns the current set of numbers in the order in which they
     *         were added to this set
     */
    public double[] getValues() {
        // STUB: not implemented
        return null;
    }

    /**
     * Returns the current set of values in an array of double primitives,
     * sorted in ascending order.  The returned array is a fresh
     * copy of the underlying data -- i.e., it is not a reference to the
     * stored data.
     * @return returns the current set of
     * numbers sorted in ascending order
     */
    public double[] getSortedValues() {
        // STUB: not implemented
        return null;
    }

    /**
     * Returns the element at the specified index
     * @param index The Index of the element
     * @return return the element at the specified index
     */
    public double getElement(int index) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns an estimate for the pth percentile of the stored values.
     * <p>
     * The implementation provided here follows the first estimation procedure presented
     * <a href="http://www.itl.nist.gov/div898/handbook/prc/section2/prc252.htm">here.</a>
     * </p><p>
     * <strong>Preconditions</strong>:<ul>
     * <li><code>0 &lt; p &le; 100</code> (otherwise an
     * <code>MathIllegalArgumentException</code> is thrown)</li>
     * <li>at least one value must be stored (returns <code>Double.NaN
     *     </code> otherwise)</li>
     * </ul></p>
     *
     * @param p the requested percentile (scaled from 0 - 100)
     * @return An estimate for the pth percentile of the stored data
     * @throws MathIllegalStateException if percentile implementation has been
     *  overridden and the supplied implementation does not support setQuantile
     * @throws MathIllegalArgumentException if p is not a valid quantile
     */
    public double getPercentile(double p) throws MathIllegalStateException, MathIllegalArgumentException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Generates a text report displaying univariate statistics from values
     * that have been added.  Each statistic is displayed on a separate
     * line.
     *
     * @return String with line feeds displaying statistics
     */
    @Override
    public String toString() {
        // STUB: not implemented
        return null;
    }

    /**
     * Apply the given statistic to the data associated with this set of statistics.
     * @param stat the statistic to apply
     * @return the computed value of the statistic.
     */
    public double apply(UnivariateStatistic stat) {
        // STUB: not implemented
        return 0.0;
    }

    // Implementation getters and setter
    /**
     * Returns the currently configured mean implementation.
     *
     * @return the UnivariateStatistic implementing the mean
     * @since 1.2
     */
    public synchronized UnivariateStatistic getMeanImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * <p>Sets the implementation for the mean.</p>
     *
     * @param meanImpl the UnivariateStatistic instance to use
     * for computing the mean
     * @since 1.2
     */
    public synchronized void setMeanImpl(UnivariateStatistic meanImpl) {
        // STUB: not implemented
    }

    /**
     * Returns the currently configured geometric mean implementation.
     *
     * @return the UnivariateStatistic implementing the geometric mean
     * @since 1.2
     */
    public synchronized UnivariateStatistic getGeometricMeanImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * <p>Sets the implementation for the gemoetric mean.</p>
     *
     * @param geometricMeanImpl the UnivariateStatistic instance to use
     * for computing the geometric mean
     * @since 1.2
     */
    public synchronized void setGeometricMeanImpl(UnivariateStatistic geometricMeanImpl) {
        // STUB: not implemented
    }

    /**
     * Returns the currently configured kurtosis implementation.
     *
     * @return the UnivariateStatistic implementing the kurtosis
     * @since 1.2
     */
    public synchronized UnivariateStatistic getKurtosisImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * <p>Sets the implementation for the kurtosis.</p>
     *
     * @param kurtosisImpl the UnivariateStatistic instance to use
     * for computing the kurtosis
     * @since 1.2
     */
    public synchronized void setKurtosisImpl(UnivariateStatistic kurtosisImpl) {
        // STUB: not implemented
    }

    /**
     * Returns the currently configured maximum implementation.
     *
     * @return the UnivariateStatistic implementing the maximum
     * @since 1.2
     */
    public synchronized UnivariateStatistic getMaxImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * <p>Sets the implementation for the maximum.</p>
     *
     * @param maxImpl the UnivariateStatistic instance to use
     * for computing the maximum
     * @since 1.2
     */
    public synchronized void setMaxImpl(UnivariateStatistic maxImpl) {
        // STUB: not implemented
    }

    /**
     * Returns the currently configured minimum implementation.
     *
     * @return the UnivariateStatistic implementing the minimum
     * @since 1.2
     */
    public synchronized UnivariateStatistic getMinImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * <p>Sets the implementation for the minimum.</p>
     *
     * @param minImpl the UnivariateStatistic instance to use
     * for computing the minimum
     * @since 1.2
     */
    public synchronized void setMinImpl(UnivariateStatistic minImpl) {
        // STUB: not implemented
    }

    /**
     * Returns the currently configured percentile implementation.
     *
     * @return the UnivariateStatistic implementing the percentile
     * @since 1.2
     */
    public synchronized UnivariateStatistic getPercentileImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * Sets the implementation to be used by {@link #getPercentile(double)}.
     * The supplied <code>UnivariateStatistic</code> must provide a
     * <code>setQuantile(double)</code> method; otherwise
     * <code>IllegalArgumentException</code> is thrown.
     *
     * @param percentileImpl the percentileImpl to set
     * @throws MathIllegalArgumentException if the supplied implementation does not
     *  provide a <code>setQuantile</code> method
     * @since 1.2
     */
    public synchronized void setPercentileImpl(UnivariateStatistic percentileImpl) throws MathIllegalArgumentException {
        // STUB: not implemented
    }

    /**
     * Returns the currently configured skewness implementation.
     *
     * @return the UnivariateStatistic implementing the skewness
     * @since 1.2
     */
    public synchronized UnivariateStatistic getSkewnessImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * <p>Sets the implementation for the skewness.</p>
     *
     * @param skewnessImpl the UnivariateStatistic instance to use
     * for computing the skewness
     * @since 1.2
     */
    public synchronized void setSkewnessImpl(UnivariateStatistic skewnessImpl) {
        // STUB: not implemented
    }

    /**
     * Returns the currently configured variance implementation.
     *
     * @return the UnivariateStatistic implementing the variance
     * @since 1.2
     */
    public synchronized UnivariateStatistic getVarianceImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * <p>Sets the implementation for the variance.</p>
     *
     * @param varianceImpl the UnivariateStatistic instance to use
     * for computing the variance
     * @since 1.2
     */
    public synchronized void setVarianceImpl(UnivariateStatistic varianceImpl) {
        // STUB: not implemented
    }

    /**
     * Returns the currently configured sum of squares implementation.
     *
     * @return the UnivariateStatistic implementing the sum of squares
     * @since 1.2
     */
    public synchronized UnivariateStatistic getSumsqImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * <p>Sets the implementation for the sum of squares.</p>
     *
     * @param sumsqImpl the UnivariateStatistic instance to use
     * for computing the sum of squares
     * @since 1.2
     */
    public synchronized void setSumsqImpl(UnivariateStatistic sumsqImpl) {
        // STUB: not implemented
    }

    /**
     * Returns the currently configured sum implementation.
     *
     * @return the UnivariateStatistic implementing the sum
     * @since 1.2
     */
    public synchronized UnivariateStatistic getSumImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * <p>Sets the implementation for the sum.</p>
     *
     * @param sumImpl the UnivariateStatistic instance to use
     * for computing the sum
     * @since 1.2
     */
    public synchronized void setSumImpl(UnivariateStatistic sumImpl) {
        // STUB: not implemented
    }

    /**
     * Returns a copy of this DescriptiveStatistics instance with the same internal state.
     *
     * @return a copy of this
     */
    public DescriptiveStatistics copy() {
        // STUB: not implemented
        return null;
    }

    /**
     * Copies source to dest.
     * <p>Neither source nor dest can be null.</p>
     *
     * @param source DescriptiveStatistics to copy
     * @param dest DescriptiveStatistics to copy to
     * @throws NullArgumentException if either source or dest is null
     */
    public static void copy(DescriptiveStatistics source, DescriptiveStatistics dest) throws NullArgumentException {
        // STUB: not implemented
    }
}
