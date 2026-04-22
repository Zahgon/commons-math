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
package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Erf;
import org.apache.commons.math3.util.FastMath;

/**
 * Implementation of the normal (gaussian) distribution.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Normal_distribution">Normal distribution (Wikipedia)</a>
 * @see <a href="http://mathworld.wolfram.com/NormalDistribution.html">Normal distribution (MathWorld)</a>
 */
public class NormalDistribution extends AbstractRealDistribution {

    /**
     * Default inverse cumulative probability accuracy.
     * @since 2.1
     */
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1e-9;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 8589540077390120676L;

    /**
     * &radic;(2)
     */
    private static final double SQRT2 = FastMath.sqrt(2.0);

    /**
     * Mean of this distribution.
     */
    private final double mean;

    /**
     * Standard deviation of this distribution.
     */
    private final double standardDeviation;

    /**
     * The value of {@code log(sd) + 0.5*log(2*pi)} stored for faster computation.
     */
    private final double logStandardDeviationPlusHalfLog2Pi;

    /**
     * Inverse cumulative probability accuracy.
     */
    private final double solverAbsoluteAccuracy;

    /**
     * Create a normal distribution with mean equal to zero and standard
     * deviation equal to one.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     */
    public NormalDistribution() {
        this(0, 1);
    }

    /**
     * Create a normal distribution using the given mean and standard deviation.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param mean Mean for this distribution.
     * @param sd Standard deviation for this distribution.
     * @throws NotStrictlyPositiveException if {@code sd <= 0}.
     */
    public NormalDistribution(double mean, double sd) throws NotStrictlyPositiveException {
        this(mean, sd, DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
    }

    /**
     * Create a normal distribution using the given mean, standard deviation and
     * inverse cumulative distribution accuracy.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param mean Mean for this distribution.
     * @param sd Standard deviation for this distribution.
     * @param inverseCumAccuracy Inverse cumulative probability accuracy.
     * @throws NotStrictlyPositiveException if {@code sd <= 0}.
     * @since 2.1
     */
    public NormalDistribution(double mean, double sd, double inverseCumAccuracy) throws NotStrictlyPositiveException {
        this(new Well19937c(), mean, sd, inverseCumAccuracy);
    }

    /**
     * Creates a normal distribution.
     *
     * @param rng Random number generator.
     * @param mean Mean for this distribution.
     * @param sd Standard deviation for this distribution.
     * @throws NotStrictlyPositiveException if {@code sd <= 0}.
     * @since 3.3
     */
    public NormalDistribution(RandomGenerator rng, double mean, double sd) throws NotStrictlyPositiveException {
        this(rng, mean, sd, DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
    }

    /**
     * Creates a normal distribution.
     *
     * @param rng Random number generator.
     * @param mean Mean for this distribution.
     * @param sd Standard deviation for this distribution.
     * @param inverseCumAccuracy Inverse cumulative probability accuracy.
     * @throws NotStrictlyPositiveException if {@code sd <= 0}.
     * @since 3.1
     */
    public NormalDistribution(RandomGenerator rng, double mean, double sd, double inverseCumAccuracy) throws NotStrictlyPositiveException {
        super(rng);
        if (sd <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.STANDARD_DEVIATION, sd);
        }
        this.mean = mean;
        standardDeviation = sd;
        logStandardDeviationPlusHalfLog2Pi = FastMath.log(sd) + 0.5 * FastMath.log(2 * FastMath.PI);
        solverAbsoluteAccuracy = inverseCumAccuracy;
    }

    /**
     * Access the mean.
     *
     * @return the mean for this distribution.
     */
    public double getMean() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Access the standard deviation.
     *
     * @return the standard deviation for this distribution.
     */
    public double getStandardDeviation() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public double density(double x) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double logDensity(double x) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     *
     * If {@code x} is more than 40 standard deviations from the mean, 0 or 1
     * is returned, as in these cases the actual value is within
     * {@code Double.MIN_VALUE} of 0 or 1.
     */
    public double cumulativeProbability(double x) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    @Override
    public double inverseCumulativeProbability(final double p) throws OutOfRangeException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     *
     * @deprecated See {@link RealDistribution#cumulativeProbability(double,double)}
     */
    @Override
    @Deprecated
    public double cumulativeProbability(double x0, double x1) throws NumberIsTooLargeException {
        return probability(x0, x1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double probability(double x0, double x1) throws NumberIsTooLargeException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double getSolverAbsoluteAccuracy() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     *
     * For mean parameter {@code mu}, the mean is {@code mu}.
     */
    public double getNumericalMean() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     *
     * For standard deviation parameter {@code s}, the variance is {@code s^2}.
     */
    public double getNumericalVariance() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     *
     * The lower bound of the support is always negative infinity
     * no matter the parameters.
     *
     * @return lower bound of the support (always
     * {@code Double.NEGATIVE_INFINITY})
     */
    public double getSupportLowerBound() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     *
     * The upper bound of the support is always positive infinity
     * no matter the parameters.
     *
     * @return upper bound of the support (always
     * {@code Double.POSITIVE_INFINITY})
     */
    public double getSupportUpperBound() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSupportLowerBoundInclusive() {
        // STUB: not implemented
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSupportUpperBoundInclusive() {
        // STUB: not implemented
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * The support of this distribution is connected.
     *
     * @return {@code true}
     */
    public boolean isSupportConnected() {
        // STUB: not implemented
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double sample() {
        // STUB: not implemented
        return 0.0;
    }
}
