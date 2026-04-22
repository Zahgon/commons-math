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

import java.io.Serializable;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;

/**
 * Base class for probability distributions on the reals.
 * Default implementations are provided for some of the methods
 * that do not vary from distribution to distribution.
 *
 * @since 3.0
 */
public abstract class AbstractRealDistribution implements RealDistribution, Serializable {

    /**
     * Default accuracy.
     */
    public static final double SOLVER_DEFAULT_ABSOLUTE_ACCURACY = 1e-6;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = -38038050983108802L;

    /**
     * RandomData instance used to generate samples from the distribution.
     * @deprecated As of 3.1, to be removed in 4.0. Please use the
     * {@link #random} instance variable instead.
     */
    @Deprecated
    protected org.apache.commons.math3.random.RandomDataImpl randomData = new org.apache.commons.math3.random.RandomDataImpl();

    /**
     * RNG instance used to generate samples from the distribution.
     * @since 3.1
     */
    protected final RandomGenerator random;

    /**
     * Solver absolute accuracy for inverse cumulative computation
     */
    private double solverAbsoluteAccuracy = SOLVER_DEFAULT_ABSOLUTE_ACCURACY;

    /**
     * @deprecated As of 3.1, to be removed in 4.0. Please use
     * {@link #AbstractRealDistribution(RandomGenerator)} instead.
     */
    @Deprecated
    protected AbstractRealDistribution() {
        // Legacy users are only allowed to access the deprecated "randomData".
        // New users are forbidden to use this constructor.
        random = null;
    }

    /**
     * @param rng Random number generator.
     * @since 3.1
     */
    protected AbstractRealDistribution(RandomGenerator rng) {
        random = rng;
    }

    /**
     * {@inheritDoc}
     *
     * The default implementation uses the identity
     * <p>{@code P(x0 < X <= x1) = P(X <= x1) - P(X <= x0)}</p>
     *
     * @deprecated As of 3.1 (to be removed in 4.0). Please use
     * {@link #probability(double,double)} instead.
     */
    @Deprecated
    public double cumulativeProbability(double x0, double x1) throws NumberIsTooLargeException {
        return probability(x0, x1);
    }

    /**
     * For a random variable {@code X} whose values are distributed according
     * to this distribution, this method returns {@code P(x0 < X <= x1)}.
     *
     * @param x0 Lower bound (excluded).
     * @param x1 Upper bound (included).
     * @return the probability that a random variable with this distribution
     * takes a value between {@code x0} and {@code x1}, excluding the lower
     * and including the upper endpoint.
     * @throws NumberIsTooLargeException if {@code x0 > x1}.
     *
     * The default implementation uses the identity
     * {@code P(x0 < X <= x1) = P(X <= x1) - P(X <= x0)}
     *
     * @since 3.1
     */
    public double probability(double x0, double x1) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     *
     * The default implementation returns
     * <ul>
     * <li>{@link #getSupportLowerBound()} for {@code p = 0},</li>
     * <li>{@link #getSupportUpperBound()} for {@code p = 1}.</li>
     * </ul>
     */
    public double inverseCumulativeProbability(final double p) throws OutOfRangeException {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns the solver absolute accuracy for inverse cumulative computation.
     * You can override this method in order to use a Brent solver with an
     * absolute accuracy different from the default.
     *
     * @return the maximum absolute error in inverse cumulative probability estimates
     */
    protected double getSolverAbsoluteAccuracy() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    public void reseedRandomGenerator(long seed) {
        // STUB: not implemented
    }

    /**
     * {@inheritDoc}
     *
     * The default implementation uses the
     * <a href="http://en.wikipedia.org/wiki/Inverse_transform_sampling">
     * inversion method.
     * </a>
     */
    public double sample() {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * {@inheritDoc}
     *
     * The default implementation generates the sample by calling
     * {@link #sample()} in a loop.
     */
    public double[] sample(int sampleSize) {
        // STUB: not implemented
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @return zero.
     * @since 3.1
     */
    public double probability(double x) {
        // STUB: not implemented
        return 0.0;
    }

    /**
     * Returns the natural logarithm of the probability density function (PDF) of this distribution
     * evaluated at the specified point {@code x}. In general, the PDF is the derivative of the
     * {@link #cumulativeProbability(double) CDF}. If the derivative does not exist at {@code x},
     * then an appropriate replacement should be returned, e.g. {@code Double.POSITIVE_INFINITY},
     * {@code Double.NaN}, or the limit inferior or limit superior of the difference quotient. Note
     * that due to the floating point precision and under/overflow issues, this method will for some
     * distributions be more precise and faster than computing the logarithm of
     * {@link #density(double)}. The default implementation simply computes the logarithm of
     * {@code density(x)}.
     *
     * @param x the point at which the PDF is evaluated
     * @return the logarithm of the value of the probability density function at point {@code x}
     */
    public double logDensity(double x) {
        // STUB: not implemented
        return 0.0;
    }
}
