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

import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.MathUtils;

/**
 * Implementation of
 * {@link org.apache.commons.math3.stat.descriptive.SummaryStatistics} that
 * is safe to use in a multithreaded environment.  Multiple threads can safely
 * operate on a single instance without causing runtime exceptions due to race
 * conditions.  In effect, this implementation makes modification and access
 * methods atomic operations for a single instance.  That is to say, as one
 * thread is computing a statistic from the instance, no other thread can modify
 * the instance nor compute another statistic.
 *
 * @since 1.2
 */
public class SynchronizedSummaryStatistics extends SummaryStatistics {

    /**
     * Serialization UID
     */
    private static final long serialVersionUID = 1909861009042253704L;

    /**
     * Construct a SynchronizedSummaryStatistics instance
     */
    public SynchronizedSummaryStatistics() {
        super();
    }

    /**
     * A copy constructor. Creates a deep-copy of the {@code original}.
     *
     * @param original the {@code SynchronizedSummaryStatistics} instance to copy
     * @throws NullArgumentException if original is null
     */
    public SynchronizedSummaryStatistics(SynchronizedSummaryStatistics original) throws NullArgumentException {
        copy(original, this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized StatisticalSummary getSummary() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void addValue(double value) {
        // STUB: not implemented
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
    public synchronized double getSum() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized double getSumsq() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized double getMean() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized double getStandardDeviation() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized double getQuadraticMean() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized double getVariance() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized double getPopulationVariance() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized double getMax() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized double getMin() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized double getGeometricMean() {
        // STUB: not implemented
        return 0.0;
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
    public synchronized StorelessUnivariateStatistic getSumImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void setSumImpl(StorelessUnivariateStatistic sumImpl) throws MathIllegalStateException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized StorelessUnivariateStatistic getSumsqImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void setSumsqImpl(StorelessUnivariateStatistic sumsqImpl) throws MathIllegalStateException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized StorelessUnivariateStatistic getMinImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void setMinImpl(StorelessUnivariateStatistic minImpl) throws MathIllegalStateException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized StorelessUnivariateStatistic getMaxImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void setMaxImpl(StorelessUnivariateStatistic maxImpl) throws MathIllegalStateException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized StorelessUnivariateStatistic getSumLogImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void setSumLogImpl(StorelessUnivariateStatistic sumLogImpl) throws MathIllegalStateException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized StorelessUnivariateStatistic getGeoMeanImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void setGeoMeanImpl(StorelessUnivariateStatistic geoMeanImpl) throws MathIllegalStateException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized StorelessUnivariateStatistic getMeanImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void setMeanImpl(StorelessUnivariateStatistic meanImpl) throws MathIllegalStateException {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized StorelessUnivariateStatistic getVarianceImpl() {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void setVarianceImpl(StorelessUnivariateStatistic varianceImpl) throws MathIllegalStateException {
        // STUB: not implemented
    }

    /**
     * Returns a copy of this SynchronizedSummaryStatistics instance with the
     * same internal state.
     *
     * @return a copy of this
     */
    @Override
    public synchronized SynchronizedSummaryStatistics copy() {
        // STUB: not implemented
        return null;
    }

    /**
     * Copies source to dest.
     * <p>Neither source nor dest can be null.</p>
     * <p>Acquires synchronization lock on source, then dest before copying.</p>
     *
     * @param source SynchronizedSummaryStatistics to copy
     * @param dest SynchronizedSummaryStatistics to copy to
     * @throws NullArgumentException if either source or dest is null
     */
    public static void copy(SynchronizedSummaryStatistics source, SynchronizedSummaryStatistics dest) throws NullArgumentException {
        // STUB: not implemented
    }
}
