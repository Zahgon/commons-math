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

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.linear.RealMatrix;

/**
 * Implementation of
 * {@link org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics} that
 * is safe to use in a multithreaded environment.  Multiple threads can safely
 * operate on a single instance without causing runtime exceptions due to race
 * conditions.  In effect, this implementation makes modification and access
 * methods atomic operations for a single instance.  That is to say, as one
 * thread is computing a statistic from the instance, no other thread can modify
 * the instance nor compute another statistic.
 * @since 1.2
 */
public class SynchronizedMultivariateSummaryStatistics extends MultivariateSummaryStatistics {

    /**
     * Serialization UID
     */
    private static final long serialVersionUID = 7099834153347155363L;

    /**
     * Construct a SynchronizedMultivariateSummaryStatistics instance
     * @param k dimension of the data
     * @param isCovarianceBiasCorrected if true, the unbiased sample
     * covariance is computed, otherwise the biased population covariance
     * is computed
     */
    public SynchronizedMultivariateSummaryStatistics(int k, boolean isCovarianceBiasCorrected) {
        super(k, isCovarianceBiasCorrected);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void addValue(double[] value) throws DimensionMismatchException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized int getDimension() {
        // STUB: not implemented
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized long getN() {
        // STUB: not implemented
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized double[] getSum() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized double[] getSumSq() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized double[] getSumLog() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized double[] getMean() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized double[] getStandardDeviation() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized RealMatrix getCovariance() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized double[] getMax() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized double[] getMin() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized double[] getGeometricMean() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized String toString() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void clear() {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized boolean equals(Object object) {
        // STUB: not implemented
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized int hashCode() {
        // STUB: not implemented
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized StorelessUnivariateStatistic[] getSumImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void setSumImpl(StorelessUnivariateStatistic[] sumImpl) throws DimensionMismatchException, MathIllegalStateException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized StorelessUnivariateStatistic[] getSumsqImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void setSumsqImpl(StorelessUnivariateStatistic[] sumsqImpl) throws DimensionMismatchException, MathIllegalStateException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized StorelessUnivariateStatistic[] getMinImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void setMinImpl(StorelessUnivariateStatistic[] minImpl) throws DimensionMismatchException, MathIllegalStateException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized StorelessUnivariateStatistic[] getMaxImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void setMaxImpl(StorelessUnivariateStatistic[] maxImpl) throws DimensionMismatchException, MathIllegalStateException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized StorelessUnivariateStatistic[] getSumLogImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void setSumLogImpl(StorelessUnivariateStatistic[] sumLogImpl) throws DimensionMismatchException, MathIllegalStateException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized StorelessUnivariateStatistic[] getGeoMeanImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void setGeoMeanImpl(StorelessUnivariateStatistic[] geoMeanImpl) throws DimensionMismatchException, MathIllegalStateException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized StorelessUnivariateStatistic[] getMeanImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void setMeanImpl(StorelessUnivariateStatistic[] meanImpl) throws DimensionMismatchException, MathIllegalStateException {
        // STUB: not implemented
    }
}
