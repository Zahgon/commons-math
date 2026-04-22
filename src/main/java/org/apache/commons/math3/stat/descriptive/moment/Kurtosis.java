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
package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

/**
 * Computes the Kurtosis of the available values.
 * <p>
 * We use the following (unbiased) formula to define kurtosis:</p>
 *  <p>
 *  kurtosis = { [n(n+1) / (n -1)(n - 2)(n-3)] sum[(x_i - mean)^4] / std^4 } - [3(n-1)^2 / (n-2)(n-3)]
 *  </p><p>
 *  where n is the number of values, mean is the {@link Mean} and std is the
 * {@link StandardDeviation}</p>
 * <p>
 *  Note that this statistic is undefined for n < 4.  <code>Double.Nan</code>
 *  is returned when there is not sufficient data to compute the statistic.
 *  Note that Double.NaN may also be returned if the input includes NaN
 *  and / or infinite values.</p>
 * <p>
 * <strong>Note that this implementation is not synchronized.</strong> If
 * multiple threads access an instance of this class concurrently, and at least
 * one of the threads invokes the <code>increment()</code> or
 * <code>clear()</code> method, it must be synchronized externally.</p>
 */
public class Kurtosis extends AbstractStorelessUnivariateStatistic implements Serializable {

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = 2784465764798260919L;

    /**
     * Fourth Moment on which this statistic is based
     */
    protected FourthMoment moment;

    /**
     * Determines whether or not this statistic can be incremented or cleared.
     * <p>
     * Statistics based on (constructed from) external moments cannot
     * be incremented or cleared.</p>
     */
    protected boolean incMoment;

    /**
     * Construct a Kurtosis
     */
    public Kurtosis() {
        incMoment = true;
        moment = new FourthMoment();
    }

    /**
     * Construct a Kurtosis from an external moment
     *
     * @param m4 external Moment
     */
    public Kurtosis(final FourthMoment m4) {
        incMoment = false;
        this.moment = m4;
    }

    /**
     * Copy constructor, creates a new {@code Kurtosis} identical
     * to the {@code original}
     *
     * @param original the {@code Kurtosis} instance to copy
     * @throws NullArgumentException if original is null
     */
    public Kurtosis(Kurtosis original) throws NullArgumentException {
        copy(original, this);
    }

    /**
     * {@inheritDoc}
     * <p>Note that when {@link #Kurtosis(FourthMoment)} is used to
     * create a Variance, this method does nothing. In that case, the
     * FourthMoment should be incremented directly.</p>
     */
    @Override
    public void increment(final double d) {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getResult() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     */
    public long getN() {
        // STUB: not implemented
        return 0;
    }

    /* UnvariateStatistic Approach  */
    /**
     * Returns the kurtosis of the entries in the specified portion of the
     * input array.
     * <p>
     * See {@link Kurtosis} for details on the computing algorithm.</p>
     * <p>
     * Throws <code>IllegalArgumentException</code> if the array is null.</p>
     *
     * @param values the input array
     * @param begin index of the first array element to include
     * @param length the number of elements to include
     * @return the kurtosis of the values or Double.NaN if length is less than 4
     * @throws MathIllegalArgumentException if the input array is null or the array
     * index parameters are not valid
     */
    @Override
    public double evaluate(final double[] values, final int begin, final int length) throws MathIllegalArgumentException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Kurtosis copy() {
        // STUB: not implemented
        return null;
    }

    /**
     * Copies source to dest.
     * <p>Neither source nor dest can be null.</p>
     *
     * @param source Kurtosis to copy
     * @param dest Kurtosis to copy to
     * @throws NullArgumentException if either source or dest is null
     */
    public static void copy(Kurtosis source, Kurtosis dest) throws NullArgumentException {
        // STUB: not implemented
    }
}
