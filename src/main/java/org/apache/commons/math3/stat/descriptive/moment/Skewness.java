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
 * Computes the skewness of the available values.
 * <p>
 * We use the following (unbiased) formula to define skewness:</p>
 * <p>
 * skewness = [n / (n -1) (n - 2)] sum[(x_i - mean)^3] / std^3 </p>
 * <p>
 * where n is the number of values, mean is the {@link Mean} and std is the
 * {@link StandardDeviation} </p>
 * <p>
 * Note that this statistic is undefined for n < 3.  <code>Double.Nan</code>
 * is returned when there is not sufficient data to compute the statistic.
 * Double.NaN may also be returned if the input includes NaN and / or
 * infinite values.</p>
 * <p>
 * <strong>Note that this implementation is not synchronized.</strong> If
 * multiple threads access an instance of this class concurrently, and at least
 * one of the threads invokes the <code>increment()</code> or
 * <code>clear()</code> method, it must be synchronized externally. </p>
 */
public class Skewness extends AbstractStorelessUnivariateStatistic implements Serializable {

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = 7101857578996691352L;

    /**
     * Third moment on which this statistic is based
     */
    protected ThirdMoment moment = null;

    /**
     * Determines whether or not this statistic can be incremented or cleared.
     * <p>
     * Statistics based on (constructed from) external moments cannot
     * be incremented or cleared.</p>
     */
    protected boolean incMoment;

    /**
     * Constructs a Skewness
     */
    public Skewness() {
        incMoment = true;
        moment = new ThirdMoment();
    }

    /**
     * Constructs a Skewness with an external moment
     * @param m3 external moment
     */
    public Skewness(final ThirdMoment m3) {
        incMoment = false;
        this.moment = m3;
    }

    /**
     * Copy constructor, creates a new {@code Skewness} identical
     * to the {@code original}
     *
     * @param original the {@code Skewness} instance to copy
     * @throws NullArgumentException if original is null
     */
    public Skewness(Skewness original) throws NullArgumentException {
        copy(original, this);
    }

    /**
     * {@inheritDoc}
     * <p>Note that when {@link #Skewness(ThirdMoment)} is used to
     * create a Skewness, this method does nothing. In that case, the
     * ThirdMoment should be incremented directly.</p>
     */
    @Override
    public void increment(final double d) {
        // STUB: not implemented
    }

    /**
     * Returns the value of the statistic based on the values that have been added.
     * <p>
     * See {@link Skewness} for the definition used in the computation.</p>
     *
     * @return the skewness of the available values.
     */
    @Override
    public double getResult() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public long getN() {
        // STUB: not implemented
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        // STUB: not implemented
    }

    /**
     * Returns the Skewness of the entries in the specifed portion of the
     * input array.
     * <p>
     * See {@link Skewness} for the definition used in the computation.</p>
     * <p>
     * Throws <code>IllegalArgumentException</code> if the array is null.</p>
     *
     * @param values the input array
     * @param begin the index of the first array element to include
     * @param length the number of elements to include
     * @return the skewness of the values or Double.NaN if length is less than
     * 3
     * @throws MathIllegalArgumentException if the array is null or the array index
     *  parameters are not valid
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
    public Skewness copy() {
        // STUB: not implemented
        return null;
    }

    /**
     * Copies source to dest.
     * <p>Neither source nor dest can be null.</p>
     *
     * @param source Skewness to copy
     * @param dest Skewness to copy to
     * @throws NullArgumentException if either source or dest is null
     */
    public static void copy(Skewness source, Skewness dest) throws NullArgumentException {
        // STUB: not implemented
    }
}
