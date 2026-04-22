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
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.FastMath;

/**
 * Implementation of the Weibull distribution. This implementation uses the
 * two parameter form of the distribution defined by
 * <a href="http://mathworld.wolfram.com/WeibullDistribution.html">
 * Weibull Distribution</a>, equations (1) and (2).
 *
 * @see <a href="http://en.wikipedia.org/wiki/Weibull_distribution">Weibull distribution (Wikipedia)</a>
 * @see <a href="http://mathworld.wolfram.com/WeibullDistribution.html">Weibull distribution (MathWorld)</a>
 * @since 1.1 (changed to concrete class in 3.0)
 */
public class WeibullDistribution extends AbstractRealDistribution {

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
     * The shape parameter.
     */
    private final double shape;

    /**
     * The scale parameter.
     */
    private final double scale;

    /**
     * Inverse cumulative probability accuracy.
     */
    private final double solverAbsoluteAccuracy;

    /**
     * Cached numerical mean
     */
    private double numericalMean = Double.NaN;

    /**
     * Whether or not the numerical mean has been calculated
     */
    private boolean numericalMeanIsCalculated = false;

    /**
     * Cached numerical variance
     */
    private double numericalVariance = Double.NaN;

    /**
     * Whether or not the numerical variance has been calculated
     */
    private boolean numericalVarianceIsCalculated = false;

    /**
     * Create a Weibull distribution with the given shape and scale and a
     * location equal to zero.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param alpha Shape parameter.
     * @param beta Scale parameter.
     * @throws NotStrictlyPositiveException if {@code alpha <= 0} or
     * {@code beta <= 0}.
     */
    public WeibullDistribution(double alpha, double beta) throws NotStrictlyPositiveException {
        this(alpha, beta, DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
    }

    /**
     * Create a Weibull distribution with the given shape, scale and inverse
     * cumulative probability accuracy and a location equal to zero.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param alpha Shape parameter.
     * @param beta Scale parameter.
     * @param inverseCumAccuracy Maximum absolute error in inverse
     * cumulative probability estimates
     * (defaults to {@link #DEFAULT_INVERSE_ABSOLUTE_ACCURACY}).
     * @throws NotStrictlyPositiveException if {@code alpha <= 0} or
     * {@code beta <= 0}.
     * @since 2.1
     */
    public WeibullDistribution(double alpha, double beta, double inverseCumAccuracy) {
        this(new Well19937c(), alpha, beta, inverseCumAccuracy);
    }

    /**
     * Creates a Weibull distribution.
     *
     * @param rng Random number generator.
     * @param alpha Shape parameter.
     * @param beta Scale parameter.
     * @throws NotStrictlyPositiveException if {@code alpha <= 0} or {@code beta <= 0}.
     * @since 3.3
     */
    public WeibullDistribution(RandomGenerator rng, double alpha, double beta) throws NotStrictlyPositiveException {
        this(rng, alpha, beta, DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
    }

    /**
     * Creates a Weibull distribution.
     *
     * @param rng Random number generator.
     * @param alpha Shape parameter.
     * @param beta Scale parameter.
     * @param inverseCumAccuracy Maximum absolute error in inverse
     * cumulative probability estimates
     * (defaults to {@link #DEFAULT_INVERSE_ABSOLUTE_ACCURACY}).
     * @throws NotStrictlyPositiveException if {@code alpha <= 0} or {@code beta <= 0}.
     * @since 3.1
     */
    public WeibullDistribution(RandomGenerator rng, double alpha, double beta, double inverseCumAccuracy) throws NotStrictlyPositiveException {
        super(rng);
        if (alpha <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.SHAPE, alpha);
        }
        if (beta <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.SCALE, beta);
        }
        scale = beta;
        shape = alpha;
        solverAbsoluteAccuracy = inverseCumAccuracy;
    }

    /**
     * Access the shape parameter, {@code alpha}.
     *
     * @return the shape parameter, {@code alpha}.
     */
    public double getShape() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Access the scale parameter, {@code beta}.
     *
     * @return the scale parameter, {@code beta}.
     */
    public double getScale() {
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
     */
    public double cumulativeProbability(double x) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     *
     * Returns {@code 0} when {@code p == 0} and
     * {@code Double.POSITIVE_INFINITY} when {@code p == 1}.
     */
    @Override
    public double inverseCumulativeProbability(double p) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Return the absolute accuracy setting of the solver used to estimate
     * inverse cumulative probabilities.
     *
     * @return the solver absolute accuracy.
     * @since 2.1
     */
    @Override
    protected double getSolverAbsoluteAccuracy() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     *
     * The mean is {@code scale * Gamma(1 + (1 / shape))}, where {@code Gamma()}
     * is the Gamma-function.
     */
    public double getNumericalMean() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * used by {@link #getNumericalMean()}
     *
     * @return the mean of this distribution
     */
    protected double calculateNumericalMean() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     *
     * The variance is {@code scale^2 * Gamma(1 + (2 / shape)) - mean^2}
     * where {@code Gamma()} is the Gamma-function.
     */
    public double getNumericalVariance() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * used by {@link #getNumericalVariance()}
     *
     * @return the variance of this distribution
     */
    protected double calculateNumericalVariance() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     *
     * The lower bound of the support is always 0 no matter the parameters.
     *
     * @return lower bound of the support (always 0)
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
}
